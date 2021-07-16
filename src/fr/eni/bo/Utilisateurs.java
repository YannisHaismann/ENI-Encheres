package fr.eni.bo;

public class Utilisateurs {
	private int id;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;	
	private String telephone;	
	private String rue;	
	private int codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private int administrateur;
	private int desactiver;
	
	public Utilisateurs() {}
	
	public Utilisateurs(String pseudo, String nom, String prenom, String email,	String telephone, String rue, int codePostal, String ville,
						String motDePasse, int credit, int administrateur, int desactiver) {
		this.setPseudo(pseudo);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setMotDePasse(motDePasse);
		this.setCredit(credit);
		this.setAdministrateur(administrateur);
		this.setDesactiver(desactiver);
	}
	
	public Utilisateurs(int id, String pseudo, String nom, String prenom, String email,	String telephone, String rue, int codePostal, String ville,
			String motDePasse, int credit, int administrateur, int desactiver) {
		this.setId(id);
		this.setPseudo(pseudo);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
		this.setMotDePasse(motDePasse);
		this.setCredit(credit);
		this.setAdministrateur(administrateur);
		this.setDesactiver(desactiver);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(int administrateur) {
		this.administrateur = administrateur;
	}
	public int getDesactiver() {
		return desactiver;
	}
	public void setDesactiver(int desactiver) {
		this.desactiver = desactiver;
	}
}
