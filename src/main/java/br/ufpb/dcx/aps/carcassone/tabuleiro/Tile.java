package br.ufpb.dcx.aps.carcassone.tabuleiro;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.ufpb.dcx.aps.carcassone.Lado;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tile {

	private String id;
	private Lado orientacao;
	private TipoTile tipoTile;
	@JsonIgnore private TipoLado ladoNorte;
	@JsonIgnore private TipoLado ladoLeste;
	@JsonIgnore private TipoLado ladoSul;
	@JsonIgnore private TipoLado ladoOeste;
	
	@JsonIgnore
	//@JsonCreator
	public Tile(@JsonProperty("id") String id, @JsonProperty("tipoTile")  TipoTile tipoTile) {
		this.id = id;
		this.tipoTile = tipoTile;
		this.orientacao = Lado.NORTE;
		setLadoLeste(tipoTile.getLadoLeste());
		setLadoSul(tipoTile.getLadoSul());
		setLadoOeste(tipoTile.getLadoOeste());
		setLadoNorte(tipoTile.getLadoNorte());
	}
	
	@JsonCreator
	public Tile(@JsonProperty("id") String id, @JsonProperty("orientacao") Lado orientacao, @JsonProperty("tipoTile") TipoTile tipoTile) {
		this(id,tipoTile);
		this.orientacao = orientacao;
	}

	@JsonIgnore
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
