package fr.vinthec.personnage.exceptions;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME , include = JsonTypeInfo.As.PROPERTY)
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private Class<?> clazz;
	private Object id;

	/**
	 * @param clazz
	 *            The class where an object with the ID was not found
	 * @param id
	 *            the not foundID
	 */
	public EntityNotFoundException(Class<?> clazz, Object id) {
		this.clazz = clazz;
		this.id = id;
	}

	/**
	 * @return the class where an object with the ID was not found
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * @return the not foundID
	 */
	public Object getId() {
		return id;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage() + " : " + getId();
	}

}
