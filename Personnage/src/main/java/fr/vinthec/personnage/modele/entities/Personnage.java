package fr.vinthec.personnage.modele.entities;

import java.util.Set;

import javax.persistence.CascadeType;
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

import fr.vinthec.personnage.modele.values.Genre;

@Entity
public class Personnage  extends AbstractEntity<Long> {

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


	public Acteur addActeur(String nom, String prenom) {
		Acteur a = new Acteur(nom, prenom);
		acteurs.add(a);
		return a;
	}
	
	
	public void addRelation(Personnage p2, TypeRelation type) {
		Relation r = new Relation( p2, type, 10, this);
		relations.add(r);
	}
	

	
	public void setMaison(Maison maison) {
		this.maison = maison;
	}
	


	public Set<Acteur> getActeurs() {
		return Sets.newHashSet(acteurs);
	}




	
	
	
	
}
