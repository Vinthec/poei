package fr.vinthec.personnage.resources.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.vinthec.personnage.modele.TypeRelation;

public interface TypeRelationRepository extends JpaRepository<TypeRelation, Long> {

	Optional<TypeRelation> findByDesignation(String nomTypeRelation);
	
	
}
