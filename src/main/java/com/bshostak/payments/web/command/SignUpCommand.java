package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
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

        // I finished on this place
        /*User user = new UserDao().addUser();
        //log.trace("Found in DB: user --> " + user); // do it later!!!
        System.out.println("Found in DB: user --> " + user);// temporary!!!*/

        return null;
    }
}
