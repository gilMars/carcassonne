package br.ufpb.dcx.aps.carcassone.teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.ExcecaoJogo;
import br.ufpb.dcx.aps.carcassone.Jogo;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

import static org.mockito.Mockito.*;
import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;
import static br.ufpb.dcx.aps.carcassone.teste.Assertiva.*;

public class JogoTest {

	private Jogo jogo;
	private BolsaDeTiles tiles;

	@Before
	public void novoJogo() {
		tiles = mock(BolsaDeTiles.class);
		jogo = new Jogo(tiles);
	}

	@Test //#01
	public void iniciarPartidaInvalida() {
		ocorreExcecaoJogo(() -> jogo.iniciarPartida(), "Cada partida deve ter uma sequência de pelo menos dois jogadores");

		ocorreExcecaoJogo(() -> jogo.iniciarPartida(PRETO),
				"Cada partida deve ter uma sequência de pelo menos dois jogadores");

		ocorreExcecaoJogo(() -> jogo.iniciarPartida(PRETO, PRETO),
				"Não pode haver repetição de cores na sequência de jogadores");

		ocorreExcecaoJogo(() -> jogo.iniciarPartida(PRETO, AMARELO, PRETO),
				"Não pode haver repetição de cores na sequência de jogadores");

		jogo.iniciarPartida(AZUL, VERDE);
		ocorreExcecaoJogo(() -> jogo.iniciarPartida(AMARELO, VERMELHO),
				"Não pode iniciar uma partida enquanto a partida anterior não for finalizada");
	}

	@Test //#02
	public void relatorioPartidaErro() {
		ocorreExcecaoJogo(() -> jogo.relatorioPartida(), "Partida não iniciada");
	}

	@Test //#03
	public void iniciarPartidaValida() {
		mockarTiles(tiles, t45);
		jogo.iniciarPartida(AZUL, VERDE);
		verificarRelatorioPartida("Início", "AZUL, VERDE", "", "AZUL", "45N");
	}

	@Test //#04
	public void girarTile() {
		mockarTiles(tiles, t45);
		jogo.iniciarPartida(VERDE, PRETO, AMARELO);
		girar(1);
		verificarRelatorioPartida("Início", "VERDE, PRETO, AMARELO", "", "VERDE", "45L");

		girar(2);
		verificarRelatorioPartida("Início", "VERDE, PRETO, AMARELO", "", "VERDE", "45O");

		girar(1);
		verificarRelatorioPartida("Início", "VERDE, PRETO, AMARELO", "", "VERDE", "45N");
	}

	@Test //#05
	public void colocarTileUnico() {
		mockarTiles(tiles, t45);
		jogo.iniciarPartida(VERDE, PRETO, AMARELO, VERMELHO, AZUL);
		rodadaInicial(2, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "VERDE, PRETO, AMARELO, VERMELHO, AZUL", "45S", "VERDE", null);
	}

	@Test //#06
	public void posicionarInicialInvalida() {
		mockarTiles(tiles, t45);
		ocorreExcecaoJogo(() -> jogo.posicionarInicial(),
				"O tile inicial não pode ser posicionado antes de iniciar a partida");
	}

	@Test //#07
	public void posicionarInicialInvalida2() {
		mockarTiles(tiles, t45, t19);
		ocorreExcecaoJogo(() -> jogo.posicionarInicial(),
				"O tile inicial não pode ser posicionado antes de iniciar a partida");
	}

	@Test //#08
	public void mudancaPosicaoInicial() {
		mockarTiles(tiles, t45);
		jogo.iniciarPartida(VERMELHO, AZUL);
		rodadaInicial(0, 1, NAO_FINALIZA);

		jogo.posicionarInicial();
		verificarRelatorioPartida("Tile", "VERMELHO, AZUL", "45L", "AZUL", null);
	}

	@Test //#09
	public void mudancaPosicaoInicial2() {
		mockarTiles(tiles, t45, t19);
		jogo.iniciarPartida(VERMELHO, AZUL);
		rodadaInicial(0, 3, NAO_FINALIZA);

		jogo.posicionarInicial();
		verificarRelatorioPartida("Tile", "VERMELHO, AZUL", "45O", "AZUL", null);
	}

	@Test //#10
	public void finalizarRodada() {
		mockarTiles(tiles, t45);
		jogo.iniciarPartida(VERDE, PRETO, AMARELO, VERMELHO, AZUL);
		rodadaInicialSemGirar();
		verificarRelatorioPartida("Fim", "VERDE, PRETO, AMARELO, VERMELHO, AZUL", "45N", null, null);
	}

	@Test //#11
	public void finalizarRodada2() {
		mockarTiles(tiles, t45, t19);
		jogo.iniciarPartida(VERDE, PRETO);
		rodadaInicialSemGirar();
		verificarRelatorioPartida("Início", "VERDE, PRETO", "45N", "PRETO", "19N");

		girar(3);
		verificarRelatorioPartida("Início", "VERDE, PRETO", "45N", "PRETO", "19O");
	}

	@Test //#12
	public void posicionarSegundoTileLeste() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		rodada(2, t45, LESTE, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "45N19S", "VERMELHO", null);
	}

	@Test //#13
	public void posicionarSegundoTileSul() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		rodada(1, t45, SUL, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "45N\n19L", "VERMELHO", null);
	}

	@Test //#14
	public void posicionarSegundoTileOeste() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		rodada(0, t45, OESTE, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "19N45N", "VERMELHO", null);
	}

	@Test //#15
	public void posicionarSegundoTileNorte() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		rodada(0, t45, NORTE, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "19N\n45N", "VERMELHO", null);
	}

	@Test //#16
	public void posicionarGirarPosicionarSegundoTile() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		rodada(0, t45, NORTE, 0, NAO_FINALIZA);
		rodada(1, t45, LESTE, 0, NAO_FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "45N19L", "VERMELHO", null);
	}

	@Test //#17
	public void erroPosicionarSegundoTile() {
		doisTilesAmareloVermelhoRodada1SemGirar();

		ocorreExcecaoJogo(() -> rodada(3, t45, LESTE, 0, NAO_FINALIZA),
				"O lado Leste do tile 45 (Campo) é incompatível com o lado Oeste do tile 19 (Cidade)");
		ocorreExcecaoJogo(() -> rodada(2, t45, OESTE, 0, NAO_FINALIZA),
				"O lado Oeste do tile 45 (Campo) é incompatível com o lado Leste do tile 19 (Cidade)");
		ocorreExcecaoJogo(() -> rodada(3, t45, SUL, 0, NAO_FINALIZA),
				"O lado Sul do tile 45 (Campo) é incompatível com o lado Norte do tile 19 (Cidade)");
		ocorreExcecaoJogo(() -> rodada(2, t45, NORTE, 0, NAO_FINALIZA),
				"O lado Norte do tile 45 (Campo) é incompatível com o lado Sul do tile 19 (Cidade)");
	}
	
	@Test //#18
	public void finalizarSegundaRodadaComDoisTiles() {
		doisTilesAmareloVermelhoRodada1SemGirar();
		
		rodada(2, t45, LESTE, 0, FINALIZA);
		verificarRelatorioPartida("Fim", "AMARELO, VERMELHO", "45N19S", null, null);
	}

	@Test //#19
	public void finalizarSegundaRodadaComTresTiles() {
		mockarTiles(tiles, t45, t19, t46);
		jogo.iniciarPartida(AMARELO, VERMELHO);
		rodadaInicialSemGirar();
		
		rodada(2, t45, LESTE, 0, FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "45N19S", "AMARELO", "46N");
	}
	
	@Test //#20
	public void posicionarMeepleAntesDePorTile() {
		mockarTiles(tiles, t08, t64);
		jogo.iniciarPartida(AMARELO, VERMELHO);	
		ocorreExcecaoJogo(() -> jogo.posicionarMeepleEstrada(NORTE),
				"Impossível posicionar meeple. Tile inválido ou inexistente");
	}
	
	@Test //#21
	public void posicionarSegundaRuaAberta() {
		mockarTiles(tiles, t08, t64,t09); //ccrc rfrf ccrc
		jogo.iniciarPartida(AMARELO, VERMELHO);		
		rodadaInicialSemGirar();
		
		rodada(0, t08, SUL, 0, FINALIZA);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "08N\n64N", "AMARELO", "09N");
		verificarEstradas("08N(X,S)64N(N,S)");
	}
	
	@Test //#22
	public void posicionarTerceiraRuaFechando() {
		mockarTiles(tiles, t08, t64,t09); //ccrc rfrf ccrc
		jogo.iniciarPartida(AMARELO, VERMELHO);		
		rodadaInicialSemGirar();
		
		rodada(0, t08, SUL, 0, FINALIZA);
		rodada(2, t64, SUL, 0, FINALIZA);
		verificarRelatorioPartida("Fim", "AMARELO, VERMELHO", "08N\n64N\n09S", "VERMELHO", null);
		verificarEstradas("08N(X,S)64N(N,S)09S(N,X)");
	}
	
	@Test //#23
	public void posicionarSegundaRuaAbertaComMeeple() {
		mockarTiles(tiles, t08, t64,t09); //ccrc rfrf ccrc
		jogo.iniciarPartida(AMARELO, VERMELHO);		
		rodadaInicialSemGirar();
		
		rodada(0, t08, SUL, 0, FINALIZA);
		jogo.posicionarMeepleEstrada(NORTE);
		verificarRelatorioPartida("Tile", "AMARELO, VERMELHO", "08N\n64N", "AMARELO", "09N");
		verificarEstradas("08N(X,S)64N(N,S)AMARELO");
	}

	private static final boolean FINALIZA = true;
	private static final boolean NAO_FINALIZA = false;

	private void girar(int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			jogo.girarTile();
		}
	}

	private void rodadaInicial(int girosAntesPosicionar, int girosDepoisPosicionar, boolean finaliza) {
		girar(girosAntesPosicionar);

		jogo.posicionarInicial();

		girar(girosDepoisPosicionar);

		if (finaliza) {
			jogo.finalizarRodada();
		}
	}

	private void rodada(int girosAntesPosicionar, Tile tileReferencia, Lado ladoTileReferencia,
			int girosDepoisPosicionar, boolean finaliza) {
		for (int i = 0; i < girosAntesPosicionar; i++) {
			jogo.girarTile();
		}
		girar(girosAntesPosicionar);

		jogo.posicionarTile(tileReferencia, ladoTileReferencia);

		girar(girosDepoisPosicionar);

		if (finaliza) {
			jogo.finalizarRodada();
		}
	}

	// girarTile? - posicionarTile - posicionarMeeple? - finalizarRodada
	// COR Início - COR Tile - COR Meeple - OUTRA COR Início

	private void ocorreExcecaoJogo(ExceptionThrower et, String mensagem) {
		ocorreExcecao(et).tipoExcecao(ExcecaoJogo.class).mensagem(mensagem);
	}

	private void mockarTiles(BolsaDeTiles mock, Tile primeiro, Tile... tiles) {
		when(mock.pegar()).thenReturn(primeiro, tiles);
	}

	private void verificarRelatorioPartida(String status, String sequencia, String tabuleiro, String jogadorRodada,
			String proximaPeca) {
		String relatorio = jogo.relatorioPartida();
		Assert.assertEquals("Status: " + status + "\nJogadores: " + sequencia + "\nTabuleiro: " + tabuleiro
				+ "\nJogador da rodada: " + jogadorRodada + "\nPróximo tile: " + proximaPeca, relatorio);
	}
	
	private void verificarEstradas(String verEstradas) {
		String estradas = jogo.getEstradas();
		Assert.assertEquals("Estradas: " + verEstradas, estradas);
	}
	
	private void verificarCidades(String verCidades) {
		String cidades = jogo.getCidades();
		Assert.assertEquals("Cidades: " + verCidades, cidades);
	}
	
	private void verificarCampos(String verCampos) {
		String campos = jogo.getCampos();
		Assert.assertEquals("Campos: " + verCampos, campos);
	}
	
	private void verificarMosteiros(String verMosteiros) {
		String mosteiros = jogo.getMosteiros();
		Assert.assertEquals("Mosteiros: " + verMosteiros, mosteiros);
	}
	
	// Pré-condições
	private void rodadaInicialSemGirar() {
		rodadaInicial(0, 0, FINALIZA);
	}

	private void doisTilesAmareloVermelhoRodada1SemGirar() {
		mockarTiles(tiles, t45, t19);
		jogo.iniciarPartida(AMARELO, VERMELHO);
		rodadaInicialSemGirar();
	}

}
