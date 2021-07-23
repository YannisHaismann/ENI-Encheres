 package fr.eni.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/NouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		if(request.getParameter("enregistrer") != null) {
			
			String nom 				= null;
			String description 		= null;
			Date dateDebut 			= new Date(Calendar.getInstance().getTime().getTime());
			long timeFin 			= dateDebut.getTime()+7*24*60*60*1000;
			Date dateFin 			= new Date(timeFin);
			int prixInitial			= 0;
			
			
			prixInitial = lirePrixInitial(request, listeCodesErreur);
			nom = lireNomArticle(request, listeCodesErreur);
			description = lireDescriptionArticle(request, listeCodesErreur);
			
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
		prixInitial = Integer.parseInt(request.getParameter("prixInitial"));
		if (prixInitial == 0 || prixInitial > 2147483640) {
			listeCodesErreur.add(CodesResultatServlets.PRIX_INCORRECT);
		}
		return prixInitial;
	}
}

