package com.gianprog.cursomc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gianprog.cursomc.services.validator.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Este campo não pode estar vazio!")
	@Length(min = 5, max = 30, message = "Este campo deve conter entre 5 e 30 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Este campo não pode estar vazio!")
	@Email(message = "Email inválido!")
	private String email;
	
	private String cpfOuCnpj;
	private Integer tipo;
	
	@NotEmpty(message = "Este campo não pode estar vazio!")
	private String senha;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	@NotEmpty(message = "Precisa ter ao menos um telefone!")
	private List<String> telefones = new ArrayList<>(); 

	private Integer idCidade;

	public ClienteNewDTO() {
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
		
	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		while (telefones.size() > 3) {
			telefones.remove(telefones.size() - 1);
		}
		this.telefones = telefones;
	}

	public Integer getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}

}
