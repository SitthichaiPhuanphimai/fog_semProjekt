<%--
  Created by IntelliJ IDEA.
  User: city
  Date: 12/05/2023
  Time: 08.40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>


<t:pagetemplate>
    <jsp:attribute name="header">

        Velkommen admin

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <form action="viewOrdersServlet" method="post">
            <button style="border-radius: 8px" type="submit">Se alle ordre</button>
        </form>



    </jsp:body>

</t:pagetemplate>