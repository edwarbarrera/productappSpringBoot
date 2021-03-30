package fr.greta91.productapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.productapp.model.Lot;

//import fr.greta.cda.model.Lot;

import fr.greta91.productapp.model.Produit;
import fr.greta91.productapp.repos.ProduitRepository;

//@CrossOrigin(maxAge =3600, origins = "*", allowedHeaders = "*")// client react le cross est pris par spring security mtn
@RestController
@RequestMapping("/produits")	
public class ProduitController {

	
	private Produit selectedProduit;
	private String filtre ="tout";
	private Integer categorie = 0;
	private String recherche;
	private List<Lot> basket = new ArrayList();
	private double total;
	private double min=0;
	private double max=100;
	private List<Produit> produitList;
	
	
	


	//@ResponseBody envoie du json si ce ne st pas un RestaContrommer mais un controller normal
	//@RequestMapping(value="/" , produces="appliaction/json")

	public Produit getSelectedProduit() {
		return selectedProduit;
	}


	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}


	public String getFiltre() {
		return filtre;
	}


	public void setFiltre(String filtre) {
		this.filtre = filtre;
	}


	public Integer getCategorie() {
		return categorie;
	}


	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}


	public String getRecherche() {
		return recherche;
	}


	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}


	public List<Lot> getBasket() {
		return basket;
	}


	public void setBasket(List<Lot> basket) {
		this.basket = basket;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public double getMin() {
		return min;
	}


	public void setMin(double min) {
		this.min = min;
	}


	public double getMax() {
		return max;
	}


	public void setMax(double max) {
		this.max = max;
	}


	public ProduitRepository getProduitRepo() {
		return produitRepo;
	}


	public void setProduitRepo(ProduitRepository produitRepo) {
		this.produitRepo = produitRepo;
	}


	public void setProduitList(List<Produit> produitList) {
		this.produitList = produitList;
	}
	@Autowired
	ProduitRepository produitRepo;

	@GetMapping("")
	public List<Produit> getProduits(){
		List<Produit> list = produitRepo.findAll();
		return list;
	}


	@GetMapping("/{id}")
	public ResponseEntity <Produit> getProduit(@PathVariable int id) {

		Optional<Produit>optional=produitRepo.findById(id);//return le produit si il existe
		
		  if(optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());

		}
		return ResponseEntity.notFound().build();

	}
	
		@PostMapping("/{id}")
		public ResponseEntity<Produit> createProduit(@RequestBody Produit produit){
		try {
			Produit res =produitRepo.save(produit);
			return ResponseEntity.ok(res);
		}
		catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produit> editProduit(@PathVariable int id, @RequestBody Produit produit){
		try {
			Produit  res = produitRepo.save(produit);
			return ResponseEntity.ok(res);
		}
		catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Produit> deleteProduit(@PathVariable int id){
		try {
			Produit  res = produitRepo.findById(id).get();
			if(null != res) {
				produitRepo.delete(res);
				return ResponseEntity.ok(res);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * @GetMapping("") public List<Produit> getProduitList() { switch (filtre) {
	 * default : produitList = produitRepo.findAll( );
	 * System.out.println("Tous les porduits entre " +min + "et " + max); break;
	 * case "motCle" : produitList = produitRepo.findByMotCle(recherche, min, max);
	 * System.out.println("le mot cle recherché est : " + recherche + " min = " +
	 * min + " max = " + max ); break; case "categorie" : produitList =
	 * produitRepo.findByCategorie(categorie, min, max);
	 * System.out.println("la categorie recherchee est : " + categorie + " min = " +
	 * min + " max = " + max ); break; case "motCleCategorie" : produitList =
	 * produitRepo.findByMotCLeCategorie(recherche, categorie, min, max);
	 * System.out.println("mot cle = " +recherche + " categorie = " + categorie +
	 * " min = " + min + " max = " + max); break; } return produitList; } public
	 * String filtrer() { System.out.println(filtre); if (getRecherche().isEmpty()
	 * && getCategorie() == 0) { filtre = "tout"; } else if
	 * (!getRecherche().isEmpty() && getCategorie() == 0 ){ filtre = "motCle"; }
	 * else if (getRecherche().isEmpty() && getCategorie() != 0 ){ filtre =
	 * "categorie"; } else if (!getRecherche().isEmpty() && getCategorie() != 0 ){
	 * filtre = "motCleCategorie"; } System.out.println("recherche par " + filtre);
	 * System.out.println("critères de recherche : mot cle = " + recherche +
	 * ", cat= "+ categorie + ", prix = " + min + " " + max); return filtre; }
	 */
}
