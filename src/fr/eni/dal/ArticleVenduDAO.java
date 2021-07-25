package fr.eni.dal;

import java.util.List;

import fr.eni.bo.ArticleVendu;
import fr.eni.exception.BusinessException;

public interface ArticleVenduDAO {
	public ArticleVendu insert(ArticleVendu article) throws BusinessException;
	public ArticleVendu selectBy(int id) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public void update (ArticleVendu article) throws BusinessException;
	public void delete (ArticleVendu article) throws BusinessException;
	public void deleteByIdUtilisateur(int id) throws BusinessException;
	public List<ArticleVendu> selectAllByIdUtilisateur(int id) throws BusinessException;
}
