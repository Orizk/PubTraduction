package com.example.projet.Modele;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Publication {
		
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_publication;
	
    @NotBlank(message = "titre obligatoire")
    private String titre;
    
    private String sens;
    
    @NotBlank(message = "Mot-Cle Obligatoire")
    private String motcle;
    
    private String auteur;
    
    @NotBlank(message = "Source Obligatoire")
    private String source;
    
    
    @NotBlank(message = "Description Obligatoire")
    private String description;
    
    private String document;
    
    
    private Boolean statut;
    
 
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="id_langue", referencedColumnName = "id_langue")
    private Langue langue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_utilisateur", referencedColumnName = "id_utilisateur")
    private Utilisateurs utilisateur;
    


}
