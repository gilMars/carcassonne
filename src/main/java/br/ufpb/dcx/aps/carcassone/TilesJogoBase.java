package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TileComVertice;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TipoTile;

/**
 * Source:
 * https://www.eecs.northwestern.edu/~robby/uc-courses/22001-2006-winter/tiles.html
 */
public interface TilesJogoBase {

	Cor AMARELO = Cor.AMARELO;
	Cor AZUL = Cor.AZUL;
	Cor PRETO = Cor.PRETO;
	Cor VERDE = Cor.VERDE;
	Cor VERMELHO = Cor.VERMELHO;

	Lado NORTE = Lado.NORTE;
	Lado LESTE = Lado.LESTE;
	Lado SUL = Lado.SUL;
	Lado OESTE = Lado.OESTE;
	
	Vertice NORDESTE = Vertice.NORDESTE;
	Vertice SUDESTE = Vertice.SUDESTE;
	Vertice SUDOESTE = Vertice.SUDOESTE;
	Vertice NOROESTE = Vertice.NOROESTE;
	
	Origem BASE = Origem.BASE;

	TipoLadoCarcassonne CAMPO = TipoLadoCarcassonne.CAMPO;
	TipoLadoCarcassonne CIDADE = TipoLadoCarcassonne.CIDADE;
	TipoLadoCarcassonne ESTRADA = TipoLadoCarcassonne.ESTRADA;

	boolean COM_ESCUDO = true;
	boolean SEM_ESCUDO = false;

	boolean COM_MOSTEIRO = true;
	boolean SEM_MOSTEIRO = false;

	boolean UMA_CIDADE = false;
	boolean DUAS_CIDADES = true;
	boolean SEM_CIDADES = false;

	TipoTile CCCCS50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, CIDADE, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CCFC50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, CAMPO, CIDADE, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CCFCS50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, CAMPO, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CCFF50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, CAMPO, CAMPO, SEM_ESCUDO, SEM_MOSTEIRO, DUAS_CIDADES);
	TipoTile CCRC50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, ESTRADA, CIDADE, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CCRCS50 = new TipoTileCarcassonne(BASE, CIDADE, CIDADE, ESTRADA, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CFCF50 = new TipoTileCarcassonne(BASE, CIDADE, CAMPO, CIDADE, CAMPO, SEM_ESCUDO, SEM_MOSTEIRO, DUAS_CIDADES);
	TipoTile CFFC50 = new TipoTileCarcassonne(BASE, CIDADE, CAMPO, CAMPO, CIDADE, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CFFCS50 = new TipoTileCarcassonne(BASE, CIDADE, CAMPO, CAMPO, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CFFF50 = new TipoTileCarcassonne(BASE, CIDADE, CAMPO, CAMPO, CAMPO, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CFRR50 = new TipoTileCarcassonne(BASE, CIDADE, CAMPO, ESTRADA, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CRFR50 = new TipoTileCarcassonne(BASE, CIDADE, ESTRADA, CAMPO, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CRRC50 = new TipoTileCarcassonne(BASE, CIDADE, ESTRADA, ESTRADA, CIDADE, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CRRCS50 = new TipoTileCarcassonne(BASE, CIDADE, ESTRADA, ESTRADA, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CRRF50 = new TipoTileCarcassonne(BASE, CIDADE, ESTRADA, ESTRADA, CAMPO, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile CRRR50 = new TipoTileCarcassonne(BASE, CIDADE, ESTRADA, ESTRADA, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile FCFC50 = new TipoTileCarcassonne(BASE, CAMPO, CIDADE, CAMPO, CIDADE, SEM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile FCFCS50 = new TipoTileCarcassonne(BASE, CAMPO, CIDADE, CAMPO, CIDADE, COM_ESCUDO, SEM_MOSTEIRO, UMA_CIDADE);
	TipoTile FFFFL50 = new TipoTileCarcassonne(BASE, CAMPO, CAMPO, CAMPO, CAMPO, SEM_ESCUDO, COM_MOSTEIRO, SEM_CIDADES);
	TipoTile FFRFL50 = new TipoTileCarcassonne(BASE, CAMPO, CAMPO, ESTRADA, CAMPO, SEM_ESCUDO, COM_MOSTEIRO, SEM_CIDADES);
	TipoTile FFRR50 = new TipoTileCarcassonne(BASE, CAMPO, CAMPO, ESTRADA, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, SEM_CIDADES);
	TipoTile FRRR50 = new TipoTileCarcassonne(BASE, CAMPO, ESTRADA, ESTRADA, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, SEM_CIDADES);
	TipoTile RFRF50 = new TipoTileCarcassonne(BASE, ESTRADA, CAMPO, ESTRADA, CAMPO, SEM_ESCUDO, SEM_MOSTEIRO, SEM_CIDADES);
	TipoTile RRRR50 = new TipoTileCarcassonne(BASE, ESTRADA, ESTRADA, ESTRADA, ESTRADA, SEM_ESCUDO, SEM_MOSTEIRO, SEM_CIDADES);

	
	Tile t01 = new TileComVertice(new Tile("01", CCCCS50), CAMPO, CAMPO, CAMPO, CAMPO);
	Tile t02 = new TileComVertice(new Tile("02", CCFC50), CIDADE, CIDADE, CAMPO, CAMPO);
	Tile t03 = new Tile("03", CCFC50);
	Tile t04 = new Tile("04", CCFC50);
	Tile t05 = new Tile("05", CCFCS50);
	Tile t06 = new Tile("06", CCFF50);
	Tile t07 = new Tile("07", CCFF50);
	Tile t08 = new Tile("08", CCRC50);
	Tile t09 = new Tile("09", CCRCS50);
	Tile t10 = new Tile("10", CCRCS50);
	Tile t11 = new Tile("11", CFCF50);
	Tile t12 = new Tile("12", CFCF50);
	Tile t13 = new Tile("13", CFCF50);
	Tile t14 = new Tile("14", CFFC50);
	Tile t15 = new Tile("15", CFFC50);
	Tile t16 = new Tile("16", CFFC50);
	Tile t17 = new Tile("17", CFFCS50);
	Tile t18 = new Tile("18", CFFCS50);
	Tile t19 = new Tile("19", CFFF50);
	Tile t20 = new Tile("20", CFFF50);
	Tile t21 = new Tile("21", CFFF50);
	Tile t22 = new Tile("22", CFFF50);
	Tile t23 = new Tile("23", CFFF50);
	Tile t24 = new Tile("24", CFRR50);
	Tile t25 = new Tile("25", CFRR50);
	Tile t26 = new Tile("26", CFRR50);
	Tile t27 = new Tile("27", CRFR50);
	Tile t28 = new Tile("28", CRFR50);
	Tile t29 = new TileComVertice(new Tile("29", CRFR50), CAMPO, CAMPO, CAMPO, CAMPO);
	Tile t30 = new TileComVertice(new Tile("30", CRFR50), CAMPO, CAMPO, CAMPO, CAMPO); // Initial
	Tile t31 = new Tile("31", CRRC50);
	Tile t32 = new Tile("32", CRRC50);
	Tile t33 = new Tile("33", CRRC50);
	Tile t34 = new Tile("34", CRRCS50);
	Tile t35 = new Tile("35", CRRCS50);
	Tile t36 = new Tile("36", CRRF50);
	Tile t37 = new Tile("37", CRRF50);
	Tile t38 = new Tile("38", CRRF50);
	Tile t39 = new Tile("39", CRRR50);
	Tile t40 = new Tile("40", CRRR50);
	Tile t41 = new Tile("41", CRRR50);
	Tile t42 = new Tile("42", FCFC50);
	Tile t43 = new Tile("43", FCFCS50);
	Tile t44 = new Tile("44", FCFCS50);
	Tile t45 = new Tile("45", FFFFL50);
	Tile t46 = new Tile("46", FFFFL50);
	Tile t47 = new Tile("47", FFFFL50);
	Tile t48 = new Tile("48", FFFFL50);
	Tile t49 = new Tile("49", FFRFL50);
	Tile t50 = new Tile("50", FFRFL50);
	Tile t51 = new TileComVertice(new Tile("51", FFRR50), CAMPO, CAMPO, CAMPO, CAMPO);
	Tile t52 = new Tile("52", FFRR50);
	Tile t53 = new Tile("53", FFRR50);
	Tile t54 = new Tile("54", FFRR50);
	Tile t55 = new Tile("55", FFRR50);
	Tile t56 = new Tile("56", FFRR50);
	Tile t57 = new Tile("57", FFRR50);
	Tile t58 = new Tile("58", FFRR50);
	Tile t59 = new Tile("59", FFRR50);
	Tile t60 = new Tile("60", FRRR50);
	Tile t61 = new Tile("61", FRRR50);
	Tile t62 = new Tile("62", FRRR50);
	Tile t63 = new Tile("63", FRRR50);
	Tile t64 = new TileComVertice(new Tile("64", RFRF50), CAMPO, CAMPO, CAMPO, CAMPO);
	Tile t65 = new Tile("65", RFRF50);
	Tile t66 = new Tile("66", RFRF50);
	Tile t67 = new Tile("67", RFRF50);
	Tile t68 = new Tile("68", RFRF50);
	Tile t69 = new Tile("69", RFRF50);
	Tile t70 = new Tile("70", RFRF50);
	Tile t71 = new Tile("71", RFRF50);
	Tile t72 = new Tile("72", RRRR50);
	
}
