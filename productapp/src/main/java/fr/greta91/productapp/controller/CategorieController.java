package fr.greta91.productapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.productapp.model.Categorie;
import fr.greta91.productapp.repos.CategorieRepository;

@CrossOrigin(maxAge =3600, origins = "*", allowedHeaders = "*") //c est spring security qui le gere mtn
@RestController
@RequestMapping("/categories")
public class CategorieController {
	
	@Autowired
	CategorieRepository categorieRepo;
	
	@GetMapping("/")
	public  List<Categorie> getCategories(){
		List <Categorie>list =categorieRepo.findAll();
		return list;
	}
	
	

}
