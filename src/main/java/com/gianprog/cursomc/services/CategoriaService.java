package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Categoria;
import com.gianprog.cursomc.dto.CategoriaDTO;
import com.gianprog.cursomc.repositories.CategoriaRepository;
import com.gianprog.cursomc.services.exception.DataIntegrityException;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	private Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Categoria findById(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(CategoriaDTO objDto) {
		Categoria obj = fromDTO(objDto);
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(CategoriaDTO objDto) {
		Categoria obj = fromDTO(objDto);
		Categoria newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

		public void deleteById(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não pode excluir uma categoria que esteja associada à um ou mais produtos!");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}


}
