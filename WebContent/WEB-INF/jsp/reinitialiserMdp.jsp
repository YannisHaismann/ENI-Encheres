<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/reinitialiserMdp.css">
<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Réinitialisation du mot de passe</title>

</head>

<body style="background-image: url('./images/fond_page_connexion.jpg');">

	<%@include file="header_logo_simple.jsp"%>

	<c:if test="${!empty listeCodesErreur}">
		<div class="alert alert-danger" role="alert">
			<strong>Erreur!</strong>
			<ul>
				<c:forEach var="code" items="${listeCodesErreur}">
					<li>${LecteurMessage.getMessageErreur(code)}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="login-box">
		<fieldset>
			<p class=login-title>Réinitialiser votre mot de passe</p>
			<div class="champ">
				<div class="full-width">
					<label for="email"></label><br> <input type="text" id="email"
						placeholder="votre adresse mail" name="email">
				</div>
				<a class="creer-button"
					href="<%=request.getContextPath()%>/ServletConnexion">Envoi</a>
			</div>
		</fieldset>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>