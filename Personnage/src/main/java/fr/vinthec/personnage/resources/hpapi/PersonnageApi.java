package fr.vinthec.personnage.resources.hpapi;

import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Sets;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnageApi {
	private String name;
	private String gender;
	private String house;
	private Optional<String> actor = Optional.empty();
	
	private Set<String> siblings= Sets.newHashSet();
	private Set<String> spouse= Sets.newHashSet();
	private Set<String> lovers= Sets.newHashSet();
	private Optional<String> father = Optional.empty();
	private Optional<String> mother = Optional.empty();
	
	
	
	
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getHouse() {
		return house;
	}
	public Optional<String> getActor() {
		return actor;
	}
	
	
	public Set<String> getSiblings() {
		return Sets.newHashSet(siblings);
	}
	public Set<String> getSpouse() {
		return Sets.newHashSet(spouse);
	}
	public Set<String> getLovers() {
		return Sets.newHashSet(lovers);
	}
	public Optional<String> getFather() {
		return father;
	}
	public Optional<String> getMother() {
		return mother;
	}
	
	@Override
	public String toString() {
		return String.format("PersonnageApi [name=%s, gender=%s, house=%s, actor=%s]", name, gender, house, actor);
	}
	
	
	
	
	
}
