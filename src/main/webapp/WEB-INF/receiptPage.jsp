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
        <h2>Tak for din ordre.</h2>
        <h2>Du vil blive kontaktet af en sælger hurtigst muligt</h2><br><br><br>
        <div>
            <h4>Du har bestilt følgende</h4>
            <h6>Carport i målende</h6>
            Bredde: ${requestScope.carportWidth}<br>
            Længde: ${requestScope.carportLength}<br>
            Tag: ${requestScope.carportRoof_materialID.name}<br>

            <c:if test="${requestScope.shedLength != 0}">
                <h3>Tilvalgt:</h3>
                <h6>Redskabsskur i målene</h6>
                Bredde ${requestScope.shedWidth}<br>
                Længde: ${requestScope.shedLength}<br>
                Beklædning: ${requestScope.shedClothing_materialID.name}<br>
            </c:if>

            <br>

            <c:if test="${requestScope.svgdrawing != null}">
            <p>Tegning af Carport</p>
            ${requestScope.svgdrawing}
            </c:if>

            <c:if test="${requestScope.stkList != null}">
            <h4>Styk liste: </h4>

            <c:forEach items="${requestScope.stkList}" var="mats" >
                ${mats.name} <br>
                Længde: ${mats.length}<br>
                Bredde: ${mats.width}<br>
                Antal: ${mats.quantity}<br>
                Pris/stk: ${mats.price}<br>
                Total: ${mats.price * mats.quantity}<br>
                <br>
            </c:forEach>
            </c:if>
        </div>
    </jsp:body>

</t:genericpage>

