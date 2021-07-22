<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSERER TITLE</title>
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
    <form action="" method="post">
    	<h2>Vous avez remporté l'enchère</h2>
        <p>{{NOM ARTICLE}}</p>
        <div class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p><span>Meilleure offre: </span><span>{{POINTS}} pts</span></p>
        <p><span>Mise à prix: </span><span>{{POINTS}} points</span></p>
        <fieldset>
        	<legend>Retrait: </legend>
        	<span>{{ADRESSE RETRAIT}}</span></p>
	        <p><span>Vendeur: </span><span><a href="PROFIL VENDEUR">{{PSEUDO VENDEUR}}</a></span></p>
	        <p>Tel <span>{{TELEPHONE VENDEUR}}</span></p>
        </fieldset>
        <p>Retrait effectué</p>
    </form>
</body>
</html>