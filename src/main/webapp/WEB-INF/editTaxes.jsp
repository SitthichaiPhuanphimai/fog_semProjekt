<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">
         Edit tax settings here
    </jsp:attribute>

    <jsp:attribute name="footer">
        Tax overview page
    </jsp:attribute>

    <jsp:body>
            <form action="UpdateMaterialServlet" method="post">
                <input type="hidden" name="id" value="${material.id}" />
                <label for="price">Price</label>
                <input type="number" id="price" name="price" value="${material.price}" />
                <input type="submit" value="Update">
            </form>

            <form action="AddNewMaterialServlet" method="post" >
                <div class="mb-3">
                    <label for="description" class="form-label"><strong>Description:</strong></label><br>
                    <input type="text" id="description" name="description" required="'required'"><br>
                    <div id="descriptionHelp" class="form-text>">Enter a description for the new material:</div>
                </div>
                <input type="submit" class="btn btn-primary" value="Submit">
            </form>


    </jsp:body>



</t:pagetemplate>