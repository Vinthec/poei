package fr.vinthec.personnage.web.controlers;

import java.util.List;

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

@RestController
@RequestMapping("/maisons")
@Transactional
public class ControlerMaison {

	
	@Resource
	private transient GestionPersonnageServices services;
	
	@GetMapping
	public List<Maison> allMaison(){
		return Lists.newArrayList(services.crud().findAll(Maison.class));
	}
	
	@GetMapping("/{id}")
	public Maison getMaison(@PathVariable ("id") Maison  maison) {
		return maison;
	}
	
	@PostMapping
	@PutMapping
	public Maison createMaison(@RequestBody Maison maison) {
		return services.crud().save(maison, Maison.class);
	}
	
	
	
}
