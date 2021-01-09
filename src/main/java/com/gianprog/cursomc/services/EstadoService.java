package com.gianprog.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Estado;
import com.gianprog.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository repository;
	
	public List<Estado> findAll(){
		return repository.findAllByOrderByNome();
	}
	
}
