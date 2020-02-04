package fr.vinthec.personnage.resources;

import java.io.Serializable;

import fr.vinthec.personnage.exceptions.EntityNotFoundException;



public interface GenericEntitiesRepository {

	public <T> T get(Serializable id, Class<T> cl) throws EntityNotFoundException;

	<T> T save(T entity, Class<T> cl);

	<T> Iterable<T> save(Iterable<T> entities, Class<T> cl);

	<T> void delete(T entity, Class<T> cl);

	<T> void delete(Iterable<T> entities, Class<T> cl);

	<T> Iterable<T> findAll(Class<T> cl);
	

}