package com.bshostak.payments.web.command;

import com.bshostak.payments.Path;
import com.bshostak.payments.db.dao.AccountDao;
import com.bshostak.payments.db.dao.CardDao;
import com.bshostak.payments.db.dao.PaymentDao;
import com.bshostak.payments.db.dao.UserDao;
import com.bshostak.payments.db.entity.Account;
import com.bshostak.payments.db.entity.Card;
import com.bshostak.payments.db.entity.Payment;
import com.bshostak.payments.db.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

public class NewPaymentCommand extends Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //log.debug("Command new payment starts"); //do it later!!!
        System.out.println("Command new payment starts");// temporary

        // obtain details of payment from the request
        String selectedCard = request.getParameter("selectedCard");
        //log.trace("Request parameter: selectedCard --> " + selectedCard); // do it later!!!
        System.out.println("Request parameter: selectedCard --> " + selectedCard); // temporary

        // obtain details of payment from the request
        String typeOfPayment = request.getParameter("typeOfPayment");
        //log.trace("Request parameter: typeOfPayment --> " + typeOfPayment); // do it later!!!
        System.out.println("Request parameter: typeOfPayment --> " + typeOfPayment); // temporary

        String cardAccountNumber = request.getParameter("cardAccountNumber");
        //log.trace("Request parameter: cardAccountNumber --> " + cardAccountNumber); // do it later!!!
        System.out.println("Request parameter: cardAccountNumber --> " + cardAccountNumber); // temporary

        String sum = request.getParameter("sum");
        System.out.println("Request parameter: sum --> " + sum); // temporary

        String description = request.getParameter("description");
        //log.trace("Request parameter: description --> " + description); // do it later!!!
        System.out.println("Request parameter: description --> " + description); // temporary

        String preparedPayment = request.getParameter("preparedPayment");
        //log.trace("Request parameter: preparedPayment --> " + preparedPayment); // do it later!!!
        System.out.println("Request parameter: preparedPayment --> " + preparedPayment); // temporary



        // error handler
        String errorMessage;
        String forward = Path.PAGE__ERROR_PAGE;


        if (selectedCard == null || selectedCard.isEmpty()) {
            errorMessage = "Payer's card cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        if (typeOfPayment == null || typeOfPayment.isEmpty()) {
            errorMessage = "Choose type of payment";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        if (!typeOfPayment.equals("byCardNumber") & !typeOfPayment.equals("byAccountNumber")) {
            errorMessage = "Something went wrong with type of payment";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        if (cardAccountNumber == null || sum == null || cardAccountNumber.isEmpty() || sum.isEmpty()) {
            errorMessage = "Card/account number and sum cannot be empty or null";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }



        if (Double.parseDouble(sum) <= 0 || Double.parseDouble(sum) >= 100000000) {
            errorMessage = "Sum of payment must be bigger then 0 and smaller then 100 000 000";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        if (description.length()>=45) {
            errorMessage = "The maximum number of description characters is 45";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        long myCardNumber = Long.parseLong(selectedCard);
        long recipientCardAccountNumber = Long.parseLong(cardAccountNumber);
        double sumOfPayment = Double.parseDouble(sum);


        PaymentDao paymentDao = new PaymentDao();
        Payment payment = new Payment();
        payment.setSum(sumOfPayment);
        payment.setPaymentDescription(description);

        if (preparedPayment!= null) {
            payment.setPaymentStatusId(0);
        } else {
            payment.setPaymentStatusId(1);
        }

        payment.setDate(new Date(2022,1,1)); // temporary

        CardDao cardDao = new CardDao();
        AccountDao accountDao = new AccountDao();
        Card payerCard = cardDao.findCard(myCardNumber);
        Card recipientCard;
        Account payerAccount = accountDao.findAccount(Long.valueOf(payerCard.getAccountId()));
        Account recipientAccount;
        payment.setAccountId(Math.toIntExact(payerAccount.getId()));

        if (typeOfPayment.equals("byCardNumber")) {
            recipientCard = cardDao.findCard(recipientCardAccountNumber);
            recipientAccount = accountDao.findAccount(Long.valueOf(recipientCard.getAccountId()));
        } else if (typeOfPayment.equals("byAccountNumber")) {
            recipientAccount = accountDao.findAccount(recipientCardAccountNumber);
            recipientCard = cardDao.findCardByAccount(recipientCardAccountNumber);
        } else {
            errorMessage = "You didn't choose type of payment";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }
        if (recipientAccount != null && recipientCard !=null) {
            payment.setRecipientAccount(recipientAccount.getUserId());
            payment.setRecipientCardNumber(recipientCard.getCardNumber());
        } else if ( typeOfPayment.equals("byCardNumber")) {
            payment.setRecipientCardNumber(recipientCardAccountNumber);
        } else if (typeOfPayment.equals("byAccountNumber")) {
            payment.setRecipientAccount(Math.toIntExact(recipientCardAccountNumber));
        }
        double payerSum = payerAccount.getSum();
        double payerCreditLimit = payerAccount.getCreditLimit();
        double recipientSum;
        double newPayerSum;
        double newRecipientSum;
        if ((payerSum + payerCreditLimit) > sumOfPayment) {
            newPayerSum = payerSum - sumOfPayment;
            payerAccount.setSum(newPayerSum);
            accountDao.updateAccount(payerAccount);
            paymentDao.addPayment(payment);
        } else {
            errorMessage = "You don't have enough money";
            request.setAttribute("errorMessage", errorMessage);
            //log.error("errorMessage --> " + errorMessage);// do it later
            System.out.println("errorMessage --> " + errorMessage); // temporary!!!
            return forward;
        }

        Account realRecipientAccount = null;
        if (recipientAccount != null) {
            realRecipientAccount = accountDao.findAccount(recipientAccount.getId());
        }

        UserDao userDao = new UserDao();
        User recipient = new User();
        String recipientName = "unknown";
        if (realRecipientAccount != null) {
            recipientSum = recipientAccount.getSum();
            newRecipientSum = recipientSum + sumOfPayment;
            recipient = userDao.findUser(Long.valueOf(realRecipientAccount.getUserId()));
            realRecipientAccount.setSum(newRecipientSum);
            accountDao.updateAccount(realRecipientAccount);
            String resFirstName = recipient.getFirstName();
            String resSecondName = recipient.getSecondName();
            recipientName = resFirstName + " " + resSecondName;
        }
        String okMessage = "You send " + sum + " UAN. Recipient: " + recipientName;
        request.setAttribute("okMessage", okMessage);
        forward = Path.PAGE__OK_MESSAGE;

        return forward;
    }

}
