<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>


    <jsp:attribute name="header">
         Bestil din carport
    </jsp:attribute>

    <jsp:attribute name="footer">
        Placer ordre
    </jsp:attribute>

    <jsp:body>

        <!-- Form for orders -->
        <div class="container-sm">
            <form action="ServletOrderPlacement" method="post">
                <input class="form-control" type="number" name="length" placeholder="Længde" min="0" step="0.01"
                       required pattern="{1,100}" title="Det ønskede mål er ikke muligt">
                <br>
                <br>
                <input class="form-control" type="number" name="width" placeholder="Bredde" min="0" step="0.01" required
                       pattern="{1,100}" title="Det ønskede mål er ikke muligt">
                <br>
                <br>
                <p>Skal carporten have et skur tilknyttet</p>
                <select class="btn btn-secondary dropdown-toggle" name="skur" id="skur">
                    <option value="ja">Ja</option>
                    <option value="nej">Nej</option>
                </select>
                <br>
                <br>
                <input class="btn btn-success" type="submit" name="order" value="Bestil">
            </form>
        </div>

    </jsp:body>

</t:pagetemplate>

