<%--
  Created by IntelliJ IDEA.
  User: Caner
  Date: 19-05-2023
  Time: 00:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">

        Stykliste

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>
        <div class="container">

            <table>
                <tr>
                    <th>OrderID</th>
                    <th>Beskrivelse</th>
                    <th>Længde</th>
                    <th>Enhed</th>
                    <th>Type</th>
                    <th>Antal</th>
                    <th>Pris pr.enhed</th>

                </tr>

                <c:forEach var="itemList" items="${requestScope.itemList}">
                    <tr>

                        <td> ${itemList.order_id}</td>
                        <td> ${itemList.description}</td>
                        <td> ${itemList.length}</td>
                        <td> ${itemList.unit}</td>
                        <td> ${itemList.type}</td>
                        <td> ${itemList.quantity}</td>
                        <td> ${itemList.price} DKK. </td>

                    </tr>
                </c:forEach>

            </table>
        </div>

    </jsp:body>

</t:pagetemplate>