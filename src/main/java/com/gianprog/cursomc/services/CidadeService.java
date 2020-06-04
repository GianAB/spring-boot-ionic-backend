package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.repositories.CidadeRepository;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	CidadeRepository repository;
	
	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Cidade.class.getName()));
	}
	
	public List<Cidade> findAll(){
		return repository.findAll();
	}
}
