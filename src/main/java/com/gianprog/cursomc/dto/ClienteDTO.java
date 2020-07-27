
package com.gianprog.cursomc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import com.gianprog.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Length(min = 5, max = 30, message = "Este campo deve conter entre 5 e 30 caracteres!")
	private String nome;
	
	@Email(message = "Email inválido!")
	private String email;
	
	private List<String> telefones = new ArrayList<>();
	
	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		while(telefones.size() > 3) {
			telefones.remove(telefones.size() -1);
		}
		
		this.telefones = telefones;
	}

}
