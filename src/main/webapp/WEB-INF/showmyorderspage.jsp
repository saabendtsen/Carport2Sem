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


                <c:forEach var="order" items="${requestScope.ordersList}">
                    <c:if test="${order != null && not empty order}">
                        <table class="table table-dark table-hover">
                            <tr>
                                <th>Ordrer nr: ${order.order_id}</th>
                                <th colspan="2">Status:
                                    <c:if test="${order.order_state}">
                                    <p style="color: greenyellow">Færdig udarbejdet
                                    <p>
                                        </c:if>
                                        <c:if test="${!order.order_state}">
                                    <p style="color: red">behandles
                                    <p>
                                        </c:if>
                                </th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/fc/showorderdetailsascostumer"
                                          method="post">
                                        <button onclick="" class="btn btn-outline-success edition" type="submit"
                                                name="showorderdetails" value="${order.order_id}">Se denne ordre
                                        </button>
                                    </form>
                                </th>
                                <th colspan="2">Oprettet: ${order.orderDate}</th>
                            </tr>
                            <tr>
                                <th>Carport længde</th>
                                <th>Carport bredde</th>
                                <th>Redskabsskur længde</th>
                                <th colspan="2">Redskabsskur bredde</th>
                            </tr>
                            <tr>
                                <td>${order.carport.length}0 cm</td>
                                <td>${order.carport.width}0 cm</td>
                                <td>${order.shed.length}0 cm</td>
                                <td colspan="2">${order.shed.width}0 cm</td>
                            </tr>

                        </table>
                    </c:if>
                </c:forEach>

            </div>
        </c:if>

    </jsp:body>
</t:genericpage>