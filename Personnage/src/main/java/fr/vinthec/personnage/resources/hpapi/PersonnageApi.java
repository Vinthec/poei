package fr.vinthec.personnage.resources.hpapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonnageApi {
	private String name;
	private String gender;
	private String house;
	private String actor;
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getHouse() {
		return house;
	}
	public String getActor() {
		return actor;
	}
	@Override
	public String toString() {
		return String.format("PersonnageApi [name=%s, gender=%s, house=%s, actor=%s]", name, gender, house, actor);
	}
	
	
	
	
	
}
