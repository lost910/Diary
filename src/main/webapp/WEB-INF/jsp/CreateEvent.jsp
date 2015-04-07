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
<div id="wrapper">
    <div id="header">
        <div class="logo"><h1>Diary</h1></div>
        <div class="loginBox">
            <div class="userImg">
                <spring:url value="/resources/images/ava.png" var="avatar"/>
                <img src="${avatar}" />
            </div>
            <p>Mr. Builin</p>
        </div>
        <div class="clr"></div>
    </div>
    <div id="main">
        <div class="text">

            <c:choose>
                <c:when test="${event['new']}"><c:set var="method" value="post"/></c:when>
                <c:otherwise><c:set var="method" value="put"/></c:otherwise>
            </c:choose>

            <form:form modelAttribute="event" methodParam="GET">
                <table>
                    <tr>
                        <td><form:input name="theme" path="theme" cssClass="CreateInput"/></td>
                    </tr>
                    <tr>
                        <td><form:textarea cssclass="CreateArea" path="descr" name="descr"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:choose>
                                <c:when test="${event['new']}">
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

                <!--<h3 class="des">Название записи</h3>
                <input type="text" id="tittleEvent" value="" class="CreateInput">
                <h3 class="des">Текст записи</h3>
                <textarea class="CreateArea" id="area" name="text"></textarea>
                <a href="#" class="btnpanels" style="margin-left: 0;">Create</a>-->

        </div>
    </div>
    <div id="footer">

    </div>
</div>
</body>
</html>
