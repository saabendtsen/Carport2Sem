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

            <div class="row">
                <div class="col-sm-3">
                    <div class="card">
                        <div class="card-body">
                            <h6>Carport i målende</h6>
                            Bredde: ${requestScope.order.carport.width}<br>
                            Længde: ${requestScope.order.carport.length}<br>
                            Tag: ${requestScope.order.carport.roof.name}<br>
                            <br>

                            <c:if test="${requestScope.order.shed.length != 0}">
                                <h4>Tilvalgt:</h4>
                                <h6>Redskabsskur i målene</h6>
                                Bredde ${requestScope.order.shed.width}<br>
                                Længde: ${requestScope.order.shed.length}<br>
                                Beklædning: ${requestScope.order.shed.clothing.name}<br>
                            </c:if>

                            <br>
                            <h4>Total pris: ${requestScope.order.saleprice}</h4>
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

        </div>
    </jsp:body>

</t:genericpage>

