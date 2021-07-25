package fr.eni.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.UtilisateursManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ArticleVenduManager articleManager = new ArticleVenduManager();
		UtilisateursManager utilisateurManager = new UtilisateursManager();
		try {
			List<ArticleVendu> listArticles = articleManager.selectionnerTous();
			int minPage 			= 0;
			int articleParPage 		= 10;
			int nombrePage		 	= listArticles.size() / articleParPage + ((listArticles.size() % articleParPage == 0) ? 0 : 1);
			request.setAttribute("nombrePage", nombrePage);
			
			if(request.getParameter("page") != null) {
				minPage = minPage + ((articleParPage * Integer.parseInt(request.getParameter("page").toString())) - articleParPage);
			}
			int maxPage = minPage + articleParPage;
			int y = 0, o = 0;
			Map[] articles 	= new Map[articleParPage];
			for(ArticleVendu article : listArticles) {
				y++;
				if(y >= minPage) {
					Map<String, String> articleActual = new HashMap<String, String>();
					articleActual.put("nom", article.getNom());
					articleActual.put("prix", String.valueOf(article.getPrixVente()));
					articleActual.put("dateFin", article.getDateFin().toString());
					articleActual.put("vendeur", utilisateurManager.selectionner(article.getIdUtilisateur()).getNom());
					articleActual.put("id", String.valueOf(article.getId()));
					articles[o] = articleActual;
					minPage++;
					o++;
					if(minPage == maxPage) break;		
				}
			}
			request.setAttribute("articles", articles);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		int admin = 0;
		if(session.getAttribute("administrateur") != null) {
			admin = (int) session.getAttribute("administrateur");
		}
		if( admin == 1) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecterAdmin.jsp");
			rd.forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
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

}
