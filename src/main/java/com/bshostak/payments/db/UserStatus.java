package com.bshostak.payments.db;

import com.bshostak.payments.db.entity.User;

/**
 * UserStatus entity.
 *
 * @author B.Shostak
 */

public enum UserStatus {
    ACTIVE, BLOCKED;

    public static UserStatus getUserStatus(User user) {
        int userStatusId = user.getUserStatusId();
        return UserStatus.values()[userStatusId];
    }

    public String getStatus() {
        return name().toLowerCase();
    }

}
