<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/modifierProfil.css">
<title>Modidier/Supprimer mon profil</title>
</head>
<body>
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
		<form name="form-profil" action="<%=request.getContextPath()%>/ModificationProfil"
			method="post">
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="pseudo">Pseudo : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="pseudo"
						value="<%=request.getParameter("pseudo")%>" />
				</div>
				<div class="form-label-d">
					<label for="nom">Nom : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="nom"
						value="<%=request.getParameter("nom")%>" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="prenom">Prénom : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="prenom"
						value="<%=request.getParameter("prenom")%>" />
				</div>
				<div class="form-label-d">
					<label for="email">Email : </label>
				</div>
				<div class="form-champ">
					<input type="email" name="email"
						value="<%=request.getParameter("email")%>" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="telephone">Téléphone : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="telephone"
						value="<%=request.getParameter("telephone")%>" />
				</div>
				<div class="form-label-d">
					<label for="rue">Rue : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="rue"
						value="<%=request.getParameter("rue")%>" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="codePostal">Code postal : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="codePostal"
						value="<%=request.getParameter("codePostal")%>" />
				</div>
				<div class="form-label-d">
					<label for="ville">Ville : </label>
				</div>
				<div class="form-champ">
					<input type="text" name="ville"
						value="<%=request.getParameter("ville")%>" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="mdpa">Mot de passe actuel : </label>
				</div>
				<div class="form-champ">
					<input type="password" name="mdpa"
						value="<%=request.getParameter("mdpa")%>" />
				</div>
			</div>
			<div class="form-ligne">
				<div class="form-label-g">
					<label for="nmdp">Nouveau mot de Passe : </label>
				</div>
				<div class="form-champ">
					<input type="password" name="nmdp"
						value="<%=request.getParameter("nmdp")%>" />
				</div>
				<div class="form-label-d">
					<label for="confirmation">Confirmation : </label>
				</div>
				<div class="form-champ">
					<input type="password" name="confirmation"
						value="<%=request.getParameter("confirmation")%>" />
				</div>
			</div>
			<div>
				<div>
					<p>Crédit : ${utilisateur.codePostal}</p>
				</div>
			</div>
			<div>
				<div>
					<input type="submit" value="Enregistrer" /> <a
						href="<%=request.getContextPath()%>/SupprimerProfil"><input
						type="button" value="Supprimer mon compte" /></a>
				</div>
			</div>
		</form>
	</div>
</body>
</html>