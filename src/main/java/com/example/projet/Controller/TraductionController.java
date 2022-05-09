package com.example.projet.Controller;




import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.projet.Modele.Publication;
import com.example.projet.Modele.Traduction;
import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.LangueRepository;
import com.example.projet.Repository.PublicationRepository;
import com.example.projet.Repository.TraductionRepository;
import com.example.projet.Service.RIZUserDetails;


@Controller
public class TraductionController {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
    private LangueRepository langueRepository;
	
	
	@Autowired
	private TraductionRepository traductionRepository;
	
	@Autowired
	ServletContext context;
	
	
	@GetMapping("traductions/index")
    public String showPubList(Model model) {

        model.addAttribute("traductions", traductionRepository.findAll());

        return "traductions/index";

    }
	
	
	@GetMapping("traductions/ajout_traduction")
    public String showAddTrad(Model model) {
		Traduction traductions = new Traduction();
		model.addAttribute("traductions",traductions);
		model.addAttribute("languetrad", langueRepository.findAll());
		model.addAttribute("publitrad", publicationRepository.findAll());

        return "traductions/ajout_traduction";
    }
	
	
	@GetMapping("traductions/ajout_traduction/pub/{id}")
    public String showAddTradPub(Model model,@PathVariable Long id) {
		Traduction traductions = new Traduction();
		model.addAttribute("traductions",traductions);
		model.addAttribute("idpub", id);
		model.addAttribute("languetrad", langueRepository.findAll());
		model.addAttribute("publitrad", publicationRepository.findById(id).get());

        return "traductions/ajout_traduction_pub";
    }
	
	@RequestMapping( value = { "traductions/save_traductions/{idpub}" },method = RequestMethod.POST)
	 public String SaveTradPub(Model model, @ModelAttribute("traductions") Traduction traductions,@AuthenticationPrincipal RIZUserDetails userDetails,@PathVariable Long idpub) 
	{
        Publication pub = new Publication();
        pub.setId_publication(idpub);
		Utilisateurs us = new Utilisateurs();
		us.setId_utilisateur(userDetails.getIduser());
		traductions.setUtilisateur(us);
		traductions.setPublication(pub);
		 try {
			 traductionRepository.save(traductions);
			 return "redirect:/traductions/index";
		 }catch(Exception e) {
			 return "redirect:/traductions/ajout_traduction?error=true";
		 }
		 
		
	 }
	
	@RequestMapping( value = { "traductions/save_traductions" },method = RequestMethod.POST)
	 public String SaveTrad(Model model, @ModelAttribute("traductions") Traduction traductions,@AuthenticationPrincipal RIZUserDetails userDetails) 
	{
		Utilisateurs us = new Utilisateurs();
		us.setId_utilisateur(userDetails.getIduser());
		traductions.setUtilisateur(us);
		 try {
			 traductionRepository.save(traductions);
			 return "redirect:/traductions/index";
		 }catch(Exception e) {
			 return "redirect:/traductions/ajout_traduction?error=true";
		 }
		 
		
	 }
	
	@GetMapping("traduction/delete/{id}")
	public String DeleteTrad(Model model,@PathVariable Long id){
		
		try {
		traductionRepository.deleteById(id);
		return "redirect:/traductions/index";
		}
		catch(Exception e){
			return "redirect:/traductions/index?error=true";
		}
	}
	
	@GetMapping("traduction/edit/{idtrad}/{idpub}")
	public String showTraductionEdit(Model model,@PathVariable("idtrad") Long idtrad,@PathVariable("idpub") Long idpub){
		//model.addAttribute("traductionss",traductionRepository.findById(id).get());
		model.addAttribute("publications",publicationRepository.findById(idpub).get());
		model.addAttribute("traductions",traductionRepository.findById(idtrad));
		model.addAttribute("languepub", langueRepository.findAll());
		return "traductions/modifier_traduction";
	}
	/*
	@RequestMapping( value = { "publications/save_publications" },method = RequestMethod.POST)
	 public String SaveTraductions(Model model,@RequestParam("image") MultipartFile file,@ModelAttribute("publications") Publication publication,@AuthenticationPrincipal RIZUserDetails userDetails,HttpServletRequest request) {
		Utilisateurs us = new Utilisateurs();
		us.setId_utilisateur(userDetails.getIduser());
              publication.setStatut(false);
             publication.setUtilisateur(us);
             //String filename = StringUtils.cleanPath(file.getOriginalFilename());
           
			//publication.setDocument(file.getOriginalFilename());
			
             String uploadDir = "publications/uploads/"+userDetails.getPrenom()+"/images/";
            // new File(uploadDir).mkdir();
             Path uploadPath = Paths.get(uploadDir);
             if (!Files.exists(uploadPath)) {
                 try {
					Files.createDirectories(uploadPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
            	 Path fileNameAndPath = Paths.get(uploadDir,file.getOriginalFilename());
            	 String filename = file.getOriginalFilename();
            	// String myroot = request.getServletContext().getRealPath("uploads");
            	//System.out.println(fileNameAndPath.toString()+" "+uploadPath.toString()+" "+myroot);
            	 try {
            		 //file.transferTo(new File(uploadDir+filename));
					Files.write(fileNameAndPath, file.getBytes());
					//FileUploadUtil.saveFile(uploadDir, filename, file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	 publication.setDocument(filename.toString());
            	
             
             
            // System.out.println("mon fichier"+filename);
             
		 try {
			// String absolutePath = context.getRealPath("resources/templates/publications/images/");
			// file.transferTo(new File(absolutePath+file.getOriginalFilename()));
			 publicationRepository.save(publication);
			// FileUploadUtil.saveFile(uploadDir, filename, file);
			 
			 return "redirect:/publications/index";
		 }catch(Exception e) {
			 System.out.println(e);
			 return "redirect:/publications/ajout_publications?error=true";
		 }
		 
		
	 }
	
	
	
	
	@GetMapping("publication/voir/{id}")
	public String showPublicationVoir(Model model,@PathVariable Long id,@AuthenticationPrincipal RIZUserDetails userDetails){
		model.addAttribute("publications",publicationRepository.findById(id).get());
		String uploadDir = "/publications/uploads/"+userDetails.getPrenom()+"/images/";
		model.addAttribute("uploadDir",uploadDir);
		return "publications/voir_publication";
	}
	
	
	*/


}
