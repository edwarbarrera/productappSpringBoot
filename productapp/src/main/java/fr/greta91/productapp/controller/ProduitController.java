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


import fr.greta91.productapp.model.Produit;
import fr.greta91.productapp.repos.ProduitRepository;

@CrossOrigin(maxAge =3600,origins="*")// client react
@RestController
@RequestMapping("/produits")	
public class ProduitController {




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
	@PostMapping("")
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
}
