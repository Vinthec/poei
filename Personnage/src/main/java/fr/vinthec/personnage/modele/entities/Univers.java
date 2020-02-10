package fr.vinthec.personnage.modele.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;

@Entity
public class Univers  extends AbstractEntity<Long> {

	//attribute
	
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@NaturalId
	private String nom;

	
	@OneToMany(mappedBy = "univers")
	private Set<Maison> maisons = Sets.newHashSet();

	//constructor
	
	public Univers() {
	}	//for Hibernate and Jackson

	public Univers(@NotEmpty String nom) {
		super();
		this.nom = nom;
	} 

	//properties
	public String getNom() {
		return nom;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Set<Maison> getMaisons() {
		return Sets.newHashSet(maisons);
	}
	
	//operations

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	//utils
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Univers [id=");
		builder.append(id);
		builder.append(", nom=");
		builder.append(nom);
		builder.append(", maisons=");
		builder.append(maisons);
		builder.append("]");
		return builder.toString();
	}
	

	
	
	
	

}
