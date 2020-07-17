package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.dto.ClienteDTO;
import com.gianprog.cursomc.repositories.ClienteRepository;
import com.gianprog.cursomc.services.exception.DataIntegrityException;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	private Cliente fromDto(ClienteDTO objDto) {
		Cliente obj = new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(),
				objDto.getCpfOuCnpj(), objDto.getTipo());
		return obj;
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: "+ id + ", Classe: " + Cliente.class.getName()));
	}

	public Cliente insert(ClienteDTO objDto) {
		Cliente obj = fromDto(objDto);
		obj.setId(null);
		return repository.save(obj);
	}

	public Cliente update(ClienteDTO objDto) {
		Cliente obj = fromDto(objDto);
		Cliente newObj = findById(obj.getId());
		newObj = updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void deleteById(Integer id) {
		Cliente obj = findById(id);
		try {
			repository.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não pode excluir um cliente que possui associações!");
		}
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	private Cliente updateData(Cliente newObj, Cliente obj) {
		newObj.setNome((obj.getNome() != null)?obj.getNome():newObj.getNome());
		newObj.setEmail((obj.getEmail() != null)?obj.getEmail():newObj.getEmail());
		newObj.setCpfOuCnpj((obj.getCpfOuCnpj() != null)?obj.getCpfOuCnpj():newObj.getCpfOuCnpj());
		newObj.setTipo((obj.getTipo() != null)?obj.getTipo():newObj.getTipo());
		return newObj;
	}
}
