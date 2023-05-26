<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:pagetemplate>


    <jsp:attribute name="header">
         Ordrebekræftelse
    </jsp:attribute>

    <jsp:attribute name="footer">
        Place Orders
    </jsp:attribute>

    <jsp:body>

        <div id="orderOverview" class="container mt-4">
            <h2>Opsummering af din ordre:</h2>
            <br>
            <h4>Du har valgt længden: ${sessionScope.uLength}</h4>
            <h4>Du har valgt bredden: ${sessionScope.uWidth}</h4>
            <br>

            <c:if test="${sessionScope.skur != null}">
                <h4>Tilkøb af skur = <strong>Ja</strong></h4>
            </c:if>

            <c:if test="${sessionScope.skur = null}">
                <h4>Tilkøb af skur = <strong>Nej</strong></h4>
            </c:if>

            <br>

            <h4> Den samlede pris for carport <fmt:formatNumber value="${sessionScope.totalPrice}" type="number"
                                                                minFractionDigits="2" maxFractionDigits="2"/> kr</h4>

            <br>
            <form action="Checkout" method="post">
                <input type="submit" name="action" class="btn btn-success" value="Acceptere">
                <input type="submit" name="action" class="btn btn-danger" value="Annuller">
            </form>
        </div>

    </jsp:body>

</t:pagetemplate>