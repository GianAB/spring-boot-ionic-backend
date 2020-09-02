package com.gianprog.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Pedido;

@Service
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); // Destinatário
		sm.setFrom(sender); // Remetente
		sm.setSubject("Pedido confirmado! Cod: " + obj.getId()); // Assunto
		sm.setSentDate(new Date(System.currentTimeMillis())); // Data do envio
		sm.setText(obj.toString()); // Corpo
		return sm;
	}
	
}
