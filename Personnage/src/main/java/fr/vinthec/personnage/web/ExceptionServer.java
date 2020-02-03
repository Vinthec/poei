package fr.vinthec.personnage.web;

public class ExceptionServer {
	private String type;
	private  String message;

	public ExceptionServer(Exception e) {
		type = e.getClass().getCanonicalName();
		message = e.getMessage();	
	}

	public String getType() {
		return type;
	}

	public String getMessage() {
		return message;
	}

	
	
}
