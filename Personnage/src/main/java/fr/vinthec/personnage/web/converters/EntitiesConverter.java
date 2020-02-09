package fr.vinthec.personnage.web.converters;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.Entity;

import org.reflections.Reflections;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;

import fr.vinthec.personnage.exceptions.EntityNotFoundException;
import fr.vinthec.personnage.modele.AbstractEntity;


@Component
public class EntitiesConverter implements GenericConverter {

	@Resource
	private transient GenericConversionService genericConversionService;

	@Resource
	private transient fr.vinthec.personnage.resources.persistance.GenericEntitiesRepository repository;

	@FunctionalInterface
	private static interface LongGetter<T extends AbstractEntity<Long>> {
		T get(long id) throws EntityNotFoundException;
	};

	private Set<Class<?>> converters = new HashSet<>();

	@PostConstruct
	private void init() throws NoSuchMethodException, SecurityException {
		Reflections reflections = new Reflections(AbstractEntity.class.getPackage().getName());
		Set<Class<?>> allLongIdentifiableEntities = reflections.getTypesAnnotatedWith(Entity.class);
		for (Class<?> cl : allLongIdentifiableEntities) {
			if (cl.getAnnotation(Entity.class) != null) {
				Method method = cl.getMethod("getId");
				if (method != null && method.getReturnType().isAssignableFrom(Long.class)) {
					converters.add(cl);
				}
			}
		}
		genericConversionService.addConverter(this);
	}

	public <T> T convert(Object source, Class<T> targetType) {
		long id = Long.parseLong((String) source);
		try {
			return repository.get(id, targetType);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		return convert(source, targetType.getType());
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> ret = new HashSet<>(converters.size());
		for (Class<?> clazz : converters) {
			ret.add(new ConvertiblePair(String.class, clazz));
		}
		return ret;
	}

}
