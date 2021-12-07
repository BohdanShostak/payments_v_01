package com.bshostak.payments.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DB manager. Works with Apache Derby DB.
 *
 * @author B.Shostak
 *
 */

public class DBManager {
    //private static final Logger log = Logger.getLogger(DBManager.class); // dorobyty Logger

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            System.out.println("DBManager getInstance() is done");//temporary
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections.
     *
     * @return A DB connection.
     */

    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            System.out.println("envContext " + envContext.toString());// temporary

            DataSource ds = (DataSource)envContext.lookup("jdbc/payments_v_01");
            System.out.println("ds " + ds.toString()); // temporary
            con = ds.getConnection();
            System.out.println("con " + con.toString()); // temporary
        } catch (NamingException ex) {
            //log.error("Cannot obtain a connection from the pool", ex); // dorobyty logger
            System.out.println("Cannot obtain a connection from the pool" + ex);  // temporary
        }
        System.out.println("end of the connection method: "); // temporary
        return con;
    }

    /*public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/payments_v_01");
            c = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }*/

    private DBManager() {
    }

    /**
     * Commits and close the given connection.
     *
     * @param con
     *            Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
