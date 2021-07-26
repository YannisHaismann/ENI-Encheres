package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.ArticleVendu;
import fr.eni.dal.ArticleVenduDAO;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.ConnectionProvider;
import fr.eni.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {
	
	public static final String INSERT 					= "INSERT INTO ARTICLES_VENDUS VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String SELECT_BY_ID 			= "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?;";
	public static final String SELECT_ALL 				= "SELECT * FROM ARTICLES_VENDUS;";
	public static final String SELECT_BY_UTILISATEUR 	= "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	public static final String UPDATE_BY_ID 			= "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?,"
														+ "	date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article = ?;";
	public static final String DELETE_BY_ID 			= "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?;";
	public static final String DELETE_BY_ID_UTILISATEUR = "DELETE FROM ENCHERES WHERE no_utilisateur = ?;";

	@Override
	public ArticleVendu insert(ArticleVendu article) throws BusinessException {
		
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				int key = -1;
				
				pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNom());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, article.getDateDebut());
				pstmt.setDate(4, article.getDateFin());
				pstmt.setInt(5, article.getPrixInitial());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getIdUtilisateur());
				pstmt.setInt(8, article.getIdCategorie());
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					key = rs.getInt(1);
					article.setId(key);
				}
				pstmt.close();
				con.commit();
				return article;
			}catch(Exception e) {
				e.printStackTrace();
				con.rollback();
				throw e;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public ArticleVendu selectBy(int id) throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				ArticleVendu nouvelArticle = new ArticleVendu();
				nouvelArticle.setId(rs.getInt("no_article"));
				nouvelArticle.setNom(rs.getString("nom_article"));
				nouvelArticle.setDescription(rs.getString("description"));
				nouvelArticle.setDateDebut(rs.getDate("date_debut_encheres"));
				nouvelArticle.setDateFin(rs.getDate("date_fin_encheres"));
				nouvelArticle.setPrixInitial(rs.getInt("prix_initial"));
				nouvelArticle.setPrixVente(rs.getInt("prix_vente"));
				nouvelArticle.setIdUtilisateur(rs.getInt("no_utilisateur"));
				nouvelArticle.setIdCategorie(rs.getInt("no_categorie"));

				rs.close();
				pstmt.close();
				
				return nouvelArticle;
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<ArticleVendu> selectAll() throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				List<ArticleVendu> articles = new ArrayList<>();
						
				while(rs.next()) {
					ArticleVendu nouvelArticle = new ArticleVendu();
					nouvelArticle.setId(rs.getInt("no_article"));
					nouvelArticle.setNom(rs.getString("nom_article"));
					nouvelArticle.setDescription(rs.getString("description"));
					nouvelArticle.setDateDebut(rs.getDate("date_debut_encheres"));
					nouvelArticle.setDateFin(rs.getDate("date_fin_encheres"));
					nouvelArticle.setPrixInitial(rs.getInt("prix_initial"));
					nouvelArticle.setPrixVente(rs.getInt("prix_vente"));
					nouvelArticle.setIdUtilisateur(rs.getInt("no_utilisateur"));
					nouvelArticle.setIdCategorie(rs.getInt("no_categorie"));
					articles.add(nouvelArticle);
				}
			
				rs.close();
				stmt.close();
				
				return articles;
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_ID_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void update(ArticleVendu article) throws BusinessException {
		
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				
				pstmt = con.prepareStatement(UPDATE_BY_ID);
				pstmt.setString(1, article.getNom());
				pstmt.setString(2, article.getDescription());
				pstmt.setDate(3, article.getDateDebut());
				pstmt.setDate(4, article.getDateFin());
				pstmt.setInt(5, article.getPrixInitial());
				pstmt.setInt(6, article.getPrixVente());
				pstmt.setInt(7, article.getIdUtilisateur());
				pstmt.setInt(8, article.getIdCategorie());
				pstmt.setInt(9, article.getId());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				con.commit();
				
			}catch(Exception e) {
				e.printStackTrace();
				con.rollback();
				throw e;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void delete(ArticleVendu article) throws BusinessException {
		
		if(article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {	
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID);
				
				pstmt.setInt(1, article.getId());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				
				con.commit();
			}catch(Exception e) {
				e.printStackTrace();
				con.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public void deleteByIdUtilisateur(int id) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID_UTILISATEUR);

				pstmt.setInt(1, id);

				pstmt.executeUpdate();

				pstmt.close();

				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				con.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}

	}
	
	@Override
	public List<ArticleVendu> selectAllByIdUtilisateur(int id) throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_UTILISATEUR);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				List<ArticleVendu> articles = new ArrayList<>();
						
				while(rs.next()) {
					ArticleVendu nouvelArticle = new ArticleVendu();
					nouvelArticle.setId(rs.getInt("no_article"));
					nouvelArticle.setNom(rs.getString("nom_article"));
					nouvelArticle.setDescription(rs.getString("description"));
					nouvelArticle.setDateDebut(rs.getDate("date_debut_encheres"));
					nouvelArticle.setDateFin(rs.getDate("date_fin_encheres"));
					nouvelArticle.setPrixInitial(rs.getInt("prix_initial"));
					nouvelArticle.setPrixVente(rs.getInt("prix_vente"));
					nouvelArticle.setIdUtilisateur(rs.getInt("no_utilisateur"));
					nouvelArticle.setIdCategorie(rs.getInt("no_categorie"));
					articles.add(nouvelArticle);
				}
			
				rs.close();
				pstmt.close();
				
				return articles;
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_LISTE_BY_ID_OBJET_ECHEC);
			throw businessException;
		}
	}
	
}
