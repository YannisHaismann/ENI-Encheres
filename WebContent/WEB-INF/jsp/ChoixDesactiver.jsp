<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"  href="css/ChoixAdmin.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Activer/Desactiver profil</title>
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
	
		<h4>Pour activer la fonctionalitée suivante saisissez 1 ou 0 pour la désactivée</h4>
	
		<form name="form-desactivation"
			action="<%=request.getContextPath()%>/ChoixDesactiver" method="post">
			<div>
				<label for="pseudo" class="label-choix">Activation/Desactivation d'un compte utilisteur : </label>
			</div>
			<div>
				<input type="text" name="pseudoDesactiver" id="pseudo"	placeholder="Saisir son pseudo" value="" required="required"/>
			</div>
			<div>
				<label for="choix" class="label-choix" >Choix : </label>
			</div>
			<div>
				<input type="text" id="choix" name="choixDesactiver" placeholder="Votre choix"	value="" required="required"/>
			</div>
			<div>
				<input type="submit" value="Valider" class="button-choix" />
			</div>
		</form>
	</div>
</body>
</html>