package com.bshostak.payments.db.dao;

import com.bshostak.payments.db.DBManager;
import com.bshostak.payments.db.EntityMapper;
import com.bshostak.payments.db.Fields;
import com.bshostak.payments.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for User entity.
 */
public class UserDao {

    private static final String SQL__FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL__FIND_USER_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL__FIND_USER_ID_BY_LOGIN = "SELECT id FROM user WHERE login=?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET password=?, first_name=?, second_name=?, email=?, tel=?"+ "	WHERE id=?";
    private static final String SQL__ADD_USER =
            "INSERT into `user`(login, `password`, first_name, second_name, email, tel, user_status_id, role_id) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }



    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            System.out.println("1 connection is enabled" + con.toString()); // temporary
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getSecondName());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getTel());
        pstmt.setLong(k++, user.getUserStatusId());
        pstmt.setLong(k++, user.getRoleId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Add user.
     *
     * @param user
     *            user to update.
     */
    public void addUser(User user) {
        Connection con = null;
        try {
            System.out.println("add user1 started"); // test
            con = DBManager.getInstance().getConnection();
            addUser(con, user);
            System.out.println("add user1 finished"); // test
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    //test method do it to the end
    public void addUser(Connection con, User user) throws SQLException {
        System.out.println("add user2 started"); // test
        PreparedStatement pstmt = con.prepareStatement(SQL__ADD_USER);
        int k = 1;
        pstmt.setString(k++, user.getLogin());
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getSecondName());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getTel());
        pstmt.setLong(k++, user.getUserStatusId());
        pstmt.setLong(k, user.getRoleId());
        System.out.println("all lines added to pstm"); // test
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("add user2 finished"); // test
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setSecondName(rs.getString(Fields.USER__SECOND_NAME));
                user.setEmail(rs.getString(Fields.USER__EMAIL));
                user.setTel(rs.getString(Fields.USER__TEL));
                user.setUserStatusId(rs.getInt(Fields.USER__USER_STATUS_ID));
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
