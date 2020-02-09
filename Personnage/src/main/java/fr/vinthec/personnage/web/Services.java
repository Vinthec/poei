package fr.vinthec.personnage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

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
	
	@GetMapping("/test")
	public Maison inventeMaison()  {
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
	
	@GetMapping("tmp/GOT")
	@Transactional
	public String tmp_4() {
		Univers u = new Univers("Game of Throne");
		u.addMaison(new Maison("Stark", u));
		u.addMaison(new Maison("Lannister", u));
		u.addMaison(new Maison("Targaryen", u));
		u.addMaison(new Maison("Baratheon", u));
		u.addMaison(new Maison("Tyrell", u));
		u.addMaison(new Maison("Martell", u));
		u.addMaison(new Maison("Greyjoy", u));
		u.addMaison(new Maison("Arryn", u));
		u.addMaison(new Maison("Tully", u));
		universRepository.save(u);
		return "OK";
	}
	
	
	private Map<String, Genre> genres = Map.of("male",Genre.MASCULIN, "female", Genre.FEMININ);
	private Pattern namePattern = Pattern.compile("\\h*(?<prenom>\\w+)\\h+(?<nom>\\w+)\\h*");
	
	@GetMapping ("/tmp/HP")
	@Transactional
	public String tmp_3() {
		Univers univers = universRepository.save(new Univers("Harry Potter"));
		for (PersonnageApi persoAPI : personnageApIRepo.findPersonnages()) {
			String houseName = Strings.isNullOrEmpty(persoAPI.getHouse())? "Aucune": persoAPI.getHouse();			
			Optional<Maison> maison = maisonRepository.findByNom(houseName);
			if(maison.isEmpty()) {
				maison = Optional.of(maisonRepository.save(new Maison(houseName, univers)));
			}
			Personnage p = new Personnage(persoAPI.getName(), genres.getOrDefault(persoAPI, Genre.NEUTRE));
			Matcher matcher = namePattern.matcher(persoAPI.getActor());
			if(matcher.matches()) {
				p.addActeur(new Acteur(matcher.group("nom"), matcher.group("prenom")));
			}
			personnageRepository.save(p);
		}
		return "OK";
	}
	
	
	@GetMapping("/univers")
	public List<Univers> listUnivers(){
		return universRepository.findAll();	
	}
	
	

	
	@GetMapping("/univers/{id}")
	public Univers getUnivers(@PathVariable("id") Long id) throws NotFoundException {
		return universRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Univers no "+ id + " not found"));
		
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
		if(like == null) {
			like = "%"+contient+"%";
		}
		return universRepository.findByNomLike(like);
	}
	
	@GetMapping("/maisons/{id}")
	public Maison getUnivers(@PathVariable("id") Maison maison) throws NotFoundException {
		return maison;
		
	}
	
	@PostMapping("/maisons")
	@Transactional
	public Maison createMaison(@RequestBody Maison maison) {
		return repository.save(maison, Maison.class);
	}
	
	@ExceptionHandler( NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND )
	public ExceptionServer handleException(Exception e) {
			return new ExceptionServer(e);
	}
}
