/**
 * 
 */
package courses.sov.petclinic.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import courses.sov.petclinic.exceptions.BadRequestException;
import courses.sov.petclinic.exceptions.NotFoundException;

/**
 * @author dcividin
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	// since @ExceptionHandler has the precedence, I have to annotate it again with @ResponseStatus
	// if you want you can use this block inside a controller also, in order to handle a specif situation
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String handleNotFoundException(Model model, NotFoundException exception) {
		log.error("Not Found Exception: {} ", exception.getLocalizedMessage());
		model.addAttribute("error_type", "Global Advice - Not Found Exception");
		model.addAttribute("error_message", exception.getLocalizedMessage());
		return "/errors/404error";
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public String handleBadRequestException(Model model, BadRequestException exception) {
		log.error("Bad Request Exception: {}", exception.getLocalizedMessage());
		model.addAttribute("error_type", "Global Advice - Bad Request Exception");
		model.addAttribute("error_message", exception.getLocalizedMessage());
		return "/errors/400error";
	}
}
