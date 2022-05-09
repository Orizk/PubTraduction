package com.example.projet.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet.Modele.Langue;


@Repository("LangueRepository")
public interface LangueRepository extends JpaRepository<Langue, Long> {

	
	
}
