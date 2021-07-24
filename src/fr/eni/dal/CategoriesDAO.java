package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.exception.BusinessException;

public interface CategoriesDAO {
	public Categories insert(Categories categorie) throws BusinessException;
	public List<Categories> selectAll() throws BusinessException;
	public void supprimer(Categories categorie) throws BusinessException;
	Categories selectBy(String libelle) throws BusinessException;
}
