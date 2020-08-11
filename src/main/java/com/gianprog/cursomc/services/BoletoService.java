package com.gianprog.cursomc.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PagamentoComBoleto pgto, LocalDate instante) {
		pgto.setDataVencimento(instante.plusDays(3));
		
	}
}
