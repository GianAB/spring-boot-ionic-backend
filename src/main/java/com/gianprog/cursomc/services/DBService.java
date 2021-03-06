package com.gianprog.cursomc.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.Categoria;
import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.domain.Cliente;
import com.gianprog.cursomc.domain.Endereco;
import com.gianprog.cursomc.domain.Estado;
import com.gianprog.cursomc.domain.ItemPedido;
import com.gianprog.cursomc.domain.Pagamento;
import com.gianprog.cursomc.domain.PagamentoComBoleto;
import com.gianprog.cursomc.domain.PagamentoComCartao;
import com.gianprog.cursomc.domain.Pedido;
import com.gianprog.cursomc.domain.Produto;
import com.gianprog.cursomc.domain.enums.EstadoPagamento;
import com.gianprog.cursomc.domain.enums.Perfil;
import com.gianprog.cursomc.domain.enums.TipoCliente;
import com.gianprog.cursomc.repositories.CategoriaRepository;
import com.gianprog.cursomc.repositories.CidadeRepository;
import com.gianprog.cursomc.repositories.ClienteRepository;
import com.gianprog.cursomc.repositories.EnderecoRepository;
import com.gianprog.cursomc.repositories.EstadoRepository;
import com.gianprog.cursomc.repositories.ItemPedidoRepository;
import com.gianprog.cursomc.repositories.PagamentoRepository;
import com.gianprog.cursomc.repositories.PedidoRepository;
import com.gianprog.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

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

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void instanciateTestDatabase() {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");

		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.0);
		Produto p5 = new Produto(null, "Toalha", 50.0);
		Produto p6 = new Produto(null, "Colcha", 200.0);
		Produto p7 = new Produto(null, "TV true color", 1200.0);
		Produto p8 = new Produto(null, "Roçadeira", 800.0);
		Produto p9 = new Produto(null, "Abajour", 100.0);
		Produto p10 = new Produto(null, "Pente", 180.0);
		Produto p11 = new Produto(null, "Shampoo", 90.0);
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().add(cid1);
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

		Cliente cli1 = new Cliente(null, "gian", "gian.antoniobatista@gmail.com", "03100066697", TipoCliente.PESSOA_FISICA, pe.encode("1233"));
		cli1.addPerfil(Perfil.ADMIN);
		Cliente cli2 = new Cliente(null, "jade", "jade@gmail.com", "03166666697", TipoCliente.PESSOA_FISICA, pe.encode("nãosei"));
		Cliente cli3 = new Cliente(null, "maique", "maique@gmail.com", "69800066697", TipoCliente.PESSOA_JURIDICA, pe.encode("minhasenhafácil"));		
		
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

		Pedido ped1 = new Pedido(null, Instant.parse("2017-09-30T10:32:00Z"), end2, cli1);
		cli1.getPedidos().add(ped1);
		Pedido ped2 = new Pedido(null, Instant.parse("2017-10-10T19:35:00Z"), end1, cli2);
		cli2.getPedidos().add(ped2);
		Pedido ped3 = new Pedido(null, Instant.parse("2018-07-10T10:00:00Z"), end4, cli3);
		cli3.getPedidos().add(ped3);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, 6);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, LocalDate.parse("2020-07-12"), null);
		ped2.setPagamento(pag2);
		Pagamento pag3 = new PagamentoComBoleto(null, EstadoPagamento.CANCELADO, LocalDate.parse("2020-08-01"), null);
		ped3.setPagamento(pag3);

		pag1.setPedido(ped1);
		pag2.setPedido(ped2);
		pag3.setPedido(ped3);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2, pag3));

		ItemPedido ip1 = new ItemPedido(p1, ped1, 00.0, 1);
		ItemPedido ip2 = new ItemPedido(p3, ped1, 00.0, 2);
		ItemPedido ip3 = new ItemPedido(p2, ped2, 00.0, 2);
		ItemPedido ip4 = new ItemPedido(p1, ped3, 00.0, 2);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		ped3.getItens().add(ip4);

		p1.getItens().addAll(Arrays.asList(ip1, ip4));
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));

	}
}
