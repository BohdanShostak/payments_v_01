package com.bshostak.payments.db.dao;

import com.bshostak.payments.db.DBManager;
import com.bshostak.payments.db.EntityMapper;
import com.bshostak.payments.db.Fields;
import com.bshostak.payments.db.entity.Account;
import com.bshostak.payments.db.entity.Payment;

import java.sql.*;

/**
 * Data access object for Payment entity.
 */
public class PaymentDao {
    private static final String SQL__FIND_PAYMENT_BY_ID = "SELECT * FROM payment WHERE id=?";
    private static final String SQL_UPDATE_PAYMENT = "UPDATE payment SET recipient_account=?, " +
            "recipient_card_number=?, sum=?, date=?, payment_description=?" + "	WHERE id=?";
    private static final String SQL__ADD_PAYMENT =
            "INSERT into `payment`(`recipient_account`, `recipient_card_number`, `sum`, `date`," +
                    "`payment_description`, `payment_status_id`, `account_id`) " +
                    "values(?, ?, ?, ?, ?, ?, ?)";

    /**
     * Returns a payment with the given identifier.
     *
     * @param id Payment identifier.
     * @return Payment entity.
     */
    public Payment findPayment(Long id) {
        Payment payment = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PaymentMapper mapper = new PaymentMapper();
            pstmt = con.prepareStatement(SQL__FIND_PAYMENT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                payment = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return payment;
    }

    /**
     * Update payment.
     *
     * @param payment payment to update.
     */
    public void updatePayment(Payment payment) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updatePayment(con, payment);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update payment.
     *
     * @param payment payment to update.
     * @throws SQLException
     */
    public void updatePayment(Connection con, Payment payment) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_PAYMENT);
        int k = 1;
        pstmt.setInt(k++, payment.getRecipientAccount());
        pstmt.setLong(k++, payment.getRecipientCardNumber());
        pstmt.setDouble(k++, payment.getSum());
        pstmt.setDate(k++, (Date) payment.getDate());
        pstmt.setString(k++, payment.getPaymentDescription());
        pstmt.setLong(k, payment.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Add payment.
     *
     * @param payment
     *            payment to update.
     */
    public void addPayment(Payment payment) {
        Connection con = null;
        try {
            System.out.println("add payment1 started"); // test
            con = DBManager.getInstance().getConnection();
            addPayment(con, payment);
            System.out.println("add payment1 finished"); // test
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update payment.
     *
     * @param payment
     *            payment to update.
     * @throws SQLException
     */
    public void addPayment(Connection con, Payment payment) throws SQLException {
        System.out.println("add payment2 started"); // test
        PreparedStatement pstmt = con.prepareStatement(SQL__ADD_PAYMENT);
        int k = 1;
        pstmt.setInt(k++, payment.getRecipientAccount());
        pstmt.setLong(k++, payment.getRecipientCardNumber());
        pstmt.setDouble(k++, payment.getSum());
        pstmt.setDate(k++, payment.getDate());
        pstmt.setString(k++, payment.getPaymentDescription());
        pstmt.setInt(k++, payment.getPaymentStatusId());
        pstmt.setInt(k, payment.getAccountId());
        System.out.println("all lines added to pstm"); // test
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("add payment2 finished"); // test
    }

    /**
     * Extracts a payment from the result set row.
     */
    private static class PaymentMapper implements EntityMapper<Payment> {

        @Override
        public Payment mapRow(ResultSet rs) {
            try {
                Payment payment = new Payment();
                payment.setId(rs.getLong(Fields.PAYMENT__ACCOUNT_ID));
                payment.setRecipientAccount(rs.getInt(Fields.PAYMENT__RECIPIENT_ACCOUNT));
                payment.setRecipientCardNumber(rs.getLong(Fields.PAYMENT__RECIPIENT_CARD_NUMBER));
                payment.setSum(rs.getDouble(Fields.PAYMENT__SUM));
                payment.setDate(rs.getDate(Fields.PAYMENT__DATE));
                payment.setPaymentDescription(rs.getString(Fields.PAYMENT__PAYMENT_DESCRIPTION));
                payment.setPaymentStatusId(rs.getInt(Fields.PAYMENT__PAYMENT_STATUS_ID));
                payment.setAccountId(rs.getInt(Fields.PAYMENT__ACCOUNT_ID));

                return payment;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

    }

}
