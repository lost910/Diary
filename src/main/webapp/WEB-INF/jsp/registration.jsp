<!DOCTYPE html>
<html lang="ru">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cs" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<c:choose>
    <c:when test="${user['new']}"><c:set var="method" value="post"/></c:when>
    <c:otherwise><c:set var="method" value="put"/></c:otherwise>
</c:choose>

    <form:form modelAttribute="user" methodParam="GET">
        <table>
            <tr>
                <td><form:label path="login">Логин</form:label></td>
                <td><form:input path="login" name="login"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Пароль</form:label></td>
                <td><form:input path="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${user['new']}">
                            <button type="submit">Add User</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit">Update User</button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>


</body>
</html>
