package fr.greta91.productapp.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3894741776119470316L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_categorie;
	private String libelle;
	public Categorie() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "Categorie [id_categorie=" + id_categorie + ", libelle=" + libelle + "]";
	}



	public Categorie(int id_categorie, String libelle) {
		super();
		this.id_categorie = id_categorie;
		this.libelle = libelle;
	}



	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}