<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="css/modifierProfil.css">

<link rel="stylesheet" href="css/style_footer.css">
<link rel="stylesheet" href="css/style_header.css">

<title>Modidier/Supprimer mon profil</title>
</head>

<body style="background-color: #F0F0E8;">

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
	<div id="form-style" class="form">
		<h1>Mon Profil</h1>
		<form name="form-profil"
			action="<%=request.getContextPath()%>/ModificationProfil"
			method="post">
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="pseudo">Pseudo : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="pseudo"
						placeholder="${utilisateur.pseudo}" value="" />
				</div>
				<div class="form-label-d">
					<label for="nom">Nom : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="nom" placeholder="${utilisateur.nom}"
						value="" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="prenom">Prénom : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="prenom"
						placeholder="${utilisateur.prenom}" value="" />
				</div>
				<div class="form-label-d">
					<label for="email">Email : </label>
				</div>
				<div class="form-champ">
					<input type="email" name="email" placeholder="${utilisateur.email}"
						value="" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="telephone">Téléphone : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="telephone"
						placeholder="${utilisateur.telephone}" value="" />
				</div>
				<div class="form-label-d">
					<label for="rue">Rue : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="rue" placeholder="${utilisateur.rue}"
						value="" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="codePostal">Code postal : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="codePostal"
						placeholder="${utilisateur.codePostal}" value="" />
				</div>
				<div class="form-label-d">
					<label for="ville">Ville : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="ville" placeholder="${utilisateur.ville}"
						value="" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="mdpa">Mot de passe actuel : </label>
				</div>
				<div class="form-champ">
					<input type="password" name="mdpa"
						value="${utilisateur.motDePasse}" />
				</div>
			</div>
			<div class="form-ligne">
				<div id="nmdp" class="form-label-g">
					<label for="nmdp">Nouveau mot de Passe : </label>
				</div>
				<div class="form-champ nmdp">
					<input type="password" name="nouveauMdp"
						value="${utilisateur.motDePasse}" />
				</div>
				<div class="form-label-d">
					<label for="confirmation">Confirmation : </label>
				</div>
				<div class="form-champ">
					<input type="password" name="confirmationMdp"
						value="${utilisateur.motDePasse}" />
				</div>
			</div>
			<div class="form-label-g">Crédit : ${utilisateur.credit}</div>
			<div class="form-ligne-boutton">
				<div class="boutton">
					<input type="submit" value="Enregistrer" class="taille-btn" />
				</div>
				<div class="boutton">
					<a href="<%=request.getContextPath()%>/SupprimerProfil"><input
						type="button" value="Supprimer mon compte" class="taille-btn" /></a>
				</div>
				<div class="boutton boutton-retour">
					<a href="<%=request.getContextPath()%>/ServletAccueil"><input
						type="button" value="retour" class="taille-btn" /></a>
				</div>
			</div>
		</form>
	</div>
	

	<%@include file="footer.jsp"%>

</body>
</html>