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
import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;
 
/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/ServletAfficherProfil")
public class ServletAfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Je verifie si une session est active
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		
		int idUtilisateur = 0;
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		//Je recupère l'id de l'utilisateur de la session
		idUtilisateur = lireParametreId(session, listeCodesErreur);
		
		//Je verifie si une erreur est stocker dans la liste pour l'afficher 
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			
		} else if (idUtilisateur > 0) {
			// J'ai un id au bon format, je récupère l'utilisateur eventuel et j'affiche son profil
			Utilisateurs utilisateurs = new Utilisateurs();
			chargerUtilisateur(session, utilisateurs, listeCodesErreur);
			
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
	
	//Permet de récuper l'id utilisateur et de le renvoyer
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
	
	//Permet de récuperer les infos de l'utilisateur et de les afficher via son id
	private void chargerUtilisateur(HttpSession session, Utilisateurs utilisateurs, List<Integer> listeCodesErreur) {
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();

		try {
			int idUtilisateur = lireParametreId(session, null);
			utilisateurs = utilisateursManager.selectionner(idUtilisateur);
			session.setAttribute("utilisateur", utilisateurs);

		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.CHARGEMENT_UTILISATEUR_ERREUR);
		}
	}

}
