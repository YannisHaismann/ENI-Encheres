package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

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
import fr.eni.utils.BCrypt;

/**
 * Servlet implementation class ServletModifiantProfil
 */
@WebServlet("/ModificationProfil")
public class ServletModifiantProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Je lis les paramètres
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		int idutilisateur = 0;
		List<Integer> listeCodesErreur = new ArrayList<>();

		idutilisateur = lireParametreId(session, listeCodesErreur);

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		} else if (idutilisateur > 0) {
			// J'ai un id au bon format, je récupère l'utilisateur associée eventuelle
			Utilisateurs utilisateurs = new Utilisateurs();
			chargerUtilisateur(session, utilisateurs, listeCodesErreur);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
	     }
		int idutilisateur;
		String pseudo;
		String nom;
		String prenom;
		String email;	
		String telephone;	
		String rue;	
		String codePostal;
		String ville;
		String motDePasse;
		int credit;
		int administrateur;
		int desactiver;
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		idutilisateur = lireParametreId(session, listeCodesErreur);
		pseudo = lireParametrePseudo(request, listeCodesErreur);
		if(pseudo.trim().equals("")) {
			pseudo = (String) session.getAttribute("pseudo");
		}
		nom = lireParametreNom(request, listeCodesErreur);
		if(nom.trim().equals("")) {
			nom = (String) session.getAttribute("nom");
		}
		prenom = lireParametrePrenom(request, listeCodesErreur);
		if(prenom.trim().equals("")) {
			prenom = (String) session.getAttribute("prenom");
		}
		email = lireParametreEmail(request, listeCodesErreur);
		if(email.trim().equals("")) {
			email = (String) session.getAttribute("email");
		}
		telephone = LireParametreTelephone(request, listeCodesErreur);
		if(telephone.trim().equals("")) {
			telephone = (String) session.getAttribute("telephone");
		}
		rue = lireParametreRue(request, listeCodesErreur);
		if(rue.trim().equals("")) {
			rue = (String) session.getAttribute("rue");
		}
		codePostal = lireParametreCodePostal(request, listeCodesErreur);
		if(codePostal.trim().equals("")) {
			codePostal = (String) session.getAttribute("codePostal");
		}
		ville = lireParametreVille(request, listeCodesErreur);
		if(ville.trim().equals("")) {
			ville = (String) session.getAttribute("ville");
		}
		motDePasse = lireParametreMDP(request, listeCodesErreur);
		credit = (int)session.getAttribute("credit");
		administrateur = (int) session.getAttribute("administrateur");
		desactiver = (int) session.getAttribute("desactiver");
		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp");
			rd.forward(request, response);
		}else {
			try {
				UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
				utilisateursManager.update(idutilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur, desactiver);
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
			rd.forward(request, response);
		}
	}

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

	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur){
		String pseudo;
		List<String> pseudos = new ArrayList<String>();
		boolean alphanumerique;
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		pseudo = request.getParameter("pseudo");
		if (!pseudo.trim().equals("")) {
			
			if (pseudo.length() > 30) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PSEUDO_DEPASSER);
			}
		
			try {
				pseudos = utilisateursManager.selectAllPseudo();
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_PSEUDO);
			}
			if (pseudos.contains(pseudo)) {
				listeCodesErreur.add(CodesResultatServlets.PSEUDO_DEJA_PRIS);
			}
			alphanumerique = isAlphanumerique(pseudo);
			if (alphanumerique == false) {
				listeCodesErreur.add(CodesResultatServlets.PSEUDO_NON_ALPHANUMERIQUE);
			}
		}
		return pseudo;
	}

	private String lireParametreNom(HttpServletRequest request, List<Integer> listeCodesErreur){
		String nom;
		nom = request.getParameter("nom");
		if (!nom.trim().equals("")) {
			if (nom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_NOM_DEPASSER);
			}
		}
		return nom;
	}

	private String lireParametrePrenom(HttpServletRequest request, List<Integer> listeCodesErreur){
		String prenom;
		prenom = request.getParameter("prenom");
		if (!prenom.trim().equals("")) {
			if (prenom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PRENOM_DEPASSER);
			}
		}
		return prenom;
	}

	private String lireParametreEmail(HttpServletRequest request, List<Integer> listeCodesErreur){
		String email;
		List<String> emails = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		email = request.getParameter("email");
		if (!email.trim().equals("")) {
			
			if (email.length() > 40) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_EMAIL_DEPASSER);
			}
			Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$");
			Matcher matcher = pattern.matcher(email);
			boolean emailValide = matcher.find();
			if (emailValide == false) {
				listeCodesErreur.add(CodesResultatServlets.EMAIL_NON_VALIDE);
			}
			try {
				emails = utilisateursManager.selectAllEmail();
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_EMAIL);
			}
			if (emails.contains(email)) {
				listeCodesErreur.add(CodesResultatServlets.EMAIL_DEJA_PRIS);
			}
		}
		return email;
	}
	

	private String LireParametreTelephone(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String telephone;
		telephone = request.getParameter("telephone");
		if (!telephone.trim().equals("")) {
			
			if (telephone.length() > 15) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_TELEPHONE_DEPASSER);
			}
			Pattern pattern = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
			Matcher matcher = pattern.matcher(telephone);
			boolean telephoneValide = matcher.find();
			if (telephoneValide == false) {
				listeCodesErreur.add(CodesResultatServlets.TELEPHONE_NON_VALIDE);
			}
		}
		return telephone;
	}

	private String lireParametreRue(HttpServletRequest request, List<Integer> listeCodesErreur){
		String rue;
		rue = request.getParameter("rue");
		if (!rue.trim().equals("")) {
			
			if (rue.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_RUE_DEPASSER);
			}
		}
		return rue;
	}

	private String lireParametreCodePostal(HttpServletRequest request, List<Integer> listeCodesErreur){
		String codePostal;
		codePostal = request.getParameter("codePostal");
		if (!codePostal.trim().equals("")) {
			
			if (codePostal.length() > 10) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_CODE_POSTAL_DEPASSER);
			}
			if(!codePostal.contains("[0-9]+")) {
				listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_INVALIDE);
			}
		}
		return codePostal;
	}

	private String lireParametreVille(HttpServletRequest request, List<Integer> listeCodesErreur){
		String ville;
		ville = request.getParameter("ville");
		if (!ville.trim().equals("")) {
			
			if (ville.length() > 30) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_VILLE_DEPASSER);
			}
		}
		return ville;
	}

	private String lireParametreMDP(HttpServletRequest request, List<Integer> listeCodesErreur){
		String mdpactuel;
		String nouveauMdp;
		String confirmationMdp;
		mdpactuel = request.getParameter("motDePasse");
		nouveauMdp = request.getParameter("nouveauMdp");
		confirmationMdp = request.getParameter("confirmationMdp");
		
		if(nouveauMdp != null && nouveauMdp.equals(confirmationMdp)) {
			if(nouveauMdp.length() < 12) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MDP_TROP_COURT);
			}
			int compteurMaj = 0;
			for (int i = 0; i < nouveauMdp.length(); i++) {
				char ch = nouveauMdp.charAt(i);
				if (Character.isUpperCase(ch)) {
					compteurMaj++;
				}
			}
			if(compteurMaj <= 0) {
				listeCodesErreur.add(CodesResultatServlets.AUCUNE_MAJ_DANS_MDP);
			}
			if(!nouveauMdp.matches(".*\\d+.*")) {
				listeCodesErreur.add(CodesResultatServlets.AUCUN_CHIFFRES_DANS_MDP);
			}
			Pattern p = Pattern.compile("[^A-Za-z0-9]");
		    Matcher m = p.matcher(nouveauMdp);
		    boolean b = m.find();
		    if(b == false) {
		    	listeCodesErreur.add(CodesResultatServlets.AUCUN_CARACTERE_SPECIAUX_DANS_MDP);
		    }
			String hashed = BCrypt.hashpw(confirmationMdp, BCrypt.gensalt(12));
			confirmationMdp = hashed;
		}else if((!mdpactuel.equals(confirmationMdp)) || (!nouveauMdp.equals(confirmationMdp))) {
			listeCodesErreur.add(CodesResultatServlets.MDP_NON_IDENTIQUE);
		}
		return confirmationMdp;
	}

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

	public boolean isAlphanumerique(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c))
				return false;
		}

		return true;
	}
}
