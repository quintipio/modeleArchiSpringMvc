<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:attribute name="title">
      Recherche d'utilisateurs
    </jsp:attribute>


    <jsp:attribute name="codeJava">
            <script>
                jQuery(document).ready(function($) {

                    $("#searchForm").submit(function(event) {

                        // Disble the search button
                        enableSearchButton(false);
                        alert('aa');
                        // Prevent the form from submitting via the browser.
                        event.preventDefault();

                        searchViaAjax();

                    });

                });

                function searchViaAjax() {

                    alert('aab');
                    $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "${home}/search/getResult",
                        data : $("#searchText").val(),
                        dataType : 'json',
                        timeout : 100000,
                        success : function(data) {
                            console.log("SUCCESS: ", data);
                            $("#searchText").val(data)
                        },
                        error : function(e) {
                            console.log("ERROR: ", e);
                            display(e);
                        },
                        done : function(e) {
                            console.log("DONE");
                            enableSearchButton(true);
                        }
                    });
                }

                function enableSearchButton(flag) {
                    $("#searchButton").prop("disabled", flag);
                }

        </script>
     </jsp:attribute>


    <jsp:body>
        <form:form method="POST" id="searchForm">
            <input type="text" id="searchText">
            <input type="button" value="Rechercher" id="searchButton"/>

            <div class="panel-heading"><span class="lead">Résultat</span></div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Nom d'utilisateur</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${user.ssoId}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </div>



        </form:form>
    </jsp:body>

</t:template>