package fr.vinthec.personnage.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Univers {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String nom;

	Univers() {
	}

	public Univers(@NotEmpty String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public Long getId() {
		return id;
	}
	
	

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	
	
}
