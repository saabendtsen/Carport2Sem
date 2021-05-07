<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Home
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user == null}">
            <h3>Du skal have en konto for at se denne side</h3>
        </c:if>

        <c:if test="${sessionScope.role == 'customer' }">
        <div>
            <h6 class="font-weight-bold">Dine gamle ordre</h6>

                    <c:forEach var="order" items="${requestScope.ordersList}" >
                        Ordre - ${order.order_id}
                        Dato - ${order.orderDate}
                        Carport længde - ${order.carport.length}
                        Carport bredde - ${order.carport.width}
                        Carport total - ${order.carport.total}
                        Redskabsskur længde - ${order.shed.length}
                        Redskabsskur bredde - ${order.shed.width}
                        Redskabsskur total - ${order.shed.total}
                    </c:forEach>

        </div>
        </c:if>

    </jsp:body>
</t:genericpage>