<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         ReceiptPage
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.user.email} </h1>
        Tak for din ordre. Du vil blive kontaktet af en sælger hurtigst muligt

        <div>
            <h2>Du har bestilt følgende</h2>
            <h6>Carport i målende</h6>
            Brede: ${requestScope.carportWidth}<br>
            længde: ${requestScope.carportLength}<br>
            Tag til carport ${requestScope.carportRoof}<br><br>

            <c:if test="${requestScope.shedLength != 0}">
                <h3>Tilvalgt redskabsskur i målende:</h3><br>
                Brede ${requestScope.shedWidth}<br>
                Længde: ${requestScope.shedLength}<br>
                Beklædning: ${requestScope.shedRoof}<br>
            </c:if>


        </div>


        </form>
    </jsp:body>

</t:genericpage>

