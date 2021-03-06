<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <meta name="theme-color" content="#7952b3">
</head>
<body style="background-color: #F9F9F9">
    <!--
        This header is inspired by this bootstrap
        example: https://getbootstrap.com/docs/5.0/examples/pricing/
    -->
<header class="d-flex flex-column flex-md-row align-items-center p-3 pb-0 px-md-4 mb-4 bg-white border-bottom shadow-sm">
    <div class="h5 my-0 me-md-auto fw-normal">

        <c:if test="${sessionScope.role == 'customer'}">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/fc/navigatetoindex"><img src="https://www.johannesfog.dk/globalassets/forsiden/fog-logo1.svg" width="60" height="60" alt=""></a>
        </c:if>
        <c:if test="${sessionScope.role == 'employee'}">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/fc/employeepage"><img src="https://www.johannesfog.dk/globalassets/forsiden/fog-logo1.svg" width="60" height="60" alt=""></a>
        </c:if>
        <c:if test="${sessionScope.user == null}">
            <a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="https://www.johannesfog.dk/globalassets/forsiden/fog-logo1.svg" width="60" height="60" alt=""></a>
        </c:if>

        <p style="font-size: larger">
            <jsp:invoke fragment="header"/>
        </p>
    </div>
    <nav class="my-2 my-md-0 me-md-3">


        <c:if test="${sessionScope.role == 'employee'}">
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/employeepage">Admin side</a>
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/showcustomers">Se Brugere</a>
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/showactiveorders">Aktive Ordre</a>
        </c:if>

        <c:if test="${sessionScope.role == 'customer'}">
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/navigatetoindex">Bestil Carport</a>
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/customerpage">Min side</a>
            <a class="p-2 text-dark" href="${pageContext.request.contextPath}/fc/showmyorders">Mine Ordre</a>
        </c:if>

    </nav>

    <div>
        <c:if test="${sessionScope.user != null }">
            ${sessionScope.user.email}
        </c:if>

        <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
        <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
        <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

        <c:if test="${isNotLoginPage && isNotRegisterPage}">
            <c:if test="${sessionScope.user != null }">
                <a type="button" class="btn btn-sm  btn-outline-secondary"
                href="${pageContext.request.contextPath}/fc/logoutcommand">Log ud</a>
            </c:if>
            <c:if test="${sessionScope.user == null }">
                <a type="button" class="btn btn-outline-info"
                   href="${pageContext.request.contextPath}/fc/loginpage">Log ind</a>
                <a type="button" class="btn btn-outline-success"
                   href="${pageContext.request.contextPath}/fc/registerpage">Register dig</a>
            </c:if>
    </div>
    </c:if>
</header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">

    <hr>
    Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 163144
    <br>
    <jsp:invoke fragment="footer"/>
</div>

</body>
</html>