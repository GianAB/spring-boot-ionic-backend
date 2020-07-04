package com.gianprog.cursomc.services.exception;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg); // Chamar a superclasse e repassar a mensagem.
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
	// Sobrecarga de construtor para repassar também a causa de alguma coisa que aconteceu antes.
		super(msg, cause);
	}
}
