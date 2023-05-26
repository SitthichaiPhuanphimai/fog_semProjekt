<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen, du er nu logget ind
    </jsp:attribute>

    <jsp:attribute name="footer">
        Du er nu logget ind
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.user != null}">
            <p>Du er nu logget ind med rollen som "${sessionScope.user.role}".</p>

            <form action="ServletOrderPlacement" method="get">
                <p>Byg og bestil din egen carport!</p>
               <input class="btn btn-success" type="submit" value="Byg nu">
            </form>

            <br>

            <form action="ViewUserOrders" method="get">
                <p>Se dine tidligere, og igangværende ordrer</p>
                <input class="btn btn-primary" type="submit" value="Se dine ordre">
            </form>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind endnu. Du kan logge ind her: <a
                    href="../login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>