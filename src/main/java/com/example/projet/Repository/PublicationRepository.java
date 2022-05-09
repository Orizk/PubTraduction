package com.example.projet.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet.Modele.Publication;
import com.example.projet.Modele.Utilisateurs;

	@Repository("PublicationRepository")
	public interface PublicationRepository extends JpaRepository<Publication, Long> {
	
		public List<Publication> findByStatut(Boolean statut);
		public List<Publication> findByUtilisateur(Utilisateurs utilisateur);
		
	}
