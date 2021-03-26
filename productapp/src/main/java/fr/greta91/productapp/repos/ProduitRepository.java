package fr.greta91.productapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta91.productapp.model.Produit;

public interface ProduitRepository  extends JpaRepository<Produit, Integer>{
	
	public Produit getProduit(int id);
	public List<Produit> getAllProduit(double min, double max);
	public List<Produit> findByMotCle(String motCle, double min, double max);
	public List<Produit> findByCategorie(int id_categorie, double min, double max);
	public List<Produit> findByMotCLeCategorie(String motCle, int id_categorie, double min, double max);

}
