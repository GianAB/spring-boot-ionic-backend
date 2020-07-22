package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.domain.Endereco;
import com.gianprog.cursomc.domain.enums.TipoCliente;
import com.gianprog.cursomc.dto.ClienteDTO;
import com.gianprog.cursomc.dto.ClienteNewDTO;
import com.gianprog.cursomc.repositories.ClienteRepository;
import com.gianprog.cursomc.repositories.EnderecoRepository;
import com.gianprog.cursomc.services.exception.DataIntegrityException;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	

	private Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}

	private Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		
		Cidade cid = new Cidade(objDto.getIdCidade(), null, null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().addAll(objDto.getTelefones());
		
		return cli;
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(ClienteNewDTO objDto) {
		Cliente obj = fromDto(objDto);
		obj.setId(null);
		
		enderecoRepository.saveAll(obj.getEnderecos());
		repository.save(obj);
		
		return obj;
	}

	public Cliente update(ClienteDTO objDto) {
		Cliente obj = fromDto(objDto);
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	public void deleteById(Integer id) {
		Cliente obj = findById(id);
		try {
			repository.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não pode excluir um cliente que possui associações!");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		/*
		 * Nesta situação, estamos supondo que não é possível mudar o CPF/CNPJ e nem o
		 * tipo de pessoa de um cliente porém, ele pode mudar seu nome e email
		 */

		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
