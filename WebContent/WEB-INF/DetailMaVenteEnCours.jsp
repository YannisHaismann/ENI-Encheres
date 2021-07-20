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
</body>
</html>