<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
      Gestion d'un compte
    </jsp:attribute>
    <jsp:body>
        <form:form method="POST" modelAttribute="user" class="form-horizontal"> <!-- form:from ajotue automatiquement l'input pour le csrf -->
            <form:input type="hidden" path="id" id="id"/>
            <div class="generic-container">
                <div class="row">
                    <div class="form-group col-md-12">
                        <c:if test="${!edit}">
                            <label class="col-md-3 control-label" for="ssoId">Nom d'utilisateur </label>
                        </c:if>
                        <div class="col-md-7">
                            <c:choose>
                                <c:when test="${edit}">
                                    <form:input type="hidden" path="ssoId" id="ssoId" class="form-control input-sm" />
                                </c:when>
                                <c:otherwise>
                                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="ssoId" class="help-inline"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="firstName">Nom</label>
                        <div class="col-md-7">
                            <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="firstName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="lastName">Prénom</label>
                        <div class="col-md-7">
                            <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="lastName" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="ssoId">Email </label>
                        <div class="col-md-7">
                                    <form:input type="text" path="email" id="email" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="email" class="help-inline"/>
                                    </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="birthDate">Date de naissance</label>
                        <div class="col-md-7">
                            <form:input type="text" path="birthDate" id="birthDate" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="birthDate" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <c:if test="${!edit}">
                            <label class="col-md-3 control-label" for="password">Mot de passe</label>
                        </c:if>
                        <div class="col-md-7">
                            <c:choose>
                                <c:when test="${edit}">
                                    <form:input type="hidden" path="password" id="password" class="form-control input-sm" />
                                </c:when>
                                <c:otherwise>
                                    <form:input type="password" path="password" id="password" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="password" class="help-inline"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                        </div>
                    </div>
                </div>


                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="ville">Ville</label>
                        <div class="col-md-7">
                            <form:select path="ville" items="${listeCommune}" itemValue="id" itemLabel="libelle" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="ville" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-label" for="userProfiles">Roles</label>
                        <div class="col-md-7">
                            <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
                            <div class="has-error">
                                <form:errors path="userProfiles" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-actions floatRight">
                        <c:choose>
                            <c:when test="${editAnotherUser}">
                                <input type="submit" value="Mettre à jour" class="btn btn-primary btn-sm"/>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${edit}">
                                        <input type="submit" value="Modifier" class="btn btn-primary btn-sm"/> ou <a href="<c:url value='/login' />">Retourner à l'acceuil</a>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="submit" value="Enregistrer" class="btn btn-primary btn-sm"/> ou <a href="<c:url value='/login' />">Retourner à l'acceuil</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>


                    </div>
                </div>
            </div>
        </form:form>
    </jsp:body>
</t:template>
