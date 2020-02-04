package fr.vinthec.personnage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.google.common.collect.Lists;

import fr.vinthec.personnage.exceptions.EntityNotFoundException;
import fr.vinthec.personnage.exceptions.NotFoundException;
import fr.vinthec.personnage.modele.Acteur;
import fr.vinthec.personnage.modele.Genre;
import fr.vinthec.personnage.modele.Maison;
import fr.vinthec.personnage.modele.Personnage;
import fr.vinthec.personnage.modele.TypeRelation;
import fr.vinthec.personnage.modele.Univers;
import fr.vinthec.personnage.resources.GenericEntitiesRepository;
import fr.vinthec.personnage.resources.UniversRepository;

@RestController
public class Services {
	
	@Resource
	private transient UniversRepository universRepository;
	
	@Resource
	private transient GenericEntitiesRepository repository;
	
	@GetMapping("/test")
	public Maison inventeMaison()  {
		Univers u = new Univers("Terre");
		return new Maison("POEI", u);
	}
	
	@GetMapping("/tmp_2") 
	@Transactional
	public String remplirLaBase() throws EntityNotFoundException {
		Univers u1 = universRepository.save(new Univers("Terre"));
		Univers u2 = universRepository.save(new Univers("Harry Potter"));
		Univers u3 = universRepository.save(new Univers("Game of Throne"));
		repository.save(new Maison("poei", u1), Maison.class);
		 Maison m = repository.save(new Maison("Gryffondor", u2), Maison.class);
		repository.save(new Maison("Poufsouffle", u2), Maison.class);
		repository.save(new Maison("Serpentard", u2), Maison.class);
		Personnage p1 = repository.save(new Personnage("Harry Potter", Genre.MASCULIN), Personnage.class);
		Personnage p2 = repository.save(new Personnage("Hermione Granger", Genre.FEMININ), Personnage.class);
		p1.setMaison(m);
		p2.setMaison(m);
		Acteur acteur = repository.save(new Acteur("Baniel", "Radcliffe"), Acteur.class);
		p1.addActeur(acteur);
		repository.save(p1, Personnage.class);
		repository.save(p2, Personnage.class);
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
	
	@GetMapping("/maison/{id}")
	public Maison getUnivers(@PathVariable("id") Maison maison) throws NotFoundException {
		return maison;
		
	}
	
	@ExceptionHandler( NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND )
	public ExceptionServer handleException(Exception e) {
			return new ExceptionServer(e);
	}
}
