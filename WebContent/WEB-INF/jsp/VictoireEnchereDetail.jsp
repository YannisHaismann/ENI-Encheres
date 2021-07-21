<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>INSERER TITLE</title>
</head>
<body>

<%@include file="header_logo_simple.jsp"%>

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
    
<%@include file="footer.jsp"%>
    
</body>
</html>