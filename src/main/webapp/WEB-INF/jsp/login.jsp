<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>

<c:set var="title" value="Index"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class = "main_normal_for_forms">
<div class="input_form">

    <form action="controller" method="post">
        <p class="standard_logo"><fmt:message key="login_jsp.text.login_here"/></p>
        <input type="hidden" name="command" value="login"/>
        <input class="input_string" name="login" required placeholder="<fmt:message key="login_jsp.text.login"/>"/><br/>
        <input class="input_string" type="password" name="password" required
               placeholder="<fmt:message key="login_jsp.text.password"/>"/><br>
        <button class="standard_button" type="submit">
            <fmt:message key="header_jspf.anchor.login"/>
        </button>
    </form>

    <form action="controller" method = "post">
        <p class="in_line"><fmt:message key="login_jsp.text.new_user"/></p>
        <input type="hidden" name="command" value="goToSignupPage"/>
        <button class = "small_button"  type="submit" >
            <fmt:message key="header_jspf.anchor.sign_up"/>
        </button>
    </form>
</div>
</div>

<div class="footer">
    <%@ include file="/WEB-INF/jspf/footer1.jspf"%>
</div>

</body>
</html>
