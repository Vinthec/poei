package fr.vinthec.personnage.modele.resources.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.entities.Personnage;

public interface PersonnageRepository  extends JpaRepository<Personnage, Long>{

	Optional<Personnage> findByNomLike(String string);

}
