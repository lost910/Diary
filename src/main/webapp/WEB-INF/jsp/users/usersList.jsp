<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>
    <h2>Users</h2>

    <datatables:table id="users" data="${selections}" row="user" theme="bootstrap2"
                      cssClass="table table-striped" pageable="false" info="false" export="pdf">
        <datatables:column title="Name" cssStyle="width: 150px;" display="html">
            <spring:url value="/users/{userId}.html" var="userUrl">
                <spring:param name="ownerId" value="${owner.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(userUrl)}"><c:out value="${user.login}"/></a>
        </datatables:column>
        <datatables:column title="Login" display="pdf">
            <c:out value="${user.login}"/>
        </datatables:column>
        <datatables:column title="Password" property="password" cssStyle="width: 200px;"/>
        <datatables:export type="pdf" cssClass="btn" cssStyle="height: 25px;" />
    </datatables:table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>
</body>

</html>
