<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Opdatere info siden
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hej ${sessionScope.user.email} </h1>

        Her kan du indtaste dine nye bruger oplysninger

        <form id="form" method="get"  action="${pageContext.request.contextPath}/fc/updateuserdata">
            <br>
            <label class="col-form-label" for="newEmail">Din nye Email:</label>
            <input class="form-control" type="text" id="newEmail" name="newEmail">
            <label class="col-form-label" for="newPassword">Dit nye kodeord:</label><br>
            <input class="form-control" type="password" id="newPassword" name="newPassword"><br>

            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>

            <button id="submit" type="submit" class="btn btn-primary">Bekræft ændringer</button>
        </form>
    </jsp:body>

</t:genericpage>

