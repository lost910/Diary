<!DOCTYPE html>
<html lang="ru">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<jsp:include page="fragments/staticFiles.jsp"/>


<script type="text/javascript">
    var EventID = null;
    var userEventID = null;
    var eventDate = null;

    function getEvent(eventId)
    {
        $.ajax({
            type: "POST",
            data: "eventId="+eventId,
            url: "${session_key}/getEvent",
            success: function (data, textStatus)
            {
                var eventJson = jQuery.parseJSON(data);
                document.getElementById("area").value = eventJson.Descr;
                document.getElementById("tittleEvent").value = eventJson.Theme;
                EventID = eventJson.id;
                userEventID = eventJson.User_id;
                eventDate = eventJson.Date;
            }
        });
    }

    function UpdateEvent()
    {
        $.ajax({
            type: "POST",
            data: "id="+ EventID + "&user=" + userEventID + "&theme=" + document.getElementById("tittleEvent").value + "&descr=" + document.getElementById("area").value,
            url: "${session_key}/UpdateEvent",
            success: function (data, textStatus)
            {
                if(data=="done") { location.reload(); }
            }
        });
    }

    function DeleteEvent()
    {
        $.ajax({
            type: "POST",
            data: "id="+ EventID,
            url: "${session_key}/DeleteEvent",
            success: function (data, textStatus)
            {
                if(data=="done") { location.reload(); }
            }
        });
    }
</script>
<spring:url value="/resources/images/exit.png" var="exit"/>
<spring:url value="/resources/images/file.png" var="file"/>
<spring:url value="/resources/images/del.png" var="del"/>
<spring:url value="/resources/userfiles/" var="filesFolder"/>
<body>
<div id="header">
    <div class="headerWrap">
        <div class="logo">Diary</div>
        <div class="rightPanel">
            <ul>
                <li><span>Привет, ${user_login}!</span></li>
                <li><a href="${session_key}/signout"><img src="${exit}" width="50" height="50"/></a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <a href="${session_key}/CreateEvent" class="btn">Создать запись</a>
        <ul class="eventList">
            <c:forEach items="${events.CEventList}" var="item">
                <li onclick="getEvent(${item.id})">
                    <c:out value="${item.theme}"/><br />
                    <span class="dateTime"><joda:format value="${item.cr_date}" pattern="dd.MM.yyyy HH:mm:ss"/></span>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <div class="content">
            <div class="contentPanel">
                <input type="text" id="tittleEvent" value="">
                <a href="#" onclick="DeleteEvent()" class="btnpanels2">Удалить</a>
                <a href="#" onclick="UpdateEvent()" class="btnpanels">Сохранить</a>
            </div>
            <textarea placeholder="Выберите запись..." id="area" name="text"></textarea>
        </div>
        <div class="rightBar">
            <img src="${file}" width="100" height="80"/>
            <p>Личное файловое хранилище.</p>
            <form method="POST" action="${session_key}/uploadFile" enctype="multipart/form-data">
                <input id="FileValue" type="text" name="fname"/>
                <p class="file_upload">Открыть<input type="file" name="file"
                    onchange="document.getElementById('FileValue').value = this.value"/></p>
                <input type="submit" value="Загрузить" />
            </form>
            <ul class="rightBarlist">
                <c:forEach items="${files}" var="item">
                    <li>
                        <a href="${filesFolder}${item.fname}"><c:out value="${item.fname}"/></a><br />
                        <span class="fileclass">
                            <fmt:formatNumber type="number" maxFractionDigits="1" value="${item.fsize/1024}" /> kb /
                            <joda:format value="${item.upl_date}" pattern="dd.MM.yyyy HH:mm"/></span>
                        <a class="delfile" href="${session_key}/deleteFile/${item.id}"><img src="${del}" width="16" height="16"/></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
</body>
</html>