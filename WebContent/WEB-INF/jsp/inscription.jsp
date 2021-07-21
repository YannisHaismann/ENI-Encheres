<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<link rel="stylesheet" href="css/style_inscription.css">
<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">



<title>Créer un compte</title>
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

	<div class="inscription-box">


		<h2 style="text-align: center;">Mon Profil</h2>

		<form action="<%=request.getContextPath()%>/ServletAjouterUtilisateur"
			method="post" class="flex-profil-elements">

			<div>
				<label class="label-gauche-inscription" for="pseudo">Pseudo <span
					class="requis">*</span></label> <input type="text" id="pseudo" required
					name="pseudo" value="">
			</div>
			<div>
				<label class="label-droit-inscription" for="nom">Nom <span
					class="requis">*</span></label> <input type="text" id="nom"
					placeholder="votre Nom" required name="nom" value=""><br>
			</div>

			<div>
				<label class="label-gauche-inscription" for="prenom">Prénom <span
					class="requis">*</span></label> <input type="text" id="prenom" required
					placeholder="votre prénom" name="prenom" value="">
			</div>
			<div>
				<label class="label-droit-inscription" for="email">Email <span
					class="requis">*</span></label> <input type="email" id="email" required
					placeholder="votre_email@mail.fr" name="email" value=""><br>
			</div>

			<div>
				<label class="label-gauche-inscription" for="telephone">Téléphone</label> <input
					type="text" id="telephone" name="telephone" value="">
			</div>
			<div>
				<label class="label-droit-inscription" for="rue">Rue <span
					class="requis">*</span></label> <input type="text" id="rue" required
					name="rue" value=""><br>
			</div>
			<div>
				<label class="label-gauche-inscription" for="codePostal">Code postal <span
					class="requis">*</span></label> <input type="text" id="codePostal" required
					name="codePostal" value="" max="99999" pattern="[0-9]{5}">
			</div>
			<div>
				<label class="label-droit-inscription" for="ville">Ville <span
					class="requis">*</span></label> <input type="text" id="ville" name="ville"
					value=""><br>
			</div>
			<div>
				<label class="label-gauche-inscription" for="motDePasse">Mot de passe <span
					class="requis">*</span></label> <input type="password" id="motDePasse"
					required maxlength="20" placeholder="entrer le mot De Passe"
					name="motDePasse" value="">
			</div>
			
			<div >
					<label class="label-droit-inscription" for="confirmation">Confirmation
						<span class="requis">*</span>
					</label> <input type="password" id="confirmation" maxlength="20" required
						placeholder="confirmer le password" value=""  name="confirmation"><br>
			</div>
			
			
				
			<div class="form-boutton" class="label-gauche-inscription">
					<input   name="creer-button" type="submit" 
					value="Créer"> 
			</div>
				
			<div class="form-boutton" class="label-droit-inscription">
					<input   name="annuler-button" type="button" 
					value="Annuler">
			</div>

		





		</form>


	</div>



	<%@include file="footer.jsp"%>


</body>

</html>