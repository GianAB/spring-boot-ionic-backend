package com.gianprog.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.domain.Pedido;

@Service
public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
		
	}
	
	/* Nesta estratégia eu terceirizo a instância do SimpleMailMessage para um método onde eu recebo
	 * a classe que conterá todos os dados relevantes. Desta maneira eu consigo organizar toda a 
	 * estrutura do email antes de sobrepo-lo no método sendEmail da interface EmailService.  
	 * Obs.: Eu não precisei sobrescrever com o sendEmail pois eu não implementei o corpo do método.
	 * */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail()); // Destinatário
		sm.setFrom(sender); // Remetente
		sm.setSubject("Pedido confirmado! Cod: " + obj.getId()); // Assunto
		sm.setSentDate(new Date(System.currentTimeMillis())); // Data do envio
		sm.setText(obj.toString()); // Corpo
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {
			MimeMessage pmm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(pmm);	
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mm;
	}
	
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);	
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail()); // Destinatário
		sm.setFrom(sender); // Remetente
		sm.setSubject("Solicitação de nova senha!"); // Assunto
		sm.setSentDate(new Date(System.currentTimeMillis())); // Data do envio
		sm.setText("Nova senha: " + newPass); // Corpo
		return sm;
	}
}
