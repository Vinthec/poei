package fr.vinthec.personnage.web;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import fr.vinthec.personnage.exceptions.EntityNotFoundException;
import fr.vinthec.personnage.exceptions.NotFoundException;
import fr.vinthec.personnage.modele.Acteur;
import fr.vinthec.personnage.modele.Genre;
import fr.vinthec.personnage.modele.Maison;
import fr.vinthec.personnage.modele.Personnage;
import fr.vinthec.personnage.modele.TypeRelation;
import fr.vinthec.personnage.modele.Univers;
import fr.vinthec.personnage.resources.hpapi.PersonnageApIRepo;
import fr.vinthec.personnage.resources.hpapi.PersonnageApi;
import fr.vinthec.personnage.resources.persistance.GenericEntitiesRepository;
import fr.vinthec.personnage.resources.persistance.MaisonRepository;
import fr.vinthec.personnage.resources.persistance.PersonnageRepository;
import fr.vinthec.personnage.resources.persistance.TypeRelationRepository;
import fr.vinthec.personnage.resources.persistance.UniversRepository;

@RestController
public class Services {

	@Resource
	private transient UniversRepository universRepository;

	@Resource
	private transient PersonnageRepository personnageRepository;

	@Resource
	private transient GenericEntitiesRepository repository;

	@Resource
	private transient PersonnageApIRepo personnageApIRepo;

	@Resource
	private transient MaisonRepository maisonRepository;
	
	@Resource
	private transient TypeRelationRepository typeRelationRepository;

	@GetMapping("/test")
	public Maison inventeMaison() {
		Univers u = new Univers("Terre");
		return new Maison("POEI", u);
	}

	@GetMapping("/tmp_2")
	@Transactional
	public String remplirLaBase() throws EntityNotFoundException {
		Univers u1 = universRepository.save(new Univers("Terre"));

		repository.save(new Maison("poei", u1), Maison.class);
		return "OK";

	}

	
	public void setRelation(Map<String, Personnage> personnages, String nomTypeRelation, Personnage personnage, Iterable<String> nomAutresPersos, Predicate<Personnage> perdicate) {

		TypeRelation typeRelation = typeRelationRepository.findByDesignation(nomTypeRelation).orElseGet(() ->typeRelationRepository.save(new TypeRelation(nomTypeRelation)) ); 
		for (String nom : nomAutresPersos) {
			Personnage autrePersonnage = personnages.get(nom);
			if(autrePersonnage != null && perdicate.test(autrePersonnage)) {
				personnage
				.addRelation(autrePersonnage,typeRelation);
			}		
		}
	
	}

	
	@GetMapping("tmp/GOT")
	@Transactional
	public String tmp_4() {
		Univers univers = universRepository.save(new Univers("Game of Thrones"));
		List<PersonnageApi> personnageApi = personnageApIRepo.findPersonnagesGOT();
		 Map<String, Personnage> personnages = addPersonnage(univers, personnageApi);
		 for (PersonnageApi api : personnageApi) {
			Personnage perso = personnages.get(api.getName());
			setRelation(personnages, "père", perso, api.getFather().stream().collect(Collectors.toList()), Predicates.alwaysTrue() );
			setRelation(personnages, "mère", perso, api.getMother().stream().collect(Collectors.toList()), Predicates.alwaysTrue() );
			setRelation(personnages, "frère", perso, api.getSiblings(),p->p.getGenre().equals(Genre.MASCULIN) );
			setRelation(personnages, "soeur", perso, api.getSiblings(),p->p.getGenre().equals(Genre.FEMININ) );
			setRelation(personnages, "marié", perso, api.getSpouse(),Predicates.alwaysTrue()  );
			setRelation(personnages, "amant", perso, api.getLovers(),Predicates.alwaysTrue() );
			
			personnageRepository.save(perso);	
		}
		return "OK";
	}

	private Map<String, Genre> genres = Map.of("male", Genre.MASCULIN, "female", Genre.FEMININ);
	private Pattern namePattern = Pattern.compile("\\h*(?<prenom>\\w+)\\h+(?<nom>\\w+)\\h*");

	@GetMapping("/tmp/HP")
	@Transactional
	public String tmp_3() {
		Univers univers = universRepository.save(new Univers("Harry Potter"));
		List<PersonnageApi> personnageApi = personnageApIRepo.findPersonnagesHP();
		addPersonnage(univers, personnageApi);
		return "OK";
	}

	public Map<String, Personnage> addPersonnage(Univers univers, List<PersonnageApi> personnagesApi) {
		Map<String, Personnage> ret = Maps.newHashMap();
		for (PersonnageApi persoAPI : personnagesApi) {
			String houseName = Strings.isNullOrEmpty(persoAPI.getHouse()) ? persoAPI.getName() : persoAPI.getHouse();
			Maison maison = maisonRepository.findByNomAndUnivers(houseName, univers)
					.orElseGet(() -> maisonRepository.save(new Maison(houseName, univers)));
			Personnage p = new Personnage(persoAPI.getName(), genres.getOrDefault(persoAPI.getGender(), Genre.NEUTRE), maison);
			Optional<Matcher> matcher = persoAPI.getActor().map(namePattern::matcher);
			matcher.ifPresent(m -> {
				if (m.matches()) {
					p.addActeur(new Acteur(m.group("nom"), m.group("prenom")));
				} else {
					p.addActeur(new Acteur(persoAPI.getActor().get(), null));
				}
			});
			ret.put(p.getNom(),personnageRepository.save(p));
		}
		
		return ret;
	}

	@PostMapping("/tmp/HP/age")
	public Personnage setAge() {
		Personnage p = personnageRepository.findByNomLike("%Harry%").orElseThrow();
		Set<Acteur> acteurs = p.getActeurs();
		if (acteurs.size() == 1) {
			Acteur a = acteurs.iterator().next();
			a.setDateNaissance(LocalDate.parse("1989-07-23"));
		}
		return personnageRepository.save(p);

	}

	@GetMapping("/univers")
	public List<Univers> listUnivers() {
		return universRepository.findAll();
	}

	@GetMapping("/univers/{id}")
	public Univers getUnivers(@PathVariable("id") Long id) throws NotFoundException {
		return universRepository.findById(id).orElseThrow(() -> new NotFoundException("Univers no " + id + " not found"));

	}

	@GetMapping("/univers2/{id}")
	public Optional<Univers> getUnivers2(@PathVariable("id") Long id) throws NotFoundException {
		return universRepository.findById(id);

	}

	@PostMapping("/univers")
	public Univers createUnivers(@RequestBody Univers u) {
		return universRepository.save(u);

	}

	@GetMapping("/univers/search")
	public List<Univers> searchUnivers(@Param("like") String like, @Param("contient") String contient) {
		if (like == null) {
			like = "%" + contient + "%";
		}
		return universRepository.findByNomLike(like);
	}

	@GetMapping("/maisons/{id}")
	public Maison getUnivers(@PathVariable("id") Maison maison) throws NotFoundException {
		return maison;

	}
	
	@GetMapping("/maisons")
	public List<Maison> allMaisons() {
		return maisonRepository.findAll();
	}

	@PostMapping("/maisons")
	@Transactional
	public Maison createMaison(@RequestBody Maison maison) {
		return repository.save(maison, Maison.class);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionServer handleException(Exception e) {
		return new ExceptionServer(e);
	}
}
