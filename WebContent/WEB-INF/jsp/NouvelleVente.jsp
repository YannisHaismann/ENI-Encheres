<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="fr.eni.messages.LecteurMessage"%>
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

	
	
<div class="main">
	
	<aside class="side-bar">
	
		<img  class="img-article" alt="photoArticle" src="https://via.placeholder.com/200C/O https://placeholder.com/ ">
	
	</aside>
	
	<div class="nouvelle-vente-main">
	
		<div class="nouvelle-box">
	
    		<form action="<%=request.getContextPath()%>/NouvelleVente" method="post">
			        <h2 style="text-align: center;"> Nouvelle vente</h2>
			        <div>
			        	<label class="label-vente" for="article">Article: </label><input required="required" type="text" name="nom" id="article">
			        </div>
			        
			        <div>
			            <label class="label-vente" for="description">Description: </label><textarea required="required" class="textarea" id="description" 
			            name="desc" rows="5" cols="30"></textarea>
			        </div>
		        
			        <div>
			        	<label class="label-vente" for="categorie">Categorie: </label>		        	 
						<select name="categorie" id="categorie">
							<c:forEach var="categorie" items="${requestScope.categorie}">
			  					<option value="${categorie}">${categorie}</option>
			  				</c:forEach>
						</select>       	
			        </div>
			        
			        <div>
			        	<label class="label-vente" for="photo">Photo de l'article: </label>
			        	<input required="required" type="file" id="photo" name="photo" value="UPLOADER">
			        </div>
			        
			        <div>
			        	<label class="label-vente" for="mise a prix">Prix initial: </label>
			       		<input required="required" type="number" min="0" max="1000000000" step="1" id="mise a prix" name="prixInitial" >
			        </div>
			        
					<fieldset>
						<legend>Retrait</legend>
						<label class="label-vente" for="rue">Rue</label> <input value="${sessionScope.rue}" required="required" type="text" name="rue" id="rue"> <br>
						<label class="label-vente" for="codePostal">Code Postal</label> <input value="${sessionScope.codePostal}" required="required" type="text" name="codePostal" id="codePostal"><br>
						<label class="label-vente" for="ville">Ville</label> <input value="${sessionScope.ville}" required="required" type="text" name="ville" id="ville">
					</fieldset>
			
			
					<div class="form-boutton" class="label-gauche-inscription">
							<a href="<%=request.getContextPath()%>/ServletAccueil"> <input id="envoyer" required="required" class="enregistrer-button" type="submit" value="Enregistrer" name="enregistrer"></a> 
					</div>
			
					<div class="form-boutton" class="label-droit-inscription">
							<a href="<%=request.getContextPath()%>/ServletAccueil"> <input required="required" class="annuler-button" type="button"
							 value="Annuler" name="annuler"></a>
					</div>

			</form>
    
  		  </div>  
  	  
  	 </div>
  	  
    
  </div> 

<%@include file="footer.jsp"%>
<script type="text/javascript">

	var img = document.querySelector("#photo");
	img.addEventListener("change", (e) => {
		var selectedFile = img.files[0];
		
		var reader = new FileReader();

		var imgtag = document.querySelector(".img-article");
		imgtag.title = selectedFile.name;

		reader.onload = function(event) {
		  imgtag.src = event.target.result;
		};

		reader.readAsDataURL(selectedFile);
		
	}, false);
	
</script>
</body>

</html>