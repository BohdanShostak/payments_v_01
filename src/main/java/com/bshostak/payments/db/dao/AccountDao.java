package com.bshostak.payments.db.dao;

import com.bshostak.payments.db.DBManager;
import com.bshostak.payments.db.EntityMapper;
import com.bshostak.payments.db.Fields;
import com.bshostak.payments.db.entity.Account;
import com.bshostak.payments.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Account entity.
 */
public class AccountDao {
    private static final String SQL__FIND_ACCOUNT_BY_ID = "SELECT * FROM account WHERE id=?";
    private static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET sum=?, credit_limit=?"+ "	WHERE id=?";
    private static final String SQL__FIND_ACCOUNT_BY_STATUS_AND_USER = "SELECT * FROM account WHERE account_status_id=? AND user_id=?";
    private static final String SQL__FIND_ACCOUNT_BY_USER = "SELECT * FROM account WHERE user_id=?";
    private static final String SQL__FIND_ALL_ACCOUNT_NUMBERS = "SELECT id from account";
    private static final String SQL__ADD_ACCOUNT =
            "INSERT into `account`(`sum`, `credit_limit`, `account_status_id`, `user_id`) " +
                    "values(?, ?, ?, ?)";

    /**
     * Returns accounts of the given user and status
     *
     * @param user
     *            User entity.
     * @param accountStatusId
     *            Status identifier.
     * @return List of account entities.
     */
    public List<Account> findAccounts(User user, int accountStatusId) {
        List<Account> accountList = new ArrayList<Account>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL__FIND_ACCOUNT_BY_STATUS_AND_USER);
            pstmt.setInt(1, accountStatusId);
            pstmt.setLong(2, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next())
                accountList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return accountList;
    }

    /**
     * Returns accounts of the given user
     *
     * @param user
     *            User entity.
     * @return List of account entities.
     */
    public List<Account> findAccounts(User user) {
        List<Account> accountList = new ArrayList<Account>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL__FIND_ACCOUNT_BY_USER);
            pstmt.setString(1, String.valueOf(user.getId()));
            rs = pstmt.executeQuery();
            while (rs.next())
                accountList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return accountList;
    }

    /**
     * Returns account with the given identifier.
     *
     * @param id
     *            Account identifier.
     * @return Account entity.
     */
    public Account findAccount(Long id) {
        Account account = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            AccountMapper mapper = new AccountMapper();
            pstmt = con.prepareStatement(SQL__FIND_ACCOUNT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                account = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return account;
    }

    /**
     * Update Account.
     *
     * @param account
     *            account to update.
     */
    public void updateAccount(Account account) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateAccount(con, account);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update account.
     *
     * @param account
     *            account to update.
     * @throws SQLException
     */

    public void updateAccount(Connection con, Account account) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ACCOUNT);
        int k = 1;
        pstmt.setDouble(k++, account.getSum());
        pstmt.setDouble(k++, account.getCreditLimit());
        pstmt.setLong(k, account.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Add account.
     *
     * @param account
     *            account to update.
     */
    public void addAccount(Account account) {
        Connection con = null;
        try {
            System.out.println("add account1 started"); // test
            con = DBManager.getInstance().getConnection();
            addAccount(con, account);
            System.out.println("add account1 finished"); // test
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update account.
     *
     * @param account
     *            account to update.
     * @throws SQLException
     */
    //test method do it to the end
    public void addAccount(Connection con, Account account) throws SQLException {
        System.out.println("add account2 started"); // test
        PreparedStatement pstmt = con.prepareStatement(SQL__ADD_ACCOUNT);
        int k = 1;
        pstmt.setDouble(k++, account.getSum());
        pstmt.setDouble(k++, account.getCreditLimit());
        pstmt.setInt(k++, account.getAccountStatus());
        pstmt.setInt(k, account.getUserId());
        System.out.println("all lines added to pstm"); // test
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("add account2 finished"); // test
    }

    public Long getFreeAccountNumber() {
        long freeAccountNumber = 1L;
        List<Long> allAccountNumbers = findAllAccountNumbers();
        if (allAccountNumbers.isEmpty()) {
            freeAccountNumber += 1;
        } else {
            for (int i = 0; i < allAccountNumbers.size(); i++) {
                long currentCardNumber = allAccountNumbers.get(i);
                if (currentCardNumber > freeAccountNumber) {
                    freeAccountNumber = currentCardNumber;
                }
            }
            freeAccountNumber += 1;
        }
        System.out.println("free account number: " + freeAccountNumber); //test
        return freeAccountNumber;
    }

    public List<Long> findAllAccountNumbers() {
        List<Long> accountNumbersList = new ArrayList<Long>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_ACCOUNT_NUMBERS);
            while (rs.next())
                accountNumbersList.add(rs.getLong(1));
            //cardNumbersList.add(rs.getLong(Fields.CARD__CARD_NUMBER)); test it later
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }

        //test//
        System.out.println("All account numbers");
        for (int i = 0; i < accountNumbersList.size(); i++) {
            System.out.println(accountNumbersList.get(i));
        }
        //test//

        return accountNumbersList;
    }

    /**
     * Extracts account from the result set row.
     */
    private static class AccountMapper implements EntityMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs) {
            try {
                Account account = new Account();
                account.setId(rs.getLong(Fields.ENTITY__ID));
                account.setSum(rs.getDouble(Fields.ACCOUNT__SUM));
                account.setCreditLimit(rs.getDouble(Fields.ACCOUNT__CREDIT_LIMIT));
                account.setAccountStatus(rs.getInt(Fields.ACCOUNT__ACCOUNT_STATUS_ID));
                account.setUserId(rs.getInt(Fields.ACCOUNT__USER_ID));
                return account;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

    }

}
