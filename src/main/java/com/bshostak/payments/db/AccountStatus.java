package com.bshostak.payments.db;

import com.bshostak.payments.db.entity.Account;

/**
 * AccountStatus entity.
 *
 * @author B.Shostak
 */

public enum AccountStatus {
    ACTIVE, BLOCKED;

    public static AccountStatus getAccountStatus(Account account) {
        int accountStatusId = account.getAccountStatus();
        return AccountStatus.values()[accountStatusId];
    }

    public String getStatus() {
        return name().toLowerCase();
    }

}
