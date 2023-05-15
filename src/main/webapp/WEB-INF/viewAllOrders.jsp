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
                <th>Email</th>
                <th>Status</th>


            </tr>

            <c:forEach var="Order" items="${requestScope.ordersList}">
            <tr>
                <td> ${Order.id}</td>
                <td> ${Order.username}</td>
                <td> ${Order.status}</td>

            </tr>
            </c:forEach>

        </table>

    </jsp:body>

</t:pagetemplate>