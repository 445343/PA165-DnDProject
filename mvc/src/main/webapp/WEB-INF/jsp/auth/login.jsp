<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="title"><fmt:message key="login.headtitle"/></jsp:attribute>
    <jsp:attribute name="body">

    <h2><fmt:message key="login.title"/></h2>

    <form method="POST" action="${pageContext.request.contextPath}/auth/login">
        <div class="form-group">
            <label for="username"><fmt:message key="login.password"/></label>
            <input class="form-control" id="username" type="text" name="username" placeholder="Username">
        </div>

        <div class="form-group">
            <label for="password"><fmt:message key="login.password"/></label>
            <input class="form-control" id="password" type="password" name="password" placeholder="Password">
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="login.button"/></button>
    </form>

</jsp:attribute>
</my:pagetemplate>
