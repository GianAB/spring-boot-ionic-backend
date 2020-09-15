package com.gianprog.cursomc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gianprog.cursomc.services.DBService;
import com.gianprog.cursomc.services.EmailService;
import com.gianprog.cursomc.services.SmtpEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instanciateDatabase() {
		dbService.instanciateTestDatabase();
		return true;
	}
	
//	@Bean
//	public EmailService emailService() {
//		return new MockEmailService();
//	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
