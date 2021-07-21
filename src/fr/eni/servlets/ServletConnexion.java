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
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateursManager;
import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;
import fr.eni.utils.BCrypt;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConnexion() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connecter.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		// On verifie les information de cet utilisateur

		List<Integer> listeCodesErreur = new ArrayList<>();

		// on Verifie la saisie s'il est un email ou pseudo

		String saisie = "";
		String motDePasseSaisie = request.getParameter("motDePasse");

		Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$");
		Matcher matcher = pattern.matcher(saisie);
		boolean saisieValide = matcher.find();

		if (saisieValide == true) {
			// veut dire c un email
			saisie = lireParametreEmail(request, listeCodesErreur);

			UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
			Utilisateurs utilisateur = new Utilisateurs();

			try {
				utilisateur = utilisateursManager.selectByEmail(saisie);
				String motDePasse = utilisateur.getMotDePasse();

				Boolean verification = false;

				if (!verification.equals(BCrypt.checkpw(motDePasseSaisie, motDePasse))) {
					listeCodesErreur.add(CodesResultatServlets.MDP_NON_IDENTIQUE);
					if (listeCodesErreur.size() > 0) {
						request.setAttribute("listeCodesErreur", listeCodesErreur);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connecter.jsp");
						rd.forward(request, response);
					}

				}

				if (verification.equals(BCrypt.checkpw(motDePasseSaisie, motDePasse))) {

					// Ajout des informations utilisateur sur sa session

					session.setAttribute("id", utilisateur.getId());
					session.setAttribute("pseudo", utilisateur.getPseudo());
					session.setAttribute("nom", utilisateur.getNom());
					session.setAttribute("prenom", utilisateur.getPrenom());
					session.setAttribute("email", utilisateur.getEmail());
					session.setAttribute("telephone", utilisateur.getTelephone());
					session.setAttribute("rue", utilisateur.getRue());
					session.setAttribute("codePostal", utilisateur.getCodePostal());
					session.setAttribute("ville", utilisateur.getVille());
					session.setAttribute("motDePasse", utilisateur.getMotDePasse());
					session.setAttribute("credit", utilisateur.getCredit());
					session.setAttribute("administrateur", utilisateur.getAdministrateur());
					session.setAttribute("desactiver", utilisateur.getDesactiver());

					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
					rd.forward(request, response);

				}

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}

		else {
			saisie = lireParametrePseudo(request, listeCodesErreur);
			UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
			Utilisateurs utilisateur = new Utilisateurs();
			try {
				utilisateur = utilisateursManager.selectByPseudo(saisie);

				String motDePasse = utilisateur.getMotDePasse();

				Boolean verification = false;

				if (!verification.equals(BCrypt.checkpw(motDePasseSaisie, motDePasse))) {
					listeCodesErreur.add(CodesResultatServlets.MDP_NON_IDENTIQUE);
					if (listeCodesErreur.size() > 0) {
						request.setAttribute("listeCodesErreur", listeCodesErreur);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/connecter.jsp");
						rd.forward(request, response);
					}

				}

				if (verification.equals(BCrypt.checkpw(motDePasseSaisie, motDePasse))) {

					// Ajout des informations utilisateur sur sa session

					session.setAttribute("id", utilisateur.getId());
					session.setAttribute("pseudo", utilisateur.getPseudo());
					session.setAttribute("nom", utilisateur.getNom());
					session.setAttribute("prenom", utilisateur.getPrenom());
					session.setAttribute("email", utilisateur.getEmail());
					session.setAttribute("telephone", utilisateur.getTelephone());
					session.setAttribute("rue", utilisateur.getRue());
					session.setAttribute("codePostal", utilisateur.getCodePostal());
					session.setAttribute("ville", utilisateur.getVille());
					session.setAttribute("motDePasse", utilisateur.getMotDePasse());
					session.setAttribute("credit", utilisateur.getCredit());
					session.setAttribute("administrateur", utilisateur.getAdministrateur());
					session.setAttribute("desactiver", utilisateur.getDesactiver());

					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilConnecte.jsp");
					rd.forward(request, response);

				}

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}

	}

	private String lireParametrePseudo(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String pseudo;
		List<String> pseudos = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		pseudo = request.getParameter("pseudo");
		if (pseudo == null || pseudo.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_OBLIGATOIRE);
		}

		try {
			pseudos = utilisateursManager.selectAllPseudo();
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_PSEUDO);
		}
		if (!pseudos.contains(pseudo)) {
			listeCodesErreur.add(CodesResultatServlets.PSEUDO_INEXISTANT);
		}

		return pseudo;
	}

	private String lireParametreEmail(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String email;
		List<String> emails = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();
		email = request.getParameter("pseudo");
		if (email == null || email.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_OBLIGATOIRE);
		}

		try {
			emails = utilisateursManager.selectAllEmail();
		} catch (BusinessException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_EMAIL);
		}
		if (!emails.contains(email)) {
			listeCodesErreur.add(CodesResultatServlets.EMAIL_INEXISTANT);
		}
		return email;
	}

}
