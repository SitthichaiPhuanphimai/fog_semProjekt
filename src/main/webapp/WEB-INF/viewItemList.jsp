<%--
  Created by IntelliJ IDEA.
  User: city
  Date: 18/05/2023
  Time: 12.40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>

    <jsp:attribute name="header">

        Materialeliste

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>


        <table>
            <tr>
                <th>OrderID</th>
                <th>Beskrivelse</th>
                <th>Længde</th>
                <th>Enhed</th>
                <th>Type</th>
                <th>Antal</th>
                <th>Price per Unit</th>

            </tr>
            <c:forEach var="itemList" items="${requestScope.itemList}">
                <tr>

                    <td> ${itemList.order_id}</td>
                    <td> ${itemList.description}</td>
                    <td> ${itemList.length}</td>
                    <td> ${itemList.unit}</td>
                    <td> ${itemList.type}</td>
                    <td> ${itemList.quantity}</td>
                    <td> ${itemList.price} KR. </td>

                </tr>
            </c:forEach>

        </table>


    </jsp:body>

</t:pagetemplate>
