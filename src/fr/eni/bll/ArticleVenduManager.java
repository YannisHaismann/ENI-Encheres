package fr.eni.bll;

import java.sql.Date;
import java.util.List;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.DAOFactory;
import fr.eni.exception.BusinessException;

public class ArticleVenduManager {

	private static ArticleVenduManager manager;
	private ArticleVenduDAO articleVenduDao;

	public ArticleVenduManager() {
		this.articleVenduDao = DAOFactory.getArticleVenduDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if(ArticleVenduManager.manager == null) {
			ArticleVenduManager.manager = new ArticleVenduManager();
		}
		return ArticleVenduManager.manager;
	}
	
	public void ajouter(String nom, String description, Date dateDebut, Date dateFin,
						int prixInitial, int prixVente, int idUtilisateur, int idCategorie) throws BusinessException {	
		try {
			ArticleVendu article = new ArticleVendu();
			article.setNom(nom);
			article.setDescription(description);
			article.setDateDebut(dateDebut);
			article.setDateFin(dateFin);
			article.setPrixInitial(prixInitial);
			article.setPrixVente(prixVente);
			article.setIdUtilisateur(idUtilisateur);
			article.setIdCategorie(idCategorie);
			
			this.articleVenduDao.insert(article);
		}catch(BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void update(int id, String nom, String description, Date dateDebut, Date dateFin,
						int prixInitial, int prixVente, int idUtilisateur, int idCategorie) throws BusinessException{
		ArticleVendu article = null;
		try {
			if (this.selectionner(id) != null) {
				article = this.selectionner(id);
			} else {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatBLL.NULL_UTILISATEUR);
				throw businessException;
			}
			article.setId(id);
			article.setNom(nom);
			article.setDescription(description);
			article.setDateDebut(dateDebut);
			article.setDateFin(dateFin);
			article.setPrixInitial(prixInitial);
			article.setPrixVente(prixVente);
			article.setIdUtilisateur(idUtilisateur);
			article.setIdCategorie(idCategorie);
			
			this.articleVenduDao.update(article);
		} catch (BusinessException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void supprimer(int id) throws BusinessException {
		if (this.selectionner(id) != null) {
			this.articleVenduDao.delete(this.selectionner(id));
		}
	}
	
	public List<ArticleVendu> selectionnerTous() throws BusinessException {
		return this.articleVenduDao.selectAll();
	}
	
	public ArticleVendu selectionner(int id) throws BusinessException {
		return this.articleVenduDao.selectBy(id);
	}
}
