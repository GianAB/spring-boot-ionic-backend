package com.gianprog.cursomc.services;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gianprog.cursomc.domain.ItemPedido;
import com.gianprog.cursomc.domain.PagamentoComBoleto;
import com.gianprog.cursomc.domain.Pedido;
import com.gianprog.cursomc.domain.enums.EstadoPagamento;
import com.gianprog.cursomc.repositories.ItemPedidoRepository;
import com.gianprog.cursomc.repositories.PagamentoRepository;
import com.gianprog.cursomc.repositories.PedidoRepository;
import com.gianprog.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido findById(Integer id) throws ObjectNotFoundException{
		Optional<Pedido> obj = repository.findById(id);
		 return obj.orElseThrow(()-> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.setInstante(Instant.now());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			LocalDate instante = LocalDate.parse(obj.getInstante().toString().substring(0,10));
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, instante);
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPedido(obj);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		
		return obj;
	}
}
