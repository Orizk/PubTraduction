package com.example.projet.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.projet.Modele.Publication;
import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.PublicationRepository;
import com.example.projet.Repository.UtilisateursRepository;
import com.example.projet.Service.RIZUserDetails;



@Controller
public class HomeController {
	
	@Autowired
	private PublicationRepository publicationRepository;
	@Autowired
	private UtilisateursRepository utilisateursRepository;
	
	@GetMapping("/account/index")
	public String showindex(Model model,@AuthenticationPrincipal RIZUserDetails userDetails) {
		if(userDetails == null) {
			return "redirect:/login";
		}
		else {
			List<Publication> Pub_site = publicationRepository.findByStatut(true);
			Utilisateurs user = utilisateursRepository.findByEmail(userDetails.getUsername());
			List<Publication> Pub = publicationRepository.findByUtilisateur(user);
			int cnt = Pub.size();
			int cnt_site = Pub_site.size();
			System.out.println(cnt);
			model.addAttribute("count", cnt);
			model.addAttribute("count_site",cnt_site);
		    return "account/index";
		}
		
	}

}
