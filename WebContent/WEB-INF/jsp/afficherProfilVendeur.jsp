<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/afficherProfil.css">
<title>Profil du vendeur</title>
</head>
<body>
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
		Pseudo : ${utilisateur.pseudo}
	</p>
	<p>
		Nom : ${utilisateur.nom}
	</p>
	<p>
		Prénom : ${utilisateur.prenom}
	</p>
	<p>
		Email : ${utilisateur.email}
	</p>
	<p>
		Téléphone : ${utilisateur.telephone}
	</p>
	<p>
		Rue : ${utilisateur.rue}
	</p>
	<p>
		Code Postal : ${utilisateur.codePostal}
	</p>
	<p>
		Ville : ${utilisateur.ville}
	</p>
	
	<div class="btn-modif">
		<a href="<%=request.getContextPath()%>/"><input type="button" value="Retour"/></a>
	</div>
	</div>
</body>
</html>