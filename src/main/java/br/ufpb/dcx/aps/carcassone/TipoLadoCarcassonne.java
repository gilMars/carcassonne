package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;

public enum TipoLadoCarcassonne implements TipoLado{

	ESTRADA("Estrada"), CAMPO("Campo"), CIDADE("Cidade");
	
	private final String abreviacao;

	TipoLadoCarcassonne(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
    public String getAbreviacao() { 
    		return abreviacao; 
    	}
}
