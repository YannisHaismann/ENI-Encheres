package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/Profil")
public class ServletAfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Je lis les paramètres
		int idutilisateur = 0;
		List<Integer> listeCodesErreur = new ArrayList<>();

		idutilisateur = lireParametreId(request, listeCodesErreur);

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		} else if (idutilisateur > 0) {
			// J'ai un id au bon format, je récupère l'utilisateur eventuel
			UtilisateursManager utilisateurManager = new UtilisateursManager();
			chargerUtilisateur(request, utilisateurManager);
			request.setAttribute("utilisateur", utilisateurManager);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/afficherProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private int lireParametreId(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int idutilisateur = 0;
		try {
			if (request.getParameter("id") != null) {
				idutilisateur = Integer.parseInt(request.getParameter("id"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
		}
		return idutilisateur;
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
