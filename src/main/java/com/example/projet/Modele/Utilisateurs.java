package com.example.projet.Modele;

import java.util.Collection;

import javax.persistence.Column;
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
public class Utilisateurs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

  
    private String nom;
    
   
    private String prenom;

    
    @Column(unique=true)
    private String email;
    
    
    @Column(columnDefinition = "varchar(10) default 'user'")
    private String role;
    

    private String password;
    
    @Column(columnDefinition = "boolean default true")
    private Boolean accountVerified;

    @OneToMany(mappedBy = "utilisateur")
	private Collection<Publication> publications;


	


	/*public Utilisateurs(long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "Surname is mandatory") String prenom,
			@NotBlank(message = "Email is mandatory") String email,
			@NotBlank(message = "Role is mandatory") String role,
			@NotBlank(message = "Password is mandatory") String mdp) {
		super();
		this.id = id;
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.role = role;
		this.mdp = mdp;
	}*/

    
}
