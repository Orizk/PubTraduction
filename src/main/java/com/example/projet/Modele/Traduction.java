package com.example.projet.Modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Traduction {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_traduction;
    
	private String text_traduction;
	
    @ManyToOne
    @JoinColumn(name="id_langue")
    private Langue langue;
    
    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateurs utilisateur;
    

    @ManyToOne
    @JoinColumn(name="id_publication")
    private Publication publication;
    


	

}
