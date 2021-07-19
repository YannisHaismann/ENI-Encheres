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

<%@include file="header_logo_simple.jsp"%>

    <form action="" method="post">
        <h2>Détail vente</h2>
        <p>{{NOM ARTICLE}}</p>
        <div class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p><span>Description: </span><span>{{DESCRIPTION}}</span></p>
        <p><span>Catégorie: </span><span>{{CATEGORIE}}</span></p>
        <p><span>Meilleur offre: </span><span>{{POINTS}} points par {{PSEUDO}}</span></p>
        <p><span>Mise à prix: </span><span>{{POINTS}} points</span></p>
        <p><span>Fin de l'enchère: </span><span>{{DATE XX/XX/XXXX}}</span></p>
        <p><span>Retrait: </span><span>{{ADRESSE RETRAIT}}</span></p>
        <p><span>Vendeur: </span><span>{{PSEUDO VENDEUR}}</span></p>
        <form action="" method="post">
        	<p> <label>Ma proposition: </label> <input type="number" name="propositionEnchere"> <input type="submit" name="encherir">
        </form>
        <a href="RETOUR">Back</a></p>
    </form>
    
<%@include file="footer.jsp"%>
    
</body>
</html>