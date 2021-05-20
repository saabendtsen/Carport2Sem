<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:genericpage>
    <jsp:attribute name="header">
         Ordre detajler
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <c:if test="${requestScope.error != null }">
            <p style="color:black">
                    ${requestScope.error}
            </p>
        </c:if><br>
        <h4>Du har bestilt følgende:</h4>
        <h6>Carport i målende</h6>
        Bredde: ${requestScope.order.carport.width} cm<br>
        Længde: ${requestScope.order.carport.length} cm<br>
        Tag: ${requestScope.order.carport.roof.name}<br>
        <c:if test="${requestScope.order.shed.length != 0}">
            <br>
            <h4>Tilvalgt:</h4>
            <h6>Redskabsskur i målene</h6>
            Bredde ${requestScope.order.shed.width} cm<br>
            Længde: ${requestScope.order.shed.length} cm<br>
            Beklædning: ${requestScope.order.shed.clothing.name}<br>
        </c:if>
        <hr>
        <fmt:formatNumber var="saleprice" type="number" minFractionDigits="2" maxFractionDigits = "2" value="${requestScope.order.saleprice}" />
        <h5>Salgs pris: ${saleprice} kr.</h5>

        <c:if test="${requestScope.svgdrawing != null}">
            <hr>
            <p>Tegning af Carport</p>
            ${requestScope.svgdrawing}
        </c:if>
        <br>
        <c:if test="${requestScope.order.order_state == true}">
            <c:if test="${requestScope.order.stkListe != null}">
                <hr>
                <h4>Styk Liste: </h4><br>
                <c:forEach items="${requestScope.order.stkListe}" var="mats">
                    <h6>${mats.name}</h6>
                    Længde: ${mats.length} cm<br>
                    Bredde: ${mats.width} cm<br>
                    Antal: ${mats.quantity} stk<br>
                    <fmt:formatNumber var="price" type="number" minFractionDigits="2" maxFractionDigits = "2" value="${mats.price}" />
                    Pris/stk: ${price} kr<br>
                    <fmt:formatNumber var="total" type="number" minFractionDigits="2" maxFractionDigits = "2" value="${mats.price * mats.quantity}" />
                    Total materiale pris: ${total} kr<br>
                    <br>
                </c:forEach>
            </c:if>
        </c:if>
    </jsp:body>

</t:genericpage>

