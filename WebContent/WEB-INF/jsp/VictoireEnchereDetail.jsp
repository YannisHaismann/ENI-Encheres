<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<link rel="stylesheet" href="css/style_inscription.css">
<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">
<link rel="stylesheet" href="css/victoireEnchereDetail.css">
<title>Enchère remportée</title>
=======

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>INSERER TITLE</title>
>>>>>>> branch 'master' of https://github.com/YannisHaismann/ENI-Encheres.git
</head>
<body style="background-image: url('./images/fond_page_connexion.jpg');">

<%@include file="header_logo_simple.jsp"%>

<div class="inscription-box">

    <form action="<%=request.getContextPath()%>/ServletVictoireEnchereDetail" method="post">
    	<h2 style="text-align: center;">Vous avez remporté l'enchère</h2>
        <p style="text-align: center;">{{NOM ARTICLE}}</p>
        <div style="text-align: left;" class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p style="text-align: center;"><span>Meilleure offre: </span><span>{{POINTS}} pts</span></p>
        <p style="text-align: center;"><span>Mise à prix: </span><span>{{POINTS}} points</span></p>
        <fieldset>
        	<legend style="text-align: center;">Retrait: </legend>
        	<p style="text-align: center;"><span>{{ADRESSE RETRAIT}}</span></p>
	        <p style="text-align: center;"><span>Vendeur: </span><span><a href="PROFIL VENDEUR">{{PSEUDO VENDEUR}}</a></span></p>
	        <p style="text-align: center;">Tel <span>{{TELEPHONE VENDEUR}}</span></p>
        </fieldset>
        <p style="text-align: center;">Retrait effectué</p>
    </form>
   </div>
    
<%@include file="footer.jsp"%>
    
</body>
</html>