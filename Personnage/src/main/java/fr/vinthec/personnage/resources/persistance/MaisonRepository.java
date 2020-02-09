package fr.vinthec.personnage.resources.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.Maison;

public interface MaisonRepository  extends JpaRepository<Maison	, Long>{

		public Optional<Maison> findByNom(String nom);
	
}
