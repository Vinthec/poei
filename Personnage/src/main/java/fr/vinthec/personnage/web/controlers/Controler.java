package fr.vinthec.personnage.web.controlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.vinthec.personnage.exceptions.NotFoundException;
import fr.vinthec.personnage.web.exceptions.ExceptionServer;

@RestController
public class Controler {


	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionServer handleException(Exception e) {
		return new ExceptionServer(e);
	}
}
