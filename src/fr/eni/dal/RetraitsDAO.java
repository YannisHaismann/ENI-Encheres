package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.exception.BusinessException;

public interface RetraitsDAO {

	public Retraits insert(Retraits retrait) throws BusinessException;
	public Retraits selectBy(int idArticle) throws BusinessException;
	public List<Retraits> selectAll() throws BusinessException;
	public void update (Retraits retrait) throws BusinessException;
	public void delete(Retraits retrait) throws BusinessException;
	
}
