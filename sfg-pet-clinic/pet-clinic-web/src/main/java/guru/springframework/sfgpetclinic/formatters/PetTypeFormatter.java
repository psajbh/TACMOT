package guru.springframework.sfgpetclinic.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;

/*
* Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting from Spring 3.0, Formatters have
* come as an improvement in comparison to legacy PropertyEditors. See the following links for more details: - The
* Spring ref doc: https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format-Formatter-SPI
* see: http://gordondickens.com/wordpress/2010/09/30/using-spring-3.0-custom-type-converter
*/

@Slf4j
@Component
public class PetTypeFormatter implements Formatter<PetType> {
	

	private final PetTypeService petTypeService;

	@Autowired
	public PetTypeFormatter(PetTypeService petTypeService) {
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		log.debug("print: petType: " + petType.getName());
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
	    log.debug("parse: text: " + text);
		Collection<PetType> findPetTypes = petTypeService.findAll();
		log.debug("parse: text: " + text);
		for (PetType type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}

		throw new ParseException("type not found: " + text, 0);
	}

}
