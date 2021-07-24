package fr.eni.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.bo.Utilisateurs;
import fr.eni.dal.CodesResultatDAL;
import fr.eni.dal.ConnectionProvider;
import fr.eni.dal.UtilisateursDAO;
import fr.eni.exception.BusinessException;

public class UtilisateursDAOJdbcImpl implements UtilisateursDAO {
	public static final String INSERT 					= "INSERT INTO UTILISATEURS VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String SELECT_BY_ID 			= "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?;";
	public static final String SELECT_ALL 				= "SELECT * FROM UTILISATEURS";
	public static final String SELECT_ALL_EMAIL 		= "SELECT email FROM UTILISATEURS";
	public static final String SELECT_ALL_PSEUDO 		= "SELECT pseudo FROM UTILISATEURS";
	public static final String SELECT_BY_PSEUDO 		= "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	public static final String UPDATE_BY_ID 					= "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?,"
			+ " code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ?, desactiver = ?  WHERE no_utilisateur = ?;";
	public static final String UPDATE_DESACTIVER_BY_PSEUDO 		= "UPDATE UTILISATEURS SET desactiver = ? WHERE pseudo = ?";
	public static final String UPDATE_ADMIN_BY_PSEUDO	= "UPDATE UTILISATEURS SET administrateur = ? WHERE pseudo = ?";
	public static final String DELETE_BY_ID 			= "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	public static final String DELETE_BY_PSEUDO 		= "DELETE FROM UTILISATEURS WHERE pseudo = ?";
	public static final String SELECT_BY_EMAIL 			= "SELECT * FROM UTILISATEURS WHERE email = ?";
	

	@Override
	public Utilisateurs insert(Utilisateurs utilisateur) throws BusinessException {

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {

			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt;
				int key = -1;

				pstmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setInt(11, utilisateur.getAdministrateur());
				pstmt.setInt(12, utilisateur.getDesactiver());

				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					key = rs.getInt(1);
					utilisateur.setId(key);
				}
				pstmt.close();
				con.commit();
				return utilisateur;
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
	public Utilisateurs selectBy(int id) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				Utilisateurs nouvelUtilisateur = new Utilisateurs();
				nouvelUtilisateur.setId(rs.getInt("no_utilisateur"));
				nouvelUtilisateur.setPseudo(rs.getString("pseudo"));
				nouvelUtilisateur.setNom(rs.getString("nom"));
				nouvelUtilisateur.setPrenom(rs.getString("prenom"));
				nouvelUtilisateur.setEmail(rs.getString("email"));
				nouvelUtilisateur.setTelephone(rs.getString("telephone"));
				nouvelUtilisateur.setRue(rs.getString("rue"));
				nouvelUtilisateur.setCodePostal(rs.getString("code_postal"));
				nouvelUtilisateur.setVille(rs.getString("ville"));
				nouvelUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				nouvelUtilisateur.setCredit(rs.getInt("credit"));
				nouvelUtilisateur.setAdministrateur(rs.getInt("administrateur"));
				nouvelUtilisateur.setDesactiver(rs.getInt("desactiver"));

				rs.close();
				pstmt.close();

				return nouvelUtilisateur;
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
	public List<Utilisateurs> selectAll() throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				List<Utilisateurs> utilisateurs = new ArrayList<>();
				while (rs.next()) {
					Utilisateurs nouvelUtilisateur = new Utilisateurs();
					nouvelUtilisateur.setId(rs.getInt("no_utilisateur"));
					nouvelUtilisateur.setPseudo(rs.getString("pseudo"));
					nouvelUtilisateur.setNom(rs.getString("nom"));
					nouvelUtilisateur.setPrenom(rs.getString("prenom"));
					nouvelUtilisateur.setEmail(rs.getString("email"));
					nouvelUtilisateur.setTelephone(rs.getString("telephone"));
					nouvelUtilisateur.setRue(rs.getString("rue"));
					nouvelUtilisateur.setCodePostal(rs.getString("code_postal"));
					nouvelUtilisateur.setVille(rs.getString("ville"));
					nouvelUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
					nouvelUtilisateur.setCredit(rs.getInt("credit"));
					nouvelUtilisateur.setAdministrateur(rs.getInt("administrateur"));
					nouvelUtilisateur.setDesactiver(rs.getInt("desactiver"));
					utilisateurs.add(nouvelUtilisateur);
				}
				rs.close();
				stmt.close();

				return utilisateurs;
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
	public void update(Utilisateurs utilisateur) throws BusinessException {

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				con.setAutoCommit(false);
				pstmt = con.prepareStatement(UPDATE_BY_ID);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getCredit());
				pstmt.setInt(11, utilisateur.getAdministrateur());
				pstmt.setInt(12, utilisateur.getDesactiver());
				pstmt.setInt(13, utilisateur.getId());

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
	public void delete(Utilisateurs utilisateur) throws BusinessException {

		if (utilisateur == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_PARAM);
			throw businessException;
		}

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_BY_ID);

				pstmt.setInt(1, utilisateur.getId());

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
	
	public List<String> selectAllEmail() throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL_EMAIL);
				List<String> emails = new ArrayList<>();
				while (rs.next()) {
					String nouvelleEmail = "";
					nouvelleEmail = rs.getString("email");
					emails.add(nouvelleEmail);
				}
				rs.close();
				stmt.close();

				return emails;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_EMAIL_ECHEC);
			throw businessException;
		}
	}
	
	public List<String> selectAllPseudo() throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				Statement stmt;
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL_PSEUDO);
				List<String> pseudos = new ArrayList<>();
				while (rs.next()) {
					String pseudo = "";
					pseudo = rs.getString("pseudo");
					pseudos.add(pseudo);
				}
				rs.close();
				stmt.close();

				return pseudos;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_PSEUDO_ECHEC);
			throw businessException;
		}
	}
	
	public Utilisateurs selectByPseudo(String pseudo) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_PSEUDO);
				pstmt.setString(1, pseudo);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				Utilisateurs nouvelUtilisateur = new Utilisateurs();
				nouvelUtilisateur.setId(rs.getInt("no_utilisateur"));
				nouvelUtilisateur.setPseudo(rs.getString("pseudo"));
				nouvelUtilisateur.setNom(rs.getString("nom"));
				nouvelUtilisateur.setPrenom(rs.getString("prenom"));
				nouvelUtilisateur.setEmail(rs.getString("email"));
				nouvelUtilisateur.setTelephone(rs.getString("telephone"));
				nouvelUtilisateur.setRue(rs.getString("rue"));
				nouvelUtilisateur.setCodePostal(rs.getString("code_postal"));
				nouvelUtilisateur.setVille(rs.getString("ville"));
				nouvelUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				nouvelUtilisateur.setCredit(rs.getInt("credit"));
				nouvelUtilisateur.setAdministrateur(rs.getInt("administrateur"));
				nouvelUtilisateur.setDesactiver(rs.getInt("desactiver"));

				rs.close();
				pstmt.close();

				return nouvelUtilisateur;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_PSEUDO_OBJET_ECHEC);
			throw businessException;
		}
	}
	
	
	public Utilisateurs selectByEmail(String email) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				PreparedStatement pstmt;
				pstmt = con.prepareStatement(SELECT_BY_EMAIL);
				pstmt.setString(1, email);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				Utilisateurs nouvelUtilisateur = new Utilisateurs();
				nouvelUtilisateur.setId(rs.getInt("no_utilisateur"));
				nouvelUtilisateur.setPseudo(rs.getString("pseudo"));
				nouvelUtilisateur.setNom(rs.getString("nom"));
				nouvelUtilisateur.setPrenom(rs.getString("prenom"));
				nouvelUtilisateur.setEmail(rs.getString("email"));
				nouvelUtilisateur.setTelephone(rs.getString("telephone"));
				nouvelUtilisateur.setRue(rs.getString("rue"));
				nouvelUtilisateur.setCodePostal(rs.getString("code_postal"));
				nouvelUtilisateur.setVille(rs.getString("ville"));
				nouvelUtilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				nouvelUtilisateur.setCredit(rs.getInt("credit"));
				nouvelUtilisateur.setAdministrateur(rs.getInt("administrateur"));
				nouvelUtilisateur.setDesactiver(rs.getInt("desactiver"));

				rs.close();
				pstmt.close();

				return nouvelUtilisateur;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_BY_EMAIL_OBJET_ECHEC);
			throw businessException;
		}
	}
	
	@Override
	public void deleteByPseudo(String pseudo) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(DELETE_BY_PSEUDO);

				pstmt.setString(1, pseudo);

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
	public void updateDesactiverByPseudo(int choix, String pseudo) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(UPDATE_DESACTIVER_BY_PSEUDO);
				
				pstmt.setInt(1, choix);
				pstmt.setString(2, pseudo);

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
	public void updateAdminByPseudo(int choix, String pseudo) throws BusinessException {

		try (Connection con = ConnectionProvider.getConnection()) {
			try {
				con.setAutoCommit(false);
				PreparedStatement pstmt = con.prepareStatement(UPDATE_ADMIN_BY_PSEUDO);

				pstmt.setInt(1, choix);
				pstmt.setString(2, pseudo);

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
	
}
