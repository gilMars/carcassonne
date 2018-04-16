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
		assertEquals("AN", tabuleiroFlexivel.toString());
	}

	@Test
	public void testeLeste() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.LESTE, TILE_B);
		assertEquals("ANBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.LESTE, TILE_C);
		assertEquals("ANBNCN", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeOeste() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.OESTE, TILE_B);
		assertEquals("BNAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.OESTE, TILE_C);
		assertEquals("CNBNAN", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeNorte() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("BN\nAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("CN\nBN\nAN", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeSul() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("AN\nBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("AN\nBN\nCN", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL1() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("AN\nBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.LESTE, TILE_C);
		assertEquals("AN \nBNCN", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_D);
		assertEquals("AN  \nBNCNDN", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL2() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("AN\nBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("AN\nBN\nCN", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_C, Lado.OESTE, TILE_D);
		assertEquals(" AN\n BN\nDNCN", tabuleiroFlexivel.toString());		
	}

	@Test
	public void testeL3() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("BN\nAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("CN\nBN\nAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_D);
		assertEquals("CNDN\nBN \nAN ", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_D, Lado.LESTE, TILE_E);
		assertEquals("CNDNEN\nBN  \nAN  ", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeL4() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_B);
		assertEquals("BN\nAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.NORTE, TILE_C);
		assertEquals("CN\nBN\nAN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.OESTE, TILE_D);
		assertEquals("DNCN\n BN\n AN", tabuleiroFlexivel.toString());		
		tabuleiroFlexivel.posicionar(TILE_D, Lado.OESTE, TILE_E);
		assertEquals("ENDNCN\n  BN\n  AN", tabuleiroFlexivel.toString());		
	}
	
	@Test
	public void testeT1() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("AN\nBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("AN\nBN\nCN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.SUL, TILE_D);
		assertEquals("AN\nBN\nCN\nDN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.OESTE, TILE_E);
		assertEquals(" AN\nENBN\n CN\n DN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_C, Lado.LESTE, TILE_F);
		assertEquals(" AN \nENBN \n CNFN\n DN ", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_E, Lado.OESTE, TILE_G);
		assertEquals("  AN \nGNENBN \n  CNFN\n  DN ", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_F, Lado.LESTE, TILE_H);
		assertEquals("  AN  \nGNENBN  \n  CNFNHN\n  DN  ", tabuleiroFlexivel.toString());
	}

	@Test
	public void testeCruz() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_A);
		tabuleiroFlexivel.posicionar(TILE_A, Lado.SUL, TILE_B);
		assertEquals("AN\nBN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_B, Lado.SUL, TILE_C);
		assertEquals("AN\nBN\nCN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.NORTE, TILE_D);
		assertEquals("DN\nAN\nBN\nCN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_D, Lado.NORTE, TILE_E);
		assertEquals("EN\nDN\nAN\nBN\nCN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.OESTE, TILE_F);
		assertEquals(" EN\n DN\nFNAN\n BN\n CN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_F, Lado.OESTE, TILE_G);
		assertEquals("  EN\n  DN\nGNFNAN\n  BN\n  CN", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_A, Lado.LESTE, TILE_H);
		assertEquals("  EN \n  DN \nGNFNANHN\n  BN \n  CN ", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_H, Lado.LESTE, TILE_I);
		assertEquals("  EN  \n  DN  \nGNFNANHNIN\n  BN  \n  CN  ", tabuleiroFlexivel.toString());
		tabuleiroFlexivel.posicionar(TILE_I, Lado.LESTE, TILE_J);
		assertEquals("  EN   \n  DN   \nGNFNANHNINJN\n  BN   \n  CN   ", tabuleiroFlexivel.toString());
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
				.mensagem("O lado Leste do tile 1 (_) é incompatível com o lado Oeste do tile 3 (*)");
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.SUL, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Sul do tile 1 (*) é incompatível com o lado Norte do tile 3 (_)");

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.OESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Oeste do tile 1 (_) é incompatível com o lado Leste do tile 3 (*)");
		
		tabuleiroFlexivel.posicionar(TILE_1, Lado.LESTE, TILE_4);

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_1, Lado.SUL, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Sul do tile 1 (*) é incompatível com o lado Norte do tile 3 (_)");
	}

	@Test
	public void testaLadoIncompatível() {
		tabuleiroFlexivel.adicionarPrimeiroTile(TILE_1);
		tabuleiroFlexivel.posicionar(TILE_1, Lado.NORTE, TILE_2);
		
		TILE_3.girar();
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.LESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Leste do tile 2 (_) é incompatível com o lado Oeste do tile 3 (*)");
		
		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.NORTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Norte do tile 2 (*) é incompatível com o lado Sul do tile 3 (_)");

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.OESTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Oeste do tile 2 (_) é incompatível com o lado Leste do tile 3 (*)");
		
		tabuleiroFlexivel.posicionar(TILE_2, Lado.LESTE, TILE_4);

		ocorreExcecao( () -> tabuleiroFlexivel.posicionar(TILE_2, Lado.NORTE, TILE_3))
				.tipoExcecao(ExcecaoJogo.class)
				.mensagem("O lado Norte do tile 2 (*) é incompatível com o lado Sul do tile 3 (_)");
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
