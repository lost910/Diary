<!DOCTYPE html>
<html lang="ru">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cs" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=windows-1257" language="java"%>

<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<div id="wrap">
    <div class="CreateEventBody">
        <h1>Diary</h1>
        <form:form modelAttribute="event" methodParam="GET">
            <p>Text name</p>
            <form:input name="theme" path="theme" cssClass="CreateInput"/>
            <p>Text</p>
            <form:textarea name="descr" path="descr" class="CreateArea"/>
            <input type="submit" value="Создать запись"/>
        </form:form>
    </div>
</div>
</body>
</html>
