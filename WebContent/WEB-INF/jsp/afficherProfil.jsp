<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/afficherProfil.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Mon profil</title>

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
			<div>
				<p>Pseudo : ${utilisateur.pseudo}</p>
				<p>Nom : ${utilisateur.nom}</p>
				<p>Prénom : ${utilisateur.prenom}</p>
				<p>Email : ${utilisateur.email}</p>
				<p>Téléphone : ${utilisateur.telephone}</p>
				<p>Rue : ${utilisateur.rue}</p>
				<p>Code Postal : ${utilisateur.codePostal}</p>
				<p>Ville : ${utilisateur.ville}</p>

				<div class="btn-modif">
					<a href="<%=request.getContextPath()%>/ModificationProfil"><input
						type="button" value="Modifier" /></a>
				</div>
			</div>
		</fieldset>
	</div>

	<%@include file="footer.jsp"%>


</body>
</html>