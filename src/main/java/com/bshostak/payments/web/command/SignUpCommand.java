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

public class SignUpCommand extends Command {
    private static final long serialVersionUID = -3066985502123664952L;
    //private static final Logger log = Logger.getLogger(SignUpCommand.class); // do it later!!!

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //log.debug("Command starts"); //do it later!!!
        System.out.println("Command signup starts");// temporary

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        //log.trace("Request parameter: loging --> " + login); // do it later!!!
        System.out.println("Request parameter: loging --> " + login); // temporary

        String password = request.getParameter("password");
        System.out.println("Request parameter: passwording --> " + password); // temporary

        String password2 = request.getParameter("password2");
        System.out.println("Request parameter: passwording2 --> " + password); // temporary

        String firstName = request.getParameter("firstName");
        System.out.println("Request parameter: firstName --> " + firstName); // temporary

        String secondName = request.getParameter("secondName");
        System.out.println("Request parameter: secondName --> " + secondName); // temporary

        String email = request.getParameter("email");
        System.out.println("Request parameter: email --> " + email); // temporary

        String tel = request.getParameter("tel");
        System.out.println("Request parameter: tel --> " + tel); // temporary

        int userStatusId = 0;
        int userRoleId = 1;

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

        if (firstName == null || secondName == null || firstName.isEmpty() || secondName.isEmpty()) {
            errorMessage = "First name and Second name cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        if (!password.equals(password2)) {
            errorMessage = "Passwords in both fields must be equals";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        //my code
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setEmail(email);
        user.setTel(tel);
        user.setUserStatusId(userStatusId);
        user.setRoleId(userRoleId);
        UserDao userDao = new UserDao();
        userDao.addUser(user);
        //my code

        Role userRole = Role.getRole(user);
        //log.trace("userRole --> " + userRole); do it later!!!
        System.out.println("userRole --> " + userRole);// temporary!!!

        if (userRole == Role.ADMIN) {
            //forward = Path.COMMAND__LIST_ORDERS; // original
            forward = Path.COMMAND__ADMIN_MAIN_CONTENT; // new
        }
        if (userRole == Role.USER) {
            //forward = Path.COMMAND__LIST_MENU;  // original
            forward = Path.COMMAND__USER_MAIN_CONTENT;// new
        }

        session.setAttribute("user", user);
        //log.trace("Set the session attribute: user --> " + user); do it later!!!
        System.out.println("Set the session attribute: user --> " + user); // temporary!!!

        session.setAttribute("userRole", userRole);
        //log.trace("Set the session attribute: userRole --> " + userRole); do it later!!!
        System.out.println("Set the session attribute: userRole --> " + userRole);// temporary!!!

        //log.info("User " + user + " logged as " + userRole.toString().toLowerCase()); do it later!!!
        System.out.println("User " + user + " logged as " + userRole.toString().toLowerCase());// temporary!!!

        //log.debug("Command finished"); do it later!!!
        System.out.println("Command finished"); //temporary
        return forward;
    }
}
