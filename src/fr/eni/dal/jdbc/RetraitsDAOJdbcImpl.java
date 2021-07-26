package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Retraits;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.ConnectionProvider;
import fr.eni.dal.RetraitsDAO;
import fr.eni.exception.BusinessException;

public class RetraitsDAOJdbcImpl implements RetraitsDAO {
	
	public static final String INSERT 		= "INSERT INTO RETRAITS VALUES (?, ?, ?, ?);";
	public static final String SELECT_BY_ID = "SELECT * FROM RETRAITS WHERE no_article = ?;";
	public static final String SELECT_ALL 	= "SELECT * FROM RETRAITS;";
	public static final String UPDATE 		= "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?;";
	public static final String DELETE 		= "DELETE FROM RETRAITS WHERE no_article = ?";
	public static final String DELETE_by_ID = "DELETE FROM RETRAITS WHERE no_article = ?";

	@Override
	public Retraits insert(Retraits retrait) throws BusinessException {
		
		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {

			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;

				pstmt = con.prepareStatement(INSERT);
				
				pstmt.setInt(1, retrait.getId());
				pstmt.setString(2, retrait.getRue());
				pstmt.setString(3, retrait.getCodePostal());
				pstmt.setString(4, retrait.getVille());

				pstmt.executeUpdate();
				
				pstmt.close();
				con.commit();
				return retrait;
			} catch (Exception e) {
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
	public Retraits selectBy(int idArticle) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, idArticle);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				Retraits retrait = new Retraits();
				retrait.setId(rs.getInt("no_article"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setVille(rs.getString("ville"));

				rs.close();
				pstmt.close();

				return retrait;
			} catch (Exception e) {
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
	public List<Retraits> selectAll() throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				List<Retraits> retraits = new ArrayList<>();
				while (rs.next()) {
					Retraits retrait = new Retraits();
					retrait.setId(rs.getInt("no_article"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));
				}
				rs.close();
				stmt.close();

				return retraits;
			} catch (Exception e) {
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
	public void update(Retraits retrait) throws BusinessException {

		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1, retrait.getRue());
				pstmt.setString(2, retrait.getCodePostal());
				pstmt.setString(3, retrait.getVille());
				pstmt.setInt(4, retrait.getId());
				
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
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void delete(Retraits retrait) throws BusinessException {

		if (retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, retrait.getId());

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
	public void deleteById(int id) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_by_ID);

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

}
