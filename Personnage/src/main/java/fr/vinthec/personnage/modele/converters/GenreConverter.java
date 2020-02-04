package fr.vinthec.personnage.modele.converters;

import javax.persistence.Converter;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumHashBiMap;

import fr.vinthec.personnage.modele.Genre;

@Converter(autoApply = true)
public class GenreConverter extends AbstractConverter<Genre, String> {

	private BiMap<Genre, String> mapping = EnumHashBiMap.create(Genre.class);
	{
		for (Genre genre : Genre.values()) {
			mapping.put(genre, genre.name());
		}
	}

	@Override
	protected BiMap<Genre, String> getMapping() {
		return mapping;
	}

}