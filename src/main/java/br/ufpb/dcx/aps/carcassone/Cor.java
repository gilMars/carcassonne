package br.ufpb.dcx.aps.carcassone;

import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;

@ApiModel
public enum Cor {
	PRETO("PRETO"), VERMELHO("VERMELHO"), AZUL("AZUL"), AMARELO("AMARELO"), VERDE("VERDE");

	private final String cor;
	
	@JsonCreator
	private Cor(String cor) {
		this.cor = cor;
	}
	
	String getCor() {
		return cor;
	}
	
	@Override
	public String toString() {
		return cor;
	}
}
