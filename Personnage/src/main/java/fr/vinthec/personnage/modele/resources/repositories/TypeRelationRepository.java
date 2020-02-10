package fr.vinthec.personnage.modele.resources.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.entities.TypeRelation;

public interface TypeRelationRepository extends JpaRepository<TypeRelation, Long> {

	Optional<TypeRelation> findByDesignation(String nomTypeRelation);
	
	
}
