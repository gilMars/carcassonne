package br.ufpb.dcx.aps.carcassone;

import java.util.LinkedList;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

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
		
		Jogador jogador = jogadores[jogadorDaVez % jogadores.length];
		Meeple meeple = new Meeple(lado, jogador.getCor(), tilesPegos.getLast());
		tabuleiro.posicionarMeeple(meeple);
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
