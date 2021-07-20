<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/Accueil.css">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

	<link rel="stylesheet" href="css/style_footer.css">
	<link rel="stylesheet" href="css/style_header.css">
	
    <title>[Title]</title>
</head>
<body>

<%@include file="header_accueil.jsp"%>

    <h1>Liste des enchères<div class="animation-underline__bottom"></div><div class="animation-underline__right"></div>
        <div class="animation-underline__top"></div><div class="animation-underline__left"></div></h1>
    <div class="search-top-nav">
        <p class="search-top-nav__title">Filtres: </p>
        <form class="search-top-nav__form">
            <input placeholder="Le nom de l'article contient" class="search-top-nav__form__input" type="text" name="nom-article">
            <div>
                <label class="search-top-nav__form__label" for="categorie">Catégorie: </label>
                <select class="search-top-nav__form__input" id="categorie" name="categorie">
                    <option value="">Toutes</option>
                    <option value="">Autres options à définir</option>
                </select>
            </div>
            <input class="search-top-nav__form__input" type="submit" name="envoyer">
        </form>
    </div>
    
<%@include file="footer.jsp"%>  

    
</body>
</html>