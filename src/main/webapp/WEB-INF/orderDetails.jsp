<%--
  Created by IntelliJ IDEA.
  User: Caner
  Date: 15-05-2023
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:attribute name="header">
         Order details
    </jsp:attribute>

  <jsp:attribute name="footer">

    </jsp:attribute>

  <jsp:body>
    <h2>Order Details</h2>
    <table>
      <tr>
        <th>Order ID</th>
        <th>Username</th>
        <th>Status</th>
      </tr>
      <tr>
        <td>${sessionScope.order.id}</td>
        <td>${sessionScope.user.username}</td>
        <td>${sessionScope.order.status}</td>
      </tr>
    </table>
  </jsp:body>

</t:pagetemplate>