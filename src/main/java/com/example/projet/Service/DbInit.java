
  package com.example.projet.Service;
  

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

  @Service 
  public class DbInit implements CommandLineRunner 
  {
  
		/*
		 * @Autowired UtilisateursRepository userRepository;
		 * 
		 * @Autowired private PasswordEncoder passwordEncoder;
		 */
  
  @Override 
  public void run(String... args) throws Exception 
  {
  //userRepository.deleteAll();
  
	/*
	 * Utilisateurs user = new Utilisateurs();
	 * user.setEmail("rizki.omar1@gmail.com"); user.setName("RIZKI");
	 * user.setPrenom("Omar"); user.setAccountVerified(true); user.setRole("admin");
	 * user.setMdp(passwordEncoder.encode("123456"));
	 * 
	 * 
	 * List<Utilisateurs> liste = new ArrayList<>(); liste.add(user);
	 * userRepository.saveAndFlush(user);
	 */
  
  }
  
  
  
  }
 
