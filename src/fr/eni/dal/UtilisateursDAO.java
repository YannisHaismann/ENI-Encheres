package fr.eni.dal;

import java.util.List;

import fr.eni.bo.Utilisateurs;
import fr.eni.exception.BusinessException;

public interface UtilisateursDAO {
	public Utilisateurs insert(Utilisateurs utilisateur) throws BusinessException;
	public Utilisateurs selectBy(int id) throws BusinessException;
	public List<Utilisateurs> selectAll() throws BusinessException;
	public void update(Utilisateurs utilisateur) throws BusinessException;
	public void delete(Utilisateurs utilisateur) throws BusinessException;
	public List<String> selectAllEmail() throws BusinessException;
	public List<String> selectAllPseudo() throws BusinessException;
	public Utilisateurs selectByPseudo(String pseudo) throws BusinessException;
	public Utilisateurs selectByEmail(String email) throws BusinessException;
	public void deleteByPseudo(String pseudo) throws BusinessException;
	public void updateDesactiverByPseudo(int choix, String pseudo) throws BusinessException;
	public void updateAdminByPseudo(int choix, String pseudo) throws BusinessException;
}
