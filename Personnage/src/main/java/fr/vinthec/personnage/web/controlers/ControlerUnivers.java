package fr.vinthec.personnage.web.controlers;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import fr.vinthec.personnage.modele.GestionPersonnageServices;
import fr.vinthec.personnage.modele.entities.Maison;
import fr.vinthec.personnage.modele.entities.Univers;

@RestController
@RequestMapping("/univers")
@Transactional
public class ControlerUnivers {

	
	@Resource
	private transient GestionPersonnageServices services;
	
	@GetMapping
	public List<Univers> allUnivers(){
		return Lists.newArrayList(services.crud().findAll(Univers.class));
	}
	
	@GetMapping("/{id}")
	public Univers getUnivers(@PathVariable ("id") Univers  univers) {
		return univers;
	}
	
	@GetMapping("/{id}/maisons")
	public Set<Maison> getMaisons(@PathVariable ("id") Univers  univers) {
		return univers.getMaisons();
	}
	
	@PostMapping
	@PutMapping
	public Univers createUnivers(@RequestBody Univers univers) {
		return services.crud().save(univers, Univers.class);
	}
	
	
	
}
