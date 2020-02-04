package fr.vinthec.personnage.web.converters;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;


//import fr.mnsp.declasso.model.valueobjects.TypeStructure;

@Component
public class TypesConverter{
	
	@Resource 
	private transient GenericConversionService genericConversionService;
	
	@PostConstruct
	private void init(){
		genericConversionService.addConverter(new Converter<String, YearMonth>() {

			@Override
			public YearMonth convert(String source) {
				return YearMonth.parse(source);
			}
			
		});
		
		genericConversionService.addConverter(new Converter<String, Year>() {

			@Override
			public Year convert(String source) {
				return Year.parse(source);
			}
			
		});
		
		genericConversionService.addConverter(new Converter<String, LocalDate>() {

			@Override
			public LocalDate convert(String source) {
				return LocalDate.parse(source);
			}

		});


		genericConversionService.addConverter(new Converter<String, Date>() {

			@Override
			public Date convert(String source) {
				return new Date(Long.parseLong(source));
			}

		});

	}
	
}
