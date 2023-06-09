<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/fog-logo.png" height="100" width="100px;"
                     class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">

                    <c:if test="${sessionScope.user.role == 'customer'}">
                        <form action="ServletOrderPlacement">
                            <input type="submit" value="Bestil din carport"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                        <form action="ViewUserOrders" method="get">
                            <input type="submit" value="Vis dine ordre"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.user.role =='admin'}">
                        <form action="viewOrdersServlet" method="post">
                            <input type="submit" value="Se alle ordre"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                        <form action="ViewMaterialsServlet" method="get">
                            <input type="submit" value="Vis alle materialer"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                        <form action="ViewTaxesServlet" method="get">
                            <input type="submit" value="Ændre momssatser"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                    </c:if>


                    <c:if test="${sessionScope.user == null }">

                        <!-- Login button trigger modal -->
                        <button type="button" class="btn btn-success" data-bs-toggle="modal"
                                data-bs-target="#exampleModal">
                            Login
                        </button>
                        &nbsp;
                        <form action="signup" method="get">
                            <input class="btn btn-primary" type="submit" value="Opret bruger"/>
                        </form>

                        <!-- Modal for login -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Login / opret bruger</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <h3>Log venligst ind for at fortsætte</h3>
                                        <form action="login" method="post">
                                            <label for="username">Brugernavn: </label><br>
                                            <input type="text" id="username" name="username" required/><br>
                                            <label for="password">Adgangskode: </label><br>
                                            <input type="password" id="password" name="password" required/><br><br>
                                            <input type="submit" class="btn btn-success" value="Log in"/>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Luk
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:if>

                    <c:if test="${sessionScope.user != null }">

                        <form action="logout" method="get">
                            <input type="submit" value="Log ud"
                                   style="background:none;border: none;color: black;cursor: pointer;">
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1>
        <jsp:invoke fragment="header"/>
    </h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container mt-3">
    <hr/>
    <div class="row mt-4">
        <div class="col">
            Fog Carport Creator v. 1.0.0
        </div>
        <div class="col">
            <jsp:invoke fragment="footer"/>
            <p>Lyngby</p>
        </div>
        <div class="col">
            Copyright &copy; 2023<br/>
        </div>
    </div>

</div>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>