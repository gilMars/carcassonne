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

	/**
	 * Caso de teste 02
	 * 
	 * Verifica os relatórios da partida logo após o seu início. O status da partida
	 * é Em_Andamento. As cores dos jogadores aparecem no formato: COR1(PONTUACAO,
	 * MEEPLES DISPONÍVEIS); COR2(... O status do turno deve ser Início_Turno,
	 * também devem ser mostrados o primeiro jogador e o segundo tile disponível na
	 * bolsa de tiles, pois o primeiro tile é posicionado automaticamente na criação
	 * da partida. O tabuleiro deve conter a peça inicial na posição norte
	 * 
	 */
	@Test
	public void relatoriosNoInicioDaPartida() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, AZUL, VERDE);

		verificarRelatorioPartida(partida, "Em_Andamento", "AZUL(0,7); VERDE(0,7)");
		verificarRelatorioTurno(partida, "AZUL", "29N", "Início_Turno");
		verificarRelatorioTabuleiro(partida, "30N");
	}

	/**
	 * Caso de teste 03
	 * 
	 * Se a bolsa de tiles contiver apenas um tile, a partida finaliza
	 * automaticamente após a sua criação, pois não há mais tiles para puxar da
	 * bolsa. Nesse ponto (partida finalizada), não deve ser possível consultar o
	 * relatório do turno, mas o tabuleiro é visível.
	 */
	@Test // #03
	public void partidaComApenasUmTile() {
		mockarTiles(tiles, t30);
		Partida partida = jogo.criarPartida(tiles, AZUL, VERDE);

		verificarRelatorioPartida(partida, "Partida_Finalizada", "AZUL(0,7); VERDE(0,7)");
		ocorreExcecaoJogo(() -> partida.relatorioTurno(), "Partida finalizada");
		verificarRelatorioTabuleiro(partida, "30N");
	}

	/**
	 * Caso de teste 04
	 * 
	 * No início do turno, o tile do turno pode ser girado. O resultado será
	 * refletido no relatório do turno.
	 */
	@Test
	public void girarTileNoInicioDoTurno() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, VERMELHO);
		girar(partida, 1);

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); VERMELHO(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29L", "Início_Turno"); // Tile da rodada virado para o leste
		verificarRelatorioTabuleiro(partida, "30N");
	}

	/**
	 * Caso de teste 05
	 * 
	 * Quando não há mais tiles na bolsa e a partida finalizou, não deve ser mais
	 * possível girar o tile da rodada.
	 */
	@Test
	public void girarTilePartidaFinalizada() {
		mockarTiles(tiles, t30);
		Partida partida = jogo.criarPartida(tiles, AZUL, VERDE);

		ocorreExcecaoJogo(() -> girar(partida, 1), "Não pode girar tiles com a partida finalizada");
	}

	/**
	 * Caso de teste 06
	 * 
	 * Um tile pode ser girado várias vezes no início de um turno.
	 */
	@Test
	public void girarTile() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, VERMELHO);

		girar(partida, 1);
		verificarRelatorioTurno(partida, "VERDE", "29L", "Início_Turno");

		girar(partida, 2);
		verificarRelatorioTurno(partida, "VERDE", "29O", "Início_Turno");

		girar(partida, 1);
		verificarRelatorioTurno(partida, "VERDE", "29N", "Início_Turno");
	}

	/**
	 * Caso de teste 07
	 * 
	 * Posiciona o tile no primeiro turno da partida, mas não finaliza o turno. O
	 * tabuleiro deve ser atualizado com o novo tile.
	 */
	@Test
	public void colocarUmTile() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO, AMARELO, VERMELHO, AZUL);
		girar(partida, 2);
		partida.posicionarTile(t30, LESTE);

		verificarRelatorioPartida(partida, "Em_Andamento",
				"VERDE(0,7); PRETO(0,7); AMARELO(0,7); VERMELHO(0,7); AZUL(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29S", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "30N29S");
	}

	/**
	 * Caso de teste 08
	 * 
	 * Não pode modificar um tile já posicionado. Nem girar nem modificar a posição.
	 * Cenário com apenas um tile disponível na bolsa.
	 */
	@Test
	public void modificarTilePosicionado() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO, AMARELO, VERMELHO, AZUL);
		girar(partida, 2);
		partida.posicionarTile(t30, LESTE);

		ocorreExcecaoJogo(() -> girar(partida, 1), "Não pode girar tile já posicionado");
		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, OESTE), "Não pode reposicionar tile já posicionado");
	}

	/**
	 * Caso de teste 09
	 * 
	 * Não pode modificar um tile já posicionado. Nem girar nem modificar a posição.
	 * Cenário com mais que um tile disponível na bolsa.
	 */
	@Test
	public void modificarTilePosicionado2() {
		mockarTiles(tiles, t30, t29, t01);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO, AMARELO, VERMELHO, AZUL);
		girar(partida, 2);
		partida.posicionarTile(t30, LESTE);

		ocorreExcecaoJogo(() -> girar(partida, 1), "Não pode girar tile já posicionado");
		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, OESTE), "Não pode reposicionar tile já posicionado");
	}

	/**
	 * Caso de teste 10
	 * 
	 * Ao finalizar o primeiro turno, quando apenas um tile está disponível na
	 * bolsa, a partida deve ser finalizada.
	 */
	@Test
	public void finalizarTurno() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		girar(partida, 2);
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();

		verificarRelatorioPartida(partida, "Partida_Finalizada", "VERDE(0,7); PRETO(0,7)");
		ocorreExcecaoJogo(() -> partida.relatorioTurno(), "Partida finalizada");
		verificarRelatorioTabuleiro(partida, "30N29S");
	}

	/**
	 * Caso de teste 11
	 * 
	 * Ao finalizar o primeiro turno, quando há mais que um tile disponível na
	 * bolsa, o próximo turno deve ser iniciado.
	 */
	@Test
	public void finalizarTurno2() {
		mockarTiles(tiles, t30, t29, t01);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		girar(partida, 2);
		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		verificarRelatorioTurno(partida, "PRETO", "01N", "Início_Turno");
		verificarRelatorioTabuleiro(partida, "30N29S");

		girar(partida, 3);
		verificarRelatorioTurno(partida, "PRETO", "01O", "Início_Turno");
	}

	/**
	 * Caso de teste 12
	 * 
	 * Adicionando um tile na posição Sul da peça inicial. Quebra a linha e adiciona
	 * o Tile abaixo do inicial.
	 */
	@Test
	public void posicionarSegundoTileSul() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29S", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "30N\n29S");
	}

	/**
	 * Caso de teste 13
	 * 
	 * Adicionando um tile na posição Oeste da peça inicial. Adiciona o Tile à
	 * esquerda do inicial.
	 */
	@Test
	public void posicionarSegundoTileOeste() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.posicionarTile(t30, OESTE);

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29N", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "29N30N");
	}

	/**
	 * Caso de teste 14
	 * 
	 * Adicionando um tile na posição Norte da peça inicial. Quebra a linha e
	 * adiciona o Tile acima do inicial.
	 */
	@Test
	public void posicionarSegundoTileNorte() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		girar(partida, 2);
		partida.posicionarTile(t30, NORTE);

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29S", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "29S\n30N");
	}

	/**
	 * Caso de teste 15
	 * 
	 * Adicionando um tile na posição Leste da peça inicial. Adiciona o Tile à
	 * esquerda do inicial.
	 */
	@Test
	public void posicionarSegundoTileLeste() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);
		partida.posicionarTile(t30, LESTE);

		verificarRelatorioPartida(partida, "Em_Andamento", "VERDE(0,7); PRETO(0,7)");
		verificarRelatorioTurno(partida, "VERDE", "29N", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "30N29N");
	}

	/**
	 * Caso de teste 16
	 * 
	 * Testa o posicionamento de tiles com lados incompatíveis.
	 */
	@Test
	public void erroPosicionarSegundoTile() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, VERDE, PRETO);

		girar(partida, 3);
		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, LESTE),
				"O lado Leste do tile 30 (Estrada) é incompatível com o lado Oeste do tile 29 (Cidade)");

		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, OESTE),
				"O lado Oeste do tile 30 (Estrada) é incompatível com o lado Leste do tile 29 (Campo)");

		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, SUL),
				"O lado Sul do tile 30 (Campo) é incompatível com o lado Norte do tile 29 (Estrada)");

		ocorreExcecaoJogo(() -> partida.posicionarTile(t30, NORTE),
				"O lado Norte do tile 30 (Cidade) é incompatível com o lado Sul do tile 29 (Estrada)");
	}

	/**
	 * Caso de teste 17
	 * 
	 * Ao tentar posicionar meeple de estrada em lado que não tem estrada, deve ser lançada uma
	 * exceção.
	 */
	@Test
	public void posicionarMeepleEstradaSemEstrada() {
		mockarTiles(tiles, t30, t29);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);

		ocorreExcecaoJogo(() -> partida.posicionarMeepleEstrada(SUL),
				"Impossível posicionar meeple em estrada pois o lado Sul do tile 29 é Cidade");

		ocorreExcecaoJogo(() -> partida.posicionarMeepleEstrada(NORTE),
				"Impossível posicionar meeple em estrada pois o lado Norte do tile 29 é Campo");
	}

	/**
	 * Caso de tese 18
	 * 
	 * Verificar estrada antes e depois de colocar Meeple
	 */
	@Test
	public void verificarEstrada() {
		mockarTiles(tiles, t30);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		
		Assert.assertEquals("30(O,L)", partida.getEstradas());
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleEstrada(OESTE),
				"Impossível posicionar meeple na peça inicial");

		Assert.assertEquals("30(O,L)", partida.getEstradas());
	}
	
	/**
	 * Caso de teste 19
	 * 
	 *  Estrada com dois tiles e meeple
	 */
	@Test
	public void estradaComDoisTilesMeeple() {
		mockarTiles(tiles, t30, t64);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 1);

		partida.posicionarTile(t30, LESTE);
		Assert.assertEquals("30(O,L) 64(O,L)", partida.getEstradas());

		partida.posicionarMeepleEstrada(LESTE);
		Assert.assertEquals("30(O,L) 64(O,L-AMARELO)", partida.getEstradas());

		partida.finalizarTurno();
		Assert.assertEquals("30(O,L) 64(O,L-AMARELO)", partida.getEstradas());
		
		verificarRelatorioPartida(partida, "Partida_Finalizada", "AMARELO(0,7); VERMELHO(0,7)");
		ocorreExcecaoJogo(() -> partida.relatorioTurno(), "Partida finalizada");
		verificarRelatorioTabuleiro(partida, "30N64L");
	}
	
	/**
	 * Caso de teste 20
	 * 
	 * Estradas desconexas com meeple
	 */
	@Test
	public void estradasDesconexasMeeple() {
		mockarTiles(tiles, t30, t64, t51);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 1);

		partida.posicionarTile(t30, LESTE);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		Assert.assertEquals("30(O,L) 64(O,L)\n51(O,S)", partida.getEstradas());
		
		partida.posicionarMeepleEstrada(SUL);
		Assert.assertEquals("30(O,L) 64(O,L)\n51(O,S-VERMELHO)", partida.getEstradas());
		
		verificarRelatorioPartida(partida, "Em_Andamento", "AMARELO(0,7); VERMELHO(0,6)");
		verificarRelatorioTurno(partida, "VERMELHO", "51N", "Meeple_Posicionado");
		verificarRelatorioTabuleiro(partida, "30N64L\n51N");

	}
	
	/**
	 * Caso de teste 21
	 * 
	 * Posicionar meeple em estrada já ocupada
	 */
	@Test
	public void posicionarMeepleEmEstradaOcupada() {
		mockarTiles(tiles, t30, t64, t51, t52);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 1);

		partida.posicionarTile(t30, LESTE);
		partida.posicionarMeepleEstrada(LESTE);
		partida.finalizarTurno();
		
		partida.posicionarTile(t30, SUL);
		partida.posicionarMeepleEstrada(OESTE);
		partida.finalizarTurno();
		Assert.assertEquals("30(O,L) 64(O,L-AMARELO)\n51(O-VERMELHO,S)", partida.getEstradas());
		
		girar(partida, 1);
		partida.posicionarTile(t64, LESTE);
		Assert.assertEquals("30(O,L) 64(O,L-AMARELO) 52(N,O)\n51(O-VERMELHO,S)", partida.getEstradas());
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleEstrada(OESTE),
				"Impossível posicionar meeple pois a estrada já está ocupada pelo meeple AMARELO no lado Leste do tile 64");
		
		verificarRelatorioPartida(partida, "Em_Andamento", "AMARELO(0,6); VERMELHO(0,6)");
		verificarRelatorioTurno(partida, "AMARELO", "52L", "Tile_Posicionado");
		verificarRelatorioTabuleiro(partida, "30N64L52L\n51N");

	}
	
	/**
	 * Caso de teste 22
	 * 
	 * Ao tentar posicionar meeple de campo em lado que não tem campo, deve ser lançada uma
	 * exceção.
	 */
	@Test
	public void posicionarMeepleCampoSemCampo() {
		mockarTiles(tiles, t30, t02);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);

		ocorreExcecaoJogo(() -> partida.posicionarMeepleCampo(SUDESTE),
				"Impossível posicionar meeple em campo pois o vertice Sudeste do tile 02 é totalmente ocupado por Cidade");

		ocorreExcecaoJogo(() -> partida.posicionarMeepleCampo(SUDOESTE),
				"Impossível posicionar meeple em campo pois o vertice Sudoeste do tile 02 é totalmente ocupado por Cidade");
	}
	
	/**
	 * Caso de tese 23
	 * 
	 * Verificar campo antes e depois de colocar Meeple
	 */
	@Test
	public void verificarCampo() {
		mockarTiles(tiles, t30);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		
		Assert.assertEquals("30(NO,NE)\n30(SO,SE)", partida.getCampos());
		
		ocorreExcecaoJogo(() -> partida.posicionarMeepleCampo(SUDESTE),
				"Impossível posicionar meeple na peça inicial");

		Assert.assertEquals("30(NO,NE)\n30(SO,SE)", partida.getCampos());
	}
	
	/**
	 * Caso de teste 24
	 * 
	 *  Campo com dois tiles e meeple
	 */
	@Test
	public void campoComDoisTilesMeeple() {
		mockarTiles(tiles, t30, t02);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 2);

		partida.posicionarTile(t30, SUL);
		Assert.assertEquals("30(NO,NE)\n30(SO,SE) 02(NO,NE)", partida.getCampos());

		partida.posicionarMeepleCampo(NORDESTE);
		Assert.assertEquals("30(NO,NE)\n30(SO,SE) 02(NO,NE-AMARELO)", partida.getCampos());

		partida.finalizarTurno();
		Assert.assertEquals("30(NO,NE)\n30(SO,SE) 02(NO,NE-AMARELO)", partida.getCampos());
	}
	
	/**
	 * Caso de teste 25
	 * 
	 *  Campo com três tiles e meeple
	 */
	@Test
	public void campoComTresTilesMeeple() {
		mockarTiles(tiles, t30, t02, t51);
		Partida partida = jogo.criarPartida(tiles, AMARELO, VERMELHO);
		girar(partida, 2);
		partida.posicionarTile(t30, SUL);
		partida.finalizarTurno();

		partida.posicionarTile(t30, LESTE);
		Assert.assertEquals("30(NO,NE) 51(NO,NE,SE)\n30(SO,SE) 02(NO,NE) 51(SO)", partida.getCampos());

		partida.posicionarMeepleCampo(SUDESTE);
		Assert.assertEquals("30(NO,NE) 51(NO,NE,SE-VERMELHO)\n30(SO,SE) 02(NO,NE) 51(SO)", partida.getCampos());

		partida.finalizarTurno();
		Assert.assertEquals("30(NO,NE) 51(NO,NE,SE-VERMELHO)\n30(SO,SE) 02(NO,NE) 51(SO)", partida.getCampos());
	}
	
	private void girar(Partida partida, int quantidade) {
		for (int i = 0; i < quantidade; i++) {
			partida.girarTile();
		}
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