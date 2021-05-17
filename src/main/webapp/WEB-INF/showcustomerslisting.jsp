<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Bruger listen
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user == null}">
            <h3>Du skal have de korrekte rettigheder for at se denne side</h3>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
            <div>
                <h6 class="font-weight-bold">Liste over brugere i databasen</h6>

                <table class="table table-dark">
                    <tr>
                        <th>Kunde id</th>
                        <th>Kunde navn</th>
                        <th>Rettigheder</th>
                        <th colspan="3">Funktioner</th>
                    </tr>
                    <c:forEach var="kunde" items="${requestScope.userlist}">
                        <c:if test="${kunde != null && not empty kunde}">
                            <tr>
                                <td>${kunde.user_id}</td>
                                <td>${kunde.email}</td>
                                <td>${kunde.role}</td>
                                <td>
                                    <c:if test="${not kunde.role.equals('employee')}">
                                        <form action="${pageContext.request.contextPath}/fc/showcustomers" method="get">
                                            <button class="btn btn-info" type="submit" name="chosen_userid" value="${kunde.user_id}">Se ordre</button>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>

            </div>
        </c:if>

    </jsp:body>
</t:genericpage>