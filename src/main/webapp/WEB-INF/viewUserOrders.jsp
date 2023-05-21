<%--
  Created by IntelliJ IDEA.
  User: Caner
  Date: 19-05-2023
  Time: 00:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        <h2>Your orders</h2>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Order history
    </jsp:attribute>

    <jsp:body>

        <c:forEach var="order" items="${orders}">
            <div>
                <p>Ordre id: ${order.id}</p>
                <p>Brugernavn: ${order.username}</p>
                <p>Status: ${order.status}</p>
                <p>Samlet pris: <fmt:formatNumber value="${order.totalPrice}" type="number" minFractionDigits="2" maxFractionDigits="2"/> DKK</p>

                <c:if test="${order.status == 'Approved'}">
                    <form action="ViewOrderMaterials" method="get">
                        <input type="hidden" name="orderId" value="${order.id}">
                        <input type="submit" value="Hent stykliste">
                    </form>
                </c:if>

            </div>
            <hr>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>