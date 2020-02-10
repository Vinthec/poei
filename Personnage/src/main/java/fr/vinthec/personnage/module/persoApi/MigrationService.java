package fr.vinthec.personnage.module.persoApi;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import fr.vinthec.personnage.modele.GestionPersonnageServices;
import fr.vinthec.personnage.modele.entities.Maison;
import fr.vinthec.personnage.modele.entities.Personnage;
import fr.vinthec.personnage.modele.entities.TypeRelation;
import fr.vinthec.personnage.modele.entities.Univers;
import fr.vinthec.personnage.modele.values.Genre;
import fr.vinthec.personnage.module.persoApi.resources.PersonnageApIRepo;
import fr.vinthec.personnage.module.persoApi.resources.PersonnageApi;

@Service
public class MigrationService {

	@Resource
	private transient GestionPersonnageServices gestionPersonnage;

	@Resource
	private transient PersonnageApIRepo personnageApIRepo;

	public Univers creerUniversHarryPotter() {
		Univers univers = gestionPersonnage.creerUnivers("Harry Potter");
		List<PersonnageApi> personnageApi = personnageApIRepo.findPersonnagesHP();
		addPersonnage(univers, personnageApi);
		return univers;
	}

	public Univers creerUniversGameOfThrones() {

		Univers univers = gestionPersonnage.creerUnivers("Game of Thrones");
		List<PersonnageApi> personnageApi = personnageApIRepo.findPersonnagesGOT();
		Map<String, Personnage> personnages = addPersonnage(univers, personnageApi);
		for (PersonnageApi api : personnageApi) {
			Personnage perso = personnages.get(api.getName());
			setRelation(personnages, "père", perso,api.getFather().map(r -> Sets.newHashSet(r)).orElse(Sets.newHashSet()), Predicates.alwaysTrue());
			setRelation(personnages, "mère", perso,  api.getMother().map(r -> Sets.newHashSet(r)).orElse(Sets.newHashSet()), Predicates.alwaysTrue());
			setRelation(personnages, "frère", perso, api.getSiblings(), p -> p.getGenre().equals(Genre.MASCULIN));
			setRelation(personnages, "soeur", perso, api.getSiblings(), p -> p.getGenre().equals(Genre.FEMININ));
			setRelation(personnages, "marié", perso, api.getSpouse(), Predicates.alwaysTrue());
			setRelation(personnages, "amant", perso, api.getLovers(), Predicates.alwaysTrue());

			gestionPersonnage.crud().save(perso, Personnage.class);
		}
		return univers;

	}

	private Map<String, Genre> genres = Maps.newHashMap();
	{
		genres.put("male", Genre.MASCULIN);
		genres.put("female", Genre.FEMININ);
	}
			
	
	private Pattern namePattern = Pattern.compile("\\h*(?<prenom>\\w+)\\h+(?<nom>.+)\\h*");

	Map<String, Personnage> addPersonnage(Univers univers, List<PersonnageApi> personnagesApi) {
		Map<String, Personnage> ret = Maps.newHashMap();
		for (PersonnageApi persoAPI : personnagesApi) {
			String houseName = Strings.isNullOrEmpty(persoAPI.getHouse()) ? persoAPI.getName() : persoAPI.getHouse();
			Maison maison = gestionPersonnage.getOrCreateMaison(houseName, univers);
			Personnage p = gestionPersonnage.creerPersonnage(persoAPI.getName(), genres.getOrDefault(persoAPI.getGender(), Genre.NEUTRE), maison);
			Optional<Matcher> matcher = persoAPI.getActor().map(namePattern::matcher);
			matcher.ifPresent(m -> {
				if (m.matches()) {
					p.addActeur(m.group("nom"), m.group("prenom"));
				} else {
					p.addActeur(persoAPI.getActor().get(), null);
				}
			});
			ret.put(p.getNom(), gestionPersonnage.crud().save(p, Personnage.class));
		}
		return ret;
	}

	void setRelation(Map<String, Personnage> personnages, String nomTypeRelation, Personnage personnage, Iterable<String> nomAutresPersos,
			Predicate<Personnage> perdicate) {

		TypeRelation typeRelation = gestionPersonnage.getOrCreateTypeRelation(nomTypeRelation);
		for (String nom : nomAutresPersos) {
			Personnage autrePersonnage = personnages.get(nom);
			if (autrePersonnage != null && perdicate.test(autrePersonnage)) {
				personnage.addRelation(autrePersonnage, typeRelation);
			}
		}

	}

}
