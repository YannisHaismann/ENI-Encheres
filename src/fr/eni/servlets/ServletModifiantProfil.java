package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.UtilisateursManager;
import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletModifiantProfil
 */
@WebServlet("/ModificationProfil")
public class ServletModifiantProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Je lis les paramètres
				int idutilisateur = 0;
				List<Integer> listeCodesErreur = new ArrayList<>();
				
				idutilisateur = lireParametreId(request, listeCodesErreur);
					
				if(listeCodesErreur.size() > 0)
				{
					request.setAttribute("listeCodesErreur",listeCodesErreur);
				}
				else if(idutilisateur > 0)
				{
					//J'ai un id au bon format, je récupère l'utilisateur associée eventuelle
					UtilisateursManager utilisateurManager = new UtilisateursManager();
					chargerUtilisateur(request, utilisateurManager);
					
					request.setAttribute("utilisateur", utilisateurManager);
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
				rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private int lireParametreId(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int idutilisateur = 0;
		try
		{
			if(request.getParameter("id") != null)
			{
				idutilisateur = Integer.parseInt(request.getParameter("id"));
			}
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
		}
		return idutilisateur;
	}
	
	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String pseudo;
		pseudo = request.getParameter("pseudo");
		if(pseudo == null || pseudo.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		
		
		else if(pseudo.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PSEUDO_DEPASSER);
		}
		return pseudo;
	}
	
	private String lireParametreNom(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String nom;
		nom = request.getParameter("nom");
		if(nom == null || nom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.NOM_OBLIGATOIRE);
		}else if(nom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_NOM_DEPASSER);
		}
		return nom;
	}
	
	private String lireParametrePrenom(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String prenom;
		prenom = request.getParameter("nom");
		if(prenom == null || prenom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PRENOM_OBLIGATOIRE);
		}else if(prenom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PRENOM_DEPASSER);
		}
		return prenom;
	}
	
	private String lireParametreEmail(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String email;
		email = request.getParameter("nom");
		if(email == null || email.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_OBLIGATOIRE);
		}else if(email.length() > 20) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_EMAIL_DEPASSER);
		}
		Pattern pattern = Pattern.compile("^ [ A - Z0 - 9 ._%+ - ] + @ [ A - Z 0 - 9 . - ] + \\. [ A - Z ] {2,} $", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		boolean emailValide = matcher.find();
		if(emailValide == false) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_NON_VALIDE);
		}
		return email;
	}
	
	private void chargerUtilisateur(HttpServletRequest request, UtilisateursManager utilisateurManager) {
		Utilisateurs utilisateur;
		
		try {
			int idUtilisateur = lireParametreId(request, null);
			utilisateur = utilisateurManager.selectionner(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
	}

}
