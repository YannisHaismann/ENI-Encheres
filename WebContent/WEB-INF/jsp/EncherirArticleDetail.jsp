<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/victoireEnchereDetail.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Enchérir</title>
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

	<form action="" method="post">
		<h2>Détail vente</h2>
		<p>{{NOM ARTICLE}}</p>
		<div class="photoArticle">
			<img src="" alt="photo-article" title="photo-article"
				id="photoArticle">
		</div>
		<p>
			<span>Description: </span><span>{{DESCRIPTION}}</span>
		</p>
		<p>
			<span>Catégorie: </span><span>{{CATEGORIE}}</span>
		</p>
		<p>
			<span>Meilleur offre: </span><span>{{POINTS}} points par
				{{PSEUDO}}</span>
		</p>
		<p>
			<span>Mise à prix: </span><span>{{POINTS}} points</span>
		</p>
		<p>
			<span>Fin de l'enchère: </span><span>{{DATE XX/XX/XXXX}}</span>
		</p>
		<p>
			<span>Retrait: </span><span>{{ADRESSE RETRAIT}}</span>
		</p>
		<p>
			<span>Vendeur: </span><span>{{PSEUDO VENDEUR}}</span>
		</p>
		<form action="" method="post">
			<p>
				<label>Ma proposition: </label> <input type="number"
					name="propositionEnchere"> <input type="submit"
					name="encherir">
		</form>
		<a href="RETOUR">Back</a>
		</p>
	</form>
	</div>
	<%@include file="footer.jsp"%>

</body>
</html>