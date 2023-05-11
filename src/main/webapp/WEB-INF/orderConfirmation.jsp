<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>


    <jsp:attribute name="header">
         Order placement site
    </jsp:attribute>

    <jsp:attribute name="footer">
        Place Orders
    </jsp:attribute>

    <jsp:body>

        <div id="orderOverview" class="container mt-4">
            <h2>Din Order inkluderer: </h2>
            <br>
            <h3> Du har valgt Højden: ${sessionScope.uLength}</h3> <br>
            <h3> Du har valgt bredden: ${sessionScope.uWidth}</h3> <br>
            <c:if test="${sessionScope.skur != null}">
                <h3> Din carport kommer med et skur </h3>
            </c:if>
            <c:if test="${sessionScope.skur = null}">
                <h3> Carporten kommer ikke med et skur <h3>
            </c:if>
        </div>

    </jsp:body>

</t:pagetemplate>