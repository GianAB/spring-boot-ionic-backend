package com.gianprog.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gianprog.cursomc.domain.Cidade;
import com.gianprog.cursomc.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Cidade cidade = service.findById(id);
		return ResponseEntity.ok().body(cidade);
	}
	
	@GetMapping
	public List<Cidade> findAll(){
		return service.findAll();
	}
}
