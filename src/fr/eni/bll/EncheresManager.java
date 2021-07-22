package fr.eni.bll;

import java.sql.Date;
import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.EncheresDAO;
import fr.eni.exception.BusinessException;

public class EncheresManager {

	private static EncheresManager manager;
	private EncheresDAO encheresDao;

	public EncheresManager() {
		this.encheresDao = DAOFactory.getEncheresDAO();
	}
	
	public static EncheresManager getInstance() {
		if(EncheresManager.manager == null) {
			EncheresManager.manager = new EncheresManager();
		}
		return EncheresManager.manager;
	}
	
	public void ajouter(int idUtilisateur, int idArticle, Date dateEnchere, int montant) throws BusinessException {
		try {
			Encheres enchere = new Encheres();
			enchere.setIdUtilisateur(idUtilisateur);
			enchere.setIdArticle(idArticle);
			enchere.setDate(dateEnchere);
			enchere.setMontant(montant);
			
			this.encheresDao.insert(enchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Encheres> selectionnerParIdArticle(int idArticle) throws BusinessException{
		return this.encheresDao.selectAllBy(idArticle);
	}
	
	public void update(Date dateEnchere, int montant, int idUtilisateur, int idArticle) throws BusinessException{
		try {
			Encheres enchere = new Encheres();
			enchere.setIdUtilisateur(idUtilisateur);
			enchere.setIdArticle(idArticle);
			enchere.setDate(dateEnchere);
			enchere.setMontant(montant);
			
			this.encheresDao.update(enchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void suprimer(int idArticle, int idUtilisateur) throws BusinessException {
		if(this.selectionnerParIdArticle(idArticle) != null) {
			Encheres enchere = new Encheres();
			enchere.setIdUtilisateur(idUtilisateur);
			enchere.setIdArticle(idArticle);
			
			this.encheresDao.delete(enchere);
		}
	}
	
}
