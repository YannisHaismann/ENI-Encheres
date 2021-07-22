<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">
<link rel="stylesheet" href="css/nouvelleVente.css">

<title>Vendre un article</title>





<title>Nouvelle Vente</title>

</head>

<body style="background-image: url('./images/fond_page_connexion.jpg');">

<%@include file="header_logo_seul.jsp"%>

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

	<div class="nouvelle-box">
	
    <form action="<%=request.getContextPath()%>/ServletNouvelleVente" method="post">
        <h2 style="text-align: center;"> Nouvelle vente</h2>
        <p style="text-align: center;"><label for="article">Article: </label><input  type="text" name="article" id="article"></p>
        <p><label for="photo">Photo de l'article: </label><input style="text-align: left;" id="photoUpload" type="file" id="photo" name="photo" value="UPLOADER"></p>
        <div style="text-align: left;" class="photoArticle">
            <img src="" alt="photo-article" title="photo-article" id="photoArticle">
        </div>
        <p style="text-align: center;"><label for="categorie">Categorie: </label><input type="text" name="categorie" id="categorie"></p>
        <p style="text-align: center;"><label for="description">Description: </label><textarea id="description" name="description" rows="10" cols="50"></textarea></p>
        <p style="text-align: center;"><label for="prixInitial">Prix initial: </label><input type="number" id="prixInitial" name="prixInitial"></p>
        <p style="text-align: center;"><label for="dateDebut">Début de l'enchère: </label><input type="date" name="dateDebut" id="dateDebut"></p>
        <p style="text-align: center;"><label for="dateFin">Fin de l'enchère: </label><input type="date" name="dateFin" id="dateFin"></p>
        <p style="text-align: center;"><label>Retrait: </label><span>ADRESSE DE RETRAIT</span></p>
        <input class="enregistrer-button" type="submit" value="ENREGISTRER" name="enregistrer">
        <input class="annuler-button" type="submit" value="ANNULER" name="annuler">
    </form>
    
    </div>
    


</body>
</html>