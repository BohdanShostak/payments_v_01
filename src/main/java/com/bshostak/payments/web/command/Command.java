package com.bshostak.payments.web.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author B.Shostak
 *
 */

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 8723658914021450150L;

    /**
     * Execution method for command.
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException;

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
