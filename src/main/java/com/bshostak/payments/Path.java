package com.bshostak.payments;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author B.Shostak
 *
 */
public final class Path {
    // pages
    public static final String PAGE__LOGIN = "/login1.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_MENU = "/WEB-INF/jsp/user/list_menu.jsp";


    // commands
    public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";
}
