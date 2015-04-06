<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/resources/images/banner-graphic.png" var="banner"/>
<img src="${banner}"/>

<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 120px;"><a href="<spring:url value="/" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
            <li style="width: 150px;"><a href="<spring:url value="/users/find.html" htmlEscape="true" />"><i
                    class="icon-search"></i> Find users</a></li>
            <li style="width: 160px;"><a href="<spring:url value="/events.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Events</a></li>
        </ul>
    </div>
</div>
	
