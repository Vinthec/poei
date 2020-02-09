package fr.vinthec.personnage.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

@Entity
public class Maison {
	@Id
	@GeneratedValue
	private Long id;

	@NotBlank
	@NaturalId
	private String nom;

	private String blason;

	@NaturalId
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
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
		Maison other = (Maison) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return id == null ? super.equals(obj) : true;
	}

	// public Univers getUnivers() {
	// return univers;
	// }
	//
	//

}
