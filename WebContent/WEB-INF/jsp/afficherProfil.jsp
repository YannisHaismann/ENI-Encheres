<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon profil</title>
</head>
<body style="text-align: center">
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
	<!--<c:if test="${!empty utilisateur}">-->
	<div>
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
	</div>
	<!--</c:if>
	<c:if test="${empty utilisateur}">
		<p>Les informations de votre profil sont introuvable</p>
	</c:if>-->
	<div>
		<a href="<%=request.getContextPath()%>/ModificationProfil"><input type="button" value="Modifier"/></a>
	</div>

</body>
</html>