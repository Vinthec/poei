package fr.vinthec.personnage.resources.persistance.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.vinthec.personnage.exceptions.EntityNotFoundException;
import fr.vinthec.personnage.resources.persistance.GenericEntitiesRepository;


@Component
public class GenericEntitiesRepositoryImpl implements GenericEntitiesRepository {

	@Resource
	private transient EntityManager em;

	@Override
	public <T> T get(Serializable id, Class<T> cl) throws EntityNotFoundException {
		T t = em.find(cl, id);
		if (t == null) {
			throw new EntityNotFoundException(cl, id);
		}
		return t;
	}

	@Override
	public <T> T save(T entity, Class<T> cl) {
		CrudRepository<T, ?> repo = new SimpleJpaRepository<T, Serializable>(cl, em);
		return repo.save(entity);
	}

	@Override
	public <T> Iterable<T> save(Iterable<T> entities, Class<T> cl) {
		CrudRepository<T, ?> repo = new SimpleJpaRepository<T, Serializable>(cl, em);
		return repo.saveAll(entities);
	}

	@Override
	public <T> void delete(T entity, Class<T> cl) {
		CrudRepository<T, ?> repo = new SimpleJpaRepository<T, Serializable>(cl, em);
		repo.delete(entity);
	}

	@Override
	public <T> void delete(Iterable<T> entities, Class<T> cl) {
		CrudRepository<T, ?> repo = new SimpleJpaRepository<T, Serializable>(cl, em);
		repo.deleteAll(entities);
	}
	@Override
	public <T> Iterable<T> findAll(Class<T> cl) {
		CrudRepository<T, ?> repo = new SimpleJpaRepository<T, Serializable>(cl, em);
		return repo.findAll();
	}

}
