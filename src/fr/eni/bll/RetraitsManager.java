package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.bo.Utilisateurs;
import fr.eni.dal.DAOFactory;
import fr.eni.dal.RetraitsDAO;
import fr.eni.exception.BusinessException;

public class RetraitsManager {
	private static RetraitsManager manager;
	private RetraitsDAO retraitsDao;
	
	public RetraitsManager() {
		this.retraitsDao = DAOFactory.getRetraitsDAO();
	}
	
	public static RetraitsManager getInstance() {
		if(RetraitsManager.manager == null) {
			RetraitsManager.manager = new RetraitsManager();
		}
		return RetraitsManager.manager;
	}
	
	public void ajouter(int idArticle, String rue, String codePostal, String ville) throws BusinessException {
		try {
			Retraits retrait = new Retraits();
			retrait.setId(idArticle);
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);

			this.retraitsDao.insert(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void update(int idArticle, String rue, String codePostal, String ville) throws BusinessException{
		try {
			Retraits retrait = new Retraits();
			retrait.setId(idArticle);
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);

			this.retraitsDao.update(retrait);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void supprimer(int idArticle) throws BusinessException{
		if(this.selectionner(idArticle) != null) {
			this.retraitsDao.delete(this.selectionner(idArticle));
		}
	}
	
	public Retraits selectionner(int idArticle) throws BusinessException {
		return this.retraitsDao.selectBy(idArticle);
	}
	
	public List<Retraits> selectionnerTous() throws BusinessException {
		return this.retraitsDao.selectAll();
	}
}
