package fr.greta91.productapp.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta91.productapp.model.Produit;

public interface ProduitRepository  extends JpaRepository<Produit, Integer>{

	
	 @Query( "select p from Produit p where p.prix_actuel >= :min AND p.prix_actuel <= :max")
	   public List<Produit>  findAllProduitsByPrix(double min, double max,Pageable page); 
	 /*  public List<Produit>	 findByMotCle(String motCle, double min, double max); public List<Produit>
	 * findByCategorie(int id_categorie, double min, double max); public
	 * List<Produit> findByMotCLeCategorie(String motCle, int id_categorie, double
	 * min, double max);
	 */
	 @Query("SELECT COUNT(p.id_produit) from Produit p where  p.prix_actuel >= :min AND p.prix_actuel <= :max")
		int getProduitsCompteurByPrix(double min,  double max);
	
	@Query ("select COUNT(p.id_produit) from Produit p")
	int getProduitsCompteur();
	
	@Query("SELECT COUNT(p.id_produit) from Produit p where p.nom LIKE %?1% ")
	int getProduitsCompteurByNom(String motCle);
	
	List<Produit> findByNomContainingIgnoreCase(String motCle, Pageable page);
	
	
	@Query("SELECT p from Produit p where p.categorie.id_categorie=?1")
	List<Produit> findByCategorie(int categorie, Pageable page);
	
	 @Query("SELECT p from Produit p where p.nom LIKE %?1% and p.categorie.id_categorie = ?2 ")
	    List<Produit> findByNomCategorie(String motCle, int categorie, Pageable page);
	
}
