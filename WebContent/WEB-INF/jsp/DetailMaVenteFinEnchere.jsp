<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSERER TITLE</title>
</head>
<body>
    <form action="" method="post">
        <h2>{{PSEUDO ACHETEUR}} a remporté l'enchere</h2>
        <p>{{NOM ARTICLE}}</p>
        <div class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p><span>Meilleure offre: </span><span>{{POINTS}} pts par <a href="PROFIL DE L ACHETEUR">{{PSEUDO ACHETEUR}}</a></span></p>
        <p><span>Mise à prix: </span><span>{{POINTS}} points</span></p>
        <p><span>Fin de l'enchère: </span><span>{{DATE XX/XX/XXXX}}</span></p>
        <p><span>Retrait: </span><span>{{ADRESSE RETRAIT}}</span></p>
        <p><span>Vendeur: </span><span>{{PSEUDO VENDEUR}}</span></p>
        <p><span>Retrait effectué</span>
        <a href="CONTACTER ACHETEUR">Contacter {{PSEUDO ACHETEUR}}</a>
        <a href="RETOUR">Back</a></p>
    </form>
</body>
</html>