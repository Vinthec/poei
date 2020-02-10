package fr.vinthec.personnage.modele.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

@Entity
public class Acteur extends AbstractEntity<Long> {

	public Acteur() {}

	@Id
	@GeneratedValue
	private Long id;
	
	private String nom; 
	
	private String prenom;
	
	private LocalDate dateNaissance;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "acteurs")
	private Set<Personnage> personnages = Sets.newHashSet();


	
	
	public Acteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	
	public String getPrenom() {
		return prenom;
	}
	
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Long getId() {
		return id;
	}
	
	@JsonIgnore
	public Set<Personnage> getPersonnages() {
		return personnages;
	}

	
	
}
