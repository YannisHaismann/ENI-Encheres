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
        <h2>Nouvelle vente</h2>
        <p><label for="article">Article: </label><input type="text" name="article" id="article"></p>
        <p><label for="categorie">Categorie: </label><input type="text" name="categorie" id="categorie"></p>
        <p><label for="description">Description: </label><textarea id="description" name="description" rows="10" cols="50"></textarea></p>
        <p><label for="photo">Photo de l'article: </label><input id="photoUpload" type="file" id="photo" name="photo" value="UPLOADER"></p>
        <div class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p><label for="prixInitial">Prix initial: </label><input type="number" id="prixInitial" name="prixInitial"></p>
        <p><label for="dateDebut">Début de l'enchère: </label><input type="date" name="dateDebut" id="dateDebut"></p>
        <p><label for="dateFin">Fin de l'enchère: </label><input type="date" name="dateFin" id="dateFin"></p>
        <p><label>Retrait: </label><span>ADRESSE DE RETRAIT</span></p>
        <p><input type="submit" value="ENREGISTRER" name="enregistrer">
        <input type="submit" value="ANNULER" name="annuler"></p>
    </form>
    
<%@include file="footer.jsp"%>
    
</body>
</html>