package com.gianprog.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.domain.Endereco;
import com.gianprog.cursomc.domain.enums.Perfil;
import com.gianprog.cursomc.domain.enums.TipoCliente;
import com.gianprog.cursomc.dto.ClienteDTO;
import com.gianprog.cursomc.dto.ClienteNewDTO;
import com.gianprog.cursomc.repositories.ClienteRepository;
import com.gianprog.cursomc.repositories.EnderecoRepository;
import com.gianprog.cursomc.security.UserSpringSecurity;
import com.gianprog.cursomc.services.exception.AuthorizationException;
import com.gianprog.cursomc.services.exception.DataIntegrityException;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		
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
	
	public Cliente findByEmail(String email) {
		UserSpringSecurity user = UserServices.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado!");
		}
		Cliente obj = repository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
	public Cliente findById(Integer id) {
		UserSpringSecurity user = UserServices.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Classe: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(ClienteNewDTO objDto) {
		Cliente obj = fromDto(objDto);
		obj.setId(null);
		
		repository.save(obj);
		
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}

	public Cliente update(Integer id, ClienteDTO objDto) {
		objDto.setId(id);
		
		Cliente findObj = findById(id);
		
		return repository.save(updateData(objDto, findObj));
	}

	public void deleteById(Integer id) {
		Cliente obj = findById(id);
		try {
			repository.delete(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos associados!");
		}

	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	private Cliente updateData(ClienteDTO objDto, Cliente findObj) {
		findObj.setNome((objDto.getNome() != null)?objDto.getNome():findObj.getNome());
		findObj.setEmail((objDto.getEmail() != null)?objDto.getEmail():findObj.getEmail());
		if (!objDto.getTelefones().isEmpty()) {
			findObj.getTelefones().removeAll(findObj.getTelefones());
			findObj.getTelefones().addAll(objDto.getTelefones());
		}
		
		return findObj;
	}
}
