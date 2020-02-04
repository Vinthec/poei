package fr.vinthec.personnage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

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

import fr.vinthec.personnage.exceptions.NotFoundException;
import fr.vinthec.personnage.modele.Maison;
import fr.vinthec.personnage.modele.Univers;
import fr.vinthec.personnage.resources.UniversRepository;

@RestController
public class Services {
	
	@Resource
	private transient UniversRepository universRepository;
	
	@GetMapping("/test")
	public Maison inventeMaison()  {
		Univers u = new Univers("Terre");
		return new Maison("POEI", u);
	}
	
	@GetMapping("/tmp_1") 
	public String remplirLaBase() {
		universRepository.save(new Univers("Terre"));
		universRepository.save(new Univers("Harry Potter"));
		universRepository.save(new Univers("Game of Throne"));
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
		return universRepository.findUniversLike(like);
	}
	

	
	@ExceptionHandler( NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND )
	public ExceptionServer handleException(Exception e) {
			return new ExceptionServer(e);
	}
}
