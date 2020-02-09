package fr.vinthec.personnage.resources.hpapi;

import java.util.List;

public interface PersonnageApIRepo {

	List<PersonnageApi> findPersonnagesHP();

	
	List<PersonnageApi> findPersonnagesGOT();
}