package br.ufpb.dcx.aps.carcassone.tabuleiro;

import java.util.ArrayList;

import br.ufpb.dcx.aps.carcassone.ExcecaoJogo;
import br.ufpb.dcx.aps.carcassone.Lado;

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

		CelulaTabuleiro celulaDuplicada = encontrarCelula(celulaInicial, novoTile);

		if (celulaDuplicada != null) {
			throw new ExcecaoJogo("O tile " + novoTile.getId() + " já foi posicionado no tabuleiro");
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
				s += (tabuleiro[i][j] == null) ? espacoVazio : tabuleiro[i][j].getTile().toString();
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
}

class CelulaTabuleiro {

	private Tile tile;
	private CelulaTabuleiro norte, sul, leste, oeste;
	private int x, y;

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
