package com.example.projet.Controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.UtilisateursRepository;

@Controller
public class UtilisateurController {
	
	@Autowired	
	private UtilisateursRepository utilisateursrepository;
	
	@Autowired
	 private PasswordEncoder passwordEncoder;
	
	@GetMapping("admin/utilisateurs/ajout_utilisateurs")
    public String showSignUpForm(Utilisateurs utilisateurs) {
        return "utilisateurs/ajout_utilisateur";
    }

@GetMapping("admin/utilisateurs/index")

    public String showUserList(Model model) {

        model.addAttribute("utilisateurs", utilisateursrepository.findAll());

        return "utilisateurs/index";

    }

@GetMapping("admin/utilisateurs/edit/{id}")
public String showUserEdit(Model model,@PathVariable Long id){
	model.addAttribute("utilisateurs",utilisateursrepository.findById(id));
	
	return "utilisateurs/modifier_utilisateur";
}


@GetMapping("admin/utilisateurs/delete/{id}")
public String DeleteUtilisateur(Model model,@PathVariable Long id){
	//model.addAttribute("utilisateurs",utilisateursrepository.findById(id));
	
	try {
	utilisateursrepository.deleteById(id);
	return "redirect:/admin/utilisateurs/index";
	}
	catch(Exception e){
		return "redirect:/admin/utilisateurs/index?error=true";
	}
}

@GetMapping("admin/utilisateurs/valide/{id}")
public String ValideUtilisateur(Model model,@PathVariable Long id){
	Optional<Utilisateurs> us = utilisateursrepository.findById(id);
	Utilisateurs user = us.get();
	
	if(user.getAccountVerified().booleanValue() == true) {
		user.setAccountVerified(false);
	}else {
		user.setAccountVerified(true);
	}
	try {
	utilisateursrepository.save(user);
	return "redirect:/admin/utilisateurs/index";
	}
	catch(Exception e){
		return "redirect:/admin/utilisateurs/index?error=true";
	}
}


@RequestMapping( value = { "admin/save_utilisateurs" },method = RequestMethod.POST)
 public String SaveUtilisateurs(Model model, @ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {
	 
	
	
	if(utilisateurs.getPassword()== "") {
		Optional<Utilisateurs> us = utilisateursrepository.findById(utilisateurs.getId_utilisateur());
		utilisateurs.setPassword(us.get().getPassword());
	}
	else {
		utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
	}

	if(utilisateurs.getAccountVerified() != null) {
		utilisateurs.setAccountVerified(utilisateurs.getAccountVerified());
	}
	else {
		utilisateurs.setAccountVerified(false);
	}

//	 String nom = utilisateurs.getNom();
//	 String prenom = utilisateurs.getPrenom();
//	 String email = utilisateurs.getEmail();
//	 String role = utilisateurs.getRole();
//	 String password = utilisateurs.getPassword();
//	 
//	 Utilisateurs user = new Utilisateurs();
//	 user.setNom(nom);
//	 user.setPrenom(prenom);
//	 user.setEmail(email);
//	 user.setRole(role);
//	 user.setPassword(passwordEncoder.encode(password));	 
//	 user.setAccountVerified(true);
	
	 try {
		 utilisateursrepository.save(utilisateurs);
		 return "redirect:/admin/utilisateurs/index";
	 }catch(Exception e) {
		 return "redirect:/admin/utilisateurs/ajout_utilisateurs?error=true";
	 }
	 
	
 }

}
