<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Acc�s refus�</title>
</head>
<body>
<div class="generic-container">
    <div class="authbar">
        <span><strong>${loggedinuser}</strong>, Cet acc�s est interdit.</span> <span class="floatRight"><a href="<c:url value="/logout" />">D�connexion</a></span>
    </div>
</div>
</body>
</html>
