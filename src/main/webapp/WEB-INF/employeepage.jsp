<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Sælger Side
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hej ${sessionScope.user.email} </h1>

        <p>Her er nogle knapper med funktionalitet du kan udføre</p>
        <div class="card">
        <a href="${pageContext.request.contextPath}/fc/showcustomers" class="btn btn-lg btn btn-outline-success" role="button">Se alle brugere</a>
        <a href="${pageContext.request.contextPath}/fc/showactiveorders" class="btn btn-lg btn btn-outline-secondary" role="button">Se Aktive Ordre</a>
        </div>


    </jsp:body>
</t:genericpage>
