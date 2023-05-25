<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Add a new material here
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <form method="post" action="AddNewMaterialServlet" class = 'card p-3 bg-light' style="border:2px navy">
                <div class="w-35 p-3 rounded" style="border:1px solid black">
                    <label for="description" class="form-label"><strong>Beskrivelse:</strong></label><br>
                    <input type="text" id="description" name="description" required><br>
                    <div id="descriptionHelp" class="form-text">Udfyld en beskrivelse til det nye materiale.</div>
                </div>
                <br>
                <div class="w-35 p-3 rounded" style="background-color: #ffff; border:1px solid black" >
                    <label for="price" class="form-label"><strong>Pris:</strong></label><br>
                    <input type="number" id="price" name="price" required><br>
                    <div id="priceHelp" class="form-text">Udfyld prisen for det nye materiale</div>
                </div>
                <br>
                <div class="w-30 p-3 rounded" style="border:1px solid black">
                    <label for="unitId" class="form-label"><strong>Enhed:</strong></label><br>
                    <select id="unitId" name="unitId" required class="form-select" aria-label="Default select example">
                        <option selected>Vælg en enhed:</option>
                        <c:forEach var="unitDefault" items="${requestScope.unitTypes}">
                            <option value="${unitDefault.unitId}">${unitDefault.unitName}</option>
                        </c:forEach>
                    </select>
                    <div id="unitIdHelp" class="form-text">Vælg den relevante enhed for materialet.</div>
                </div>
                <br>
                <div class="w-30 p-3 rounded" style="background-color: #ffff; border:1px solid black">
                    <label for="typeId" class="form-label"><strong>Materiale Type:</strong></label><br>
                    <select id="typeId" name="typeId" required class="form-select" aria-label="Default select example">
                        <option selected>Vælg en type:</option>
                        <c:forEach var="type" items="${requestScope.types}">
                            <option value="${type.typeId}">${type.typeName}</option>
                        </c:forEach>
                    </select>
                    <div id="typeIdHelp" class="form-text">Vælg materiale typen for det nye materiale.</div>
                </div>
                <br>
                <div class="w-30 p-3 rounded" style="border:1px solid black">
                    <label for="lengthId" class="form-label"><strong>Materiale Længde:</strong></label><br>
                    <select id="lengthId" name="lengthId" required class="form-select" aria-label="Default select example">
                        <option selected>Vælg en længde: </option>
                        <c:forEach var="lengthType" items="${requestScope.lengthTypes}">
                            <option value="${lengthType.lengthId}">${lengthType.length}</option>
                        </c:forEach>
                    </select>
                    <div id="lengthIdHelp" class="form-text">Vælg en længde for det nye materiale. (hvis ikke det er relevant, kan du vælge 1)</div>
                </div>
                <br>
                <div class="span2" style="align-content: center">
                    <input type="submit" class="btn btn-primary" value="Tilføj Nyt Materiale">
                </div>
            </form>
        </div>

    </jsp:body>
</t:pagetemplate>
