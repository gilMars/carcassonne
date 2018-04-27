package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoTile;

public class TileComVertice extends Tile {
	
	
	TipoLado ladoNoroeste;
	TipoLado ladoNordeste;
	TipoLado ladoSudeste;
	TipoLado ladoSudoeste;
	
	TileComVertice(String id, TipoTile tipoTile, TipoLado ladoNoroeste, TipoLado ladoNordeste,
	TipoLado ladoSudeste, TipoLado ladoSudoeste) {
		super(id,tipoTile);
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

}
