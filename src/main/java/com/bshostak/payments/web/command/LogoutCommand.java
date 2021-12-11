package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Logout command.
 *
 * @author B.Shostak
 *
 */

public class LogoutCommand extends Command {

    private static final long serialVersionUID = -2466225598811225023L;

    //private static final Logger log = Logger.getLogger(LogoutCommand.class); do it later

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //log.debug("Command starts"); do it later
        System.out.println("Command Logout starts"); // temporary

        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        //log.debug("Command finished"); do it later
        System.out.println("Command logout finished");  // temporary
        return Path.PAGE__INDEX;
    }
}
