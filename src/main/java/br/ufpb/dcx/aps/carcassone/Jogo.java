package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import java.util.LinkedList;

public class Jogo {

	private Tile tileAtual;
	static int indice = 0;
	private BolsaDeTiles tiles;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("");
	private boolean iniciado = false;
	private String status = "Início";
	private Cor[] jogadores;
	private Cor proximoJogador;
	LinkedList<Tile> tilesPego = new LinkedList<Tile>();

	public Jogo(BolsaDeTiles tiles) {
		this.tiles = tiles;
	}

	public Jogo iniciarPartida(Cor... sequencia) {
		if (iniciado == true) {
			throw new ExcecaoJogo("Não pode iniciar uma partida enquanto a partida anterior não for finalizada");
		}
		if (sequencia.length < 2) {
			throw new ExcecaoJogo("Cada partida deve ter uma sequência de pelo menos dois jogadores");
		}

		for (int i = 0; i < sequencia.length - 1; ++i) {
			for (int j = i + 1; j < sequencia.length; ++j) {
				if (sequencia[i] == sequencia[j]) {
					throw new ExcecaoJogo("Não pode haver repetição de cores na sequência de jogadores");
				}
			}
		}

		jogadores = sequencia;
		pegarProximoTile();
		tileAtual = tilesPego.getLast();
		iniciado = true;
		indice = 0;
		proximoJogador = jogadores[indice % jogadores.length];
		
		return this;
	}

	public Jogo iniciarPartida() {
		throw new ExcecaoJogo("Cada partida deve ter uma sequência de pelo menos dois jogadores");
	}

	public String relatorioPartida() {
		if (iniciado == false) {
			throw new ExcecaoJogo("Partida não iniciada");
		}
		String sequencia = "";
		for (int i = 0; i < jogadores.length - 1; i++) {
			sequencia += jogadores[i].toString() + ", ";
		}
		sequencia += jogadores[jogadores.length - 1];

		String relatorio = "Status: " + status + "\nJogadores: " + sequencia + "\nTabuleiro: " + tabuleiro
				+ "\nJogador da rodada: " + proximoJogador + "\nPróximo tile: " + tilesPego.getLast();

		return relatorio;
	}

	public Jogo girarTile() {
		if (tileAtual != null) {
			tileAtual.girar();
		}
		return this;
	}

	public Jogo posicionarInicial() {
		if (iniciado == false) {
			throw new ExcecaoJogo("O tile inicial não pode ser posicionado antes de iniciar a partida");
		}
		tabuleiro.adicionarPrimeiroTile(tileAtual);
		pegarProximoTile();
		status = "Tile";
		
		return this;
	}

	private void pegarProximoTile() {
		Tile tile = tiles.pegar();
		if (tile != null) {
			tile.reset();
		}
		tilesPego.add(tile);
	}

	public Jogo finalizarRodada() {
		tileAtual = tilesPego.getLast();
		if (tileAtual == null) {
			status = "Fim";
			proximoJogador = null;
		} else {
			status = "Início";
			indice++;
			proximoJogador = jogadores[indice % jogadores.length];
		}
		return this;
	}

	public Jogo posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		status = "Tile";
		tileAtual = tilesPego.getLast();
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, tileAtual);
		pegarProximoTile();

		return this;
	}

	public Jogo posicionarMeepleEstrada(Lado lado) {
		return this;
	}

	public Jogo posicionarMeepleCampo(Vertice vertice) {
		return this;
	}

	public Jogo posicionarMeepleCidade(Lado lado) {
		return this;
	}

	public Jogo posicionarMeepleMosteiro() {
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
}
