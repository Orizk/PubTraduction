package com.example.projet.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.UtilisateursRepository;

@Controller
public class LoginPageController {
	
	@Autowired	
	private UtilisateursRepository utilisateursrepository;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@GetMapping({"/login","/"})
	public String login() {
		return "account/login";
	}
	

@GetMapping("/inscription")
public String showRegistrationForm(Utilisateurs utilisateurs) {
    return "account/inscription";
}



@RequestMapping(value="/page_denied")
public String deniedPage() {
	return "errors/denied";
}

@RequestMapping( value = { "/account/register" },method = RequestMethod.POST)
public String SaveUtilisateurs(Model model, @ModelAttribute("utilisateurs") Utilisateurs utilisateurs,BindingResult result) {
	 
	 String nom = utilisateurs.getNom();
	 String prenom = utilisateurs.getPrenom();
	 String email = utilisateurs.getEmail();
	 String password = utilisateurs.getPassword();
	 Utilisateurs user = new Utilisateurs();
	 user.setNom(nom);
	 user.setPrenom(prenom);
	 user.setEmail(email);
	 user.setPassword(passwordEncoder.encode(password));
	 user.setAccountVerified(false);
	 user.setRole("user");
	 try {
		 utilisateursrepository.save(user);
		 return "redirect:/login?errvalid=true";
	 }catch(Exception e) {
		 return "redirect:/inscription?error=true";
	 }
	 
	
}
}
