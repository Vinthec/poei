package fr.vinthec.personnage.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NaturalId;

@Entity
public class Univers {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@NaturalId
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
		Univers other = (Univers) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
}
