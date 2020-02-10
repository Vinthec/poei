package fr.vinthec.personnage.modele.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Relation extends AbstractEntity<Long> {

	@Id
	@GeneratedValue
	private Long id; 
	
	private int niveau;
	
	@ManyToOne
	private Personnage source;
	
	@ManyToOne
	private Personnage cible;
	
	@OneToOne
	private Relation reciproque;
	
	@ManyToOne 
	private TypeRelation type;
	

	public Relation() {
	}

	public Relation( Personnage cible, TypeRelation type, int niveau , Personnage source) {
		this.niveau = niveau;
		this.cible = cible;
		this.type = type;
		this.source = source;
	}
	public Relation( int niveau, Relation relation) {
		this.niveau = niveau;
		this.cible = relation.source;
		this.source = relation.cible;
		this.type = relation.type;
		this.reciproque = relation;
		relation.reciproque= this;
	}

	public Long getId() {
		return id;
	}

	public int getNiveau() {
		return niveau;
	}

	public Personnage getSource() {
		return source;
	}

	public Personnage getCible() {
		return cible;
	}

	public Relation getReciproque() {
		return reciproque;
	}

	public TypeRelation getType() {
		return type;
	}
	
	
}
