package br.ufpb.dcx.aps.carcassone.tabuleiro;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.ufpb.dcx.aps.carcassone.TipoLadoCarcassonne;

@JsonDeserialize(as = TipoLadoCarcassonne.class)
public interface TipoLado {
	
	String getAbreviacao();

}
