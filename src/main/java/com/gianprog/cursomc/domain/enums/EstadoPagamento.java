package com.gianprog.cursomc.domain.enums;

public enum EstadoPagamento {
	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer cod;
	private String valor;

	private EstadoPagamento(Integer cod, String valor) {
		this.cod = cod;
		this.valor = valor;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Código inválido! Cod: " + cod);
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public String getValor() {
		return valor;
	}
}
