package com.bshostak.payments.db.entity;

//import java.util.Date;
import java.sql.Date;

/**
 * Payment entity.
 *
 * @author B.Shostak
 */

public class Payment extends Entity {

    private static final long serialVersionUID = 3047205890475619521L;

    private int recipientAccount;
    private long recipientCardNumber;
    private double sum;
    private Date date;
    private String paymentDescription;
    private int accountId;
    private int paymentStatusId;

    public int getRecipientAccount() {
        return recipientAccount;
    }

    public void setRecipientAccount(int recipientAccount) {
        this.recipientAccount = recipientAccount;
    }

    public long getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(long recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    @Override
    public String toString() {
        return "Payment[recipientAccount=" + recipientAccount + ", recipientCardNumber=" + recipientCardNumber
                + ", sum=" + sum + ", date=" + date + ", paymentDescription='" + paymentDescription
                + ", accountId=" + accountId + ", paymentStatusId=" + paymentStatusId + ']';
    }

}
