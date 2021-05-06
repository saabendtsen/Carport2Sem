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
            <h2>Carport test page</h2>

            <form id="form" method="get" action="${pageContext.request.contextPath}">
                Carport Bredde
                <select class="form-select" name="carportWidth" id="carportWidth" aria-label="Default select example">
                    <option selected>Vælg bredde:</option>
                    <c:forEach begin="240" end="750" step="30" var="carportWidth" varStatus="carportWidth">
                        <option value="${carportWidth.index}">${carportWidth.index} cm</option>
                    </c:forEach>
                </select><br>
                <p class="font-weight-bold">Carport Længde</p>
                <select class="form-select" name="carportLength" id="carportLength" aria-label="Default select example">
                    <option selected>Vælg længde:</option>
                    <c:forEach begin="240" end="780" step="30" var="carportLength" varStatus="carportLength">
                        <option value="${carportLength.index}">${carportLength.index} cm</option>
                    </c:forEach>
                </select><br>
                <hr>
                Carport Tag
                <select class="form-select" name="carportRoof" id="carportRoof" aria-label="Default select example">
                    <option selected>Vælg tag:</option>
                    <option value="supertag">nogle stumper træ</option>
                </select><br>
                <hr>
                Redskabsrums Bredde
                <select class="form-select" name="shedWidth" id="shedWidth" aria-label="Default select example">
                    <option selected value="null">Ønsker ikke redskabsrum</option>
                    <c:forEach begin="210" end="720" step="30" var="shedWidth" varStatus="shedWidth">
                        <option value="${shedWidth.index}">${shedWidth.index} cm</option>
                    </c:forEach>
                </select><br>
                Redskabsrums Længde
                <select class="form-select" name="shedLength" id="shedLength" aria-label="Default select example">
                    <option selected value="null">Ønsker ikke redskabsrum</option>
                    <c:forEach begin="150" end="690" step="30" var="shedLength" varStatus="shedLength">
                        <option value="${shedLength.index}">${shedLength.index} cm</option>
                    </c:forEach>
                </select><br>
                <button id="submit" type="submit" class="btn btn-primary btn-sm">Send Forespørgsel</button>
            </form>

        </div>

    </jsp:body>
</t:genericpage>