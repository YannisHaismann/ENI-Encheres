package fr.eni.bll;

import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.dal.CategoriesDAO;
import fr.eni.dal.DAOFactory;
import fr.eni.exception.BusinessException;

public class CategoriesManager {
	private static CategoriesManager manager;
	private CategoriesDAO categoriesDao;
	
	public CategoriesManager() {
		this.categoriesDao = DAOFactory.getCategoriesDAO();
	}
	
	public static CategoriesManager getInstance() {
		if(CategoriesManager.manager == null) {
			CategoriesManager.manager = new CategoriesManager();
		}
		return CategoriesManager.manager;
	}
	
	public void ajouter(String libelle) throws BusinessException {
		
		try {
			Categories categorie = new Categories();
			categorie.setLibelle(libelle);
			
			this.categoriesDao.insert(categorie);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public void supprimer(int id) throws BusinessException{
		try {
			Categories categorie = new Categories();
			categorie.setId(id);
			this.categoriesDao.supprimer(categorie);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<Categories> selectionnerTous() throws BusinessException{
		return this.categoriesDao.selectAll();
	}
	
}
