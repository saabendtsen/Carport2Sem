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

        <h2>todo - at kunne se order, gøre den færdig, vise styk liste + svg og give rabat på pris</h2>

    </jsp:body>
</t:genericpage>
