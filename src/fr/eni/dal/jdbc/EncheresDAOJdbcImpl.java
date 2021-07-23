package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.ArticleVendu;
import fr.eni.bo.Encheres;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.ConnectionProvider;
import fr.eni.dal.EncheresDAO;
import fr.eni.exception.BusinessException;

public class EncheresDAOJdbcImpl implements EncheresDAO {
	
	public static final String INSERT 				= "INSERT INTO ENCHERES VALUES (?, ?, ?, ?);";
	public static final String SELECT_ALL_BY_ID 	= "SELECT * FROM ENCHERES WHERE no_article = ?;";
	public static final String SELECT_ALL 			= "SELECT * FROM ENCHERES;";
	public static final String UPDATE_BY_IDID 		= "UPDATE ENCHERES SET date_enchere = ?,"
													+ " montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?;";
	public static final String DELETE_BY_IDID 		= "DELETE FROM ENCHERES WHERE no_article = ? AND no_utilisateur = ?;";


	@Override
	public Encheres insert(Encheres enchere) throws BusinessException {
		
		if(enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
					
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				
				pstmt = con.prepareStatement(INSERT);
				pstmt.setInt(1, enchere.getIdUtilisateur());
				pstmt.setInt(2, enchere.getIdArticle());
				pstmt.setDate(3, enchere.getDate());
				pstmt.setInt(4, enchere.getMontant());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				con.commit();
				return enchere;
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
	public List<Encheres> selectAllBy(int idArticle) throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_ALL_BY_ID);
				pstmt.setInt(1, idArticle);
				ResultSet rs = pstmt.executeQuery();
				List<Encheres> encheres = new ArrayList<>();
				while(rs.next()) {
					Encheres enchere = new Encheres();
					enchere.setIdUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setIdArticle(rs.getInt("no_article"));
					enchere.setDate(rs.getDate("date_enchere"));
					enchere.setMontant(rs.getInt("montant_enchere"));
					encheres.add(enchere);
				}

				rs.close();
				pstmt.close();
				
				return encheres;
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
	public void update(Encheres enchere) throws BusinessException {
		
		if(enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				
				pstmt = con.prepareStatement(UPDATE_BY_IDID);
				pstmt.setDate(1, enchere.getDate());
				pstmt.setInt(2, enchere.getMontant());
				pstmt.setInt(3, enchere.getIdUtilisateur());
				pstmt.setInt(4, enchere.getIdArticle());
				
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
	public void delete(Encheres enchere) throws BusinessException {
		
		if(enchere == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {	
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_BY_IDID);
				
				pstmt.setInt(1, enchere.getIdArticle());
				pstmt.setInt(2, enchere.getIdUtilisateur());
				
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
	
	
	
}
