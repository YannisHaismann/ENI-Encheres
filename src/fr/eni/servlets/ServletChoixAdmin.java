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
import javax.servlet.http.HttpSession;

import fr.eni.bll.UtilisateursManager;
import fr.eni.exception.BusinessException;

/**
 * Servlet implementation class ServletChoixAdmin
 */
@WebServlet("/ServletChoixAdmin")
public class ServletChoixAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/AccueilNonConnecte.jsp");
			rd.forward(request, response);
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ChoixDesactiver.jsp");
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

		List<Integer> listeCodesErreur = new ArrayList<>();
		String pseudoAdmin = request.getParameter("pseudoAdmin");
		int choixAdmin = Integer.parseInt(request.getParameter("choixAdmin"));

		List<String> pseudos = new ArrayList<String>();
		UtilisateursManager utilisateursManager = UtilisateursManager.getInstance();

		if (!pseudoAdmin.trim().equals("")) {
			try {
				pseudos = utilisateursManager.selectAllPseudo();
			} catch (BusinessException e) {
				e.printStackTrace();
				listeCodesErreur.add(CodesResultatServlets.ECHEC_RECUPERATION_PAR_PSEUDO);
			}
			if (!pseudos.contains(pseudoAdmin)) {
				listeCodesErreur.add(CodesResultatServlets.PSEUDO_INEXISTANT);
			} else {
				try {
					if ((choixAdmin < 0) || (choixAdmin > 1)) {
						listeCodesErreur.add(CodesResultatServlets.CHOIX_INCORRECT);
					}
					if (listeCodesErreur.size() > 0) {
						request.setAttribute("listeCodesErreur", listeCodesErreur);
						RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ChoixAdmin.jsp");
						rd.forward(request, response);
					}
					if(choixAdmin == 0) {
						utilisateursManager.updateAdminByPseudo(0,pseudoAdmin);
						}else {
							utilisateursManager.updateAdminByPseudo(1,pseudoAdmin);
						}
				} catch (BusinessException e) {
					e.printStackTrace();
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}

			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Administrateur.jsp");
		rd.forward(request, response);
	}
}
