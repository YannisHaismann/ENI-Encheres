package fr.eni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.bll.UtilisateursManager;
import fr.eni.exception.BusinessException;
import fr.eni.utils.BCrypt;



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
		
		String pseudo;
		String nom;
		String prenom;
		String email;	
		String telephone;	
		String rue;	
		String codePostal;
		String ville;
		String motDePasse;
		int credit = 0;
		int administrateur = 0;
		int desactiver = 0;
		
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		pseudo = lireParametrePseudo(request, listeCodesErreur);
		nom = lireParametreNom(request, listeCodesErreur);
		prenom = lireParametrePrenom(request, listeCodesErreur);
		email = lireParametreEmail(request, listeCodesErreur);
		telephone = LireParametreTelephone(request, listeCodesErreur);
		rue = lireParametreRue(request, listeCodesErreur);
		codePostal = lireParametreCodePostal(request, listeCodesErreur);
		ville = lireParametreVille(request, listeCodesErreur);
		motDePasse = lireParametreMDP(request, listeCodesErreur);

		
		if(listeCodesErreur.size()>0)
		{
			request.setAttribute("listeCodesErreur", listeCodesErreur);
		}else {
			try {
				UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
				utilisateursManager.ajouter(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, 
				administrateur, desactiver);
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				
			}
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp"); 
			rd.forward(request, response);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp"); // A REVOIR 
		rd.forward(request, response);
		
	}
		
		
		
		

	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur){
		String pseudo;
		List<String> pseudos = new ArrayList<String>();
		boolean alphanumerique;
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		pseudo = request.getParameter("pseudo");
		if (pseudo == null || pseudo.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}
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
		
		// APRES TOUT VERIFICATION ON VERIFIE S'IL CONTIENT LES DEUX : CHIFFRES ET LETTRES
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
		List<String> emails = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		email = request.getParameter("email");
		if (email == null || email.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_OBLIGATOIRE);
		} else if (email.length() > 40) {
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
		boolean num = isNumerique(codePostal);
		if (num == false){
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_INVALIDE);
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

		String motDePasse;
		String confirmation;
		motDePasse 		= request.getParameter("motDePasse");
		confirmation = request.getParameter("confirmation");
		
		if(motDePasse != null && motDePasse.equals(confirmation)) {
			if(motDePasse.length() < 12) {
				listeCodesErreur.add(CodesResultatServlets.TAILLE_MDP_TROP_COURT);
			}
			int compteurMaj = 0;
			for (int i = 0; i < motDePasse.length(); i++) {
				char ch = motDePasse.charAt(i);
				if (Character.isUpperCase(ch)) {
					compteurMaj++;
				}
			}
			if(compteurMaj <= 0) {
				listeCodesErreur.add(CodesResultatServlets.AUCUNE_MAJ_DANS_MDP);
			}
			if(!motDePasse.matches(".*\\d+.*")) {
				listeCodesErreur.add(CodesResultatServlets.AUCUN_CHIFFRES_DANS_MDP);
			}
			Pattern p = Pattern.compile("[^A-Za-z0-9]");
		    Matcher m = p.matcher(motDePasse);
		    boolean b = m.find();
		    if(b == false) {
		    	listeCodesErreur.add(CodesResultatServlets.AUCUN_CARACTERE_SPECIAUX_DANS_MDP);
		    }
			String hashed = BCrypt.hashpw(confirmation, BCrypt.gensalt(12));
			confirmation = hashed;
		}else if(!motDePasse.equals(confirmation) ) {
			listeCodesErreur.add(CodesResultatServlets.MDP_NON_IDENTIQUE);
		}
		return confirmation;
	}

	public boolean isAlphanumerique(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c) && !Character.isLetter(c))
				return false;
		}

		return true;
	}
	
	public boolean isNumerique(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c))
				return false;
		}

		return true;
	}

}
