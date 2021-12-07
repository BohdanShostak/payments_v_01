package com.bshostak.payments.web;

import com.bshostak.payments.web.command.Command;
import com.bshostak.payments.web.command.CommandContainer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Main servlet controller.
 *
 * @author B. Shostak
 *
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2531700214538914271L;
    //private static final Logger log = Logger.getLogger(Controller.class); // do it later

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    /**
     * Main method of this controller.
     */
    private void process( HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        //log.debug("Controller starts"); do it later
        System.out.println("Controller starts");// temporary

        // extract command name from the request
        String commandName = request.getParameter("command");
        //log.trace("Request parameter: command --> " + commandName); // do it later
        System.out.println("Request parameter: command --> " + commandName);// temporary

        // obtain command object by its name
        Command command = CommandContainer.get(commandName);
        //log.trace("Obtained command --> " + command); // do it later
        System.out.println("Obtained command --> " + command); // temporary

        // execute command and get forward address
        String forward = command.execute(request, response);
        //log.trace("Forward address --> " + forward); do it later!!!
        System.out.println("Forward address --> " + forward); // temporary

        //log.debug("Controller finished, now go to forward address --> " + forward); do it later!!!
        System.out.println("Controller finished, now go to forward address --> " + forward);// temporary


        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }



}
