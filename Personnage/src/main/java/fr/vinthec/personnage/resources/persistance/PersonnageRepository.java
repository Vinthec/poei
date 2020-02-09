package fr.vinthec.personnage.resources.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.Personnage;

public interface PersonnageRepository  extends JpaRepository<Personnage, Long>{

	Optional<Personnage> findByNomLike(String string);

}
