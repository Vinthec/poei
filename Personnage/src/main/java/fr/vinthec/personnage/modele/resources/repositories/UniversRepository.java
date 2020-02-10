package fr.vinthec.personnage.modele.resources.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.vinthec.personnage.modele.entities.Univers;

public interface UniversRepository extends JpaRepository<Univers, Long> {
	
	@Query("select u from Univers u where nom like :param")
	List<Univers> findUniversLike(@Param("param") String param);

	
	List<Univers> findByNomLike(String param);
}
