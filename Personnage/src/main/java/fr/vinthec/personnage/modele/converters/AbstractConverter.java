package fr.vinthec.personnage.modele.converters;

import javax.persistence.AttributeConverter;

import com.google.common.collect.BiMap;

public abstract class AbstractConverter<X,Y> implements AttributeConverter<X, Y>{

	@Override
	public Y convertToDatabaseColumn(X attribute) {
		if(attribute == null){
			return null;
		}
		Y ret = getMapping().get(attribute);
		if(ret == null){
			throw new IllegalArgumentException("No mapping found for " + attribute);	
		}
		return ret;
	}

	@Override
	public X convertToEntityAttribute(Y dbData) {
		if(dbData == null){
			return null;
		}
		X ret = getMapping().inverse().get(dbData);
		if(ret == null){
			throw new IllegalArgumentException("No mapping found for " + dbData);	
		}
		return ret;
	}

	abstract protected BiMap<X, Y> getMapping();
	
	
}
