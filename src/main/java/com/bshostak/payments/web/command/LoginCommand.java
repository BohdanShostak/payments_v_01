package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import com.bshostak.payments.db.Role;
import com.bshostak.payments.db.dao.UserDao;
import com.bshostak.payments.db.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Login command.
 *
 * @author B.Shostak
 *
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = -3066985502123664952L;

    //private static final Logger log = Logger.getLogger(LoginCommand.class); // do it later!!!


    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        //log.debug("Command starts"); //do it later!!!
        System.out.println("Command starts");// temporary

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        //log.trace("Request parameter: loging --> " + login); // do it later!!!
        System.out.println("Request parameter: loging --> " + login); // temporary

        String password = request.getParameter("password");
        System.out.println("Request parameter: passwording --> " + password); // temporary

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        User user = new UserDao().findUserByLogin(login);
        //log.trace("Found in DB: user --> " + user); // do it later!!!
        System.out.println("Found in DB: user --> " + user);// temporary!!!

        if(user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage); do it later!!!
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        } else {
            Role userRole = Role.getRole(user);
            //log.trace("userRole --> " + userRole); do it later!!!
            System.out.println("userRole --> " + userRole);// temporary!!!

            if (userRole == Role.ADMIN) {
                forward = Path.COMMAND__LIST_ORDERS;
            }
            if (userRole == Role.USER) {
                forward = Path.COMMAND__LIST_MENU;
            }

            session.setAttribute("user", user);
            //log.trace("Set the session attribute: user --> " + user); do it later!!!
            System.out.println("Set the session attribute: user --> " + user); // temporary!!!

            session.setAttribute("userRole", userRole);
            //log.trace("Set the session attribute: userRole --> " + userRole); do it later!!!
            System.out.println("Set the session attribute: userRole --> " + userRole);// temporary!!!

            //log.info("User " + user + " logged as " + userRole.toString().toLowerCase()); do it later!!!
            System.out.println("User " + user + " logged as " + userRole.toString().toLowerCase());// temporary!!!

            // work with i18n
            /*String userLocaleName = user.getLocaleName();
            log.trace("userLocalName --> " + userLocaleName);

            if (userLocaleName != null && !userLocaleName.isEmpty()) {
                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

                session.setAttribute("defaultLocale", userLocaleName);
                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);

                log.info("Locale for user: defaultLocale --> " + userLocaleName);
            }*/ // do it later!!!

        }

        //log.debug("Command finished"); do it later!!!
        System.out.println("Command finished"); //temporary
        return forward;
    }
}
