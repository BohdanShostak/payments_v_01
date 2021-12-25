package com.bshostak.payments.web.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

public class SwitchLanguageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("Command switch_lanquage started");// temporary

        HttpSession session = request.getSession();

        String language = request.getParameter("language");
        System.out.println("Request parameter: language --> " + language); // temporary

        Locale en = Locale.ENGLISH;
        Locale uk = new Locale("uk", "UA");
        System.out.println("current locale: " + Locale.getDefault().toString());// test

        if ("en".equals(language)) {
            Locale.setDefault(en);
        } else if ("en".equals(language)) {
            Locale.setDefault(uk);
        }

        System.out.println("Command switch language finished"); //temporary
        return null;
    }
}
