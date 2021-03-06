<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Bestillings Siden
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user == null}">
            <h3>Velkommen til fogs carport bestillings side.</h3>
            <h5>Du skal have en konto for at kunne bestille en Carport. <br>Tryk på log ind eller registre dig for at forstætte</h5>
            <div class="card-body">
            <a type="button" class="btn btn-outline-info" href="${pageContext.request.contextPath}/fc/loginpage">Log ind</a>
            <a type="button" class="btn btn-outline-success" href="${pageContext.request.contextPath}/fc/registerpage">Register dig</a>
            </div>
        </c:if>

        <c:if test="${sessionScope.role == 'customer' }">
        <div>
            <h2>QUICK-BYG TILBUD - CARPORT</h2>
            <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram.</p>
            <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt. Standardbyggevejledning medfølger ved bestilling. Dit redskabsskur må ikke være større eller bredder end din carport.</p>
            <h6 class="font-weight-bold">Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</h6>

            <form id="form" method="post" action="${pageContext.request.contextPath}/fc/orderPage">
                <h6 class="font-weight-bold">Carport bredde</h6>
                <select class="form-select" name="carportWidth" id="carportWidth" aria-label="Default select example">
                    <option selected>Vælg bredde:</option>
                    <c:forEach begin="240" end="750" step="30" var="carportWidth" varStatus="carportWidth">
                        <option value="${carportWidth.index}">${carportWidth.index} cm</option>
                    </c:forEach>
                </select><br>
                <h6 class="font-weight-bold">Carport længde</h6>
                <select class="form-select" name="carportLength" id="carportLength" aria-label="Default select example">
                    <option selected>Vælg længde:</option>
                    <c:forEach begin="240" end="780" step="30" var="carportLength" varStatus="carportLength">
                        <option value="${carportLength.index}">${carportLength.index} cm</option>
                    </c:forEach>
                </select><br>
                    <h6 class="font-weight-bold">Carport Tagbeklædning</h6>
                <select class="form-select" name="carportRoof" id="carportRoof" aria-label="Default select example">
                    <option selected value="0">Vælg Tagbeklædning:</option>
                    <c:forEach var="order" items="${requestScope.carportClothingList}" >
                        <option value="${order.material_id}">${order.name}</option>
                    </c:forEach>

                </select><br>
                <hr>
                <h6 class="font-weight-bold">Redskabsrums Bredde</h6>
                <select class="form-select" name="shedWidth" id="shedWidth" aria-label="Default select example">
                    <option selected value="0">Ønsker ikke redskabsrum</option>
                    <c:forEach begin="210" end="720" step="30" var="shedWidth" varStatus="shedWidth">
                        <option value="${shedWidth.index}">${shedWidth.index} cm</option>
                    </c:forEach>
                </select><br>
                <h6 class="font-weight-bold">Redskabsrums Længde</h6>
                <select class="form-select" name="shedLength" id="shedLength" aria-label="Default select example">
                    <option selected value="0">Ønsker ikke redskabsrum</option>
                    <c:forEach begin="150" end="690" step="30" var="shedLength" varStatus="shedLength">
                        <option value="${shedLength.index}">${shedLength.index} cm</option>
                    </c:forEach>
                </select><br>
                <h6 class="font-weight-bold">Redskabsskur Sidebeklædning</h6>
                <select class="form-select" name="shedClothing" id="shedClothing" aria-label="Default select example">
                    <option selected value="0">Vælg Sidebeklædning:</option>
                    <c:forEach var="order" items="${requestScope.shedClothingList}" >
                        <option value="${order.material_id}">${order.name}</option>
                    </c:forEach>
                </select><br>
                <c:if test="${requestScope.error != null }">
                    <p style="color:red">
                            ${requestScope.error}
                    </p>
                </c:if>
                <button id="submit" type="submit" class="btn btn-primary btn-sm">Bestil tilbud</button>
            </form>
        </div>
        </c:if>

    </jsp:body>
</t:genericpage>