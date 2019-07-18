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

		for (PetType type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}

		throw new ParseException("type not found: " + text, 0);
	}

}
