package br.ufpb.dcx.aps.carcassone;

public class Jogador {
	private Cor corJogador;
	private int pontos = 0;
	private int meeples = 7;
	
	public Jogador(Cor cor) {
		corJogador = cor;
	}
	
	public Cor getCor() {
		return corJogador;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	public int quantidadeMeeples() {
		return meeples;
	}
	
	@Override
	public String toString() {
		return corJogador+"("+pontos+","+meeples+")";
	}
}
