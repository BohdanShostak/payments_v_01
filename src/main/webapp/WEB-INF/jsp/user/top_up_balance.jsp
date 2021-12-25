<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Index"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body id = "user_top_up_balance">
<%@include file="/WEB-INF/jspf/header.jspf"%>
<%@include file="/WEB-INF/jspf/user_menu.jspf"%>
<div class = "main_normal_for_forms_and_menu">

    <div class="input_form">
        <form action="controller" method="post">
            <p class="standard_logo"><fmt:message key="user_main.top_up_balance.balance_top_up"/></p>
            <input type="hidden" name="command" value="topUpBalance"/>
            <p class="margin_bottom_small"><fmt:message key="user_main.top_up_balance.select_a_card"/></p>
            <select class="standard_button" name="selectedCard" required>
                <c:forEach items="${allAccounts}" varStatus="loop">
                    <option value = ${allCards.get(loop.index).cardNumber}>
                        <c:out value="${allCards.get(loop.index).cardName}"/>
                    </option>
                </c:forEach>
            </select>
            <input class="input_string" name="sum" type="number"
                   required placeholder="<fmt:message key="user_main.top_up_balance.sum"/>"/>
            <br>
            <button class="standard_button" type="submit">
                <fmt:message key="user_main.top_up_balance.top_up"/>
            </button>
        </form>
    </div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>

