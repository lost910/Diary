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
<script type="text/javascript">
    function Registration() {
        debugger;
        var login = $('#login').val();
        if (!login) {
            $('#erorr').empty();
            $('#erorr').append('Введите логин!');
            return;
        }
        var pass = $('#password').val();
        if (!pass) {
            $('#erorr').empty();
            $('#erorr').append('Введите пороль!');
        }
        var pass2 = $('#password_repeat').val();
        if (!pass2) {
            $('#erorr').empty();
            $('#erorr').append('Не введен проверочный пороль');
        }
        if (pass !== pass2) {
            $('#erorr').empty();
            $('#erorr').append('Введенные пороли не совпадают!');
        }
        $.ajax({
            type: "POST",
            data: "login=" + document.getElementById("login").value + "&password=" + document.getElementById("password").value,
            url: "registrationProcess",
            success: function (data, textStatus) {
                if (data == "done") {
                    location.replace("/");
                }
                else {
                    $('#erorr').empty();
                    $('#erorr').append(data);
                }
            }
        });
    }
</script>
<body>
<div id="wrap">
    <div class="RegistrationBody">
        <h1>Diary</h1>

        <div class="clr"></div>
        <h2>Быстрая регистрация</h2>

        <div class="clr"></div>
        <div class="RegistrationForm">
            <form id="user" action="/registration" method="post" class="formMain">
                <p><input class="li" id="login" name="login" type="text" placeholder="Login" value=""/></p>

                <p><input class="li" id="password" name="password" placeholder="Password" type="password" value=""/></p>

                <p><input class="li" id="password_repeat" name="password_repeat" placeholder="Repeat password"
                          type="password" value=""/></p>
                <!--<input type="submit" value="Зарегестрироваться"/>-->
                <a href="#" onclick="Registration()" class="btn">Зарегестрироваться</a>
            </form>
            <div class="errorRegForm" id="erorr"></div>
        </div>
    </div>
</div>
</body>
</html>
