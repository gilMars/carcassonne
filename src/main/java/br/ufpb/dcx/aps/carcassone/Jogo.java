package br.ufpb.dcx.aps.carcassone;
import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
public class Jogo {
	
	private Tile proximoTile;
	static int indice = 0;
	private BolsaDeTiles tiles;
	private TabuleiroFlexivel tabuleiro = new TabuleiroFlexivel("");
	private boolean iniciado = false;
	private String status = "Início" ;
	private Cor[] jogadores;
	
	public Jogo(BolsaDeTiles tiles) {
		this.tiles = tiles;
	}

	public Jogo iniciarPartida(Cor... sequencia) {
		if (iniciado == true) {
			throw new ExcecaoJogo("Não pode iniciar uma partida enquanto a partida anterior não for finalizada");
		}
		if (sequencia.length < 2) {
			throw new ExcecaoJogo("Cada partida deve ter uma sequência de pelo menos dois jogadores");
		}
		
		for (int i = 0; i < sequencia.length - 1; ++i) {
			for (int j = i+1; j < sequencia.length; ++j) {
				if (sequencia[i] == sequencia[j]) {
					throw new ExcecaoJogo("Não pode haver repetição de cores na sequência de jogadores");
				}
			}
		}
		jogadores = sequencia;
		pegarProximoTile();
		iniciado = true;
		return this;
	}

	public Jogo iniciarPartida() {
		throw new ExcecaoJogo("Cada partida deve ter uma sequência de pelo menos dois jogadores");
	}
	public String relatorioPartida() {
		if (iniciado == false) {
			throw new ExcecaoJogo("Partida não iniciada");
		}
	String sequencia = "";	
	for (int i = 0 ;i < jogadores.length-1; i++) {
		sequencia+=jogadores[i].toString()+", ";
	}
	sequencia+=jogadores[jogadores.length-1];
	if(status.equals("Fim")){
		jogadores[0] = null;
		
	}
		
	String relatorio = "Status: " + status + "\nJogadores: " + sequencia + "\nTabuleiro: "
	+ tabuleiro+ "\nJogador da rodada: " + jogadores[0] + "\nPróximo tile: "
			+ proximoTile;
	
		return relatorio;
	}
	
	
	
	public Jogo girarTile() {
		if(proximoTile != null){
			proximoTile.girar();			
		}
		return this;
	}

	public Jogo posicionarInicial() {
		if (iniciado == false) {
			throw new ExcecaoJogo("O tile inicial não pode ser posicionado antes de iniciar a partida");
		}
		tabuleiro.adicionarPrimeiroTile(proximoTile);
		proximoTile = null;
		status = "Tile";
		return this;
	}

	private void pegarProximoTile() {
		proximoTile = tiles.pegar();
		proximoTile.reset();	
	}

	public Jogo finalizarRodada() {
		if(status.equals("Início")){
			pegarProximoTile();
			
		}else{
			proximoTile = null;
			status = "Fim";
		}
		return this;
	}

	public Jogo posicionarTile(Tile tileReferencia, Lado ladoTileReferencia) {
		status = "Tile";
		tabuleiro.posicionar(tileReferencia, ladoTileReferencia, proximoTile);
		return this;		
	}
	
	public Jogo posicionarMeepleEstrada(Lado lado) {
		return this;
	}
	
	public Jogo posicionarMeepleCampo(Vertice vertice) {
		return this;
	}
	
	public Jogo posicionarMeepleCidade(Lado lado) {
		return this;
	}
	
	public Jogo posicionarMeepleMosteiro() {
		return this;
	}
	
	public String getEstradas() {
		return null;
	}
	
	public String getCampos() {
		return null;
	}
	
	public String getCidades() {
		return null;
	}

	public String getMosteiros() {
		return null;
	}
}
