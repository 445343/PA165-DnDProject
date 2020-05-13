<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="title"><fmt:message key="register.headtitle"/></jsp:attribute>
    <jsp:attribute name="body">

    <h2><fmt:message key="register.title"/></h2>

    <form method="POST" action="${pageContext.request.contextPath}/auth/register">
        <div class="form-group">
            <label for="username"><fmt:message key="register.password"/></label>
            <input class="form-control" id="username" type="text" name="username" placeholder="Username">
        </div>

        <div class="form-group">
            <label for="password"><fmt:message key="register.password"/></label>
            <input class="form-control" id="password" type="password" name="password" placeholder="Password">
        </div>

        <p>Is admin</p>
        <div class="form-check-inline">
            <label class="form-check-label" for="isadmin">
                <input type="radio" class="form-check-input" id="isadmin" name="isAdmin" value="true"> Yes
            </label>
        </div>
        <div class="form-check-inline">
            <label class="form-check-label" for="isntadmin">
                <input type="radio" class="form-check-input" id="isntadmin" name="isAdmin" value="false" checked> No
            </label>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="register.button"/></button>
    </form>

</jsp:attribute>
</my:pagetemplate>
