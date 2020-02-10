package fr.vinthec.personnage.modele.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends AbstractEntity<Long> {

	@Id
	@GeneratedValue
	private Long id;

	
	@Override
	public Long getId() {
		return id;
	}
	
	
	
}
