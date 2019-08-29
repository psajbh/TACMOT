package guru.springframework.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.exceptions.BadRequestException;
import guru.springframework.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {
	
    @ResponseStatus(HttpStatus.NOT_FOUND) //404
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception e){
    	log.error("handleNotFound: Handling not found exception");
    	log.error(e.getMessage());
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("404Error");
    	mav.addObject("exception", e);
    	return mav;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handleBadRequest(Exception e){
    	log.error("handleInvalidInput: Handling invalid input exception");
    	log.error(e.getMessage());
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("400Error");
    	mav.addObject("exception", e);
    	return mav;
    	
    }
	

}
