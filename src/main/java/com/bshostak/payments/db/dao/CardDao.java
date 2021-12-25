package com.bshostak.payments.db.dao;

import com.bshostak.payments.db.DBManager;
import com.bshostak.payments.db.EntityMapper;
import com.bshostak.payments.db.Fields;
import com.bshostak.payments.db.entity.Card;
import com.bshostak.payments.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Data access object for Card entity.
 */
public class CardDao {
    private static final String SQL__FIND_CARD_BY_CARD_NUMBER = "SELECT * FROM card WHERE card_number=?";
    private static final String SQL__FIND_CARD_BY_ACCOUNT_ID = "SELECT * FROM card WHERE account_id=?";
    private static final String SQL_UPDATE_CARD = "UPDATE card SET due_date=?, cvv=?, card_name=?"+ "	WHERE card_number=?";
    private static final String SQL__FIND_ALL_CARD_NUMBERS = "SELECT card_number from card";
    private static final String SQL__ADD_CARD =
            "INSERT into `card`(`card_number`, `due_date`, `cvv`, `card_name`, `account_id`) " +
                    "values(?, ?, ?, ?, ?)";

    /**
     * Returns card with the given card_number.
     *
     * @param cardNumber
     *            Card identifier.
     * @return Card entity.
     */
    public Card findCard(Long cardNumber) {
        Card card = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardMapper mapper = new CardMapper();
            pstmt = con.prepareStatement(SQL__FIND_CARD_BY_CARD_NUMBER);
            pstmt.setLong(1, cardNumber);
            rs = pstmt.executeQuery();
            if (rs.next())
                card = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return card;
    }

    /**
     * Returns card with the given account_number.
     *
     * @param accountNumber
     *            Account identifier.
     * @return Card entity.
     */
    public Card findCardByAccount(Long accountNumber) {
        Card card = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CardMapper mapper = new CardMapper();
            pstmt = con.prepareStatement(SQL__FIND_CARD_BY_ACCOUNT_ID);
            pstmt.setLong(1, accountNumber);
            rs = pstmt.executeQuery();
            if (rs.next())
                card = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return card;
    }

    /**
     * Update Card.
     *
     * @param card
     *            card to update.
     */
    public void updateCard(Card card) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateCard(con, card);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update card.
     *
     * @param card
     *            card to update.
     * @throws SQLException
     */
    public void updateCard(Connection con, Card card) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_CARD);
        int k = 1;
        pstmt.setString(k++, card.getDueDate());
        pstmt.setInt(k++, card.getCvv());
        pstmt.setString(k++, card.getCardName());
        pstmt.setLong(k, card.getCardNumber());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Add card.
     *
     * @param card
     *            card to update.
     */
    public void addCard(Card card) {
        Connection con = null;
        try {
            System.out.println("add card1 started"); // test
            con = DBManager.getInstance().getConnection();
            addCard(con, card);
            System.out.println("add card1 finished"); // test
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update card.
     *
     * @param card
     *            card to update.
     * @throws SQLException
     */
    //test method do it to the end
    public void addCard(Connection con, Card card) throws SQLException {
        System.out.println("add card2 started"); // test
        PreparedStatement pstmt = con.prepareStatement(SQL__ADD_CARD);
        int k = 1;
        pstmt.setLong(k++, card.getCardNumber());
        pstmt.setString(k++, card.getDueDate());
        pstmt.setInt(k++, card.getCvv());
        pstmt.setString(k++, card.getCardName());
        pstmt.setInt(k, card.getAccountId());
        System.out.println("all lines added to pstm"); // test
        pstmt.executeUpdate();
        pstmt.close();
        System.out.println("add card2 finished"); // test
    }


    public Long getFreeCardNumber() {
        long freeCardNumber = 1000000000000001L;
        List<Long> allCardNumbers = findAllCardNumbers();
        if (allCardNumbers.isEmpty()) {
            freeCardNumber += 1;
        } else {
            for (int i = 0; i < allCardNumbers.size(); i++) {
                long currentCardNumber = allCardNumbers.get(i);
                if (currentCardNumber > freeCardNumber) {
                    freeCardNumber = currentCardNumber;
                }
            }
            freeCardNumber += 1;
        }
        System.out.println("free card number: " + freeCardNumber); //test
        return freeCardNumber;
    }

    public List<Long> findAllCardNumbers() {
        List<Long> cardNumbersList = new ArrayList<Long>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_CARD_NUMBERS);
            while (rs.next())
                cardNumbersList.add(rs.getLong(1));
                //cardNumbersList.add(rs.getLong(Fields.CARD__CARD_NUMBER)); test it later
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }

        //test//
        System.out.println("All card numbers");
        for (int i = 0; i < cardNumbersList.size(); i++) {
            System.out.println(cardNumbersList.get(i));
        }
        //test//

        return cardNumbersList;
    }

    public int getRandomCVV() {
        int randomCVV = 0;
        while (randomCVV <= 100 | randomCVV > 999 ) {
            randomCVV = (int) (Math.random() * 1000);
        }
        System.out.println("Random CVV: " + randomCVV); // test;
        return randomCVV;
    }

    public String getDueDate() {
        String dueDate ="";
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR) - 2000 + 5;
        int month = calendar.get(Calendar.MONTH) + 1;
        dueDate = dueDate + month + "/" + year;
        if (dueDate.length()< 5) {
            dueDate = "0" + dueDate;
        }
        System.out.println("dueDate: " + dueDate);
        return dueDate;
    }

    /*
    // testing
    public static void main(String[] args) {
        CardDao cardDao = new CardDao();
        //cardDao.getFreeCardNumber();
        cardDao.getRandomCVV();
        cardDao.getDueDate();
    }
*/
    /**
     * Extracts card from the result set row.
     */
    private static class CardMapper implements EntityMapper<Card> {

        @Override
        public Card mapRow(ResultSet rs) {
            try {
                Card card = new Card();
                card.setCardNumber(rs.getLong(Fields.CARD__CARD_NUMBER));
                card.setDueDate(rs.getString(Fields.CARD__DUE_DATE));
                card.setCvv(rs.getInt(Fields.CARD__CVV));
                card.setCardName(rs.getString(Fields.CARD__CARD_NAME));
                card.setAccountId(rs.getInt(Fields.CARD__ACCOUNT_ID));
                return card;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

    }
}
