package fr.vinthec.personnage.resources.hpapi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

import fr.vinthec.personnage.resources.hpapi.ConfigProperties;
import fr.vinthec.personnage.resources.hpapi.PersonnageApIRepo;
import fr.vinthec.personnage.resources.hpapi.PersonnageApi;

@Component
public class PersonnageAPIRepoImpl implements PersonnageApIRepo {

	@Resource
	private transient RestTemplate restTemplate;
	
	@Resource
	private transient ConfigProperties properties;
	
	@Override
	public List<PersonnageApi> findPersonnages(){
		return Lists.newArrayList(restTemplate.getForObject(properties.getUrl(), PersonnageApi[].class));
	}
	
	
}
