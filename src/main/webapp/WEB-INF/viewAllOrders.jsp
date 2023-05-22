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

        <table class="table table-striped">
            <c:forEach var="Order" items="${requestScope.ordersList}">
                <tr>
                    <thead>
                    <tr>
                        <th>Ordre ID</th>
                        <th>Brugernavn</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${Order.id}</td>
                        <td>${Order.username}</td>
                        <td>${Order.status}</td>
                    </tr>
                    <td>
                        <form name="skift" action="viewOrdersServlet" method="post">
                            <input type="hidden" name="orderId" value="${Order.id}">
                            <select name="status">
                                <option value="Approved" ${"approved".equals(Order.status) ? 'selected' : ''}>Godkendt
                                </option>
                                <option value="Pending" ${"pending".equals(Order.status) ? 'selected' : ''}>Afventer
                                </option>
                                <option value="Declined" ${"declined".equals(Order.status) ? 'selected' : ''}>Afvist
                                </option>
                            </select>
                            <input style="background-color: #5cb85c; color: white; border-color: #5cb85c" type="submit" name="skift" value="Skift status">
                        </form>

                    </td>
                    <td>
                        <form name="Slet" action="deleteOrderServlet" method="post">
                            <input type="hidden" name="orderId" value="${Order.id}">
                            <input style="background-color: orangered; color: white; border-color: orangered" onclick="confirm()" id="Slet" type="submit" name="Slet"
                                   value="Slet ordre"></form>
                    </td>
                    <td>
                        <form name="getList" action="getItemListServlet" method="post">
                            <input type="hidden" name="orderId" value="${Order.id}">
                            <input style="background-color: dodgerblue; color: white; border-color: dodgerblue" type="submit" name="runServlet" value="Hent materialeliste">
                        </form>
                    </td>
                    </tbody>

                </tr>
            </c:forEach>

        </table>

    </jsp:body>

</t:pagetemplate>