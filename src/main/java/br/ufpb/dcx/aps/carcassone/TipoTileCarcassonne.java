package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoTile;

public class TipoTileCarcassonne extends TipoTile{

	private Origem origem;

	private boolean escudo;
	private boolean mosteiro;
	private boolean cidadeContinua;

	public TipoTileCarcassonne() {
	}

	public TipoTileCarcassonne(Origem origem, TipoLado ladoNorte, TipoLado ladoLeste, TipoLado ladoSul, TipoLado ladoOeste,
			boolean escudo, boolean mosteiro, boolean cidadeContinua) {
		super(ladoNorte, ladoLeste, ladoSul, ladoOeste);
		this.origem = origem;
		this.escudo = escudo;
		this.mosteiro = mosteiro;
		this.cidadeContinua = cidadeContinua;
	}
	
	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public boolean isEscudo() {
		return escudo;
	}

	public void setEscudo(boolean escudo) {
		this.escudo = escudo;
	}

	public boolean isMosteiro() {
		return mosteiro;
	}

	public void setMosteiro(boolean mosteiro) {
		this.mosteiro = mosteiro;
	}

	public boolean isCidadeContinua() {
		return cidadeContinua;
	}

	public void setCidadeContinua(boolean cidadeContinua) {
		this.cidadeContinua = cidadeContinua;
	}

}
