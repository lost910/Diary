<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Find Owners</h2>

    <spring:url value="/users.html" var="formUrl"/>
    <form:form modelAttribute="user" action="${fn:escapeXml(formUrl)}" method="get" class="form-horizontal"
               id="search-user-form">
        <fieldset>
            <div class="control-group" id="login">
                <label class="control-label">Login </label>
                <form:input path="login" size="30" maxlength="32"/>
                <span class="help-inline"><form:errors path="*"/></span>
            </div>
            <div class="form-actions">
                <button type="submit">Find User</button>
            </div>
        </fieldset>
    </form:form>

    <br/>
    <a href='<spring:url value="/users/new" htmlEscape="true"/>'>Add Owner</a>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
