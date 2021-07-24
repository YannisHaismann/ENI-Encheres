 package fr.eni.servlets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.eni.bll.ArticleVenduManager;
import fr.eni.bll.CategoriesManager;
import fr.eni.bll.RetraitsManager;
import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Categories;
import fr.eni.bo.Retraits;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
@MultipartConfig
public class ServletNouvelleVente extends HttpServlet {
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
		request.setAttribute("categorie", Categories.listeLibelle);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		if(request.getParameter("enregistrer") != null) {
			
			String nom 				= null;
			String description 		= null;
			Date dateDebut 			= new Date(Calendar.getInstance().getTime().getTime());
			long timeFin 			= dateDebut.getTime()+7*24*60*60*1000;
			Date dateFin 			= new Date(timeFin);
			int prixInitial			= 0;
			Retraits retrait		= null;
			//GERER L ENREGISTREMENT D IMAGE
					
			prixInitial 	= lirePrixInitial(request, listeCodesErreur);
			nom 			= lireNomArticle(request, listeCodesErreur);
			description 	= lireDescriptionArticle(request, listeCodesErreur);
			retrait 		= lireRetraitArticle(request, listeCodesErreur);
			
			ArticleVenduManager articleManager = new ArticleVenduManager();	
			CategoriesManager categorieManager = new CategoriesManager();
			RetraitsManager retraitManager 	   = new RetraitsManager();
				
			try {
				Categories categorie = categorieManager.selectionner(request.getParameter("categorie"));
				ArticleVendu article = articleManager.ajouter(nom, description, dateDebut, dateFin, prixInitial, prixInitial, Integer.parseInt(session.getAttribute("id").toString()), categorie.getId());
				retrait.setId(article.getId());
				retraitManager.ajouter(retrait.getId(), retrait.getRue(), retrait.getCodePostal(), retrait.getVille());
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
			}
		}
		
		doGet(request, response);
	}
	
	private String lireNomArticle(HttpServletRequest request, List<Integer> listeCodesErreur){
		String nom;
		nom = request.getParameter("nom");
		if (nom == null || nom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.NOM_OBLIGATOIRE);
		} else if (nom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_NOM_DEPASSER);
		}
		Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]");
		Matcher matcher = pattern.matcher(nom);
		boolean nomValide = matcher.find();
		if (nomValide == false) {
			listeCodesErreur.add(CodesResultatServlets.NOM_INVALIDE);
		}
		return nom;
	}
	
	private String lireDescriptionArticle(HttpServletRequest request, List<Integer> listeCodesErreur){
		String desc;
		desc = request.getParameter("desc");
		if (desc == null || desc.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.DESC_OBLIGATOIRE);
		} else if (desc.length() > 300) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_DESC_DEPASSER);
		}
		Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]");
		Matcher matcher = pattern.matcher(desc);
		boolean descValide = matcher.find();
		if (descValide == false) {
			listeCodesErreur.add(CodesResultatServlets.DESC_INVALIDE);
		}
		return desc;
	}
	
	private int lirePrixInitial(HttpServletRequest request, List<Integer> listeCodesErreur){
		int prixInitial;
		prixInitial = Integer.parseInt(request.getParameter("prixInitial").toString());
		if (prixInitial == 0 || prixInitial > 2147483640) {
			listeCodesErreur.add(CodesResultatServlets.PRIX_INCORRECT);
		}
		return prixInitial;
	}
	
	private Retraits lireRetraitArticle(HttpServletRequest request, List<Integer> listeCodesErreur){
		String rue;
		String codePostal;
		String ville;
		rue = request.getParameter("rue");
		codePostal = request.getParameter("codePostal");
		ville = request.getParameter("ville");
		if (rue == null || rue.trim().equals("") && codePostal == null || codePostal.trim().equals("") && ville == null || ville.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.RETRAIT_OBLIGATOIRE);
		} else if (rue.length() > 30 && codePostal.length() > 15 && ville.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_RETRAIT_DEPASSER);
		}
		Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]");
		Matcher matcher = pattern.matcher(rue);
		boolean retraitValide = matcher.find();
		if (retraitValide == false) {
			listeCodesErreur.add(CodesResultatServlets.RETRAIT_INVALIDE);
		}	
		matcher = pattern.matcher(codePostal);
		retraitValide = matcher.find();
		if (retraitValide == false) {
			listeCodesErreur.add(CodesResultatServlets.RETRAIT_INVALIDE);
		}
		
		matcher = pattern.matcher(ville);
		retraitValide = matcher.find();
		if (retraitValide == false) {
			listeCodesErreur.add(CodesResultatServlets.RETRAIT_INVALIDE);
		}
		
		Retraits retrait = new Retraits();
		retrait.setRue(rue);
		retrait.setVille(ville);
		retrait.setCodePostal(codePostal);
		return retrait;
	}
}

