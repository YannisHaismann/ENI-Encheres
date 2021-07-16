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
 * Servlet implementation class ServletAjouterUtilisateur
 */
@WebServlet("/ServletAjouterUtilisateur")
public class ServletAjouterUtilisateur extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjouterUtilisateur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//Je vais faire le traitement pour 
		//ajouter un NOUVEAU utilisateur
		
		String pseudo 		= request.getParameter("pseudo");
		String nom 			= request.getParameter("nom");
		String prenom 		=  request.getParameter("prenom");
		String email 		= request.getParameter("email");	
		String telephone 	= request.getParameter("telephone");	
		String rue 			= request.getParameter("rue");	
		int codePostal		= Integer.valueOf(request.getParameter("codePostal")) ;
		String ville 		= request.getParameter("ville");
		String motDePasse	= request.getParameter("motDePasse");
		int credit 			= 0;
		int administrateur 	= 0;
		int desactiver 		= 0;
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		
		Utilisateurs utilisateur = new Utilisateurs();
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(prenom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motDePasse);
		
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		
		try {
			utilisateursManager.ajouter(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur, desactiver);
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ECHEC_INSERTION_NOUVEAU_UTILISATEUR);
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());			
			
		}		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp"); // A REVOIR 
		rd.forward(request, response);
		
	}

}
