package com.bshostak.payments.db.entity;

/**
 * Account entity.
 *
 * @author B.Shostak
 *
 */

public class Account extends Entity {

    private static final long serialVersionUID = 5224470336987702141L;

    private double sum;
    private int userId;
    private double creditLimit;
    private int accountStatus;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account[sum=" + sum + ", userId=" + userId + ", creditLimit=" + creditLimit +
                ", accountStatus=" + accountStatus + ']';
    }

}
