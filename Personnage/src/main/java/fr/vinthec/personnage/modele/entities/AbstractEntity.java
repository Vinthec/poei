package fr.vinthec.personnage.modele.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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

	@CreatedBy
	@ManyToOne
	private User userCreation;

	@LastModifiedDate
	private Date dateLastModification;

	@ManyToOne
	@LastModifiedBy
	private User userLastModification;

	public abstract T getId();


}
