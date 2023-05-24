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
        <div >
            <h3>Here's a list of all materials:</h3>
        </div>

        <table class="table table-dark table-hover">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price Per Unit in DKK</th>
                    <th scope="col">Unit</th>
                    <th scope="col">Material type</th>
                    <th scope="col">Material Length</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="material" items="${applicationScope.materialList}">
                <tr>
                    <th scope="row">${material.id}</th>
                    <td>${material.description}</td>
                    <td>${material.price}</td>
                    <td>${material.unit}</td>
                    <td>${material.type}</td>
                    <td>${material.length}</td>
                    <td>
                        <a href="UpdateMaterialServlet?id=${material.id}" class="btn btn-primary">Edit Price</a>
                        <form action="DeleteMaterialServlet" method="post">
                            <input type="hidden" name="id" value="${material.id}">
                            <input type="submit" value="Delete" class="btn btn-primary">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>

        <form action="AddNewMaterialServlet" method="post">
            <button type="submit" class="btn btn-primary" value="add">Add Material</button>
        </form>
        <br>
        <form action="RefreshMaterialsServlet" method="post">
            <button type="submit" class="btn btn-primary">Refresh Data</button>
        </form>


    </jsp:body>
</t:pagetemplate>>
