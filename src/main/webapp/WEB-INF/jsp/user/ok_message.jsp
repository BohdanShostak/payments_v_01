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
            <br>
            <p class="standard_white_text">
                <c:out value="${okMessage}"/>
            </p>
            <input type="hidden" name="command" value="userMain"/>
            <br>
            <button class="standard_button" type="submit">
                <fmt:message key="user_main.top_up_balance_message.ok"/>
            </button>
            <br>
        </form>
    </div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>