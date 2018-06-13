package br.ufpb.dcx.aps.carcassone;

import java.util.Collections;
import java.util.LinkedList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class BolsaTileConcreta implements BolsaDeTiles {

	LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public BolsaTileConcreta(Tile primeiro, Tile... tiles) {
		for (int i = 0; i < tiles.length; ++i) {
			this.tiles.add(tiles[i]);
		}
		Collections.shuffle(this.tiles);
		this.tiles.addFirst(primeiro);
	}
	@Override
	public Tile pegar() {
		if (tiles.isEmpty()) {
			return null;
		}
		// TODO Auto-generated method stub
		return tiles.removeFirst();
	}

}
