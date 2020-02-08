package mil.dtic.cbes.utils.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mil.dtic.cbes.utils.exceptions.rest.PRCPDataProcessingException;
import mil.dtic.cbes.utils.exceptions.rest.PRCPFileProcessingException;
import mil.dtic.cbes.utils.exceptions.rest.PRCPUnknownTypeException;
import mil.dtic.cbes.utils.exceptions.rest.user.NotAuthorizedException;

@RestControllerAdvice(basePackages = "mil.dtic.cbes.controllers.prcp")
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {PRCPUnknownTypeException.class, PRCPFileProcessingException.class, PRCPDataProcessingException.class })
	protected ResponseEntity<Object> handleInternalErrorException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();

		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
	
	@ExceptionHandler(value = {NotAuthorizedException.class})
	protected ResponseEntity<Object> handleForbiddenErrorException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN,
				request);
	}
}
