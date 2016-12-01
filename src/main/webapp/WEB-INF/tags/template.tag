<%@tag description="Page modele" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@attribute name="title" fragment="true" %>
<%@attribute name="codeJava" fragment="true" required="false" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><jsp:invoke fragment="title"/></title>
        <link href="<c:url value='/webjars/bootstrap/3.3.5/css/bootstrap.min.css' />" rel="stylesheet"/>
        <link href="<c:url value='/static/css/style.css' />" rel="stylesheet"/>
    </head>

    <body>
        <div class="container">
            <header class="row">
                <div class="row">
                    <div class="col-md-12">
                        Entête du site
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <nav class="navbar navbar-inverse">
                            <div class="container-fluid">
                                <div class="row">
                                    <ul class="nav navbar-nav col-sm-6">
                                        <li><a href="<c:url value='/' />">Acceuil</a></li>
                                    </ul>
                                    <sec:authorize access="isAuthenticated()">
                                    <div class="col-lg-offset-2 col-sm-4 header-right">
                                       Bonjour  ${loggedinuser} <a href="<c:url value="/logout"/>">Se déconnecter</a>
                                    </div>
                                    </sec:authorize>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </header>


            <div class="row">
                <aside class="col-sm-2 menu">
                    Menu
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <ul>
                        <li><a href="<c:url value='/list'/> ">Liste des comptes</a></li>
                    </ul>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_DBA','ROLE_USER')">
                    <ul>
                        <li><a href="<c:url value='/search'/> ">Recherche</a></li>
                        <li><a href="<c:url value='/update'/> ">Mon compte</a></li>
                    </ul>
                    </sec:authorize>
                    <ul>
                        <li><a href="<c:url value='/create'/> ">Créer un compte</a></li>
                    </ul>
                </aside>

                <section class="col-sm-offset-1 col-sm-9">
                    <div class="row">
                        <article class="col-md-12">
                            <jsp:doBody />
                        </article>
                    </div>
                </section>
            </div>

            <div class="clearfix visible-sm-block"></div>

            <footer class="row">
                <div class="col-md-12">
                    <ul>
                        <li><a>Lien 1</a></li>
                        <li><a>Lien 2</a></li>
                        <li><a>Lien 3</a></li>
                    </ul>
                </div>
            </footer>
        </div>
    </body>
    <script src="<c:url value='/webjars/jquery/3.1.1/jquery.min.js'/>"  type="text/javascript"></script>
    <script src="<c:url value='/webjars/bootstrap/3.3.5/js/bootstrap.min.js'/>" type="text/javascript"></script>
    <jsp:invoke fragment="codeJava"/>
</html>