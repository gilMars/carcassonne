package rest.carcassonne;

import br.ufpb.dcx.aps.carcassone.Estado;
import br.ufpb.dcx.aps.carcassone.Jogador;

public class RelatorioPartida extends Relatorio {
	
	Jogador[] jogadores = null;
	
	public RelatorioPartida(Estado status, Jogador...jogadores) {
		super(status);
		this.jogadores = jogadores;
	}
	
	public Jogador[] getJogadores() {
		return jogadores;
	}

}
