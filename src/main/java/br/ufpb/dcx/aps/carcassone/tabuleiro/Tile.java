package br.ufpb.dcx.aps.carcassone.tabuleiro;

import java.util.Arrays;
import java.util.List;

import br.ufpb.dcx.aps.carcassone.Lado;

public class Tile {

	private String id;
	private Lado orientacao;
	private TipoTile tipoTile;
	private TipoLado ladoNorte;
	private TipoLado ladoLeste;
	private TipoLado ladoSul;
	private TipoLado ladoOeste;
	
	public Tile(String id, TipoTile tipoTile) {
		this.id = id;
		this.tipoTile = tipoTile;
		this.orientacao = Lado.NORTE;
		setLadoLeste(tipoTile.getLadoLeste());
		setLadoSul(tipoTile.getLadoSul());
		setLadoOeste(tipoTile.getLadoOeste());
		setLadoNorte(tipoTile.getLadoNorte());
	}

	public List<TipoLado> getLados() {
		return Arrays.asList(ladoNorte, ladoLeste, ladoSul, ladoOeste);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoTile getTipoTile() {
		return tipoTile;
	}

	public Lado getOrientacao() {
		return orientacao;
	}

	public TipoLado getLadoNorte() {
		return ladoNorte;
	}

	private void setLadoNorte(TipoLado ladoNorte) {
		this.ladoNorte = ladoNorte;
	}

	public TipoLado getLadoLeste() {
		return ladoLeste;
	}

	private void setLadoLeste(TipoLado ladoLeste) {
		this.ladoLeste = ladoLeste;
	}

	public TipoLado getLadoSul() {
		return ladoSul;
	}

	private void setLadoSul(TipoLado ladoSul) {
		this.ladoSul = ladoSul;
	}

	public TipoLado getLadoOeste() {
		return ladoOeste;
	}

	private void setLadoOeste(TipoLado ladoOeste) {
		this.ladoOeste = ladoOeste;
	}
	
	public void girar() {
		switch (orientacao) {
		case NORTE:
			orientacao = Lado.LESTE;
			break;
		case LESTE:
			orientacao = Lado.SUL;
			break;
		case SUL:
			orientacao = Lado.OESTE;
			break;
		case OESTE:
			orientacao = Lado.NORTE;
			break;
		}

		TipoLado temp = ladoNorte;
		ladoNorte = ladoOeste;
		ladoOeste = ladoSul;
		ladoSul = ladoLeste;
		ladoLeste = temp;
		
	}
	
	public void reset() {
		while (orientacao != Lado.NORTE) {
			girar();
		}
	}

	public String toString() {
		return getId() + getOrientacao().getAbreviacao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Tile other = (Tile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
