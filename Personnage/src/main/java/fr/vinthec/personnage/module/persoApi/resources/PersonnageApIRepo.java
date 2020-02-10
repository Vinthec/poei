package fr.vinthec.personnage.module.persoApi.resources;

import java.util.List;

public interface PersonnageApIRepo {

	List<PersonnageApi> findPersonnagesHP();

	
	List<PersonnageApi> findPersonnagesGOT();
}