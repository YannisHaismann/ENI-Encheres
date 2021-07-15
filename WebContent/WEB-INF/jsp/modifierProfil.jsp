<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.messages.LecteurMessage" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modidier/Supprimer mon profil</title>
</head>
<body style="text-align: center">
	<h1>Mon Profil</h1>
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
	<form action="<%=request.getContextPath()%>/ModificationProfil"
		method="post">
		<div>
			<label for="pseudo">Pseudo : </label> <input type="text"
				name="pseudo" value="<%=request.getParameter("pseudo")%>" />
		</div>
		<div>
			<label for="nom">Nom : </label> <input type="text" name="nom"
				value="<%=request.getParameter("nom")%>" />
		</div>
		<div>
			<label for="prenom">Prénom : </label> <input type="text"
				name="prenom" value="<%=request.getParameter("prenom")%>" />
		</div>
		<div>
			<label for="email">Email : </label> <input type="email" name="email"
				value="<%=request.getParameter("email")%>" />
		</div>
		<div>
			<label for="telephone">Téléphone : </label> <input type="text"
				name="telephone" value="<%=request.getParameter("telephone")%>" />
		</div>
		<div>
			<label for="rue">Rue : </label> <input type="text" name="rue"
				value="<%=request.getParameter("rue")%>" />
		</div>
		<div>
			<label for="codePostal">Code postal : </label> <input type="text"
				name="codePostal" value="<%=request.getParameter("codePostal")%>" />
		</div>
		<div>
			<label for="ville">Ville : </label> <input type="text" name="ville"
				value="<%=request.getParameter("ville")%>" />
		</div>
		<div>
			<label for="mdpa">Mot de passe actuel : </label> <input
				type="password" name="mdpa"
				value="<%=request.getParameter("mdpa")%>" />
		</div>
		<div>
			<label for="nmdp">Nouveau mot de Passe : </label> <input
				type="password" name="nmdp"
				value="<%=request.getParameter("nmdp")%>" />
		</div>
		<div>
			<label for="confirmation">Confirmation : </label> <input
				type="password" name="confirmation"
				value="<%=request.getParameter("confirmation")%>" />
		</div>
		<div>
			<p>Crédit : ${utilisateur.codePostal}</p>
		</div>
		<div>
			<input type="submit" value="Enregistrer" /> <a
				href="<%=request.getContextPath()%>/SupprimerProfil"><input
				type="button" value="Supprimer mon compte" /></a>
		</div>
	</form>
</body>
</html>