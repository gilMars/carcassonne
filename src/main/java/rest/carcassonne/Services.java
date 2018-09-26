package rest.carcassonne;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.BolsaTileConcreta;
import br.ufpb.dcx.aps.carcassone.Cor;
import br.ufpb.dcx.aps.carcassone.Estado;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.Partida;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Services {
	private BolsaDeTiles bolsaDeTile = new BolsaTileConcreta(t30, t01, t02, t03, t04, t05, t06, t07, t08, t09, t10, t11,
			t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t31, t32, t33,
			t34, t35, t36, t37, t38, t39, t40, t41, t42, t43, t44, t45, t46, t47, t48, t49, t50, t51, t52, t53, t54,
			t55, t56, t57, t58, t59, t60, t61, t62, t63, t64, t65, t66, t67, t68, t69, t70, t71, t72);

	private Partida partida = new Partida();

	public ResponseEntity<?> criarPartida(Cor... sequencia) {
		if (sequencia.length < 2) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		for (int i = 0; i < sequencia.length - 1; ++i) {
			for (int j = i + 1; j < sequencia.length; ++j) {
				if (sequencia[i] == sequencia[j]) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}

		if (partida != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		partida = new Partida(bolsaDeTile, sequencia);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public void finalizarPartida() {

	}

	public ResponseEntity<RelatorioPartida> relatorioPartida() {

		if (partida == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RelatorioPartida>(
				new RelatorioPartida(partida.getEstadoPartida(), partida.getJogadores()), HttpStatus.OK);
	}

	public ResponseEntity<RelatorioTurno> relatorioTurno() {

		if (partida == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<RelatorioTurno>(
				new RelatorioTurno(partida.jogadorDaVez(), partida.getTile(), partida.getEstadoTurno()),
				HttpStatus.OK);
	}

	public ResponseEntity<Jogador[]> resgatarJogadores() {
		if (partida == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Jogador[]>(partida.getJogadores(), HttpStatus.OK);
	}

	public ResponseEntity<Jogador> resgatarJogador(Cor cor) {
		if (partida != null) {
			for (Jogador j : partida.getJogadores()) {
				if (j.getCor().equals(cor)) {
					return new ResponseEntity<Jogador>(j, HttpStatus.OK);
				}
			}
		}

		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Tile> pegarTile() {

		if ((partida == null) || (partida.getEstadoTurno() == Estado.T_ANDAMENTO)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Tile>(partida.getTile(), HttpStatus.OK);
	}

	public ResponseEntity<Tile> girarTile() {
		if (partida == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		partida.girarTile();
		return new ResponseEntity<>(partida.getTile(), HttpStatus.OK);
	}

	public ResponseEntity<?> posicionarTile(Tile tileRef, Lado ladoRef) {
		partida.posicionarTile(tileRef, ladoRef);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public ResponseEntity<TabuleiroFlexivel> getTabuleiro() {
		if (partida == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<TabuleiroFlexivel>(partida.getTabuleiro(), HttpStatus.OK);
	}
}
