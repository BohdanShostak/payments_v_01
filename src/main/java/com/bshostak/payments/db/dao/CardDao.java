package com.bshostak.payments.db.dao;

import com.bshostak.payments.db.DBManager;
import com.bshostak.payments.db.EntityMapper;
import com.bshostak.payments.db.Fields;
import com.bshostak.payments.db.entity.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data access object for Card entity.
 */
public class CardDao {
    private static final String SQL__FIND_CARD_BY_CARD_NUMBER = "SELECT * FROM card WHERE card_number=?";
    private static final String SQL_UPDATE_CARD = "UPDATE card SET due_date=?, cvv=?, card_name=?"+ "	WHERE card_number=?";

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
