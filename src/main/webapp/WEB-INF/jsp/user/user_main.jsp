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
                <div class="card">

                </div>
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
