package fr.greta91.productapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.productapp.model.Lot;

//import fr.greta.cda.model.Lot;

import fr.greta91.productapp.model.Produit;
import fr.greta91.productapp.repos.ProduitRepository;

@CrossOrigin(maxAge =3600, origins = "*", allowedHeaders = "*")// client react le cross est pris par spring security mtn
@RestController
@RequestMapping("/api")	
public class ProduitController {

	@Autowired
	ProduitRepository produitRepo;
	
	private Produit selectedProduit;
	private String filtre ="tout";
	private Integer categorie = 0;
	private String recherche;
	private List<Lot> basket = new ArrayList();
	private double total;
	private double min=0;
	private double max=100;
	private List<Produit> produitList;// getters et setters a batir
	
	
	


	//@ResponseBody envoie du json si ce ne st pas un RestaContrommer mais un controller normal
	//@RequestMapping(value="/" , produces="appliaction/json")

	

	@GetMapping("/public/produits")
	public List<Produit> getProduits(@RequestParam(value="numeroPage", required=false,  defaultValue="0") int numeroPage,
			@RequestParam(value="parPage",required=false, defaultValue = "10") int parPage,
			@RequestParam(value="motCle",required=false, defaultValue="")String motCle){
		
		Pageable page=PageRequest.of(numeroPage, parPage);		
		List<Produit> list = null;
		if(motCle.length()>0) {
			list=produitRepo.findByNomContainingIgnoreCase( motCle,page);
		}
		else {
			Page<Produit> pageProduit=produitRepo.findAll(page);
			list=pageProduit.getContent();
		}
		return list;
	}

	@GetMapping("/public/count")
	public HashMap<String, Integer> getProduitsCompteur(@RequestParam(value="motCle", required=false, defaultValue="")String motCle ){
	
	
	HashMap<String, Integer> map =new HashMap<String, Integer>();
	if(motCle.length()>0) {
		 map.put("produitsCompteur", produitRepo.getProduitsCompteurByNom(motCle));
	}else {
		map.put("produitsCompteur",produitRepo.getProduitsCompteur());
	}
	
	return map;
	}
	
	

	@GetMapping("/public/produits/{id}")
	public ResponseEntity <Produit> getProduit(@PathVariable int id) {

		Optional<Produit>optional=produitRepo.findById(id);//return le produit si il existe
		
		  if(optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());

		}
		return ResponseEntity.notFound().build();

	}
	
		@PostMapping("/employe/produits/create")
		public ResponseEntity<Produit> createProduit(@RequestBody Produit produit){
		try {
			Produit newProduit =produitRepo.save(produit);
			return ResponseEntity.ok(newProduit);
		}
		catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/employe/produits/edit")
	public ResponseEntity<Produit> editProduit(@PathVariable int id, @RequestBody Produit produit){
		try {
			Produit  res = produitRepo.save(produit);
			return ResponseEntity.ok(res);
		}
		catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/employe/produits/delete/{id}")
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
