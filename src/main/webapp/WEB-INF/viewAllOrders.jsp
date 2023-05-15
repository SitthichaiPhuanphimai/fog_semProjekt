<%--
  Created by IntelliJ IDEA.
  User: city
  Date: 12/05/2023
  Time: 12.13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dat.backend.model.entities.Order" %>


<t:pagetemplate>
    <jsp:attribute name="header">

        Orders List

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>


        <table>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Status</th>
            </tr>

            <tr>
                <td> ${requestScope.ordersList.get(0).id}</td>
                <td> ${requestScope.ordersList.get(0).username}</td>
                <td> ${requestScope.ordersList.get(0).status}</td>

            </tr>

        </table>

    </jsp:body>

</t:pagetemplate>