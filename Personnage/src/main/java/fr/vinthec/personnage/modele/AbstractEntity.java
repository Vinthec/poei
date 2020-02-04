package fr.vinthec.personnage.modele;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Classe de base technique qui ajoute les attributs nécessaires au suivi des
 * modifications des différentes entities.
 * 
 * @param <T>
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<T extends Serializable> {

	@CreatedDate
	private Date dateCreation;

//	@CreatedBy
//	@ManyToOne
//	private User userCreation;

	@LastModifiedDate
	private Date dateLastModification;

//	@ManyToOne
//	@LastModifiedBy
//	private User userLastModification;
	
	
	public abstract T getId();

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass().isInstance(obj)) {
			return ((AbstractEntity<T>) obj).getId().equals(getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		Object o = getId();
		int i = (o == null ? "" : getId()).hashCode();
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + getClass().hashCode();
		return result;
	}

	
	
	
	
}
