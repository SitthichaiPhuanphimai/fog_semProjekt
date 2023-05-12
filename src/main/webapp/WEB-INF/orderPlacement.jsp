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

<<<<<<< Updated upstream
        <!-- Form for orders -->
        <div id="body" class="container mt-4" style="min-height: 400px;">
            <form action="ServletOrderPlacement" method="post">
                <p>Vælg den ønskede <strong>længde</strong> til din carport</p>
                <select class="form-select form-select-lg mb-3" name="length" id="length"
                        aria-label=".form-select-lg example">
                    <option value="0.0" disabled selected>Vælg længde</option>
                    <option value="2.40">2.40</option>
                    <option value="2.70">2.70</option>
                    <option value="3.00">3.00</option>
                    <option value="3.30">3.30</option>
                    <option value="3.60">3.60</option>
                    <option value="3.90">3.90</option>
                    <option value="4.20">4.20</option>
                    <option value="4.50">4.50</option>
                    <option value="4.80">4.80</option>
                    <option value="5.10">5.10</option>
                    <option value="5.40">5.40</option>
                    <option value="5.70">5.70</option>
                    <option value="6.00">6.00</option>
                    <option value="6.30">6.30</option>
                    <option value="6.60">6.60</option>
                    <option value="6.90">6.90</option>
                    <option value="7.20">7.20</option>
                    <option value="7.50">7.50</option>
                    <option value="7.80">7.80</option>
                </select>
                <br>
                <p>Vælg den ønskede <strong>bredde</strong> til din carport</p>
                <select class="form-select form-select-lg mb-3" name="width" id="width"
                        aria-label=".form-select-lg example">
                    <option value="0.0" disabled selected>Vælg bredde</option>
                    <option value="2.40">2.40</option>
                    <option value="2.70">2.70</option>
                    <option value="3.00">3.00</option>
                    <option value="3.30">3.30</option>
                    <option value="3.60">3.60</option>
                    <option value="3.90">3.90</option>
                    <option value="4.20">4.20</option>
                    <option value="4.50">4.50</option>
                    <option value="4.80">4.80</option>
                    <option value="5.10">5.10</option>
                    <option value="5.40">5.40</option>
                    <option value="5.70">5.70</option>
                    <option value="6.00">6.00</option>
                </select>
                <br>
                <br>
                <p>Skal carporten have et skur tilknyttet</p>
                <select class="form-select form-select-lg mb-3" name="skur" id="skur" aria-label=".form-select-lg example">
                    <option value="ja">Ja</option>
                    <option value="nej">Nej</option>
                </select>
                <br>
                <br>
                <input class="btn btn-success" type="submit" name="order" value="Bestil" style="align-content: center">
            </form>

        </div>

=======

        <div id="body" class="container mt-4" style="min-height: 400px;">
        <form action="ServletOrderPlacement" method="post">
            <p>Vælg den ønskede <strong>længde</strong> til din carport</p>
            <select class="form-select form-select-lg mb-3" name="length" id="length"
                    aria-label=".form-select-lg example" required>
                <%--<option value="0.0" disabled selected>Vælg længde</option>--%>
                <option value="" > Vælg en længde:</option>
                <option value="2.40">2.40</option>
                <option value="2.70">2.70</option>
                <option value="3.00">3.00</option>
                <option value="3.30">3.30</option>
                <option value="3.60">3.60</option>
                <option value="3.90">3.90</option>
                <option value="4.20">4.20</option>
                <option value="4.50">4.50</option>
                <option value="4.80">4.80</option>
                <option value="5.10">5.10</option>
                <option value="5.40">5.40</option>
                <option value="5.70">5.70</option>
                <option value="6.00">6.00</option>
                <option value="6.30">6.30</option>
                <option value="6.60">6.60</option>
                <option value="6.90">6.90</option>
                <option value="7.20">7.20</option>
                <option value="7.50">7.50</option>
                <option value="7.80">7.80</option>
            </select>
            <br>
            <p>Vælg den ønskede <strong>bredde</strong> til din carport</p>
            <select class="form-select form-select-lg mb-3" name="width" id="width"
                    aria-label=".form-select-lg example" required>
                <%--<option value="0.0" disabled selected>Vælg bredde</option>--%>
                <option value="" >Vælg en bredde:</option>
                <option value="2.40">2.40</option>
                <option value="2.70">2.70</option>
                <option value="3.00">3.00</option>
                <option value="3.30">3.30</option>
                <option value="3.60">3.60</option>
                <option value="3.90">3.90</option>
                <option value="4.20">4.20</option>
                <option value="4.50">4.50</option>
                <option value="4.80">4.80</option>
                <option value="5.10">5.10</option>
                <option value="5.40">5.40</option>
                <option value="5.70">5.70</option>
                <option value="6.00">6.00</option>
            </select>
            <br>
            <br>
            <p>Skal carporten have et skur tilknyttet</p>
            <select class="form-select form-select-lg mb-3" name="skur" id="skur" aria-label=".form-select-lg example">
                <option value="ja">Ja</option>
                <option value="nej" selected>Nej</option>
            </select>
            <br>
            <br>
            <input class="btn btn-success" type="submit" name="order" value="Bestil" style="align-content: center">
        </form>

>>>>>>> Stashed changes
    </jsp:body>

</t:pagetemplate>

