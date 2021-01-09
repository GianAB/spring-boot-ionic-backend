package com.gianprog.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.domain.Estado;
import com.gianprog.cursomc.dto.CidadeDTO;
import com.gianprog.cursomc.dto.EstadoDTO;
import com.gianprog.cursomc.services.CidadeService;
import com.gianprog.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDto = list.stream()
										.map(x -> new EstadoDTO(x))
										.collect(Collectors.toList());
										
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value = "/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findById(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listDto = list.stream()
										.map(x -> new CidadeDTO(x))
										.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
}
