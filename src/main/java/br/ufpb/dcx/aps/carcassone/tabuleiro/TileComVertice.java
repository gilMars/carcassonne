package br.ufpb.dcx.aps.carcassone.tabuleiro;

import br.ufpb.dcx.aps.carcassone.Lado;

public class TileComVertice extends Tile {
	
	TipoLado ladoNoroeste;
	TipoLado ladoNordeste;
	TipoLado ladoSudeste;
	TipoLado ladoSudoeste;

	public TileComVertice(Tile tile, TipoLado ladoNoroeste, TipoLado ladoNordeste,
			TipoLado ladoSudeste, TipoLado ladoSudoeste) {
		super(tile.getId(),tile.getTipoTile());
		this.ladoNoroeste = ladoNoroeste;
		this.ladoNordeste = ladoNordeste;
		this.ladoSudeste = ladoSudeste;
		this.ladoSudoeste = ladoSudoeste;
	}
	
	public void girar() {
		super.girar();
		TipoLado temp = ladoNoroeste;
		ladoNoroeste = ladoSudoeste;
		ladoSudoeste = ladoSudeste;
		ladoSudeste = ladoNordeste;
		ladoNordeste = temp;
	}
	
	public void reset() {
		while (getOrientacao() != Lado.NORTE) {
			girar();
		}
	}
	
	public TipoLado getLadoNoroeste() {
		return ladoNoroeste;
	}
	
	public void setLadoNoroeste(TipoLado Noroeste) {
		this.ladoNoroeste = Noroeste;
	}
	
	public TipoLado getLadoNordeste() {
		return ladoNordeste;
	}
	
	public void setLadoNordeste(TipoLado Nordeste) {
		this.ladoNordeste = Nordeste;
	}
	
	public TipoLado getLadoSudeste() {
		return ladoSudeste;
	}
	
	public void setLadoSudeste(TipoLado ladoSudeste) {
		this.ladoSudeste = ladoSudeste;
	}
	
	public TipoLado getLadoSudoeste() {
		return ladoSudoeste;
	}
	
	public void setLadoSudoeste(TipoLado ladoSudoeste) {
		this.ladoSudoeste = ladoSudoeste;
	}

}
