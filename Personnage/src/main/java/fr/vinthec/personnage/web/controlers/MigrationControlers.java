package fr.vinthec.personnage.web.controlers;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.vinthec.personnage.modele.entities.Univers;
import fr.vinthec.personnage.module.persoApi.MigrationService;

@RestController
@RequestMapping("/migration")
@Transactional
public class MigrationControlers {


	@Resource
	private transient MigrationService migration;

	
	@PostMapping("/HP")
	public Univers creationUniversHP() {
		return migration.creerUniversHarryPotter();
	}
	
	
	@PostMapping("/GOT")
	public Univers creationUniversGOT() {
		return migration.creerUniversGameOfThrones();
	}
	
}
