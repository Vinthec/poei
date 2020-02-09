package fr.vinthec.personnage.modele;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Relation {

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

	public Relation( Personnage cible, TypeRelation type, int niveau) {
		this.niveau = niveau;
		this.cible = cible;
		this.type = type;
	}
	public Relation( int niveau, Relation relation) {
		this.niveau = niveau;
		this.cible = relation.source;
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
		Relation other = (Relation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
