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
			int i = 0;
			int t = 5;
			Map[] articles = new Map[t];
			int nombrePage = listArticles.size() / t;
			request.setAttribute("nombrePage", nombrePage);
			
			if(request.getParameter("page") != null) {
				System.out.println(Integer.parseInt(request.getParameter("page").toString()));
				i = i * Integer.parseInt(request.getParameter("page").toString());
			}
			int u = i + t;
			int y = 0;
			for(ArticleVendu article : listArticles) {
				y++;
				if(i < y) {
					Map<String, String> articleActual = new HashMap<String, String>();
					articleActual.put("nom", article.getNom());
					articleActual.put("prix", String.valueOf(article.getPrixVente()));
					articleActual.put("dateFin", article.getDateFin().toString());
					articleActual.put("vendeur", utilisateurManager.selectionner(article.getIdUtilisateur()).getNom());
					articleActual.put("id", String.valueOf(article.getId()));
					articles[i] = articleActual;
					i++;
					if(i == u) break;		
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
