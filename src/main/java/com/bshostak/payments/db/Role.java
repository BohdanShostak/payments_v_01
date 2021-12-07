package com.bshostak.payments.db;

import com.bshostak.payments.db.entity.User;

/**
 * Role entity.
 *
 * @author B.Shostak
 *
 */

public enum Role {
    ADMIN, USER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
