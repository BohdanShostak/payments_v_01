package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import com.bshostak.payments.db.dao.AccountDao;
import com.bshostak.payments.db.dao.CardDao;
import com.bshostak.payments.db.entity.Account;
import com.bshostak.payments.db.entity.Card;
import com.bshostak.payments.db.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoToTopUpBalanceCommand extends Command
{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User currentUser = (User) request.getSession().getAttribute("user");
        AccountDao accountDao = new AccountDao();
        CardDao cardDao = new CardDao();
        List<Account> allAccounts = accountDao.findAccounts(currentUser);
        List<Card> allCards = new ArrayList<>();

        for (int i = 0; i < allAccounts.size(); i++) {
            Account currentAccount = allAccounts.get(i);
            System.out.println("current account: " + currentAccount.toString()); //test
            long accountId = currentAccount.getId();
            System.out.println("accountId: " + accountId);//test
            Card card = cardDao.findCardByAccount(accountId);
            if (card != null) {
                System.out.println("current card: " + card.toString()); //test
            } else {
                System.out.println("card == null"); // test
            }
            allCards.add(i, card);
        }
        request.setAttribute("allAccounts", allAccounts);
        request.setAttribute("allCards", allCards);
        return Path.PAGE__TOP_UP_BALANCE;
    }
}
