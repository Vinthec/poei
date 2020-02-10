package fr.vinthec.personnage.modele.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

@Entity
public class TypeRelation  extends AbstractEntity<Long> {

	@Id
	@GeneratedValue
	private Long id; 
	
	@NaturalId
	private String designation;
	
	
	public TypeRelation() {
	}

	

	public TypeRelation(String designation) {
		super();
		this.designation = designation;
	}



	public Long getId() {
		return id;
	}


	public String getDesignation() {
		return designation;
	}


	
	
	
}
