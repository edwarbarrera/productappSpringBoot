package fr.greta91.productapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta91.productapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//@Query("select u from User u where u.username=?1")
	public User findByUsername(String username);// avec le findby et la convetion de nomage trop bien jpa contruit lui meme la requete avec les params sinon t es oblige de taper la requete

}
