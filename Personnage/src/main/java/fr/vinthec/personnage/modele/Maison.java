package fr.vinthec.personnage.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Maison {
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotBlank
	private String nom;
	
	
	private String blason;
	
	@ManyToOne
	private Univers univers;
	
	public Maison() {
	}

	public Maison(String nom, Univers univers) {
		super();
		this.nom = nom;
		this.univers = univers;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getBlason() {
		return blason;
	}

	public Univers getUnivers() {
		return univers;
	}
	
	
	
	
	

	
}
