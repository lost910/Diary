<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cs" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <c:choose>
        <c:when test="${user['new']}"><c:set var="method" value="post"/></c:when>
        <c:otherwise><c:set var="method" value="put"/></c:otherwise>
    </c:choose>

    <h2>
        <c:if test="${user['new']}">New </c:if> User
    </h2>
    <form:form modelAttribute="user" method="${method}" class="form-horizontal" id="add-user-form">
        <cs:inputField label="Login" name="login"/>
        <cs:inputField label="Password" name="password"/>

        <div class="form-actions">
            <c:choose>
                <c:when test="${user['new']}">
                    <button type="submit">Add User</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Update User</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
