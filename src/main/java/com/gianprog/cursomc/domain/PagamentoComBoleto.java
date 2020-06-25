package com.gianprog.cursomc.domain;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.gianprog.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;


//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT-3")
//	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	private LocalDate dataVencimento;



//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT-3")
//	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
	private LocalDate dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, LocalDate dataVencimento,
			LocalDate dataPagamento) {
		super(id, estadoPagamento);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
