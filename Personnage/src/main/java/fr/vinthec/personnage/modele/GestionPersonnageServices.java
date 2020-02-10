package fr.vinthec.personnage.modele;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fr.vinthec.personnage.modele.entities.Maison;
import fr.vinthec.personnage.modele.entities.Personnage;
import fr.vinthec.personnage.modele.entities.TypeRelation;
import fr.vinthec.personnage.modele.entities.Univers;
import fr.vinthec.personnage.modele.resources.repositories.GenericEntitiesRepository;
import fr.vinthec.personnage.modele.resources.repositories.MaisonRepository;
import fr.vinthec.personnage.modele.resources.repositories.TypeRelationRepository;
import fr.vinthec.personnage.modele.resources.repositories.UniversRepository;
import fr.vinthec.personnage.modele.values.Genre;

@Service
public class GestionPersonnageServices {

	@Resource
	private transient GenericEntitiesRepository repository;

	@Resource
	private transient UniversRepository universRepository;

	@Resource
	private transient MaisonRepository maisonRepository;

	@Resource
	private transient TypeRelationRepository typeRelationRepository;

	public Univers creerUnivers(String nom) {
		return universRepository.save(new Univers(nom));
	}

	public Maison getOrCreateMaison(String nom, Univers univers) {
		return maisonRepository.findByNomAndUnivers(nom, univers).orElseGet(() -> maisonRepository.save(new Maison(nom, univers)));
	}

	public Personnage creerPersonnage(String nom, Genre genre, Maison maison) {
		return new Personnage(nom, genre, maison);
	}

	public GenericEntitiesRepository crud() {
		return repository;
	}

	public TypeRelation getOrCreateTypeRelation(String nomTypeRelation) {
		return typeRelationRepository.findByDesignation(nomTypeRelation)
				.orElseGet(() -> typeRelationRepository.save(new TypeRelation(nomTypeRelation)));
	}

}
