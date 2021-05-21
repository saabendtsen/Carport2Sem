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
        <div class="row">
            <div class="col-sm-3">
                <div class="card">
                    <div class="card-body">
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
                        <hr>
                        <c:if test="${requestScope.order.order_state == true}">
                            <c:if test="${requestScope.order.stkListe != null}">
                                <h4>Styk Liste: </h4>
                                <c:forEach items="${requestScope.order.stkListe}" var="mats">
                                    <h6>${mats.name}</h6>
                                    Længde: ${mats.length} cm<br>
                                    Bredde: ${mats.width} cm<br>
                                    Antal: ${mats.quantity} stk<br>
                                    <br>
                                </c:forEach>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${requestScope.svgdrawing != null}">
                            <h6>Tegning af Carport</h6>
                            ${requestScope.svgdrawing}
                        </c:if>
                    </div>
                </div>
            </div>
        </div>


    </jsp:body>

</t:genericpage>

