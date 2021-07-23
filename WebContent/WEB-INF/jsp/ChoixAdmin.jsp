<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/ChoixAdmin.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">
<title>Activer/Desactiver les droits d'administrations</title>
</head>
<body>
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
	<h1>Commande administrateur</h1>
	<div>
	<p>Pour activer la fonctionalitée suivante saisissez 1 ou 0 pour la désactivée</p>
		<form name="form-desactivation"
			action="<%=request.getContextPath()%>/ChoixAdmin" method="post">
			<div>
				<label for="pseudo">Activer/Desactiver les droits d'administrations : </label>
			</div>
			<div>
				<input type="text" name="pseudoAdmin"
					placeholder="Saisir son pseudo" value="" />
			</div>
			<div>
				<label for="choix">Choix : </label>
			</div>
			<div>
				<input type="text" name="choixAdmin" placeholder="Votre choix"
					value="" />
			</div>
			<div>
				<input type="submit" value="Valider" />
			</div>
		</form>
	</div>
</body>
</html>