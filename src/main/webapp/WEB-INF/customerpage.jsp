<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Køber Side
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <h1>Hej ${sessionScope.user.email} </h1>

        <p>Her er nogle knapper med funktionalitet du kan udføre</p>

            <a class="btn btn-outline-success btn-block" href="${pageContext.request.contextPath}/fc/navigatetoindex" role="button">Bestil Carport</a>
            <a class="btn btn-outline-secondary btn-block" href="${pageContext.request.contextPath}/fc/showmyorders" role="button">Mine Ordre</a>
            <a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/fc/changeinfo" role="button">Skift dine login oplysninger</a>

    </jsp:body>
</t:genericpage>

