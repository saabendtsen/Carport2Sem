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
        Role: ${sessionScope.user.role}

        <div>
            <h2>Du har bestilt følgende</h2>
            Carport i målende
            Brede ${requestScope.carportWidth}
            længde ${requestScope.carportLength}
            Tag til carport ${requestScope.carportRoof}

            <c:if test="${requestScope.shedLength != null}">
                Tilvalgt redskabsskur i målende:
                Brede ${requestScope.shedWidth}
                Længde: ${requestScope.shedLength}
                Beklædning: ${requestScope.shedRoof}
            </c:if>


        </div>



        </form>
    </jsp:body>

</t:genericpage>

