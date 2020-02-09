package fr.vinthec.personnage.modele;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import com.google.common.collect.Sets;

@Entity
public class Personnage {

	@Id
	@GeneratedValue
	private Long id; 
	
	@NaturalId
	private String nom;
	
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Maison maison;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Acteur> acteurs = Sets.newHashSet();
	
	@OneToMany(mappedBy = "source" , cascade = CascadeType.ALL)
	private Set<Relation> relations = Sets.newHashSet();
	
	public Personnage() {}

	public Personnage(String nom, Genre genre, Maison maison) {
		super();
		this.nom = nom;
		this.genre = genre;
		this.maison = maison;
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


	public void addActeur(Acteur acteur) {
		acteurs.add(acteur);
	}
	
	
	public void addRelation(Personnage p2, TypeRelation type) {
		Relation r = new Relation( p2, type, 10, this);
		relations.add(r);
	}
	

	
	public void setMaison(Maison maison) {
		this.maison = maison;
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

	public Set<Acteur> getActeurs() {
		return Sets.newHashSet(acteurs);
	}




	
	
	
	
}
