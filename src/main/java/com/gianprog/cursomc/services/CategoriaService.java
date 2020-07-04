package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Categoria;
import com.gianprog.cursomc.repositories.CategoriaRepository;
import com.gianprog.cursomc.services.exception.DataIntegrityException;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll() {
		List<Categoria> lista = repository.findAll();
		return lista;
	}
	
	public Categoria findById(Integer id) throws ObjectNotFoundException{
		Optional<Categoria> obj = repository.findById(id);
		 return obj.orElseThrow(()-> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Categoria obj) {
		findById(obj.getId());
		return obj = repository.save(obj);
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
		repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não pode excluir uma categoria que esteja associada à um ou mais produtos!");
		}
	}
}
