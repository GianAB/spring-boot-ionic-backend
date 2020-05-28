package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Categoria;
import com.gianprog.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		List<Categoria> lista = repository.findAll();
		return lista;
	}
	
	public Categoria findById(Integer id){
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElse(null);
	}
}
