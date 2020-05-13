<%-- TODO: Delete this page if navbar and footer will be same for admin/regular --%>

<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <%-- TODO: like pagetemplate.tag but with more permissions --%>

</jsp:attribute>
</my:pagetemplate>