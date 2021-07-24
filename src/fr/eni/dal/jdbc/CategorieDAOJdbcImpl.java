package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Categories;
import fr.eni.bo.Utilisateurs;
import fr.eni.dal.CategoriesDAO;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.ConnectionProvider;
import fr.eni.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategoriesDAO {
	public static final String INSERT 				= "INSERT INTO CATEGORIES VALUES (?);";
	public static final String SELECT_ALL			= "SELECT * FROM CATEGORIES";
	public static final String UPDATE 				= "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
	public static final String DELETE 				= "DELETE FROM CATEGORIES WHERE no_categorie = ?";
	public static final String SELECT_BY 			= "SELECT * FROM CATEGORIES WHERE libelle = ?";
	
	
	@Override
	public Categories insert(Categories categorie) throws BusinessException {
		
		if(categorie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				
				pstmt = con.prepareStatement(INSERT);
				pstmt.setString(1, categorie.getLibelle());
				
				pstmt.executeUpdate();
				
				pstmt.close();
				con.commit();
				return categorie;
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
	public List<Categories> selectAll() throws BusinessException {
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				List<Categories> categories = new ArrayList<>();
				while(rs.next()) {
					Categories categorie = new Categories();
					categorie.setId(rs.getInt("no_categorie"));
					categorie.setLibelle(rs.getString("libelle"));
					categories.add(categorie);
				}
				rs.close();
				stmt.close();
				
				return categories;
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_OBJET_ECHEC);
			throw businessException;
		}
	}
	@Override
	public void supprimer(Categories categorie) throws BusinessException {

		if(categorie == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {	
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				
				pstmt.setInt(1, categorie.getId());
				
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
	public Categories selectBy(String libelle) throws BusinessException {

		if(libelle == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}
		
		try(Connection con = ConnectionProvider.getConnection()) {
			try {	
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(SELECT_BY);
				
				pstmt.setString(1, libelle);
				
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				
				Categories categorie = new Categories();
				categorie.setId(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				
				pstmt.close();
				con.commit();
				
				return categorie;
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
