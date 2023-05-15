<%--
  Created by IntelliJ IDEA.
  User: Caner
  Date: 13-05-2023
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dat.backend.model.entities.Item" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item List</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <h1>Item List</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Length</th>
            <th>Price</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${itemList}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.length} m</td>
                <td>${item.price} kr. pr.m</td>
                <td>${item.type}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
