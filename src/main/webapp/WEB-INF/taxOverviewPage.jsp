<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Hello ${sessionScope.user.role}! View all the different taxes here.
    </jsp:attribute>

    <jsp:attribute name="footer">
        Tax overview page
    </jsp:attribute>

    <jsp:body>
        <h1>Taxes</h1>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Value</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="tax" items="${applicationScope.taxList}">
                <tr>
                    <td>${tax.id}</td>
                    <td>${tax.name}</td>
                    <td>${tax.value}</td>
                    <td>
                        <form action="ViewTaxesServlet" method="post">
                            <input type="hidden" name="id" value="${tax.id}">
                            <input type="hidden" name="taxName" value="${tax.name}">
                            <input type="number" step="0.01" name="taxValue" value="${tax.value}">
                            <input type="submit" class="btn btn-primary" value="Update">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <form action="AdminHomePageServlet" method="get">
           <button type="submit" class="btn btn-primary">Tilbage til admin page</button>
        </form>

    </jsp:body>



</t:pagetemplate>