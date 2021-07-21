package fr.eni.dal;

import fr.eni.dal.jdbc.ArticleVenduDAOJdbcImpl;
<<<<<<< HEAD
import fr.eni.dal.jdbc.CategorieDAOJdbcImpl;
=======
>>>>>>> d5b6ba2b8d5caf7f9c5c657915785cdbe8aa33c2
import fr.eni.dal.jdbc.EncheresDAOJdbcImpl;
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
<<<<<<< HEAD
	public static CategoriesDAO getCategoriesDAO() {
		CategoriesDAO CategoriesDao = new CategorieDAOJdbcImpl();
		return CategoriesDao;
	}
=======
>>>>>>> d5b6ba2b8d5caf7f9c5c657915785cdbe8aa33c2
}
