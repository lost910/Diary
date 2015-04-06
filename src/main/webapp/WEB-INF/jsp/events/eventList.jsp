<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Events</h2>

    <datatables:table id="events" data="${events.CEventList}" row="ev" theme="bootstrap2" cssClass="table table-striped" pageable="false" info="false">
        <datatables:column title="Theme">
            <c:out value="${ev.theme}"/>
        </datatables:column>
        <datatables:column title="Description">
            <c:out value="${ev.descr}"/>
        </datatables:column>
        <datatables:column title="Creation data">
            <c:out value="${ev.cr_date}"/>
        </datatables:column>
        <datatables:column title="Linked user Id">
            <c:out value="${ev.user.id}"/>
        </datatables:column>
    </datatables:table>

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
