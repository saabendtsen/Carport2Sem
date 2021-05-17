<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Kundes tidligere ordre
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user == null}">
            <h3>Du skal have en konto for at se denne side</h3>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">

            <h6><a class="btn btn-outline-success" href="${pageContext.request.contextPath}/fc/showcustomers">Tilbage til oversigten</a></h6>

            <c:choose>
                <c:when test="${requestScope.specificuserList.size() > 0}">
                    <c:forEach var="order" items="${requestScope.specificuserList}">

                        <c:if test="${order != null && not empty order}">
                            <table class="table table-dark table-hover">
                                <tr>
                                    <th>Ordrer nr: ${order.order_id}</th>
                                    <th colspan="2">Status:
                                        <c:if test="${order.order_state}">
                                            <p style="color: greenyellow">Færdig udarbejdet<p>
                                        </c:if>
                                        <c:if test="${!order.order_state}">
                                            <p style="color: red">Behandles<p>
                                        </c:if>
                                    </th>
                                    <th>
                                        <c:if test="${!order.order_state}">
                                        <form action="${pageContext.request.contextPath}/fc/updatestatuscommand" method="post">
                                            <button onclick="" class="btn btn-outline-success edition" type="submit" name="updateOrder" value="${order.order_id}">Færdiggør ordre</button>
                                            <input type="hidden" name="chosen_userid" value="${order.user_id}">
                                        </form>
                                        </c:if>
                                    </th>
                                    <th colspan="2">Oprettet: ${order.orderDate}</th>
                                </tr>
                                <tr>
                                    <th>Carport længde</th>
                                    <th>Carport bredde</th>
                                    <th>Carport total</th>
                                    <th>Redskabsskur længde</th>
                                    <th>Redskabsskur bredde</th>
                                    <th>Redskabsskur total</th>
                                </tr>
                                <tr>
                                    <td>${order.carport.length}0 cm</td>
                                    <td>${order.carport.width}0 cm</td>
                                    <td>${order.carport.total}0</td>
                                    <td>${order.shed.length}0 cm</td>
                                    <td>${order.shed.width}0 cm</td>
                                    <td>${order.shed.total}0</td>
                                </tr>
                            </table>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="jumbotron-fluid card-body">
                        <h2>Kunden har endnu ingen Carport ordrer</h2>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>


    </jsp:body>
</t:genericpage>