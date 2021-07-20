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
		int idutilisateur = 0;
		List<Integer> listeCodesErreur = new ArrayList<>();

		idutilisateur = lireParametreId(request, listeCodesErreur);

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		} else if (idutilisateur > 0) {
			// J'ai un id au bon format, je récupère l'utilisateur associée eventuelle
			UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
			chargerUtilisateur(request, utilisateursManager);

			request.setAttribute("utilisateur", utilisateursManager);
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
		
		idutilisateur = lireParametreId(request, listeCodesErreur);
		pseudo = lireParametrePseudo(request, listeCodesErreur);
		nom = lireParametreNom(request, listeCodesErreur);
		prenom = lireParametrePrenom(request, listeCodesErreur);
		email = lireParametreEmail(request, listeCodesErreur);
		telephone = LireParametreTelephone(request, listeCodesErreur);
		rue = lireParametreRue(request, listeCodesErreur);
		codePostal = lireParametreCodePostal(request, listeCodesErreur);
		ville = lireParametreVille(request, listeCodesErreur);
		motDePasse = lireParametreMDP(request, listeCodesErreur);
		credit = Integer.parseInt(request.getParameter("credit"));
		administrateur = Integer.parseInt(request.getParameter("administrateur"));
		desactiver = Integer.parseInt(request.getParameter("desactiver"));
		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}else {
			try {
				UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
				utilisateursManager.update(idutilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur, desactiver);
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
			}
		}
	}

	private int lireParametreId(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int idutilisateur = 0;
		try {
			if (request.getParameter("id") != null) {
				idutilisateur = Integer.parseInt(request.getParameter("id"));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_UTILISATEUR_ERREUR);
		}
		return idutilisateur;
	}

	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur){
		String pseudo;
		boolean alphanumerique;
		Utilisateurs utilisateur = new Utilisateurs();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		pseudo = request.getParameter("pseudo");
		if (pseudo == null || pseudo.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
		if (pseudo.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PSEUDO_DEPASSER);
		}
		
		try {
			utilisateur = utilisateursManager.selectionnerTousParPseudo(pseudo);
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_PSEUDO);
		}
		if (utilisateur != null) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_DEJA_PRIS);
		}
		alphanumerique = isAlphanumerique(pseudo);
		if (alphanumerique == false) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_NON_ALPHANUMERIQUE);
		}

		return pseudo;
	}

	private String lireParametreNom(HttpServletRequest request, List<Integer> listeCodesErreur){
		String nom;
		nom = request.getParameter("nom");
		if (nom == null || nom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.NOM_OBLIGATOIRE);
		} else if (nom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_NOM_DEPASSER);
		}
		return nom;
	}

	private String lireParametrePrenom(HttpServletRequest request, List<Integer> listeCodesErreur){
		String prenom;
		prenom = request.getParameter("prenom");
		if (prenom == null || prenom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PRENOM_OBLIGATOIRE);
		} else if (prenom.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_PRENOM_DEPASSER);
		}
		return prenom;
	}

	private String lireParametreEmail(HttpServletRequest request, List<Integer> listeCodesErreur){
		String email;
		Utilisateurs utilisateur = new Utilisateurs();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		email = request.getParameter("email");
		if (email == null || email.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_OBLIGATOIRE);
		} else if (email.length() > 20) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_EMAIL_DEPASSER);
		}
		Pattern pattern = Pattern.compile("^ [ A - Z0 - 9 ._%+ - ] + @ [ A - Z 0 - 9 . - ] + \\. [ A - Z ] {2,} $");
		Matcher matcher = pattern.matcher(email);
		boolean emailValide = matcher.find();
		if (emailValide == false) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_NON_VALIDE);
		}
		try {
			utilisateur = utilisateursManager.selectionnerTousParEmail(email);
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_EMAIL);
		}
		if (utilisateur != null) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_DEJA_PRIS);
		}
		return email;
	}

	private String LireParametreTelephone(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String telephone;
		telephone = request.getParameter("telephone");
		if (telephone.length() > 15) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_TELEPHONE_DEPASSER);
		}
		Pattern pattern = Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$");
		Matcher matcher = pattern.matcher(telephone);
		boolean telephoneValide = matcher.find();
		if (telephoneValide == false) {
			listeCodesErreur.add(CodesResultatServlets.TELEPHONE_NON_VALIDE);
		}
		return telephone;
	}

	private String lireParametreRue(HttpServletRequest request, List<Integer> listeCodesErreur){
		String rue;
		rue = request.getParameter("rue");
		if (rue == null || rue.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.RUE_OBLIGATOIRE);
		} else if (rue.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_RUE_DEPASSER);
		}
		return rue;
	}

	private String lireParametreCodePostal(HttpServletRequest request, List<Integer> listeCodesErreur){
		String codePostal;
		codePostal = request.getParameter("codePostal");
		if (codePostal == null || codePostal.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_OBLIGATOIRE);
		} else if (codePostal.length() > 10) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_CODE_POSTAL_DEPASSER);
		}
		return codePostal;
	}

	private String lireParametreVille(HttpServletRequest request, List<Integer> listeCodesErreur){
		String ville;
		ville = request.getParameter("ville");
		if (ville == null || ville.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.VILLE_OBLIGATOIRE);
		} else if (ville.length() > 30) {
			listeCodesErreur.add(CodesResultatServlets.TAILLE_MAX_VILLE_DEPASSER);
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
			if(!nouveauMdp.contains("[0-9]+")) {
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
		}else if(!mdpactuel.equals(confirmationMdp) || !nouveauMdp.equals(confirmationMdp)) {
			listeCodesErreur.add(CodesResultatServlets.MDP_NON_IDENTIQUE);
		}
		return confirmationMdp;
	}

	private void chargerUtilisateur(HttpServletRequest request, UtilisateursManager utilisateurManager){
		Utilisateurs utilisateur;

		try {
			int idUtilisateur = lireParametreId(request, null);
			utilisateur = utilisateurManager.selectionner(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);

		} catch (BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
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
