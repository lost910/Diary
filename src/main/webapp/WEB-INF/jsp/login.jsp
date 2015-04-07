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

<div id="wrap">
    <div class="LoginBody">
        <div class="description">
            <div class="descr">
                <h1>Добро пожаловать в Diary</h1>
                <p>
                    Организуйте свою жизнь вместе с нами!
                    Diary это рабочее пространство для ежедневных заметок, записей, мыслей.
                    Храните все в одном месте.
                </p>
                <a class="registr" href="/registration">Присоединиться</a>
            </div>
        </div>
        <div class="loginForm">
            <h1>Авторизация</h1>
            <form:form modelAttribute="user" methodParam="GET" class="formMain">
                <p><form:input path="login" class="li" id="login" name="login" type="text" placeholder="Login"/></p>
                <p><form:input path="password"  class="li" id="password"  name="password" placeholder="Password" type="password"/></p>
                <input type="submit" value="Войти"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
