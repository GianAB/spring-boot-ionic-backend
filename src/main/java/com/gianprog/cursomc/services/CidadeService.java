package com.gianprog.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository repository;
	
	public List<Cidade> findByEstado(Integer estadoId) {
		return repository.findCidades(estadoId);
	}
}
