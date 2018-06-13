package rest.carcassonne;

import br.ufpb.dcx.aps.carcassone.Estado;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class RelatorioTurno extends Relatorio {

	Tile tile;
	Jogador jogador;
	RelatorioTurno(Jogador jogador, Tile tile, Estado status) {
		super(status);
		this.jogador = jogador;
		this.tile = tile;
		// TODO Auto-generated constructor stub
	}
	
	public Jogador getJogador() {
		return jogador;
	}
	
	public Tile getTile() {
		return tile;
	}

}
