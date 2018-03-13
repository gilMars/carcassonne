package br.ufpb.dcx.aps.carcassone.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.dcx.aps.carcassone.ExcecaoJogo;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoLado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoTile;

import static br.ufpb.dcx.aps.carcassone.teste.Assertiva.*;

public class TabuleiroFlexivelTest {
	
	private static TipoLado ASTERISK = TipoLadoTeste.ASTERISK;
	private static TipoLado UNDERSCORE = TipoLadoTeste.UNDERSCORE;
	
	private static final TipoTile TIPO_DUMMY = new TipoTile(ASTERISK, ASTERISK, ASTERISK, ASTERISK);

	private static Tile TILE_A = new Tile("A", TIPO_DUMMY);
	private static Tile TILE_B = new Tile("B", TIPO_DUMMY);
	private static Tile TILE_C = new Tile("C", TIPO_DUMMY);
	private static Tile TILE_D = new Tile("D", TIPO_DUMMY);
	private static Tile TILE_E = new Tile("E", TIPO_DUMMY);
	private static Tile TILE_F = new Tile("F", TIPO_DUMMY);
	private static Tile TILE_G = new Tile("G", TIPO_DUMMY);
	private static Tile TILE_H = new Tile("H", TIPO_DUMMY);
	private static Tile TILE_I = new Tile("I", TIPO_DUMMY);
	private static Tile TILE_J = new Tile("J", TIPO_DUMMY);
	

	private static final TipoTile TIPO_ALTERNADO = new TipoTile(ASTERISK, UNDERSCORE, ASTERISK, UNDERSCORE);

	private static Tile TILE_1 = new Tile("1", TIPO_ALTERNADO);
	private static Tile TILE_2 = new Tile("2", TIPO_ALTERNADO);
	private static Tile TILE_3 = new Tile("3", TIPO_ALTERNADO);
	private static Tile TILE_4 = new Tile("4", TIPO_ALTERNADO);
	

	private TabuleiroFlexivel tabuleiroFlexivel;
	
	@Before
	public void novoJogo() {
		tabuleiroFlexivel = new TabuleiroFlexivel();
		TILE_3.reset();
	}
	
	@Test
	public void testeUmaPeca() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		assertEquals("A\n", tabuleiroFlexivel.toString());
	}

	@Test
	public void testeLeste() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.LESTE, TILE_B);
		assertEquals("AB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.LESTE, TILE_C);
		assertEquals("ABC\n", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeOeste() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.OESTE, TILE_B);
		assertEquals("BA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.OESTE, TILE_C);
		assertEquals("CBA\n", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeNorte() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("B\nA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("C\nB\nA\n", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeSul() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("A\nB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("A\nB\nC\n", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL1() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("A\nB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.LESTE, TILE_C);
		assertEquals("A \nBC\n", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_D);
		assertEquals("A  \nBCD\n", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL2() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("A\nB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("A\nB\nC\n", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_C, Lado.OESTE, TILE_D);
		assertEquals(" A\n B\nDC\n", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL3() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("B\nA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("C\nB\nA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_D);
		assertEquals("CD\nB \nA \n", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_D, Lado.LESTE, TILE_E);
		assertEquals("CDE\nB  \nA  \n", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeL4() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("B\nA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("C\nB\nA\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.OESTE, TILE_D);
		assertEquals("DC\n B\n A\n", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_D, Lado.OESTE, TILE_E);
		assertEquals("EDC\n  B\n  A\n", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeT1() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("A\nB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("A\nB\nC\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.SUL, TILE_D);
		assertEquals("A\nB\nC\nD\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.OESTE, TILE_E);
		assertEquals(" A\nEB\n C\n D\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_F);
		assertEquals(" A \nEB \n CF\n D \n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_E, Lado.OESTE, TILE_G);
		assertEquals("  A \nGEB \n  CF\n  D \n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_F, Lado.LESTE, TILE_H);
		assertEquals("  A  \nGEB  \n  CFH\n  D  \n", tabuleiroFlexivel.toString());
	}

	@Test
	public void testeCruz() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("A\nB\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("A\nB\nC\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_D);
		assertEquals("D\nA\nB\nC\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_D, Lado.NORTE, TILE_E);
		assertEquals("E\nD\nA\nB\nC\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.OESTE, TILE_F);
		assertEquals(" E\n D\nFA\n B\n C\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_F, Lado.OESTE, TILE_G);
		assertEquals("  E\n  D\nGFA\n  B\n  C\n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.LESTE, TILE_H);
		assertEquals("  E \n  D \nGFAH\n  B \n  C \n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_H, Lado.LESTE, TILE_I);
		assertEquals("  E  \n  D  \nGFAHI\n  B  \n  C  \n", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_I, Lado.LESTE, TILE_J);
		assertEquals("  E   \n  D   \nGFAHIJ\n  B   \n  C   \n", tabuleiroFlexivel.toString());
	}
	
	@Test
	public void testesColisaoProximo() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("A posição NORTE do tile B já está ocupada pelo tile A");
	}

	@Test
	public void testesColisaoDistante() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);

		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_D);
		tabuleiroFlexivel.posicionar(TILE_D, Lado.LESTE, TILE_E);

		tabuleiroFlexivel.posicionar(TILE_A, Lado.LESTE, TILE_F);
		tabuleiroFlexivel.posicionar(TILE_F, Lado.LESTE, TILE_G);
		tabuleiroFlexivel.posicionar(TILE_G, Lado.SUL, TILE_H);
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_H, Lado.SUL, TILE_I))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("A posição SUL do tile H já está ocupada pelo tile E");
	}
	
	@Test
	public void testesDuplicidade() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_A))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O tile A já foi posicionado no tabuleiro");
		
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		tabuleiroFlexivel.posicionar(TILE_B, Lado.LESTE, TILE_C);

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_C, Lado.SUL, TILE_A))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O tile A já foi posicionado no tabuleiro");
	}
	
	@Test
	public void testaLadoIncompatívelInicial() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_1);
		tabuleiroFlexivel.posicionar(TILE_1, Lado.NORTE, TILE_2);
		
		TILE_3.girar();
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.LESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado LESTE do tile 1 (_) é diferente do lado OESTE (*) do tile 3");
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.SUL, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado SUL do tile 1 (*) é diferente do lado NORTE (_) do tile 3");

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.OESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado OESTE do tile 1 (_) é diferente do lado LESTE (*) do tile 3");
		
		tabuleiroFlexivel.posicionar(TILE_1, Lado.LESTE, TILE_4);

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.SUL, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado SUL do tile 1 (*) é diferente do lado NORTE (_) do tile 3");
	}

	@Test
	public void testaLadoIncompatível() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_1);
		tabuleiroFlexivel.posicionar(TILE_1, Lado.NORTE, TILE_2);
		
		TILE_3.girar();
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.LESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado LESTE do tile 2 (_) é diferente do lado OESTE (*) do tile 3");
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.NORTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado NORTE do tile 2 (*) é diferente do lado SUL (_) do tile 3");

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.OESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado OESTE do tile 2 (_) é diferente do lado LESTE (*) do tile 3");
		
		tabuleiroFlexivel.posicionar(TILE_2, Lado.LESTE, TILE_4);

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.NORTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado NORTE do tile 2 (*) é diferente do lado SUL (_) do tile 3");
	}
	
}

enum TipoLadoTeste implements TipoLado{

	ASTERISK("*"), UNDERSCORE("_");
	
	private final String abreviacao;

	TipoLadoTeste(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
    public String getAbreviacao() { 
    		return abreviacao; 
    	}
}
