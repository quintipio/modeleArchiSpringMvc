<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:attribute name="title">
      Page de login
    </jsp:attribute>
    <jsp:body>
        <div id="mainWrapper">
            <div class="generic-container">
                <sec:authorize access="isAuthenticated()">
                    Vous �tes connect�.
                    <br/>
                    <a href="<c:url value="/logout"/>">Se d�connecter</a>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()">
                <form action="${loginUrl}" method="post" class="form-horizontal">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Mauvais nom d'utilisateur ou mot de passe</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>Vous avez �t� d�connect�</p>
                        </div>
                    </c:if>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
                        <input type="text" class="form-control" id="username" name="sso" placeholder="Nom d'utilisateur" required>
                    </div>
                    <div class="input-group input-sm">
                        <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                    </div>
                    <div class="input-group input-sm">
                        <div class="checkbox">
                            <label><input type="checkbox" id="rememberme" name="remember-me">Se souvenir de moi</label>
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

                    <div class="form-actions">
                        <input type="submit" class="btn btn-block btn-primary btn-default" value="Se connecter">
                    </div>
                </form>
                </sec:authorize>
            </div>
        </div>
    </jsp:body>
</t:template>