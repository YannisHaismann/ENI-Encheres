package fr.eni.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	public static DataSource dataSource;
	
	public static Connection getConnection() {
		if(dataSource == null) {
			Context context;
			try {
				context = new InitialContext();
				ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
				
			} catch (NamingException e) {
				e.printStackTrace();
				System.out.println("Une erreur est survenu lors de l'utilisation de la base de données: " + e.getMessage());
			}
		}
		Connection cnx = null;
		try {
			cnx = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Une erreur est survenu lors de l'utilisation de la base de données: " + e.getMessage());
		}
		return cnx;
	}
}
