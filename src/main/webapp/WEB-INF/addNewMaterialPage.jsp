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
        <form method="post" action="AddNewMaterialServlet">
            <div class="mb-3">
                <label for="description" class="form-label"><strong>Description:</strong></label><br>
                <input type="text" id="description" name="description" required><br>
                <div id="descriptionHelp" class="form-text">Enter a description for the new material:</div>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label"><strong>Price:</strong></label><br>
                <input type="number" id="price" name="price" required><br>
                <div id="priceHelp" class="form-text">Enter the price for the new material:</div>
            </div>

            <div class="mb-3">
                <label for="unitId" class="form-label"><strong>Unit:</strong></label><br>
                <select id="unitId" name="unitId" required>
                    <option selected>Vælg en enhed:</option>
                    <c:forEach var="unitDefault" items="${requestScope.unitTypes}">
                        <option value="${unitDefault.unitId}">${unitDefault.unitName}</option>
                    </c:forEach>
                </select>
                <div id="unitIdHelp" class="form-text">Choose a unit for the new material:</div>
            </div>

            <div class="mb-3">
                <label for="typeId" class="form-label"><strong>Material Type:</strong></label><br>
                <select id="typeId" name="typeId" required>
                    <option selected>Vælg en type:</option>
                    <c:forEach var="type" items="${requestScope.types}">
                        <option value="${type.typeId}">${type.typeName}</option>
                    </c:forEach>
                </select>
                <div id="typeIdHelp" class="form-text">Choose a type for the new material:</div>
            </div>

            <div class="mb-3">
                <label for="lengthId" class="form-label"><strong>Material Length:</strong></label><br>
                <select id="lengthId" name="lengthId" required>
                    <option selected>Vælg en længde: </option>
                    <c:forEach var="lengthType" items="${requestScope.lengthTypes}">
                        <option value="${lengthType.lengthId}">${lengthType.length}</option>
                    </c:forEach>
                </select>
                <div id="lengthIdHelp" class="form-text">Choose a length for the new material:</div>
            </div>

            <input type="submit" class="btn btn-primary" value="Tilføj Nyt Materiale">
        </form>
    </jsp:body>
</t:pagetemplate>
