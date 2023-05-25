<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Materialeliste
    </jsp:attribute>

    <jsp:attribute name="footer">
        Materialeliset - Admin
    </jsp:attribute>


    <jsp:body>
        <div >
            <h3>Listen over alle registredede materialer i systemet</h3>
        </div>

        <c:if test="${not empty successMessage}">
            <div class="success-message">${successMessage}</div>
        </c:if>


        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Beskrivelse</th>
                    <th scope="col">Pris per enhed</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Materiale type</th>
                    <th scope="col">Materiale længde</th>
                    <th scope="col">Handling</th>
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
                        <form action="UpdateMaterialServlet" method="get">
                            <input type="hidden" name="id" value="${material.id}">
                            <input type="submit" value="Rediger Pris" class="btn btn-primary">
                        </form>
<%--                        <a href="UpdateMaterialServlet?id=${material.id}" class="btn btn-primary">Edit Price</a>--%>
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

        <form action="AddNewMaterialServlet" method="get">
            <button type="submit" class="btn btn-primary" value="add">Tilføj Materiale</button>
        </form>
        <br>
        <form action="AdminHomePageServlet" method="get">
            <button type="submit" class="btn btn-primary">Tilbage til forside</button>
        </form>



    </jsp:body>
</t:pagetemplate>>
