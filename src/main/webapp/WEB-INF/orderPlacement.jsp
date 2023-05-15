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

    <!-- Form for orders -->
    <div id="body" class="container mt-4" style="min-height: 400px;">
        <form action="ServletOrderPlacement" method="post">
            <input type="number" name="length" placeholder="Length" min="0" step="0.01" required="'required'" pattern="{1,100}" title="Det ønskede mål er ikke muligt">
            <br>
            <br>
            <input type="number" name="width" placeholder="Bredde" min="0" step="0.01" required="'required'" pattern="{1,100}" title="det ønskede mål er ikke muligt">
            <br>
            <br>
            <p>Skal carporten have et skur tilknyttet</p>
                <select name="skur" id="skur">
                    <option value="ja">ja</option>
                    <option value="nej">nej</option>
                </select>
            <br>
            <br>
            <input type="submit" name="order" value="Bestil">
        </form>
    </div>

    </jsp:body>

</t:pagetemplate>

