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
            <input type="hidden" name="id" value="${material.id}" />
            <label for="price">Price</label>
            <input type="number" id="price" name="price" value="${material.price}" />
            <input type="submit" value="Skift Pris">
        </form>
        <br>

    </jsp:body>

</t:pagetemplate>