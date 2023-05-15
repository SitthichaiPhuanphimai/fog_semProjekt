<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the logged in area
    </jsp:attribute>

    <jsp:attribute name="footer">
        Logged in area
    </jsp:attribute>

    <jsp:body>

        <h3>Here you can edit a material</h3>

        <form action="UpdateMaterialServlet" method="post">
                <%--
                <div class="mb-2">
                    <input type="hidden" name="id" value="${material.id}" />
                    <label for="price" class="form-label">New Price:</label>
                    <input type="number" class="form-control" id="price" aria-describedby="priceHelp">
                    <div id="priceHelp" class="form-text">Enter a new price for the material:</div>
                    <button type="submit" class="btn btn-primary">Update Price</button>
                </div>
                --%>

            <input type="hidden" name="id" value="${material.id}" />
            <label for="price">Price</label>
            <input type="number" id="price" name="price" value="${material.price}" />
            <input type="submit" value="Update">
        </form>
        <br>
        <br>
        <h3>Here you can add a new material to the database</h3>
        <%--<form method="post" action="AddNewMaterialServlet">
            <label for="description">Description:</label><br>
            <input type="text" id="description" name="description"><br>
            <label for="price">Price:</label><br>
            <input type="text" id="price" name="price"><br>
            <label for="unitId">Unit ID:</label><br>
            <input type="text" id="unitId" name="unitId"><br>
            <label for="materialType">Material Type:</label><br>
            <input type="text" id="materialType" name="materialType"><br>
            <label for="materialLength">Material Length:</label><br>
            <input type="text" id="materialLength" name="materialLength"><br>
            <input type="submit" value="Submit">
        </form>--%>

    </jsp:body>

</t:pagetemplate>