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
    <title>[Title]</title>
</head>
<body>
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
</body>
</html>