<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Demo Page for Customer Roles
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.user.email} </h1>
        You are now logged in as a Customer of our wonderful site.
        Role: ${sessionScope.user.role}

        <form id="form" method="get"  action="${pageContext.request.contextPath}/fc/updateuserdata">
            <label for="newEmail">Update Email:</label><br>
            <input type="text" id="newEmail" name="newEmail"><br>
            <label for="newPassword">Updater kodeord:</label><br>
            <input type="text" id="newPassword" name="newPassword"><br><br>
            <c:if test="${requestScope.error != null }">
                <p style="color:red">
                        ${requestScope.error}
                </p>
            </c:if>

            <button id="submit" type="submit" class="btn btn-primary btn-sm">Send Forespørgsel</button>
        </form>
    </jsp:body>

</t:genericpage>

