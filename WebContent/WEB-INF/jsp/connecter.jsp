<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="fr">

<head>  
	<meta charset="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    
    <link rel="stylesheet" href="css/style_connecter.css">    
    <link rel="stylesheet" href="css/style_footer.css">
    <link rel="stylesheet" href="css/style_header.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    
    <title>Connecter au compte</title> 
    
</head> 



<body style="background-image: url('./images/fond_page_connexion.jpg');"> 
  
<%@include file="header_logo_simple.jsp"%>


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


	<div class="login-box"> 


    
        <img src="./images/avatar_connexion.png" alt="avatar" class="avatar">        
        
	        <form action="<%=request.getContextPath()%>/ServletConnexion" method="post">
	        
	        	<fieldset > <p class=login-title>Se connecter</p>  
	        
	                    <div class="full-width">
			                <label for="pseudo">Identifiant</label><br>
			                <input type="text" id="pseudo" placeholder="votre mail ou pseudo" name="pseudo">
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
			            <a class="creer-button" href="<%=request.getContextPath()%>/ServletAjouterUtilisateur" >Créer un Compte</a>
	             </fieldset>
	        
	        </form>        
   
    
    </div>
    
<%@include file="footer.jsp"%>

 
</body>

</html>
