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

                    jQuery('#searchText').on('input', function() {

                        // Prevent the form from submitting via the browser.
                        event.preventDefault();

                        searchViaAjax();
                    });

                });

                function searchViaAjax() {
                    var token = $('#csrfToken').val();
                    var header = $('#csrfHeader').val();
                    $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "${pageContext.request.contextPath}/search/getResult",
                        data : $("#searchText").val(),
                        dataType : 'json',
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },
                        timeout : 100000,
                        success : function(data) {
                            console.log("SUCCESS: ", data);
                        },
                        error : function(e) {
                            console.log("ERROR: ", e);
                        },
                        done : function(e) {
                            console.log("DONE");
                        }
                    });
                }
        </script>
     </jsp:attribute>


    <jsp:body>
        <form:form method="POST" id="searchForm">
            <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
            <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

            <input type="text" id="searchText">

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