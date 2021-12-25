<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Index"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body id = "user_main">
<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class = "main_normal">
    <%@include file="/WEB-INF/jspf/user_menu.jspf"%>

    <div class="in_line whole_space">
        <div class="left_part">
            <div class="form_for_buttons">
                <form action="controller" method = "post">
                    <input type="hidden" name="command" value="goToNewPayment"/>
                    <button class = big_button type="submit">
                        <fmt:message key="user_main.button.make_new_payment"/>
                    </button>
                </form>

                <form action="controller" method = "post">
                    <input type="hidden" name="command" value="goToTopUpBalance"/>
                    <button class = big_button type="submit">
                        <fmt:message key="user_main.button.top_up_your_account"/>
                    </button>
                </form>
            </div>
        </div>

        <div class="right_part">
            <div class="card_container">

                    <c:forEach items="${allAccounts}" varStatus="loop">
                        <div class="card">
                            <p class=" card_text text_LEFT" >PAYMENTS.UA</p>
                            <p class="card_text text_left"><c:out value="${allCards.get(loop.index).cardName.toUpperCase()}"/></p>
                            <p class=" card_text text_right" ><fmt:message key="user_main.card.balance"/>  <c:out value="${allAccounts.get(loop.index).sum} UAN"/></p>
                            <p class=" card_text text_right"><fmt:message key="user_main.card.credit_limit"/>  <c:out value="${allAccounts.get(loop.index).creditLimit} UAN"/></p>
                            <p class=" card_text text_right"><fmt:message key="user_main.card.account_number"/>  <c:out value="${allAccounts.get(loop.index).id}"/></p>
                            <c:set var="number" value="${allCards.get(loop.index).cardNumber}"></c:set>
                            <p class="card_bigger_text">
                                <c:out value="${String.valueOf(number).substring(0,4)}
                                ${String.valueOf(number).substring(4,8)}
                                ${String.valueOf(number).substring(8,12)}
                                ${String.valueOf(number).substring(12,16)}"/>
                            </p>
                            <p class="card_text text_left"><c:out value="${user.firstName.toUpperCase()} ${user.secondName.toUpperCase()}    ${allCards.get(loop.index).dueDate}"/></p>
                        </div>
                    </c:forEach>
                    <hr>

            </div>

            <form action="controller" method = "post">
                <input type="hidden" name="command"/>
                <button class = big_button type="submit">
                    <fmt:message key="user_main.button.add_new_card"/>
                </button>
            </form>
        </div>
    </div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>
