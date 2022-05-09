
  package com.example.projet.Service;
  


import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
  import org.springframework.security.core.userdetails.UserDetailsService; 
  import org.springframework.security.core.userdetails.UsernameNotFoundException;
  import org.springframework.stereotype.Service;


import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.UtilisateursRepository;
  
  @Service 
  public class UserDetailsServiceImpl implements UserDetailsService 
  {
  
  @Autowired 
  UtilisateursRepository userRepository;
  
  HttpSession session;
  
  
  @Override 
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
  
  
		/*
		 * final Utilisateurs customer = userRepository.findByEmail(email);
		 * 
		 * 
		 * if (customer == null) {
		 * 
		 * throw new UsernameNotFoundException(email);
		 * 
		 * }
		 */
	
	/*
	 * UserDetails user = User.withUsername(customer.getEmail())
	 * .password(customer.getPassword()) .username(customer.getPrenom())
	 * .roles(customer.getRole().isEmpty() ? "USER" : customer.getRole()).build();
	 * 
	 * 
	 * 
	 * return user;
	 */
  
	  
	  Utilisateurs user = userRepository.findByEmail(email);
	  
	  if(user == null) {
		  throw new UsernameNotFoundException("Aucun utilisateurs avec email: "+email);
	  }
	  if(user.getAccountVerified() == false) {
		  throw new UsernameNotFoundException("le Compte : "+email+" est desactiver");
	  }
	  return new RIZUserDetails(user);
  
  }
  
  
  
  
  
  }
  
  
  
  
 