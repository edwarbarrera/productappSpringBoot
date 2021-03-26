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

//import fr.greta.cda.model.Lot;

import fr.greta91.productapp.model.Produit;
import fr.greta91.productapp.repos.ProduitRepository;

@CrossOrigin(maxAge =360000, origins ="*")// client react
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
	
	@GetMapping("")
	public List<Produit> getProduitList() {
		switch (filtre) {
		default : produitList = produitRepo.getAllProduit(min, max);
			System.out.println("Tous les porduits entre " +min + "et " + max);
			break;
		case "motCle" : produitList = produitRepo.findByMotCle(recherche, min, max);
			System.out.println("le mot cle recherché est : " + recherche + " min = " + min + " max = " + max );
			break;
		case "categorie" : produitList = produitRepo.findByCategorie(categorie, min, max);
			System.out.println("la categorie recherchee est : " + categorie + " min = " + min + " max = " + max );
			break;
		case "motCleCategorie" : produitList = produitRepo.findByMotCLeCategorie(recherche, categorie, min, max);
			System.out.println("mot cle = " +recherche + " categorie = " + categorie + " min = "  + min + " max = " + max);
			break;
	}
		return produitList;	
	}

}
