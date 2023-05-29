<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the logged in area
    </jsp:attribute>

    <jsp:attribute name="footer">
        Logged in area
    </jsp:attribute>

    <jsp:body>

        <h3>Here you can edit a material</h3>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <form method="post" action="UpdateMaterialServlet" class='card p-3 bg-light' style=" border: 2px solid navy; border-radius: 25px;">
                        <div class="w-35 p-3 rounded-lg" style="border:1px">
                            <label for="price" class="form-label"><strong>Ny Price:</strong></label><br>
                            <input type="number" id="price" name="price" class="form-control" required><br>
                            <div id="descriptionHelp" class="form-text">Udfyld den nye pris for materialet</div>
                        </div>
                        <br>
                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-primary" value="Opdater Pris">
                        </div>
                    </form>
                </div>
            </div>
        </div>

            </form>
        </div>

    </jsp:body>

</t:pagetemplate>