package br.ufpb.dcx.aps.carcassone;

import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

public class Meeple {
	private Lado lado;
	private Cor jogador;
	private Tile referencia;
	
	public Meeple(Lado lado, Cor jogador, Tile referencia) {
		this.lado = lado;
		this.jogador = jogador;
		this.referencia = referencia;
	}
	
	public Lado getLado() {
		return lado;
	}
	
	public Cor getCor() {
		return jogador;
	}
	
	public Tile getReferencia() {
		return referencia;
	}
	
	@Override
	public String toString() {
		return lado.getAbreviacao()+"-"+jogador;
	}
	
}
