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
<body >

	<%@include file="header_page_connectee.jsp"%>

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
						<input type="checkbox" name="encheres-ouvertes"
							id="encheres-ouvertes"><label for="encheres-ouvertes">Enchères
							ouvertes</label>
					</div>
					<div>
						<input type="checkbox" name="mes-encheres-en-cours"
							id="mes-encheres-en-cours"><label
							for="mes-encheres-en-cours">Mes enchères en cours</label>
					</div>
					<div>
						<input type="checkbox" name="mes-encheres-remportees"
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
						<input type="checkbox" name="mes-ventes-en-cours"
							id="mes-ventes-en-cours"><label for="mes-ventes-en-cours">Mes
							ventes en cours</label>
					</div>
					<div>
						<input type="checkbox" name="ventes-non-debutees"
							id="ventes-non-debutees"><label for="ventes-non-debutees">Ventes
							non débutées</label>
					</div>
					<div>
						<input type="checkbox" name="ventes-terminees"
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
			 <div class="article-div">
			 	<p><a href="<%=request.getContextPath()%>/ServletEncherirArticleDetail?id=${article.id}"><c:out value="${article.nom}"/></a></p>
			 	<p>Prix: <c:out value="${article.prix}"/></p>
			 	<p>Fin de l'enchère: <c:out value="${article.dateFin}"/></p>
			 	<p>Vendeur: <c:out value="${article.vendeur}"/></p>
			 </div>
		</c:forEach>	
	</div>


	<%@include file="footer.jsp"%>

</body>
</html>