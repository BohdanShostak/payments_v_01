package com.bshostak.payments.db;

/**
 * Holder for fields names of DB tables and beans.
 *
 * @author B.Shostak
 *
 */

public final class Fields {

    //entities
    public static final String ENTITY__ID = "id";

    public static final String USER__LOGIN = "login";
    public static final String USER__PASSWORD = "password";
    public static final String USER__FIRST_NAME = "first_name";
    public static final String USER__SECOND_NAME = "second_name";
    public static final String USER__EMAIL = "email";
    public static final String USER__TEL = "tel";
    public static final String USER__USER_STATUS_ID = "user_status_id";
    public static final String USER__ROLE_ID = "role_id";

    public static final String ACCOUNT__SUM = "sum";
    public static final String ACCOUNT__CREDIT_LIMIT = "credit_limit";
    public static final String ACCOUNT__ACCOUNT_STATUS_ID = "account_status_id";
    public static final String ACCOUNT__USER_ID = "user_id";

    public static final String CARD__CARD_NUMBER = "card_number";
    public static final String CARD__DUE_DATE = "due_date";
    public static final String CARD__CVV = "cvv";
    public static final String CARD__CARD_NAME = "card_name";
    public static final String CARD__ACCOUNT_ID = "account_id";

    public static final String PAYMENT__RECIPIENT_ACCOUNT = "recipient_account";
    public static final String PAYMENT__RECIPIENT_CARD_NUMBER = "recipient_card_number";
    public static final String PAYMENT__SUM = "sum";
    public static final String PAYMENT__DATE = "date";
    public static final String PAYMENT__PAYMENT_DESCRIPTION = "payment_description";
    public static final String PAYMENT__PAYMENT_STATUS_ID = "payment_status_id";
    public static final String PAYMENT__ACCOUNT_ID = "account_id";

    // beans
    // dopysaty kod

}
