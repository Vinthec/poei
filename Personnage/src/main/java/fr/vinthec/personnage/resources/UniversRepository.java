package fr.vinthec.personnage.resources;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.vinthec.personnage.modele.Univers;

public interface UniversRepository extends JpaRepository<Univers, Long> {
	
	
	List<Univers> findByNomLike(String param);
}
