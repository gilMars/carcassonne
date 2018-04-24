package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Partida {

	private BolsaDeTiles tiles;
	private Tile proximoTile;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("  ");
	Jogador[] jogadores;
	Estado estadoTurno = Estado.T_INICIO;
	Estado estadoPartida;
	int jogadorDaVez = 0;

	Partida(BolsaDeTiles tiles, Cor... sequencia) {

		this.tiles = tiles;
		pegarProximoTile();

		jogadores = new Jogador[sequencia.length];
		for (int i = 0; i < sequencia.length; ++i) {
			jogadores[i] = new Jogador(sequencia[i]);
		}
		estadoPartida = Estado.P_ANDAMENTO;
		tabuleiro.adicionarPrimeiroTile(proximoTile);
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
		String relatorio = "Jogador: " + jogador.getCor() + "\nTile: " + proximoTile + "\nStatus: " + estadoTurno;
		return relatorio;
	}

	public Partida girarTile() {
		if (verificarFimDaPartida()) {
			throw new ExcecaoJogo("Não pode girar tiles com a partida finalizada");
		}
		if (tabuleiro.verificarTilePosicionado(proximoTile)) {
			throw new ExcecaoJogo("Não pode girar tile já posicionado");
		}

		proximoTile.girar();
		System.out.println("\nNorte: "+proximoTile.getLadoNorte()+"\nLeste: "+proximoTile.getLadoLeste()+"\nSul: "+proximoTile.getLadoSul()+"\nOeste: "+proximoTile.getLadoOeste());
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		if (!verificarTileVazio()) {
			proximoTile.reset();
		}
	}

	public Partida finalizarTurno() {
		pegarProximoTile();
		jogadorDaVez++;
		estadoTurno = Estado.T_INICIO;
		verificarFimDaPartida();
		return this;
	}

	public Partida posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		estadoTurno = Estado.T_ANDAMENTO;
		return this;
	}

	public Partida posicionarMeepleEstrada(Lado lado) {
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
		return null;
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

	public boolean verificarTileVazio() {
		if (proximoTile == null) {
			return true;
		}
		return false;
	}
	
	public boolean verificarFimDaPartida() {
		if (verificarTileVazio()) {
			estadoPartida = Estado.P_FIM;
			return true;
		}
		return false;
	}
	
}
