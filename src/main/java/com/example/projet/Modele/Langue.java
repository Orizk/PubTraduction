package com.example.projet.Modele;


import java.util.Collection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Langue {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_langue;


	private String nom_langue;


	private String code_langue;
	
	@OneToMany(mappedBy = "langue")
	private Collection<Publication> publications;
	
	
}
