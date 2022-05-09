package com.example.projet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.projet.Modele.Langue;
import com.example.projet.Repository.LangueRepository;

@Controller
public class LangueController {

	@Autowired
	private LangueRepository langueRepository;
	
	@GetMapping("admin/langues/index")
    public String showLangueList(Model model) {

        model.addAttribute("langues", langueRepository.findAll());

        return "langues/index";

    }
	
	@GetMapping("admin/langues/ajout_langues")
    public String showAddLangue(Model model) {
		Langue langues = new Langue();
		model.addAttribute("langues",langues);
        return "langues/ajout_langue";
    }
	
	@RequestMapping( value = { "admin/langues/save_langues" },method = RequestMethod.POST)
	 public String SaveLangues(Model model, @ModelAttribute("langues") Langue langues) {
		 
		 try {
			 langueRepository.save(langues);
			 return "redirect:/admin/langues/index";
		 }catch(Exception e) {
			 return "redirect:/admin/langues/ajout_langues?error=true";
		 }
		 
		
	 }
	
	@GetMapping("admin/langues/edit/{id}")
	public String showLangueEdit(Model model,@PathVariable Long id){
		model.addAttribute("langues",langueRepository.findById(id));
		
		return "langues/modifier_langue";
	}
	
	@GetMapping("admin/langues/delete/{id}")
	public String DeleteLangue(Model model,@PathVariable Long id){
		
		try {
		langueRepository.deleteById(id);
		return "redirect:/admin/langues/index";
		}
		catch(Exception e){
			return "redirect:/admin/langues/index?error=true";
		}
	}
	
}
