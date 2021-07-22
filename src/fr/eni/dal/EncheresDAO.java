package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Encheres;
import fr.eni.exception.BusinessException;

public interface EncheresDAO {
	public Encheres insert (Encheres enchere) throws BusinessException;
	public List<Encheres> selectAllBy (int idArticle) throws BusinessException;
	public void update(Encheres enchere) throws BusinessException;
	public void delete(Encheres enchere) throws BusinessException;
}
