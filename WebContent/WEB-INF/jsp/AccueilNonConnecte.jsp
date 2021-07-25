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

<title>[Title]</title>
</head>
<body >

	<%@include file="header_accueil.jsp"%>

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
			<input class="search-top-nav__form__input" type="submit"
				name="envoyer">
		</form>
	</div>
	<div class="liste-article">
		<c:forEach var="article" items="${requestScope.articles}">
			 <c:if test ="${article != null}">
			 <div class="article-div">
			 	<p><c:out value="${article.nom}"/></p>
			 	<p>Prix: <c:out value="${article.prix}"/></p>
			 	<p>Fin de l'enchère: <c:out value="${article.dateFin}"/></p>
			 	<p>Vendeur: <c:out value="${article.vendeur}"/></p>
			 </div>
			 </c:if>
		</c:forEach>	
	</div>
	
	<div class="pagination">
		<% 
		int u = Integer.parseInt(request.getAttribute("nombrePage").toString());
		
		for(int i=1; i <= u; i++) { %>
			<a href="<%=request.getContextPath()%>/ServletAccueil?page=<%=i%>"> <%=i%> </a>
		<%} %>
	</div>

	<%@include file="footer.jsp"%>


</body>
</html>