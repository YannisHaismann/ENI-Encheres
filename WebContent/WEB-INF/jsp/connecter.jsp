<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="fr">

<head>  
	<meta charset="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../../css/style_connecter.css">
    <link rel="stylesheet" href="../../css/style_footer.css">
    <link rel="stylesheet" href="../../css/style_header.css">
    
    <title>Connecter au compte</title> 
</head> 



<body> 


<h1><b>Bienvenue sur Troc Enchère </b></h1>
  <p><b>Le site de bonnes affaires</b></p> 
  
<%@include file="header_logo_simple.jsp"%>
    
    <div class="login-box"> 
    
        <img src="../../photos/avatar_connexion.png" alt="avatar" class="avatar">        
        
	        <form action="./index.html" method="post">
	        
	        	<fieldset > <p class=login-title>Se connecter</p>  
	        
	                    <div class="full-width">
			                <label for="username">Identifiant</label><br>
			                <input type="text" id="username" placeholder="votre mail ou pseudo" name="pseudo">
			            </div> 
			            
			            <div class="full-width">
			                <label for="motDePasse">Mot de passe</label><br>
			                <input type="password" id="motDePasse"  name="motDePasse" placeholder="entrer votre mot de passe"><br><br>
			            </div>
			                        
			            <div class="aligner-login">
			                <input  type="submit" value="Connexion">
			                
			                <div class="checkbox">
			                    <label for="seSouvenir"> Se souvenir de moi</label>
			                    <input type="checkbox" name="seSouvenir" id="seSouvenir">
			                </div>
			            </div>
			            
			            <a href="#" class="password-field">Password oublié?</a><br>
			            <a class="creer-button" href="#">Créer un Compte</a>
	             </fieldset>
	        
	        </form>        
   
    
    </div>
    
<%@include file="footer.jsp"%>

 
</body>

</html>
