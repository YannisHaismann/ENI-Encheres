<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html> 
<html lang="fr">
<head>  
	<meta charset="UTF-8"> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="assets/css/style.css">

    <link rel="stylesheet" href="../../css/style_inscription.css">
    <link rel="stylesheet" href="../../css/style_footer.css">
    <link rel="stylesheet" href="../../css/style_header.css">
    
    
    <title>Créer un compte</title>
</head>



<body>  

	<a  style="display: inline-block; " href=""> <img src="../../photos/logo-ENI.png" style="margin: 20px 0 0 20px;" height="60" width="60" alt="logo-eni"> </a>
  
        
    <div class="inscription-box">    
        
        <h2 style="text-align: center;"> Mon Profil</h2>
        
        <form action="" method="post" class="flex-profil-elements">
        
                        <div >                            
                                <label class="label-gauche" for="Pseudo" >Pseudo</label>
                                <input type="text" id="Pseudo" required >
                        </div>
                        <div>
                            <label class="label-droit" for="nom" >Nom</label>
                            <input type="text" id="nom" placeholder="votre Nom" required><br>
                        </div>                            
                        
		                <div>
                            <label class="label-gauche" for="prenom" >PrÃ©nom</label>
                            <input type="text" id="prenom" required placeholder="votre prÃ©nom">
                        </div>
                        <div>
                            <label class="label-droit" for="email" >Email</label>
                            <input type="email" id="email" required placeholder="votre_email@mail.fr"><br>
                        </div>
                        
                        <div >
                            <label class="label-gauche" for="telephone" >TÃ©lÃ©phone</label>
                            <input type="text" id="telephone"  >
                        </div>
                        <div>
                            <label class="label-droit" for="rue" >Rue</label>
                            <input type="text" id="rue" required ><br>
                        </div>
                        <div >
                            <label class="label-gauche" for="code-postale" >Code postal</label>
                            <input type="text" id="code-postale" required >
                        </div>
                        <div>
                            <label class="label-droit" for="ville" >Ville</label>
                            <input type="text" id="ville" ><br>
                        </div>                  
                       	<div >
                            <label class="label-gauche" for="password" >Mot de passe</label>
                            <input type="password" id="password" required placeholder="entrer votre password">
                        </div>
                        <div>
                            <label class="label-droit" for="confirmation" >Confirmation</label>
                            <input type="password" id="confirmation" required placeholder="confirmer le password"><br>
                        </div>
                        <div style="width: 228px;">
                            <input class="creer-button" name="creer-button" type="submit" value="CrÃ©er">                                                        
                        </div>
                        <div>
                            <input class="annuler-button" name="annuler-button" type="submit" value="Annuler">
                        </div>
                        
                         
        
        </form>        
   
    
    </div>    


        
<%@include file="footer.jsp"%>  

    
</body>

</html>