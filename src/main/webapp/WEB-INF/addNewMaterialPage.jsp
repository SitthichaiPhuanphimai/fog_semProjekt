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
        <form method="get" action="TestServlet">
            <div class="mb-3">
                <label for="description" class="form-label"><strong>Description:</strong></label><br>
                <input type="text" id="description" name="description" required><br>
                <div id="descriptionHelp" class="form-text>">Enter a description for the new material:</div>
            </div>
                <label for="price" class="form-label"><strong>Price:</strong></label><br>
                <input type="number" id="price" name="price" required><br>
                <div id="priceHelp" class="form-text>">Enter the price for the new material:</div>
            <div class="mb-3">
                <label for="unitId" class="form-label" ><strong>Unit ID:</strong> </label><br>
                <input type="number" id="unitId" name="unitId"required><br>
                <div id="unitIDHelp" class="form-text>">Enter the unitID number:</div>
            </div>
            <div class="mb-3">
                <label for="materialType" class="form-label" ><strong>Material Type:</strong></label><br>
                <input type="number" id="materialType" name="materialType" required><br>
                <div id="materialTypeHelp" class="form-text>">Enter the material type ID number:</div>
            </div>
            <div class="mb-3">
                <label for="materialLength" class="form-label" ><strong>Material Length:</strong></label><br>
                <input type="number" id="materialLength" name="materialLength" required><br>
                <div id="materialLengthHelp" class="form-text>">Enter the length for the new material:</div>
            </div>
            <input type="submit" class="btn btn-primary" value="Submit">
        </form>
    </jsp:body>
</t:pagetemplate>