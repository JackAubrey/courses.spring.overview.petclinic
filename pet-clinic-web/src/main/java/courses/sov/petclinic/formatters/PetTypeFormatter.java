/**
 * 
 */
package courses.sov.petclinic.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import courses.sov.petclinic.model.PetType;
import courses.sov.petclinic.service.PetTypeService;

/**
 * @author dcividin
 *
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {
	private final PetTypeService service;
	
	public PetTypeFormatter(PetTypeService service) {
		this.service = service;
		
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}

	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		return service.findAll().stream()
		.filter(t -> t.getName().equalsIgnoreCase(text.trim()))
		.findAny()
		.orElseThrow( () -> new ParseException("Pet type not found: "+text, 0));
	}

}
