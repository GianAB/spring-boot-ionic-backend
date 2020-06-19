package com.gianprog.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gianprog.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
