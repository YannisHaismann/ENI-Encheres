package fr.eni.bo;

public class Categories {
	
	public static String[] listeLibelle = {"VETEMENTS", "ACCESSOIRES", "JEUX", "SPORT", "ANIMAUX", "ENFANTS", "HIGH-TECH"};
	
	private int id;
	private String libelle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
