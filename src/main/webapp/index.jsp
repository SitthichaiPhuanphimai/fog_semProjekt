<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Johannes Fog - Carport
    </jsp:attribute>

    <jsp:attribute name="footer">
        Johannes Fog Trælast A/S
    </jsp:attribute>

    <jsp:body>
        <style>
            div {
                text-align: center;
            }
        </style>
        <div class="container" style="align-content: center">
            <h3>Velkommen</h3>
        </div>

        <c:if test="${sessionScope.user != null}">
            <p>Du er logget ind som "${sessionScope.user.role}".</p>
        </c:if>

        <p>Kreer din carport på under 5 minutter</p>
        <div class="spinner-grow text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
        <div class="spinner-grow text-secondary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
        <div class="spinner-grow text-success" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>

        <span class="placeholder col-12 bg-primary"></span>
        <span class="placeholder col-12 bg-secondary"></span>
        <br><br>

        <iframe width="560" height="315" src="https://www.youtube.com/embed/IGdZCDEa9no"
                title="YouTube video player"
                frameborder="0"
                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                allowfullscreen></iframe>

        <br><br><br>
        <div class="container">
            <div class="row">
                <div class="col">
                    <img src="images/carport1.jpg" class="rounded float-start">
                </div>
                <div class="col">
                    <img src="images/carport3.jpg" class="rounded mx-auto d-block">
                </div>
                <div class="col">
                    <img src="images/carport2.jpg" class="rounded float-end">
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>