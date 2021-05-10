<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Din Kvittering
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h2>Tak for din ordre.</h2>
        <h2>Du vil blive kontaktet af en sælger hurtigst muligt</h2><br><br><br>
        <div>
            <h4>Du har bestilt følgende</h4>
            <h6>Carport i målende</h6>
            Brede: ${requestScope.carportWidth}<br>
            længde: ${requestScope.carportLength}<br>
            Tag til carport ${requestScope.carportRoof}<br><br>

            <c:if test="${requestScope.shedLength != 0}">
                <h3>Tilvalgt:</h3>
                <h6>Redskabsskur i målende</h6>
                Brede ${requestScope.shedWidth}<br>
                Længde: ${requestScope.shedLength}<br>
                Beklædning: ${requestScope.shedRoof}<br>
            </c:if>


        </div>
    </jsp:body>

</t:genericpage>

