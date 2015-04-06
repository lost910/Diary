<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>User Information</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Login</th>
            <td><b><c:out value="${user.login}"/></b></td>
        </tr>
        <tr>
            <th>Password</th>
            <td><c:out value="${user.password}"/></td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
