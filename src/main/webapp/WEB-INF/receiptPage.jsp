<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<t:genericpage>
    <jsp:attribute name="header">
         Din Kvittering
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h2>Tak for din bestilling.</h2>
        <h2>Du vil blive kontaktet af en sælger hurtigst muligt</h2><br><br><br>
        <div>
            <h4>Du har bestilt følgende</h4>
            <h6>Carport i målende</h6>
            Bredde: ${requestScope.order.carport.width} cm<br>
            Længde: ${requestScope.order.carport.length} cm<br>
            Tag: ${requestScope.order.carport.roof.name}<br>
            <br>
            <c:if test="${requestScope.order.shed.length != 0}">
                <h4>Tilvalgt:</h4>
                <h6>Redskabsskur i målene</h6>
                Bredde ${requestScope.order.shed.width} cm<br>
                Længde: ${requestScope.order.shed.length} cm<br>
                Beklædning: ${requestScope.order.shed.clothing.name}<br>
            </c:if>

            <br>
            <h4>Total pris: ${requestScope.order.saleprice} kr</h4>

            <c:if test="${requestScope.svgdrawing != null}">
            <p>Tegning af Carport</p>
            ${requestScope.svgdrawing}
            </c:if>

        </div>
    </jsp:body>

</t:genericpage>

