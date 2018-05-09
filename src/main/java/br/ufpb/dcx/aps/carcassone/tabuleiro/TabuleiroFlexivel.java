package br.ufpb.dcx.aps.carcassone.tabuleiro;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.ExcecaoJogo;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.Meeple;
import br.ufpb.dcx.aps.carcassone.TipoLadoCarcassonne;

public class TabuleiroFlexivel {

	CelulaTabuleiro celulaInicial;

	CelulaTabuleiro extremoNorte;
	CelulaTabuleiro extremoLeste;
	CelulaTabuleiro extremoSul;
	CelulaTabuleiro extremoOeste;

	private String espacoVazio;

	public TabuleiroFlexivel() {
		this(" ");
	}

	public TabuleiroFlexivel(String espacoVazio) {
		this.espacoVazio = espacoVazio;
	}

	public void adicionarPrimeiroTile(Tile tile) {
		celulaInicial = new CelulaTabuleiro(tile, 0, 0);
		extremoNorte = extremoLeste = extremoSul = extremoOeste = celulaInicial;
	}

	public void posicionar(Tile tileReferencia, Lado ladoTileReferencia, Tile novoTile) {
		CelulaTabuleiro celulaReferencia = validarTiles(tileReferencia, novoTile);

		CelulaTabuleiro[][] tabuleiro = montarTabuleiro();

		switch (ladoTileReferencia) {
		case NORTE:
			posicionarNorte(novoTile, celulaReferencia, tabuleiro);
			break;

		case LESTE:
			posicionarLeste(novoTile, celulaReferencia, tabuleiro);
			break;

		case SUL:
			posicionarSul(novoTile, celulaReferencia, tabuleiro);
			break;

		case OESTE:
			posicionarOeste(novoTile, celulaReferencia, tabuleiro);
			break;
		}
	}

	private CelulaTabuleiro validarTiles(Tile tileReferencia, Tile novoTile) {

		CelulaTabuleiro celulaReferencia = encontrarCelula(celulaInicial, tileReferencia);

		if (celulaReferencia == null) {
			throw new ExcecaoJogo("Tile não encontrada: " + tileReferencia.getId());
		}
		
		if (verificarTilePosicionado(novoTile)) {
			throw new ExcecaoJogo("Não pode reposicionar tile já posicionado");
		}

		return celulaReferencia;
	}

	private void posicionarNorte(Tile tileNovo, CelulaTabuleiro celulaReferencia, CelulaTabuleiro[][] tabuleiro) {
		celulaOcupada(celulaReferencia, tabuleiro, celulaReferencia.getX(), celulaReferencia.getY() + 1, "NORTE");

		verificarTipoLado(tileNovo, celulaReferencia.getTile(), "Norte", celulaReferencia.getTile().getLadoNorte(),
				"Sul", tileNovo.getLadoSul());

		CelulaTabuleiro novaCelula = new CelulaTabuleiro(tileNovo, celulaReferencia.getX(),
				celulaReferencia.getY() + 1);
		celulaReferencia.setNorte(novaCelula);
		novaCelula.setSul(celulaReferencia);

		if (celulaReferencia.getY() == extremoNorte.getY()) {
			extremoNorte = novaCelula;
		}
	}

	private void posicionarLeste(Tile tileNovo, CelulaTabuleiro celulaReferencia, CelulaTabuleiro[][] tabuleiro) {
		celulaOcupada(celulaReferencia, tabuleiro, celulaReferencia.getX() + 1, celulaReferencia.getY(), "LESTE");

		verificarTipoLado(tileNovo, celulaReferencia.getTile(), "Leste", celulaReferencia.getTile().getLadoLeste(),
				"Oeste", tileNovo.getLadoOeste());

		CelulaTabuleiro novaCelula = new CelulaTabuleiro(tileNovo, celulaReferencia.getX() + 1,
				celulaReferencia.getY());
		celulaReferencia.setLeste(novaCelula);
		novaCelula.setOeste(celulaReferencia);

		if (celulaReferencia.getX() == extremoLeste.getX()) {
			extremoLeste = novaCelula;
		}
	}

	private void posicionarSul(Tile tileNovo, CelulaTabuleiro celulaReferencia, CelulaTabuleiro[][] tabuleiro) {
		celulaOcupada(celulaReferencia, tabuleiro, celulaReferencia.getX(), celulaReferencia.getY() - 1, "SUL");

		verificarTipoLado(tileNovo, celulaReferencia.getTile(), "Sul", celulaReferencia.getTile().getLadoSul(), "Norte",
				tileNovo.getLadoNorte());

		CelulaTabuleiro novaCelula = new CelulaTabuleiro(tileNovo, celulaReferencia.getX(),
				celulaReferencia.getY() - 1);
		celulaReferencia.setSul(novaCelula);
		novaCelula.setNorte(celulaReferencia);

		if (celulaReferencia.getY() == extremoSul.getY()) {
			extremoSul = novaCelula;
		}
	}

	private void posicionarOeste(Tile tileNovo, CelulaTabuleiro celulaReferencia, CelulaTabuleiro[][] tabuleiro) {
		celulaOcupada(celulaReferencia, tabuleiro, celulaReferencia.getX() - 1, celulaReferencia.getY(), "OESTE");

		verificarTipoLado(tileNovo, celulaReferencia.getTile(), "Oeste", celulaReferencia.getTile().getLadoOeste(),
				"Leste", tileNovo.getLadoLeste());

		CelulaTabuleiro novaCelula = new CelulaTabuleiro(tileNovo, celulaReferencia.getX() - 1,
				celulaReferencia.getY());
		celulaReferencia.setOeste(novaCelula);
		novaCelula.setLeste(celulaReferencia);

		if (celulaReferencia.getX() == extremoOeste.getX()) {
			extremoOeste = novaCelula;
		}
	}

	public void posicionarMeeple(Meeple meeple) {
		switch(meeple.getLado()) {
		case NORTE:
			posicionarMeepleEstradaNorte(meeple);
			break;
		case LESTE:
			posicionarMeepleEstradaLeste(meeple);
			break;
		case SUL:
			posicionarMeepleEstradaSul(meeple);
			break;
		case OESTE:
			posicionarMeepleEstradaOeste(meeple);
		case NORDESTE:
			posicionarMeepleCampo(meeple);
			break;
		case NOROESTE:
			posicionarMeepleCampo(meeple);
			break;
		case SUDESTE:
			posicionarMeepleCampo(meeple);
			break;
		case SUDOESTE:
			posicionarMeepleCampo(meeple);
			break;
		default:
			break;
		}
		
	}
	private void posicionarMeepleCampo(Meeple meeple) {
		CelulaTabuleiro celulaReferencial = encontrarCelula(celulaInicial, meeple.getReferencia());


		celulaReferencial.setMeeple(meeple);
	}
	private void posicionarMeepleEstradaNorte(Meeple meeple) {
		CelulaTabuleiro celulaReferencial = encontrarCelula(celulaInicial, meeple.getReferencia());
		Tile tile = celulaReferencial.getTile();
		TipoLado lado = tile.getLadoNorte();
		if (lado != TipoLadoCarcassonne.ESTRADA) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Norte do tile "+tile.getId()+" é "+lado.getAbreviacao());
		}


		celulaReferencial.setMeeple(meeple);
	}

	private void posicionarMeepleEstradaLeste(Meeple meeple) {
		CelulaTabuleiro celulaReferencial = encontrarCelula(celulaInicial, meeple.getReferencia());
		Tile tile = celulaReferencial.getTile();
		TipoLado lado = tile.getLadoLeste();
		if (lado != TipoLadoCarcassonne.ESTRADA) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Leste do tile "+tile.getId()+" é "+lado.getAbreviacao());
		}

		celulaReferencial.setMeeple(meeple);
	}

	private void posicionarMeepleEstradaSul(Meeple meeple) {
		CelulaTabuleiro celulaReferencial = encontrarCelula(celulaInicial, meeple.getReferencia());
		Tile tile = celulaReferencial.getTile();
		TipoLado lado = tile.getLadoSul();
		if (lado != TipoLadoCarcassonne.ESTRADA) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Sul do tile "+tile.getId()+" é "+lado.getAbreviacao());
		}
		
		celulaReferencial.setMeeple(meeple);
	}
	
	private void posicionarMeepleEstradaOeste(Meeple meeple) {
		CelulaTabuleiro celulaReferencial = encontrarCelula(celulaInicial, meeple.getReferencia());
		Tile tile = celulaReferencial.getTile();
		TipoLado lado = tile.getLadoOeste();
		if (lado != TipoLadoCarcassonne.ESTRADA) {
			throw new ExcecaoJogo("Impossível posicionar meeple em estrada pois o lado Oeste do tile "+tile.getId()+" é "+lado.getAbreviacao());
		}
		
		celulaReferencial.setMeeple(meeple);
	}
	
	private void celulaOcupada(CelulaTabuleiro celulaReferencia, CelulaTabuleiro[][] tabuleiro, int x, int y,
			String posicao) {

		int xAjustado = x - extremoOeste.getX();
		int yAjustado = y - extremoSul.getY();

		if (xAjustado < 0 || xAjustado > tabuleiro.length - 1) {
			return;
		}

		if (yAjustado < 0 || yAjustado > tabuleiro[0].length - 1) {
			return;
		}

		if (tabuleiro[xAjustado][yAjustado] != null) {
			throw new ExcecaoJogo("A posição " + posicao + " do tile " + celulaReferencia.getTile().getId()
					+ " já está ocupada pelo tile " + tabuleiro[xAjustado][yAjustado].getTile().getId());
		}
	}

	private void verificarTipoLado(Tile tileNovo, Tile tileReferencia, String ladoReferencia,
			TipoLado tipoLadoReferencia, String ladoNovo, TipoLado tipoLadoNovo) {
		if (!tipoLadoReferencia.equals(tipoLadoNovo)) {
			throw new ExcecaoJogo("O lado " + ladoReferencia + " do tile " + tileReferencia.getId() + " ("
					+ tipoLadoReferencia.getAbreviacao() + ") é incompatível com o lado " + ladoNovo + " do tile "
					+ tileNovo.getId() + " (" + tipoLadoNovo.getAbreviacao() + ")");
		}
	}

	// "O lado Leste do tile 45 (Campo) é incompatível com o lado Oeste do tile 19
	// (Cidade)"

	private CelulaTabuleiro encontrarCelula(CelulaTabuleiro celulaAtual, Tile tileReferencia) {
		ArrayList<CelulaTabuleiro> celulasVisitadas = new ArrayList<>();
		return encontrarCelulaInterno(celulaAtual, tileReferencia, null, celulasVisitadas);
	}

	public boolean verificarTilePosicionado(Tile tile) {
		CelulaTabuleiro celulaDuplicada = encontrarCelula(celulaInicial, tile);
		if (celulaDuplicada != null) {
			return true;
		}
		return false;
	}

	private CelulaTabuleiro encontrarCelulaInterno(CelulaTabuleiro celulaAtual, Tile tileReferencia, Lado movimento,
			ArrayList<CelulaTabuleiro> celulasVisitadas) {

		if (celulaAtual == null || celulasVisitadas.contains(celulaAtual)) {
			return null;
		}

		celulasVisitadas.add(celulaAtual);

		if (celulaAtual.getTile().equals(tileReferencia)) {
			return celulaAtual;
		}

		return encontrarNosLados(celulaAtual, tileReferencia, celulasVisitadas);
	}

	private CelulaTabuleiro encontrarNosLados(CelulaTabuleiro celulaAtual, Tile tileReferencia,
			ArrayList<CelulaTabuleiro> celulasVisitadas) {

		CelulaTabuleiro tentativa = encontrarCelulaInterno(celulaAtual.getNorte(), tileReferencia, Lado.NORTE,
				celulasVisitadas);
		if (tentativa != null) {
			return tentativa;
		}

		tentativa = encontrarCelulaInterno(celulaAtual.getLeste(), tileReferencia, Lado.LESTE, celulasVisitadas);
		if (tentativa != null) {
			return tentativa;
		}

		tentativa = encontrarCelulaInterno(celulaAtual.getSul(), tileReferencia, Lado.SUL, celulasVisitadas);
		if (tentativa != null) {
			return tentativa;
		}

		return encontrarCelulaInterno(celulaAtual.getOeste(), tileReferencia, Lado.OESTE, celulasVisitadas);
	}

	@Override
	public String toString() {
		if (extremoLeste == null) {
			return "";
		}

		String s = "";

		CelulaTabuleiro[][] tabuleiro = montarTabuleiro();

		for (int j = tabuleiro[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < tabuleiro.length; i++) {
				if (tabuleiro[i][j] != null) {
					s += tabuleiro[i][j].getTile().toString();
				}
				//s += (tabuleiro[i][j] == null) ? espacoVazio : tabuleiro[i][j].getTile().toString();
			}

			if (j > 0) {
				s += "\n";
			}
		}

		return s;
	}

	private CelulaTabuleiro[][] montarTabuleiro() {
		int largura = extremoLeste.getX() - extremoOeste.getX() + 1;
		int altura = extremoNorte.getY() - extremoSul.getY() + 1;
		CelulaTabuleiro[][] tabuleiro = new CelulaTabuleiro[largura][altura];

		montarTabuleiro(celulaInicial, tabuleiro, new ArrayList<>());

		return tabuleiro;
	}

	private void montarTabuleiro(CelulaTabuleiro celulaAtual, CelulaTabuleiro[][] tabuleiro,
			ArrayList<CelulaTabuleiro> celulasVisitadas) {
		if (celulaAtual == null || celulasVisitadas.contains(celulaAtual)) {
			return;
		}

		celulasVisitadas.add(celulaAtual);

		tabuleiro[celulaAtual.getX() - extremoOeste.getX()][celulaAtual.getY() - extremoSul.getY()] = celulaAtual;
		montarTabuleiro(celulaAtual.getNorte(), tabuleiro, celulasVisitadas);
		montarTabuleiro(celulaAtual.getLeste(), tabuleiro, celulasVisitadas);
		montarTabuleiro(celulaAtual.getSul(), tabuleiro, celulasVisitadas);
		montarTabuleiro(celulaAtual.getOeste(), tabuleiro, celulasVisitadas);
	}

	public String verificarEstrada() {
		if (extremoLeste == null) {
			return "";
		}

		String s = "";
		
		CelulaTabuleiro[][] tabuleiro = montarTabuleiro();

		int t = tabuleiro.length;
		
		for (int j = tabuleiro[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < tabuleiro.length; i++) {
				if (tabuleiro[i][j] != null) {
					s += verificarEstradaTile(tabuleiro[i][j])+ ((i < t-1)?" ":"");
				}
			}
			if (j > 0) {
				s += "\n";
			}
	
		}
		if (s.charAt(s.length()-1) == ' ') {
			s = s.substring(0, s.length()-1);
		}
		return s;

	}

	private String verificarEstradaTile(CelulaTabuleiro celula) {
		Tile tile = celula.getTile();
		ArrayList<String> ladosStr = new ArrayList<String>();
		String s = "";
		if (tile != null) {
			if (tile.getLadoNorte() == TipoLadoCarcassonne.ESTRADA) {
				ladosStr.add("N");
			}
			if (tile.getLadoOeste() == TipoLadoCarcassonne.ESTRADA) {
				ladosStr.add("O");
			}
			if (tile.getLadoSul() == TipoLadoCarcassonne.ESTRADA) {
				ladosStr.add("S");
			}
			if (tile.getLadoLeste() == TipoLadoCarcassonne.ESTRADA) {
				ladosStr.add("L");
			} 

			Meeple meeple = celula.getMeeple();

			if (meeple != null) {
				String lado = meeple.getLado().getAbreviacao();
				int indice = ladosStr.indexOf(lado);
				if (indice > -1) {
					ladosStr.set(indice, meeple.toString());
				}
			}
			s = tile.getId() + ladosStr.toString().replace('[', '(').replace(']', ')').replace(" ", "");
		}

		return s;

	}
	
	public String verificarCampo() {
		if (extremoLeste == null) {
			return "";
		}

		String s = "";

		CelulaTabuleiro[][] tabuleiro = montarTabuleiro();

		for (int j = tabuleiro[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < tabuleiro.length; i++) {
				s += (tabuleiro[i][j] == null) ? "" : verificarCampoTile(tabuleiro[i][j]);
			}

			if (j > 0) {
				s += " ";
			}
		}

		return s;
	}

	private String verificarCampoTile(CelulaTabuleiro celula) {
		TileComVertice tile = (TileComVertice) celula.getTile();
		ArrayList<String> ladosStr = new ArrayList<String>();
		String s = "";
		if (tile != null) {
			if (tile.getLadoNoroeste() == TipoLadoCarcassonne.CAMPO) {
				ladosStr.add("NO");
			}
			if (tile.getLadoNordeste() == TipoLadoCarcassonne.CAMPO) {
				ladosStr.add("NE");
			} 
			if (tile.getLadoSudeste() == TipoLadoCarcassonne.CAMPO) {
				ladosStr.add("SE");
			}
			if (tile.getLadoSudoeste() == TipoLadoCarcassonne.CAMPO) {
				ladosStr.add("SO");
			}
			Meeple meeple = celula.getMeeple();

			if (meeple != null) {
				String lado = meeple.getLado().getAbreviacao();
				int indice = ladosStr.indexOf(lado);
				if (indice > -1) {
					ladosStr.set(indice, meeple.toString());
				}
			}

			s = tile.getId() + ladosStr.toString().replace('[', '(').replace(']', ')').replace(" ", "");
			if (tile.getId().equals("30")) {
				s = tile.getId()+"("+ladosStr.get(0)+","+ladosStr.get(1)+")"+ "\n"+tile.getId()+"("+ladosStr.get(3)+","+ladosStr.get(2)+")";
			}
		}

		return s;

	}
	
	public boolean verificarCeculaInicial(Tile tile) {
		System.out.println(celulaInicial.getTile().equals(tile));
		return celulaInicial.getTile().equals(tile);
	}
}

class CelulaTabuleiro {

	private Tile tile;
	private CelulaTabuleiro norte, sul, leste, oeste;
	private int x, y;
	private Meeple meeplePosicionado = null;
	
	public static CelulaTabuleiro celulaVazia(int x, int y) {
		return new CelulaTabuleiro(null, x, y);
	}

	public CelulaTabuleiro(Tile tile, int x, int y) {
		this.tile = tile;
		this.x = x;
		this.y = y;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Meeple getMeeple() {
		return meeplePosicionado;
	}
	
	public void setMeeple(Meeple meeple) {
		meeplePosicionado = meeple;
	}
	
	public CelulaTabuleiro getNorte() {
		return norte;
	}

	public void setNorte(CelulaTabuleiro norte) {
		this.norte = norte;
	}

	public CelulaTabuleiro getSul() {
		return sul;
	}

	public void setSul(CelulaTabuleiro sul) {
		this.sul = sul;
	}

	public CelulaTabuleiro getLeste() {
		return leste;
	}

	public void setLeste(CelulaTabuleiro leste) {
		this.leste = leste;
	}

	public CelulaTabuleiro getOeste() {
		return oeste;
	}

	public void setOeste(CelulaTabuleiro oeste) {
		this.oeste = oeste;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tile == null) ? 0 : tile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CelulaTabuleiro other = (CelulaTabuleiro) obj;
		if (tile == null) {
			if (other.tile != null)
				return false;
		} else if (!tile.equals(other.tile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (tile == null) {
			return "";
		}

		return tile.getId() + x + y;
	}

}
