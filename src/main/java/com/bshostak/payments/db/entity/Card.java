package com.bshostak.payments.db.entity;

import java.io.Serializable;

/**
 * Card entity.
 *
 * @author B.Shostak
 */

public class Card implements Serializable {

    private static final long serialVersionUID = 8521450003541493311L;

    private long cardNumber;
    private String dueDate;
    private int cvv;
    private String cardName;
    private int accountId;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Card[cardNumber=" + cardNumber + ", dueDate='" + dueDate
                + ", cvv=" + cvv + ", cardName='" + cardName
                + ", accountId=" + accountId + ']';
    }

}
