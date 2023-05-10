<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Velkommen til forsiden
    </jsp:attribute>

    <jsp:attribute name="footer">
        Velkommen til forsiden
    </jsp:attribute>

    <jsp:body>

        <h1>Fogs carporte </h1>

        <c:if test="${sessionScope.user != null}">
            <p>Du er logget ind som "${sessionScope.user.role}".</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <p>Du er ikke logget ind. Log ind her: <a
                    href="login.jsp">Login</a></p>
        </c:if>

    </jsp:body>

</t:pagetemplate>