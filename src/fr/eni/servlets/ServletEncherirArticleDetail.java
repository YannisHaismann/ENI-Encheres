package fr.eni.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
import fr.eni.bo.Encheres;
import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletEncherirArticleDetail
 */
@WebServlet("/ServletEncherirArticleDetail")
public class ServletEncherirArticleDetail extends HttpServlet {
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
		String id = request.getParameter("id");
		ArticleVenduManager articleManager = new ArticleVenduManager();
		EncheresManager encheresManager = new EncheresManager();
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		RetraitsManager retraitManager = new RetraitsManager();
		try {
			ArticleVendu article 		= articleManager.selectionner(Integer.parseInt(id));
			List<Encheres> encheres     = encheresManager.selectionnerParIdArticle(article.getId());
			int prixTopEnchere = article.getPrixInitial();
			String utilisateurTopEnchere = "";
			Utilisateurs vendeur = utilisateurManager.selectionner(article.getIdUtilisateur());
			for(Encheres enchere : encheres) {
				if(prixTopEnchere < enchere.getMontant()) {
					prixTopEnchere = enchere.getMontant();
					utilisateurTopEnchere = utilisateurManager.selectionner(enchere.getIdUtilisateur()).getPseudo();
				}
			}
			Retraits retrait = retraitManager.selectionner(article.getId());
			if(request.getParameter("encherir") != null) {
				if(Integer.parseInt(request.getParameter("propositionEnchere")) > prixTopEnchere) {
					Encheres ancienneEnchere = encheresManager.selectionnerParIdUtilisateur(Integer.parseInt(session.getAttribute("id").toString()));
					encheresManager.suprimer(ancienneEnchere.getIdArticle(), ancienneEnchere.getIdUtilisateur());;

					Encheres nouvelleEnchere = new Encheres();
					nouvelleEnchere.setDate(new Date(Calendar.getInstance().getTime().getTime()));
					nouvelleEnchere.setIdArticle(article.getId());
					nouvelleEnchere.setIdUtilisateur(Integer.parseInt(session.getAttribute("id").toString()));
					nouvelleEnchere.setMontant(Integer.parseInt(request.getParameter("propositionEnchere")));
					encheresManager.ajouter(nouvelleEnchere.getIdUtilisateur(), nouvelleEnchere.getIdArticle(), nouvelleEnchere.getDate(), nouvelleEnchere.getMontant());
					
					utilisateurTopEnchere = session.getAttribute("pseudo").toString();
					prixTopEnchere = Integer.parseInt(request.getParameter("propositionEnchere"));
				}
			}
			
			request.setAttribute("article", article);
			request.setAttribute("encheres", encheres);
			request.setAttribute("prixTopEnchere", prixTopEnchere);
			request.setAttribute("utilisateurTopEnchere", utilisateurTopEnchere);
			request.setAttribute("vendeur", vendeur);
			request.setAttribute("retrait", retrait);
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/EncherirArticleDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doGet(request, response);
	}

}
