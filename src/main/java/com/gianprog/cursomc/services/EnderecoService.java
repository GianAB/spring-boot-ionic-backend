package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Endereco;
import com.gianprog.cursomc.repositories.EnderecoRepository;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public List<?> findAll(){
		return repository.findAll();
	}
	
	public Endereco findById(Integer id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Endereco.class.getName()));
	}
}
