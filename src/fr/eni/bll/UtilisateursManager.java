package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Utilisateurs;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.UtilisateursDAO;
import fr.eni.exception.BusinessException;

public class UtilisateursManager {

	private static UtilisateursManager manager;
	private UtilisateursDAO utilisateursDao;

	public UtilisateursManager() {
		this.utilisateursDao = DAOFactory.getUtilisateursDAO();
	}
	
	public static UtilisateursManager getInstance() {
		if(UtilisateursManager.manager == null) {
			UtilisateursManager.manager = new UtilisateursManager();
		}
		return UtilisateursManager.manager;
	}
	public void ajouter(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, int administrateur, int desactiver)
			throws BusinessException {

		try {
			Utilisateurs utilisateur = new Utilisateurs();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setCredit(credit);
			utilisateur.setAdministrateur(administrateur);
			utilisateur.setDesactiver(desactiver);

			this.utilisateursDao.insert(utilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void update(int id, String pseudo, String nom, String prenom, String email, String tel, String rue,
			String codePostal, String ville, String motDePasse, int credit, int administrateur, int desactiver)
			throws BusinessException {
		Utilisateurs utilisateur = null;
		try {
			if (this.selectionner(id) != null) {
				utilisateur = this.selectionner(id);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatBLL.NULL_UTILISATEUR);
				throw businessException;
			}
			utilisateur.setId(id);
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(tel);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setCredit(credit);
			utilisateur.setAdministrateur(administrateur);
			utilisateur.setDesactiver(desactiver);

			this.utilisateursDao.update(utilisateur);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void supprimer(int id) throws BusinessException {
		if (this.selectionner(id) != null) {
			this.utilisateursDao.delete(this.selectionner(id));
		}
	}

	public List<Utilisateurs> selectionnerTous() throws BusinessException {
		return this.utilisateursDao.selectAll();
	}

	public Utilisateurs selectionner(int id) throws BusinessException {
		return this.utilisateursDao.selectBy(id);
	}

}
