package fr.vinthec.personnage.resources.hpapi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import fr.vinthec.personnage.resources.hpapi.PersonnageApIRepo;
import fr.vinthec.personnage.resources.hpapi.PersonnageApi;

@Component
public class PersonnageAPIRepoImpl implements PersonnageApIRepo {

	@Resource
	private transient RestTemplate restTemplate;
	
	@Value("${personnage-api.url.harry-potter}")
	private String urlHP;

	@Value("${personnage-api.url.game-of-thrones}")
	private String urlGOT;
	
	
	@Override
	public List<PersonnageApi> findPersonnagesHP(){
		return Lists.newArrayList(restTemplate.getForObject(urlHP, PersonnageApi[].class));
	}


	@Override
	public List<PersonnageApi> findPersonnagesGOT() {
		return Lists.newArrayList(restTemplate.getForObject(urlGOT, PersonnageApi[].class));
	}
	
	
}
