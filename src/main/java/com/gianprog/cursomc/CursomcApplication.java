package com.gianprog.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gianprog.cursomc.domain.Categoria;
import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.domain.Endereco;
import com.gianprog.cursomc.domain.Estado;
import com.gianprog.cursomc.domain.Produto;
import com.gianprog.cursomc.domain.enums.TipoCliente;
import com.gianprog.cursomc.repositories.CategoriaRepository;
import com.gianprog.cursomc.repositories.CidadeRepository;
import com.gianprog.cursomc.repositories.ClienteRepository;
import com.gianprog.cursomc.repositories.EnderecoRepository;
import com.gianprog.cursomc.repositories.EstadoRepository;
import com.gianprog.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().add(cat1);
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(cid1);
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "gian", "gian@gmail.com", "03100066697", TipoCliente.PESSOA_FISICA);
		Cliente cli2 = new Cliente(null, "jade", "jade@gmail.com", "03166666697", TipoCliente.PESSOA_FISICA);
		Cliente cli3 = new Cliente(null, "maique", "maique@gmail.com", "69800066697", TipoCliente.PESSOA_JURIDICA);
		
		Endereco end1 = new Endereco(null, "Rua q", "100", "casa 2", "Centro", "39855-0000", cli2, cid1);
		Endereco end2 = new Endereco(null, "Rua zero", "2", null, "Centro", "39855-0000", cli1, cid1);
		Endereco end3 = new Endereco(null, "Av. rio", "12", null, "Centro", "39855-0000", cli1, cid2);
		Endereco end4 = new Endereco(null, "Rua asd", "9", "Ap. 2", "Centro", "01512-000", cli3, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end2, end3));
		cli2.getEnderecos().addAll(Arrays.asList(end1));
		cli3.getEnderecos().addAll(Arrays.asList(end4));
		
		cli1.getTelefones().add("(11) 9 9743-5750");
		cli2.getTelefones().add("(33) 9 8857-1665");
		cli3.getTelefones().add("(33) 9 8852-6233");
		cli1.getTelefones().add("(33) 9 8833-6545");
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		enderecoRepository.saveAll(Arrays.asList(end1, end2, end3, end4));
		
		
	}

}
