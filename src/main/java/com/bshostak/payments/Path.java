package com.bshostak.payments;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author B.Shostak
 *
 */
public final class Path {
    // pages
    public static final String PAGE__LOGIN1 = "/login1.jsp";
    public static final String PAGE__INDEX = "/index.jsp";
    public static final String PAGE__LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE__SIGNUP = "/WEB-INF/jsp/signup.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_MENU = "/WEB-INF/jsp/user/list_menu.jsp";
    public static final String PAGE__USER_MAIN_PAGE = "/WEB-INF/jsp/user/user_main.jsp";
    public static final String PAGE__ADMIN_MAIN_PAGE = "/WEB-INF/jsp/admin/admin_main.jsp";
    public static final String PAGE__TOP_UP_BALANCE = "/WEB-INF/jsp/user/top_up_balance.jsp";
    public static final String PAGE__NEW_PAYMENT = "/WEB-INF/jsp/user/new_payment.jsp";
    public static final String PAGE__PAYMENTS = "/WEB-INF/jsp/user/payments.jsp";
    public static final String PAGE__ACCOUNTS = "/WEB-INF/jsp/user/accounts.jsp";
    public static final String PAGE__PROFILE = "/WEB-INF/jsp/user/profile.jsp";


    // commands
    public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders"; //temporary used when login
    public static final String COMMAND__LIST_MENU = "/controller?command=listMenu"; //temporary used when login
    public static final String COMMAND__USER_MAIN_CONTENT = "/controller?command=userMain";
    public static final String COMMAND__ADMIN_MAIN_CONTENT = "/controller?command=adminMain";
    public static final String COMMAND__TOP_UP_BALANCE = "/controller?command=topUpBalance";
    public static final String COMMAND__NEW_PAYMENT = "/controller?command=newPayment";
}
