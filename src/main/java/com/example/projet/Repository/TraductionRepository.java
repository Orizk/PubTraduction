package com.example.projet.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet.Modele.Publication;
import com.example.projet.Modele.Traduction;

	@Repository("TraductionRepository")
	public interface TraductionRepository extends JpaRepository<Traduction, Long> {
	
		
		public List<Traduction> findByPublication(Publication pub);
		
	}
