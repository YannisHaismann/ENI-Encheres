package fr.eni.dal;

import fr.eni.dal.jdbc.UtilisateursDAOJdbcImpl;

public class DAOFactory {
	public static UtilisateursDAO getUtilisateursDAO() {
		UtilisateursDAO utilisateursDao = new UtilisateursDAOJdbcImpl();
		return utilisateursDao;
	}
}
