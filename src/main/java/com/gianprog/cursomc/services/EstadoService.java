package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Estado;
import com.gianprog.cursomc.repositories.EstadoRepository;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	EstadoRepository repository;
	
	public Estado findById(Integer id) {
		Optional<Estado> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Estado.class.getName()));
	}
	
	public List<Estado> findAll(){
		return repository.findAll();
	}
}
