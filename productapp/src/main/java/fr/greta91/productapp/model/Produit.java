package fr.greta91.productapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produit")
public class Produit implements Serializable{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 8389709651322457155L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_produit;
	private String nom;
	private int quantite;
	private String description;
	private String url_image;
	//private int id_categorie;
	private double prix_actuel;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;
	
	
	
	public Produit() {
		super();
	}
	

	
	
	
	public int getId_produit() {
		return id_produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	/*public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}*/

	public double getPrix_actuel() {
		return prix_actuel;
	}

	public void setPrix_actuel(double prix_actuel) {
		this.prix_actuel = prix_actuel;
	}

	public Produit(int id_produit, String nom, int quantite, String description, String url_image, double prix_actuel,
			Categorie categorie) {
		super();
		this.id_produit = id_produit;
		this.nom = nom;
		this.quantite = quantite;
		this.description = description;
		this.url_image = url_image;
		this.prix_actuel = prix_actuel;
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Produit [id_produit=" + id_produit + ", nom=" + nom + ", quantite=" + quantite + ", description="
				+ description + ", url_image=" + url_image + ", prix_actuel=" + prix_actuel + ", categorie=" + categorie
				+ "]";
	}

	


}
