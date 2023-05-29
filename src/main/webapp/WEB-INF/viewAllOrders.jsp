<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
            Ordreliste
    </jsp:attribute>
    <jsp:attribute name="footer"></jsp:attribute>

    <jsp:body>

        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th>Ordre ID</th>
                <th>Brugernavn</th>
                <th>Status</th>
                <th>Samlet pris</th>
                <th>Actions</th>
            </tr>
            </thead>

            <c:forEach var="Order" items="${requestScope.ordersList}">
                <tbody>
                <tr>
                    <td>${Order.id}</td>
                    <td>${Order.username}</td>
                    <td>${Order.status}</td>
                    <td><fmt:formatNumber value="${Order.totalPrice}" type="number" minFractionDigits="2"
                                          maxFractionDigits="2"/> DKK
                    </td>
                    <td>
                        <div class="row">
                            <div class="col-4">
                                <form name="skift" action="viewOrdersServlet" method="post">
                                    <input type="hidden" name="orderId" value="${Order.id}">
                                    <select class="form-control form-control-sm mb-2" name="status">
                                        <option value="Approved" ${"approved".equals(Order.status) ? 'selected' : ''}>Godkendt</option>
                                        <option value="Pending" ${"pending".equals(Order.status) ? 'selected' : ''}>Afventer</option>
                                        <option value="Declined" ${"declined".equals(Order.status) ? 'selected' : ''}>Afvist</option>
                                    </select>
                                    <input class="btn btn-sm btn-success mb-2" type="submit" name="skift" value="Skift status">
                                </form>
                            </div>
                            <div class="col-4">
                                <form name="Slet" action="deleteOrderServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this order?')">
                                    <input type="hidden" name="orderId" value="${Order.id}">
                                    <input class="btn btn-sm btn-danger mb-2" type="submit" name="Slet" value="Slet ordre">
                                </form>
                            </div>
                            <div class="col-4">
                                <form name="getList" action="getItemListServlet" method="post">
                                    <input type="hidden" name="orderId" value="${Order.id}">
                                    <input class="btn btn-sm btn-primary" type="submit" name="runServlet" value="Hent materialeliste">
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </c:forEach>

        </table>

    </jsp:body>

</t:pagetemplate>