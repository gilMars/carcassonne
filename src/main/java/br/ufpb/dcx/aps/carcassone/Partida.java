package br.ufpb.dcx.aps.carcassone;

import java.util.LinkedList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;

public class Partida {

	private BolsaDeTiles tiles;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	Jogador[] jogadores;
	Estado estadoTurno = Estado.T_INICIO;
	Estado estadoPartida;
	int jogadorDaVez = 0;

	LinkedList<Tile> tilesPegos = new LinkedList<Tile>();

	Partida(BolsaDeTiles tiles, Cor... sequencia) {

		this.tiles = tiles;
		pegarProximoTile();
		jogadores = new Jogador[sequencia.length];
		for (int i = 0; i < sequencia.length; ++i) {
			jogadores[i] = new Jogador(sequencia[i]);
		}
		estadoPartida = Estado.P_ANDAMENTO;
		tabuleiro.adicionarPrimeiroTile(tilesPegos.getFirst());
		pegarProximoTile();
		verificarFimDaPartida();
	}

	public String relatorioPartida() {

		String sequencia = "";
		for (int i = 0; i < jogadores.length - 1; i++) {
			sequencia += jogadores[i].toString() + "; ";
		}
		sequencia += jogadores[jogadores.length - 1];

		String relatorio = "Status: " + estadoPartida + "\nJogadores: " + sequencia;

		return relatorio;
	}

	public String relatorioTurno() {
		if (verificarFimDaPartida()) {
			throw new ExcecaoJogo("Partida finalizada");
		}
		Jogador jogador = jogadores[jogadorDaVez % jogadores.length];
		String relatorio = "Jogador: " + jogador.getCor() + "\nTile: " + tilesPegos.getLast() + "\nStatus: "
				+ estadoTurno;
		return relatorio;
	}

	public Partida girarTile() {
		if (verificarFimDaPartida()) {
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		if (tabuleiro.verificarTilePosicionado(tilesPegos.getLast())) {
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		}

		tilesPegos.getLast().girar();
		return this;
	}

	private void pegarProximoTile() {
		Tile tile = tiles.pegar();

		// proximoTile = tiles.pegar();
		if (!verificarTileVazio(tile)) {
			tile.reset();
			tile.setMeeple(null);
		}

		tilesPegos.add(tile);
	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		jogadorDaVez++;
		estadoTurno = Estado.T_INICIO;
		verificarFimDaPartida();
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, tilesPegos.getLast());
		estadoTurno = Estado.T_ANDAMENTO;
		return this;
	}

	public Partida posicionarMeepleEstrada(Lado lado) {
		Tile tile = tilesPegos.getLast();
		if (tilesPegos.size() == 2 && tile == null) {
			throw new ExcecaoJogo("Impossível posicionar meeple na peça inicial");
		}
		boolean error = false;
		TipoLado ladoTile = null;
		switch(lado) {
		case NORTE:
			ladoTile = tile.getLadoNorte();
			if (ladoTile != TipoLadoCarcassonne.ESTRADA) {
				error = true;
			}
			break;
		case LESTE:
			ladoTile = tile.getLadoLeste();
			if (ladoTile != TipoLadoCarcassonne.ESTRADA) {
				error = true;
			}
			break;
		case SUL:
			ladoTile = tile.getLadoSul();
			if (ladoTile != TipoLadoCarcassonne.ESTRADA) {
				error = true;
			}
			break;
		case OESTE:
			ladoTile = tile.getLadoOeste();
			if (ladoTile != TipoLadoCarcassonne.ESTRADA) {
				error = true;
			}
		}
		if (error) {
			String l = lado.toString().charAt(0)+lado.toString().substring(1).toLowerCase();
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado "+l+" do tile "+tile.getId()+" é "+ladoTile.getAbreviacao());
		}
		if (tile.getMeeple() == null) {
			Jogador jogador = jogadores[jogadorDaVez % jogadores.length];
			Meeple meeple = new Meeple(Lado.SUL, jogador.getCor(),
					tilesPegos.getLast());
			tile.setMeeple(meeple);
			System.out.println(meeple);
		}
		return this;
	}

	public Partida posicionarMeepleCampo(Vertice vertice) {
		return this;
	}

	public Partida posicionarMeepleCidade(Lado lado) {
		return this;
	}

	public Partida posicionarMeepleMosteiro() {
		return this;
	}

	public String getEstradas() {
		return tabuleiro.verificarEstrada();
	}

	public String getCampos() {
		return null;
	}

	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}

	public String relatorioTabuleiro() {
		return tabuleiro.toString();
	}

	public boolean verificarTileVazio(Tile tile) {
		if (tile == null) {
			return true;
		}
		return false;
	}

	public boolean verificarFimDaPartida() {
		if (verificarTileVazio(tilesPegos.getLast())) {
			estadoPartida = Estado.P_FIM;
			return true;
		}
		return false;
	}

}
