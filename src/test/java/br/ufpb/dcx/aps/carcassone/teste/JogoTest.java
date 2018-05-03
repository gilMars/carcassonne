package br.ufpb.dcx.aps.carcassone.teste;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;
import static br.ufpb.dcx.aps.carcassone.teste.Assertiva.ocorreExcecao;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.ExcecaoJogo;
import br.ufpb.dcx.aps.carcassone.Jogo;
import br.ufpb.dcx.aps.carcassone.Partida;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

/**
 * Casos de teste para o Jogo Carcassonne básico. A partir do objeto jogo,
 * pode-se criar objetos para representar as partidas.
 */
public class JogoTest {

	private Jogo jogo;
	private BolsaDeTiles tiles;

	@Before
	public void novoJogo() {
		tiles = mock(BolsaDeTiles.class);
		jogo = new Jogo();
	}

	/**
	 * Caso de teste 01
	 * 
	 * Verifica se o jogo lança exceções para partidas criadas com dados inválidos.
	 * A regra para criação de partidas é: informar uma bolsa de tiles que compõem a
	 * partida e pelo menos duas cores não repetidas representando os jogadores que
	 * farão parte da partida.
	 */
	@Test
	public void iniciarPartidaInvalida() {
		ocorreExcecaoJogo(() -> jogo.criarPartida(null),
				"A bolsa de tiles deve ser fornecida na criação de uma partida");

		ocorreExcecaoJogo(() -> jogo.criarPartida(tiles),
				"Cada partida deve ter uma sequência de pelo menos dois jogadores");

		ocorreExcecaoJogo(() -> jogo.criarPartida(null, PRETO),
				"A bolsa de tiles deve ser fornecida na criação de uma partida");

		ocorreExcecaoJogo(() -> jogo.criarPartida(tiles, PRETO),
				"Cada partida deve ter uma sequência de pelo menos dois jogadores");

		ocorreExcecaoJogo(() -> jogo.criarPartida(tiles, PRETO, PRETO),
				"Não pode haver repetição de cores na sequência de jogadores");

		ocorreExcecaoJogo(() -> jogo.criarPartida(null, PRETO, AMARELO),
				"A bolsa de tiles deve ser fornecida na criação de uma partida");

		ocorreExcecaoJogo(() -> jogo.criarPartida(tiles, PRETO, AMARELO, PRETO),
				"Não pode haver repetição de cores na sequência de jogadores");
	}

	private void ocorreExcecaoJogo(ExceptionThrower et, String mensagem) {
		ocorreExcecao(et).tipoExcecao(ExcecaoJogo.class).mensagem(mensagem);
	}

	private void mockarTiles(BolsaDeTiles mock, Tile primeiro, Tile... tiles) {
		when(mock.pegar()).thenReturn(primeiro, Arrays.copyOf(tiles, tiles.length + 1));
	}

	private void verificarRelatorioPartida(Partida partida, String status, String sequencia) {
		String relatorio = partida.relatorioPartida();
		Assert.assertEquals("Status: " + status + "\nJogadores: " + sequencia, relatorio);
	}

	private void verificarRelatorioTurno(Partida partida, String jogador, String tile, String status) {
		String relatorio = partida.relatorioTurno();
		Assert.assertEquals("Jogador: " + jogador + "\nTile: " + tile + "\nStatus: " + status, relatorio);
	}

	private void verificarRelatorioTabuleiro(Partida partida, String configuracao) {
		Assert.assertEquals(configuracao, partida.relatorioTabuleiro());
	}

}