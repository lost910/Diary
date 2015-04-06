<!DOCTYPE html>
<html lang="ru">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="fragments/staticFiles.jsp"/>


<script type="text/javascript">
    var EventID = null;
    var userEventID = null;
    var eventDate =null;

    function encode_utf8( s )
    {
        return unescape( encodeURIComponent( s ) );
    }

    function getEvent(eventId)
    {
        $.ajax({
            type: "POST",
            data: "eventId="+eventId,
<<<<<<< HEAD
            url: "${session_key}/getEvent" ,
=======
            url: "${user_id}/getEvent" ,
>>>>>>> origin/master
            //contentType: 'text/plain; charset=UTF-8',
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
<<<<<<< HEAD
            url: "${session_key}/UpdateEvent" ,
=======
            url: "${user_id}/UpdateEvent" ,
>>>>>>> origin/master
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
<<<<<<< HEAD
            url: "${session_key}/DeleteEvent" ,
=======
            url: "${user_id}/DeleteEvent" ,
>>>>>>> origin/master
            success: function (data, textStatus)
            {
                if(data=="done") { location.reload(); }
            }
        });
    }

</script>

<body>
<div id="wrapper">
    <div id="header">
        <div class="logo"><h1>Diary</h1></div>
        <div class="loginBox">
            <div class="userImg">
                <spring:url value="/resources/images/ava.png" var="avatar"/>
                <img src="${avatar}" />
            </div>
            <p>${user_login}</p>
        </div>
        <div class="clr"></div>
    </div>
    <div id="main">
        <div class="leftbar">
            <a href="/CreateEvent" class="btn">Create record/reminder</a>

            <ul>
                <c:forEach items="${events.CEventList}" var="item">
                    <li>
                        <a onclick="getEvent(${item.id})" href="#">
                            <c:out value="${item.theme}"/><br />
                            <span class="dateTime"><joda:format value="${item.cr_date}" pattern="dd.MM.yyyy HH:mm:ss"/></span>
                        </a>
                    </li>
                </c:forEach>
            </ul>

            <p class="triangle-border top">Сегодня напоминаний нет...</p>

        </div>
        <div class="rightbar">
            <div class="title">
                <div class="leftpanel">
                    <input type="text" class="line" id="tittleEvent" value="">
                </div>
                <div class="rightpanel">
                    <a href="#" onclick="DeleteEvent()" class="btnpanels2">Delete</a>
                    <a href="#" onclick="UpdateEvent()" class="btnpanels">Save</a>
                </div>

            </div>
            <textarea style="font-family:arial;" class="area" id="area" name="text"></textarea>
        </div>
    </div>
    <div id="footer">

    </div>
</div>
</body>
</html>