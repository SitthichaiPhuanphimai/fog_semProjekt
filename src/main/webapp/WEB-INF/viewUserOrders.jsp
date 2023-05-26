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
        <h2>Dine ordre</h2>
    </jsp:attribute>

    <jsp:attribute name="footer">
        Order history
    </jsp:attribute>

    <jsp:body>

        <c:forEach var="order" items="${orders}">
            <div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Ordre ID</th>
                        <th scope="col">Brugernavn</th>
                        <th scope="col">Status</th>
                        <th scope="col">Samlet pris</th>
                        <th>Stykliste</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.username}</td>
                        <td>${order.status}</td>
                        <td><fmt:formatNumber value="${order.totalPrice}" type="number" minFractionDigits="2" maxFractionDigits="2"/> DKK</td>
                        <td><c:if test="${order.status == 'Approved'}">
                            <form action="ViewOrderMaterials" method="get">
                                <input type="hidden" name="orderId" value="${order.id}">
                                <input class="btn btn-success btn-sm" type="submit" value="Hent stykliste">
                            </form>
                        </c:if></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>