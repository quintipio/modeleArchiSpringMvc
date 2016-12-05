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

                    var res = searchViaAjax("");

                    jQuery('#searchText').on('input', function() {
                        event.preventDefault();
                        var toSend = $("#searchText").val();
                        var res = searchViaAjax(toSend);
                    });

                });

                function loadDatatable(data) {

                    var table = $('#tableResult');
                    if ( ! $.fn.DataTable.fnIsDataTable( table[0] ) ) {
                        $('#tableResult').DataTable( {
                            bProcessing : true,
                            bFilter : false,
                            bInfo : false,
                            bJQueryUI : true,
                            bLengthChange : false,
                            bDestroy : true,
                            bRetrieve : true,
                            bStateSave : true,
                            iDisplayLength : 10,
                            aaData: data,
                            aoColumns:[
                                {mData: "firstName"},
                                {mData: "lastName"},
                                {mData: "email"},
                                {mData: "ssoId"}
                            ]
                        } );
                    } else {
                        $('#tableResult').DataTable().fnClearTable();
                        $('#tableResult').DataTable().fnAddData(data);
                    }
                }

                function searchViaAjax(toSearch) {
                    var token = $('#csrfToken').val();
                    var header = $('#csrfHeader').val();
                    if(!toSearch) {
                        toSearch = "null";
                    }
                    $.ajax({
                        type : "POST",
                        contentType : "application/json",
                        url : "${pageContext.request.contextPath}/search/getResult",
                        data : toSearch,
                        dataType : 'json',
                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                            xhr.setRequestHeader(header, token);
                        },
                        timeout : 100000,
                        success : function(data) {
                            console.log("SUCCESS: ", data);
                            loadDatatable(data);
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
        <form:form>
            <input type="hidden" id="csrfToken" value="${_csrf.token}"/>
            <input type="hidden" id="csrfHeader" value="${_csrf.headerName}"/>

            <input type="text" id="searchText">

            <div class="panel-heading"><span class="lead">Résultat</span></div>
            <table class="table table-hover" id="tableResult">
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