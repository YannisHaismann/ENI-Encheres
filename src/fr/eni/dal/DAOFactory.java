package fr.eni.dal;

import fr.eni.dal.jdbc.ArticleVenduDAOJdbcImpl;
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
}
