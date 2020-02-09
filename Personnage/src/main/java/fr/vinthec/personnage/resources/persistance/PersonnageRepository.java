package fr.vinthec.personnage.resources.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.Personnage;

public interface PersonnageRepository  extends JpaRepository<Personnage, Long>{

}
