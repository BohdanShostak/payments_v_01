<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Index"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class = "main">

    <div class="main_top">
        <div class = "left_top">
            <div class="motto">
                <h1>
                    <fmt:message key="index_jsp.text.motto"/>
                </h1>
            </div>
            <form class="alignment_center" action="http://localhost:8080/payments_v_01_war_exploded/">
                <button class="big_button" type="submit">
                    <fmt:message key="login_jsp.button.my_account"/>
                </button>
            </form>
        </div>

        <div class="right_top">
            <img src="img/money1.png" alt="Money">
        </div>
    </div>

    <div class="big_table_container">
            <p class="main_table_head"><fmt:message key="index_jsp.ul.our_benefits"/></p>

            <table class="main_table">
                <tr class="tr_image">
                <td><img src="img/speed128.png"></td>
                <td><img src="img/easy128.png"></td>
                <td><img src="img/security128.png"></td>
                </tr>
                <tr class="tr_text">
                    <td><fmt:message key="index_jsp.ul.li.speed"/></td>
                    <td><fmt:message key="index_jsp.ul.li.convenience"/></td>
                    <td><fmt:message key="index_jsp.ul.li.security"/></td>
                </tr>
                <tr class="tr_image">
                    <td><img src="img/limit128.png"></td>
                    <td><img src="img/commission128.png"></td>
                    <td><img src="img/individual128.png"></td>
                </tr>
                <tr class="tr_text">
                    <td><fmt:message key="index_jsp.ul.li.big_credit_limit"/></td>
                    <td><fmt:message key="index_jsp.ul.li.low_commission"/></td>
                    <td><fmt:message key="index_jsp.ul.li.individual_approach"/></td>
                </tr>
            </table>
    </div>

</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>