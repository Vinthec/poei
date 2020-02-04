package fr.vinthec.personnage.modele;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

@Entity
public class Personnage {

	@Id
	@GeneratedValue
	private Long id; 
	
	@NaturalId
	private String nom;
	
	private Genre genre;
	
	@ManyToOne
	@NaturalId
	private Maison maison;
	
	@ManyToMany(mappedBy = "personnages")
	private Set<Acteur> acteurs;
	
	public Personnage() {}

	public Personnage(String nom, Genre genre) {
		super();
		this.nom = nom;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public Genre getGenre() {
		return genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personnage other = (Personnage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
