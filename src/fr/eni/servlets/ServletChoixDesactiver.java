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

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.EncheresManager;
import fr.eni.bll.RetraitsManager;
import fr.eni.bll.UtilisateursManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletChoixDesactiver
 */
@WebServlet("/ChoixDesactiver")
public class ServletChoixDesactiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Je verifie si une session est active
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ChoixDesactiver.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Je verifie si une session est active
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
		}
		
		request.setCharacterEncoding("UTF-8");

		//Récupération des saisies de l'utilisateur
		String pseudoDesactiver = request.getParameter("pseudoDesactiver");
		int choixDesactiver = Integer.parseInt(request.getParameter("choixDesactiver"));
		
		//Déclaration des variables
		int idProfilDesactiver;
		Utilisateurs profilDesactiver = new Utilisateurs();
		List<ArticleVendu> listeArticle = new ArrayList<ArticleVendu>();
		List<Integer> noArticle = new ArrayList<Integer>();
		List<Integer> listeCodesErreur = new ArrayList<>();
		List<String> pseudos = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		ArticleVenduManager articleVenduManager = ArticleVenduManager.getInstance();
		RetraitsManager retraitsManager = RetraitsManager.getInstance();
		EncheresManager encheresManager = EncheresManager.getInstance();
		
		//Verification de la conformiter des infos saisies
		if (!pseudoDesactiver.trim().equals("")) {
			try {
				pseudos = utilisateursManager.selectAllPseudo();
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_PSEUDO);
			}
			if (!pseudos.contains(pseudoDesactiver)) {
				listeCodesErreur.add(CodesResultatServlets.PSEUDO_INEXISTANT);
			} else {
				try {
					if((choixDesactiver < 0) || (choixDesactiver > 1)) {
						listeCodesErreur.add(CodesResultatServlets.CHOIX_INCORRECT);
					}
					if(listeCodesErreur.size()>0)
					{
						request.setAttribute("listeCodesErreur", listeCodesErreur);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ChoixDesactiver.jsp");
						rd.forward(request, response);
					}
					if(choixDesactiver == 0) {
					utilisateursManager.updateDesactiverByPseudo(0,pseudoDesactiver);
					}else {
						utilisateursManager.updateDesactiverByPseudo(1,pseudoDesactiver);
						profilDesactiver = utilisateursManager.selectByPseudo(pseudoDesactiver);
						session.setAttribute("idProfilDesactiver", profilDesactiver.getId());
						idProfilDesactiver = (int)session.getAttribute("idProfilDesactiver");
						encheresManager.supprimerParIdUtilisateur(idProfilDesactiver);
						listeArticle = articleVenduManager.selectionnerTousByIdUtilisateur(idProfilDesactiver);
						for(ArticleVendu article : listeArticle) {
							noArticle.add(article.getId());
						}
						for(int i : noArticle) {
							retraitsManager.deleteById(i);
						}
						articleVenduManager.supprimerParIdUtilisateur(idProfilDesactiver);
					}
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				}
			
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Administrateur.jsp");
		rd.forward(request, response);
	}

}
