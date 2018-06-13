package br.ufpb.dcx.aps.carcassone;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Jogador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Cor cor;
	private Integer pontos;
	private Integer meeples;

	@JsonCreator
	public Jogador(@JsonProperty("cor") Cor cor, @JsonProperty("pontos")Integer pontos, @JsonProperty("meeples") Integer meeples) {
		this.cor = cor;
		this.pontos = pontos;
		this.meeples = meeples;
	}

	public Jogador(Cor cor) {
		this(cor,0,7);
	}
	
	public Cor getCor() {
		return cor;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public int getMeeples() {
		return meeples;
	}
	
	public void decMeeples() {
		--meeples;
	}
	
	public void reset() {
		meeples = 7;
		pontos = 0;
	}
	
	public String toString() {
		return cor+"("+pontos+","+meeples+")";
	}
}
