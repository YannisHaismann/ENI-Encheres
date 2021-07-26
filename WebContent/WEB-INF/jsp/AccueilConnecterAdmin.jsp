<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/Accueil.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Accueil</title>
</head>
<body>

	<%@include file="header_page_connecter_admin.jsp"%>

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

	<h1>
		Liste des enchères
		<div class="animation-underline__bottom"></div>
		<div class="animation-underline__right"></div>
		<div class="animation-underline__top"></div>
		<div class="animation-underline__left"></div>
	</h1>
	<div class="search-top-nav">
		<p class="search-top-nav__title">Filtres:</p>
		<form class="search-top-nav__form">
			<input placeholder="Le nom de l'article contient"
				class="search-top-nav__form__input" type="text" name="nom-article">
			<div>
				<label class="search-top-nav__form__label" for="categorie">Catégories:</label> 
				<input  list="liste-categories" name="categorie" id="categorie">
				
				<datalist class="search-top-nav__form__input" id="liste-categories">
					<option value="Toutes" selected="selected">
 					<option value="Informatique">
				    <option value="Ameublement">
					<option value="Vetements">
					<option value="Sport et loisirs">
				</datalist>
			</div>
			<div>
				<div class="search-top-nav__form__achats">
					<div>
						<input id="achats" value="achats" type="radio"
							name="achat-ou-vente"><label for="achats">Achats</label>
					</div>
					<div>
						<input class="encheres-ouvertes" type="checkbox" name="encheres-ouvertes"
							id="encheres-ouvertes"><label for="encheres-ouvertes">Enchères
							ouvertes</label>
					</div>
					<div>
						<input class="mes-encheres-en-cours" type="checkbox" name="mes-encheres-en-cours"
							id="mes-encheres-en-cours"><label
							for="mes-encheres-en-cours">Mes enchères en cours</label>
					</div>
					<div>
						<input class="mes-encheres-remportees" type="checkbox" name="mes-encheres-remportees"
							id="mes-encheres-remportees"><label
							for="mes-encheres-remportees">Mes enchères remportées</label>
					</div>
				</div>
				<div class="search-top-nav__form__ventes">
					<div>
						<input id="ventes" value="ventes" type="radio"
							name="achat-ou-vente"><label for="ventes">Mes
							ventes</label>
					</div>
					<div>
						<input class="mes-ventes-en-cours" type="checkbox" name="mes-ventes-en-cours"
							id="mes-ventes-en-cours"><label for="mes-ventes-en-cours">Mes
							ventes en cours</label>
					</div>
					<div>
						<input class="ventes-non-debutees" type="checkbox" name="ventes-non-debutees"
							id="ventes-non-debutees"><label for="ventes-non-debutees">Ventes
							non débutées</label>
					</div>
					<div>
						<input  class="ventes-terminees" type="checkbox" name="ventes-terminees"
							id="ventes-terminees"><label for="ventes-terminees">Ventes
							terminées</label>
					</div>
				</div>
			</div>
			<input class="search-top-nav__form__input" type="submit"
				name="envoyer">
		</form>
	</div>
	<div class="liste-article">
		<c:forEach var="article" items="${requestScope.articles}">
			 <c:if test ="${article != null}">
			 <div class="article-div">
			 	<p><a href="<%=request.getContextPath()%>/ServletEncherirArticleDetail?id=${article.id}"><c:out value="${article.nom}"/></a></p>
			 	<p>Prix: <c:out value="${article.prix}"/></p>
			 	<p>Fin de l'enchère: <c:out value="${article.dateFin}"/></p>
			 	<p>Vendeur: <c:out value="${article.vendeur}"/></p>
			 </div>
			 </c:if>
		</c:forEach>	
	</div>
	
	<div class="pagination">
		<% 
		if(request.getAttribute("nombrePage") != null){
		int u = Integer.parseInt(request.getAttribute("nombrePage").toString());
		
		for(int i=1; i <= u; i++) { %>
			<a href="<%=request.getContextPath()%>/ServletAccueil?page=<%=i%>"> <%=i%> </a>
		<%} 
		}%>
	</div>
	
			<script type="text/javascript">
		var achats = document.querySelector("#achats");
		var ventes = document.querySelector("#ventes");
		
		var encheresOuvertes		= document.querySelector(".encheres-ouvertes");
		var mesEncheresEnCours		= document.querySelector(".mes-encheres-en-cours");
		var mesEncheresRemportees	= document.querySelector(".mes-encheres-remportees");

		var mesVentesEnCours 		= document.querySelector(".mes-ventes-en-cours");
		var ventesNonDebutees 		= document.querySelector(".ventes-non-debutees");
		var ventesTerminees 		= document.querySelector(".ventes-terminees");
		
		achats.addEventListener("click", (e) => {
			encheresOuvertes.disabled = false;
			mesEncheresEnCours.disabled = false;
			mesEncheresRemportees.disabled = false;
			
			mesVentesEnCours.disabled = true;
			ventesNonDebutees.disabled 	 = true;
			ventesTerminees.disabled = true;
			
			mesVentesEnCours.checked = false;
			ventesNonDebutees.checked = false;
			ventesTerminees.checked = false;
		});
		
		ventes.addEventListener("click", (e) => {
			encheresOuvertes.disabled = true;
			mesEncheresEnCours.disabled = true;
			mesEncheresRemportees.disabled = true;
			
			encheresOuvertes.checked = false;
			mesEncheresEnCours.checked = false;
			mesEncheresRemportees.checked = false;
			
			mesVentesEnCours.disabled = false;
			ventesNonDebutees.disabled 	 = false;
			ventesTerminees.disabled = false;
		});
	</script>
	
	

</body>

<style type="text/css">
	.article-div{
		border: 2px solid black;
		width: 20%;
		margin: auto;
		margin-bottom: 10px;
		margin-top: 10px;
		padding: 20px;
		font-size: 20px
	}
	.pagination{
		font-size: 22px;
		text-align: center;
		margin: auto;
		border: 2px solid black;
		display: inline-block;
		padding: 20px;
	}
</style>
</html>