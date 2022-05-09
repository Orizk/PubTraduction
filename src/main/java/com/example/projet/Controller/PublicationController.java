package com.example.projet.Controller;









import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.projet.Modele.Publication;
import com.example.projet.Modele.Utilisateurs;
import com.example.projet.Repository.LangueRepository;
import com.example.projet.Repository.PublicationRepository;
import com.example.projet.Repository.TraductionRepository;
import com.example.projet.Service.RIZUserDetails;

@Controller
public class PublicationController {
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
    private LangueRepository langueRepository;
	
	@Autowired
	private TraductionRepository traductionRepository;
	

	
	@Autowired
	ServletContext context;
	
	
	@GetMapping("publications/index")
    public String showPubList(Model model) {

        model.addAttribute("publications", publicationRepository.findAll());
        model.addAttribute("activepub", publicationRepository.findByStatut(true));

        return "publications/index";

    }
	
	
	@GetMapping("publications/ajout_publications")
    public String showAddPub(Model model) {
		Publication publications = new Publication();
		model.addAttribute("publications",publications);
		model.addAttribute("languepub", langueRepository.findAll());
		//model.addAttribute("formuser", utilisateursRepository.findById(1L).get());
		//model.addAttribute("nomcomp", userDetails.getPrenom());

        return "publications/ajout_publication";
    }
	
	
	@RequestMapping( value = { "publications/save_publications" },method = RequestMethod.POST)
	 public String SavePublications(Model model,@RequestParam("image") MultipartFile file,@ModelAttribute("publications") Publication publication,@AuthenticationPrincipal RIZUserDetails userDetails,HttpServletRequest request) {
		Utilisateurs us = new Utilisateurs();
		us.setId_utilisateur(userDetails.getIduser());
              publication.setStatut(false);
             publication.setUtilisateur(us);
             //String filename = StringUtils.cleanPath(file.getOriginalFilename());
           
			//publication.setDocument(file.getOriginalFilename());
			
           //  String uploadDir = "~/publications/uploads/"+userDetails.getPrenom()+"/images/";
            // String uploadDir = "uploads/publications/"+userDetails.getPrenom()+"/images/"; 
             String uploadDir = "src\\main\\resources\\static\\uploads\\publications\\"+userDetails.getPrenom()+"\\images";
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
            	 System.out.println(fileNameAndPath);
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
	
	@GetMapping("publication/edit/{id}")
	public String showPublicationEdit(Model model,@PathVariable Long id,@AuthenticationPrincipal RIZUserDetails userDetails){
		model.addAttribute("publications",publicationRepository.findById(id));
		model.addAttribute("publicationss",publicationRepository.findById(id).get());
		model.addAttribute("languepub", langueRepository.findAll());
		String uploadDir = "/uploads/publications/"+userDetails.getPrenom()+"/images/";
		model.addAttribute("uploadDir",uploadDir);
		return "publications/modifier_publication";
	}
	
	
	@GetMapping("publication/voir/{id}")
	public String showPublicationVoir(Model model,@PathVariable Long id,@AuthenticationPrincipal RIZUserDetails userDetails){
		Publication pub = new Publication();
		pub = publicationRepository.findById(id).get();
		model.addAttribute("publications",publicationRepository.findById(id).get());
		model.addAttribute("traductions", traductionRepository.findByPublication(pub));
		String uploadDir = "/uploads/publications/"+pub.getUtilisateur().getPrenom()+"/images/";
		model.addAttribute("uploadDir",uploadDir);
		return "publications/voir_publication";
	}
	
	@GetMapping("publication/delete/{id}")
	public String DeleteLangue(Model model,@PathVariable Long id){
		
		try {
		publicationRepository.deleteById(id);
		return "redirect:/publications/index";
		}
		catch(Exception e){
			return "redirect:/publication/index?error=true";
		}
	}
	
	@GetMapping("publication/valide/{id}")
	public String ValidePublication(Model model,@PathVariable Long id){
		Optional<Publication> pub = publicationRepository.findById(id);
		Publication publication = pub.get();
		
		if(publication.getStatut().booleanValue() == true) {
			publication.setStatut(false);
		}else {
			publication.setStatut(true);
		}
		try {
		publicationRepository.save(publication);
		return "redirect:/publications/index";
		}
		catch(Exception e){
			return "redirect:/publication/index?error=true";
		}
	}

	


}
