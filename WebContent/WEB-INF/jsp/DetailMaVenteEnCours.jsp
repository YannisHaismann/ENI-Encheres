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
        <h2>{{NOM ARTICLE}}</h2>
        <div class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p><span>Meilleure offre: </span><span>{{POINTS}} pts par {{PSEUDO ACHETEUR}}</span></p>
        <p><span>Mise à prix: </span><span>{{POINTS}} points</span></p>
        <p><span>Fin de l'enchère: </span><span>{{DATE XX/XX/XXXX}}</span></p>
        <p><span>Retrait: </span><span>{{ADRESSE RETRAIT}}</span></p>
        <p><span>Vendeur: </span><span>{{PSEUDO VENDEUR}}</span></p>
        <p><a href="ANNULER">Annuler la vente</a>
        <a href="RETOUR">Back</a></p>
    </form>
    
<%@include file="footer.jsp"%>

</body>
</html>