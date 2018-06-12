package br.ufpb.dcx.aps.carcassone;

import java.util.LinkedList;
import java.util.List;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class BolsaTileCheia implements BolsaDeTiles {

	LinkedList<Tile> tiles = new LinkedList<Tile>();
	
	public BolsaTileCheia(Tile... tiles) {
		for (int i = 0; i < tiles.length; ++i) {
			this.tiles.add(tiles[i]);
		}
	}
	@Override
	public Tile pegar() {
		// TODO Auto-generated method stub
		return tiles.removeFirst();
	}

}
