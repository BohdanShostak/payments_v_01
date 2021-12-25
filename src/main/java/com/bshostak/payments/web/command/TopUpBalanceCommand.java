package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import com.bshostak.payments.db.dao.AccountDao;
import com.bshostak.payments.db.dao.CardDao;
import com.bshostak.payments.db.entity.Account;
import com.bshostak.payments.db.entity.Card;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TopUpBalanceCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //log.debug("Command top up balance starts"); //do it later!!!
        System.out.println("Command top up balance starts");// temporary

        // obtain card and sum from the request
        String cardId = request.getParameter("selectedCard");
        //log.trace("Request parameter: selectedCard --> " + cardId); // do it later!!!
        System.out.println("Request parameter: selectedCard --> " + cardId); // temporary

        String sum = request.getParameter("sum");
        System.out.println("Request parameter: sum --> " + sum); // temporary

        // error handler
        String errorMessage;
        String forward = Path.PAGE__ERROR_PAGE;

        if (cardId == null || sum == null || cardId.isEmpty() || sum.isEmpty()) {
            errorMessage = "Card/ sum cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        int maxTopUpSum = 1000000;
        if (Double.parseDouble(sum) <= 0 | Double.parseDouble(sum) >= maxTopUpSum) {
            errorMessage = "The sum must be bigger than 0 and smaller than " + maxTopUpSum;
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        AccountDao accountDao = new AccountDao();
        CardDao cardDao = new CardDao();
        Card card = cardDao.findCard(Long.valueOf(cardId));
        int accountId = card.getAccountId();
        Account account = accountDao.findAccount((long) accountId);
        double currentSum = account.getSum();
        double newSum = currentSum + Double.parseDouble(sum);
        account.setSum(newSum);
        accountDao.updateAccount(account);

        //log.debug("Command finished"); do it later!!!
        System.out.println("Command finished"); //temporary
        forward = Path.COMMAND__USER_MAIN_CONTENT;
        return forward;
    }

}
