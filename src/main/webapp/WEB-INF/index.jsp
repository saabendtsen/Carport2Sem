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

        <div>
            <h2>QUICK-BYG TILBUD - CARPORT</h2>
            <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram.</p>
            <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.
            Standardbyggevejledning medfølger ved bestilling.</p>
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
                    <c:forEach var="clothing" items="${requestScope.carportClothingList}" >
                        <option value="${clothing.material_id}">${clothing.name}</option>
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
                <select class="form-select" name="shedRoof" id="shedRoof" aria-label="Default select example">
                    <option selected value="0">Vælg Sidebeklædning:</option>
                    <c:forEach var="clothing" items="${requestScope.shedClothingList}" >
                        <option value="${clothing.material_id}">${clothing.name}</option>
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

    </jsp:body>
</t:genericpage>