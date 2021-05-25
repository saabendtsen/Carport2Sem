<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
         Log ind Siden
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div style="margin-top: 5em;" class="container">
            <form name="login" action="${pageContext.request.contextPath}/fc/logincommand"  method="POST">
                <div class="row mb-3">
                    <label class="col-form-label" for="email">Email</label>
                    <div class="col-sm-4">
                        <input id="email" class="form-control" type="text" name="email" placeholder="someone@nowhere.com">
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-form-label" for="password">Password</label>
                    <div class="col-sm-4">
                        <input id="password" class="form-control" type="password" name="password" placeholder="****">
                    </div>
                </div>
                <c:if test="${requestScope.error != null }">
                    <p style="color:red">
                            ${requestScope.error}
                    </p>
                </c:if>

                <c:if test="${not empty param.msg}">
                    <p style="font-size: large">${param.msg}</p>
                </c:if>
                <button class="btn btn-primary" type="submit" value="Login">Log ind</button>

                <a class="btn btn-primary" href="${pageContext.request.contextPath}/fc/registerpage">Opret konto</a>

            </form>


        </div>
    </jsp:body>
</t:genericpage>
