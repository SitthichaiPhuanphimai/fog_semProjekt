<%--
  Created by IntelliJ IDEA.
  User: Caner
  Date: 08-05-2023
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Opret profil
    </jsp:attribute>

    <jsp:attribute name="footer">
            login
    </jsp:attribute>

    <jsp:body>

        <h3>Du kan oprette en profil her</h3>

        <form action="signup" method="post">
            <div class="mb-3"><input class="form-control" type="text" name="username" placeholder="username"></div>
            <div class="mb-3"><input class="form-control" type="password" name="password" placeholder="password"></div>
            <div class="mb-3"><input class="form-control" type="password" name="confirmpassword" placeholder="confirm password"></div>
            <div class="mb-3"><button class="btn btn-primary d-block w-100" type="submit">Opret profil</button></div>
        </form>
        ${sessionScope.passwordMismatch}
        ${sessionScope.userExists}

    </jsp:body>
</t:pagetemplate>