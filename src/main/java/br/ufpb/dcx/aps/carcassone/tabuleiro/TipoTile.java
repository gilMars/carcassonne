package br.ufpb.dcx.aps.carcassone.tabuleiro;

public class TipoTile {

	private TipoLado ladoNorte;
	private TipoLado ladoLeste;
	private TipoLado ladoSul;
	private TipoLado ladoOeste;

	public TipoTile() {
	}

	public TipoTile(TipoLado ladoNorte, TipoLado ladoLeste, TipoLado ladoSul, TipoLado ladoOeste) {
		this.ladoNorte = ladoNorte;
		this.ladoLeste = ladoLeste;
		this.ladoSul = ladoSul;
		this.ladoOeste = ladoOeste;
		
	}
	
	public TipoLado getLadoNorte() {
		return ladoNorte;
	}

	public void setLadoNorte(TipoLado ladoNorte) {
		this.ladoNorte = ladoNorte;
	}

	public TipoLado getLadoLeste() {
		return ladoLeste;
	}

	public void setLadoLeste(TipoLado ladoLeste) {
		this.ladoLeste = ladoLeste;
	}

	public TipoLado getLadoSul() {
		return ladoSul;
	}

	public void setLadoSul(TipoLado ladoSul) {
		this.ladoSul = ladoSul;
	}

	public TipoLado getLadoOeste() {
		return ladoOeste;
	}

	public void setLadoOeste(TipoLado ladoOeste) {
		this.ladoOeste = ladoOeste;
	}
}
