<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Tidligere ordre
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
                <h6 class="font-weight-bold">Her er dine tidligere ordre:</h6>


                <c:forEach var="order" items="${requestScope.ordersList}">
                    <c:if test="${order != null && not empty order}">
                        <table class="table table-dark table-hover">
                            <tr>
                                <th>Ordrer nr: ${order.order_id}</th>
                                <th>Status:
                                    <c:if test="${order.order_state}">
                                        <p style="color: #00ff00">Færdig<p>
                                    </c:if>
                                        <c:if test="${!order.order_state}">
                                            <p style="color: red">behandles<p>
                                        </c:if>
                                </th>
                                <th>
                                    <form action="${pageContext.request.contextPath}/fc/showorderdetailsascostumer" method="post">
                                        <button onclick="" class="btn btn-sm btn-outline-success" type="submit" name="showorderdetails" value="${order.order_id}">Se denne ordre</button>
                                    </form>
                                </th>
                                <th>Oprettet: ${order.orderDate}</th>
                                <c:if test="${order.order_state}">
                                    <th colspan="2" style="color: #ffffff">Pris: ${order.saleprice} kr.</th>
                                </c:if>
                            </tr>
                            <tr>
                                <th>Carport længde</th>
                                <th>Carport bredde</th>
                                <th>Redskabsskur længde</th>
                                <th colspan="2">Redskabsskur bredde</th>
                                <c:if test="${order.order_state}">
                                    <th>Tegning</th>
                                </c:if>
                            </tr>
                            <tr>
                                <td>${order.carport.length}0 cm</td>
                                <td>${order.carport.width}0 cm</td>
                                <td>${order.shed.length}0 cm</td>
                                <td colspan="2">${order.shed.width}0 cm</td>
                                <c:if test="${order.order_state}">
                                <form action="${pageContext.request.contextPath}/fc/showorderdetailsascostumer" method="post">
                                <td><button onclick="" class="btn btn-sm btn-outline-secondary" type="submit" name="showorderdetails" value="${order.order_id}">Se tegning</button></td>
                                </form>

                                </c:if>
                            </tr>

                        </table>
                    </c:if>
                </c:forEach>

            </div>
        </c:if>

    </jsp:body>
</t:genericpage>