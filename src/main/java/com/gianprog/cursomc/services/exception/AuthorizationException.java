package com.gianprog.cursomc.services.exception;

public class AuthorizationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AuthorizationException(String msg) {
		super(msg); // Chamar a superclasse e repassar a mensagem.
	}
	
	public AuthorizationException(String msg, Throwable cause) {
	// Sobrecarga de construtor para repassar também a causa de alguma coisa que aconteceu antes.
		super(msg, cause);
	}
}
