package fr.vinthec.personnage.resources;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.Univers;

public interface UniversRepository extends JpaRepository<Univers, Long> {

}
