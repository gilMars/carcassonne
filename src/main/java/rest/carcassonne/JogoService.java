package rest.carcassonne;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.AZUL;
import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.VERDE;
import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.t29;
import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.t30;

import java.util.Arrays;

import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.BolsaTileCheia;
import br.ufpb.dcx.aps.carcassone.Jogo;
import br.ufpb.dcx.aps.carcassone.Partida;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;


public class JogoService {
	
	private Partida partida;
	
	public JogoService() {
		partida = new Partida(new BolsaTileCheia(t30, t29), AZUL, VERDE);
	}
	
	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

}
