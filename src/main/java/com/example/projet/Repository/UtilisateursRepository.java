package com.example.projet.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projet.Modele.Utilisateurs;

@Repository("UtilisateursRepository")
public interface UtilisateursRepository extends JpaRepository<Utilisateurs, Long> {
	Utilisateurs findByEmail(String email);

	
	
//	@Modifying
//	@Query("update utilisateurs u set u.account_verified = :accountverified where id = :id")
//	void validateusers(@Param(value="id") Long id, @Param(value="accountverified") String accountVerified);
}
