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
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateursManager;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletSupprimerProfil
 */
@WebServlet("/SupprimerProfil")
public class ServletSupprimerProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		int idUtilisateur = 0;
		List<Integer> listeCodesErreur = new ArrayList<>();

		idUtilisateur = lireParametreId(session, listeCodesErreur);

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			
		} else if (idUtilisateur > 0) {
			// J'ai un id au bon format, je supprime l'utilisateur associer a celui-ci
			UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
			try {
				utilisateursManager.supprimer(idUtilisateur);
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.ERREUR_SUPPRESSION_PROFIL);
			}
			
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//Permet de verifier et recuperer l'id au bon format
	private int lireParametreId(HttpSession session, List<Integer> listeCodesErreur) {
		int idUtilisateur = 0;
		try {
			if (session.getAttribute("id") != null) {
				idUtilisateur = (int)session.getAttribute("id");
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
		}
		return idUtilisateur;
	}

}
