<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Index"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body id = "user_new_payment">
<%@include file="/WEB-INF/jspf/header.jspf"%>
<%@include file="/WEB-INF/jspf/user_menu.jspf"%>
<div class = "main_normal_for_forms_and_menu">

    <div class="input_form">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="newPayment"/>
            <p class="standard_logo"><fmt:message key="user_main.new_payment.new_payment"/></p>

            <p class="margin_bottom_small"><fmt:message key="user_main.new_payment.from_card"/></p>
            <select class="standard_button" name="selectedCard" required>
                <c:forEach items="${allAccounts}" varStatus="loop">
                    <option value = ${allCards.get(loop.index).cardNumber}>
                        <c:out value="${allCards.get(loop.index).cardName}"/>
                    </option>
                </c:forEach>
            </select>

            <select class="standard_button" name="typeOfPayment" required>
                <option disabled selected>
                    <fmt:message key="user_main.new_payment.select_payment_type"/>
                </option>
                <option value = "byCardNumber">
                    <fmt:message key="user_main.new_payment.by_card_number"/>
                </option>
                <option value = "byAccountNumber">
                    <fmt:message key="user_main.new_payment.by_account_number"/>
                </option>
            </select>
            <input class="input_string" type="number" name = "cardAccountNumber" required maxlength="10"
                   placeholder="<fmt:message key="user_main.new_payment.account_or_card_number"/>"/>
            <input class="input_string" type="number" name = "sum" required maxlength="14"
                   placeholder="<fmt:message key="user_main.new_payment.sum"/>"/>
            <input class="input_string" name = "description" maxlength="45"
                   placeholder="<fmt:message key="user_main.new_payment.description"/>"/>
            <p class="thin_white_text"><input type="checkbox" name="preparedPayment" value="true"/>
                <fmt:message key="user_main.new_payment.archive"/></p>
            <br>
            <button class="standard_button" type="submit">
                <fmt:message key="user_main.new_payment.send"/>
            </button>
        </form>
    </div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>

