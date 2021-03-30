package fr.greta91.productapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		System.out.println(bcryptPasswordEncoder().encode("User1000@"));
        System.out.println(bcryptPasswordEncoder().encode("Employe1000@"));
	// On désactive la protection contre les CSRF.
    // Dans l'état actuel du logiciel, elle est inutile
    // Le probléme est que cette protection est stateful...
    http.csrf().disable();
    // on autorise cors
    http.cors();// cross origin request sharing
//    // pas de session, on utilise JWT pour ça...
   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// on desactive la session car po utilse react pour nous connecter et non un user
//    // on se débarrasse de la sécurité par défaut.
    http.httpBasic().disable();
//    // on désactive aussi la page de login
    http.formLogin().disable();  //chain of responsibility  
//    // on met en place le filtre qui va récupérer le token s'il existe et authentifier l'utilisateur.
//  http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);// on ajoute un filtre qui va lire le token et donne les instructions a spring
//    // régles d'accès proprement dites
//    // vu leur tête, on pourrait aussi les placer au niveau des méthodes, comme annotations.
 
    
    http.authorizeRequests()//authorisation orientee url fonctionne comme un pare feu
        .mvcMatchers(HttpMethod.GET, "/api/public/**").permitAll()  //permitAll =>tout mle monde peu y acceder= public  
        //.mvcMatchers(HttpMethod.POST, "/api/public/**").permitAll()
        //.mvcMatchers(HttpMethod.PUT, "/api/public/**").permitAll()        
        //.mvcMatchers(HttpMethod.DELETE, "/api/panier/all").permitAll() similaire a /** mais couvre /** couvre plus
        //.mvcMatchers(HttpMethod.DELETE, "/api/panier/**").permitAll()    
        .mvcMatchers(HttpMethod.POST, "/api/auth/login").permitAll()        
        .mvcMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
       
        .mvcMatchers(HttpMethod.GET, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")//**=> wildcard
        .mvcMatchers(HttpMethod.POST, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")//**=> wildcard
        .mvcMatchers(HttpMethod.PUT, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")//**=> wildcard
        .mvcMatchers(HttpMethod.DELETE, "/api/employe/**").access("hasRole('ROLE_EMPLOYE')")//**=> wildcard
        //.mvcMatchers(HttpMethod.DELETE, "/api/gerant/**").access("hasRole('ROLE_GERANT')")
      
        .mvcMatchers("/api/**").authenticated();//en dernier car couvre tous les autre cas non definis en haut exige l autautification

}
	}
