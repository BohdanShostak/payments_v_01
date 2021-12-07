package com.bshostak.payments.db;

import com.bshostak.payments.db.entity.Payment;
import com.bshostak.payments.db.entity.User;

/**
 * PaymentStatus entity.
 *
 * @author B.Shostak
 *
 */

public enum PaymentStatus {
    PREPARED, SENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public static PaymentStatus getPaymentStatus(Payment payment) {
        int paymentStatusId = payment.getPaymentStatusId();
        return PaymentStatus.values()[paymentStatusId];
    }

    public String getStatusDescription() {
        return name().toLowerCase();
    }
}
