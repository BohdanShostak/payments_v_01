package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToTopUpBalanceCommand extends Command
{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        findMyCards(request, response);
        return Path.PAGE__TOP_UP_BALANCE;
    }
}
