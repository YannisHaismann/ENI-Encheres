<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/afficherProfil.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Profil du vendeur</title>
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

	<div class="info">
	<p>
		Pseudo : ${vendeur.pseudo}
	</p>
	<p>
		Nom : ${vendeur.nom}
	</p>
	<p>
		Prénom : ${vendeur.prenom}
	</p>
	<p>
		Email : ${vendeur.email}
	</p>
	<p>
		Téléphone : ${vendeur.telephone}
	</p>
	<p>
		Rue : ${vendeur.rue}
	</p>
	<p>
		Code Postal : ${vendeur.codePostal}
	</p>
	<p>
		Ville : ${vendeur.ville}
	</p>
	
	<div class="btn-modif">
		<a href="<%=request.getContextPath()%>/"><input type="button" value="Retour"/></a>
	</div>

	<%@include file="footer.jsp"%>

</body>
</html>