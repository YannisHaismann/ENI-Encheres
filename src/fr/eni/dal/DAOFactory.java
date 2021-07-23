package fr.eni.dal;

import fr.eni.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.dal.jdbc.CategorieDAOJdbcImpl;
import fr.eni.dal.jdbc.EncheresDAOJdbcImpl;
import fr.eni.dal.jdbc.RetraitsDAOJdbcImpl;
import fr.eni.dal.jdbc.UtilisateursDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateursDAO getUtilisateursDAO() {
		UtilisateursDAO utilisateursDao = new UtilisateursDAOJdbcImpl();
		return utilisateursDao;
	}
	public static ArticleVenduDAO getArticleVenduDAO() {
		ArticleVenduDAO articleVenduDao = new ArticleVenduDAOJdbcImpl();
		return articleVenduDao;
	}
	public static EncheresDAO getEncheresDAO() {
		EncheresDAO encheresDao = new EncheresDAOJdbcImpl();
		return encheresDao;
	}
	public static CategoriesDAO getCategoriesDAO() {
		CategoriesDAO CategoriesDao = new CategorieDAOJdbcImpl();
		return CategoriesDao;
	}
	public static RetraitsDAO getRetraitsDAO() {
		RetraitsDAO retraitsDao = new RetraitsDAOJdbcImpl();
		return retraitsDao;
	}
}
