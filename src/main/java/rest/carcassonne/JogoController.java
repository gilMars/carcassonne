package rest.carcassonne;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static br.ufpb.dcx.aps.carcassone.TilesJogoBase.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.ufpb.dcx.aps.carcassone.BolsaDeTiles;
import br.ufpb.dcx.aps.carcassone.BolsaTileConcreta;
import br.ufpb.dcx.aps.carcassone.Cor;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.Partida;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

@RestController
@RequestMapping("/partida")
public class JogoController {
	
	Services sv = new Services();
	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> criarPartida(@RequestBody Cor... sequencia){
    	return sv.criarPartida(sequencia);
    }
    
	@RequestMapping("/relatorio")
	public ResponseEntity<RelatorioPartida> relatorioPartida() {
		return sv.relatorioPartida();
	}

	@RequestMapping("relatorio/turno")
	public ResponseEntity<RelatorioTurno> relatorioTurno() {
		return sv.relatorioTurno();
	}

	@RequestMapping(value = "/girartile", method = RequestMethod.PUT)
	public ResponseEntity<Tile> girarTile() {
		return sv.girarTile();
	}

	@RequestMapping("/tile")
	public ResponseEntity<Tile> pegarTile() {
		return sv.pegarTile();
	}
	
	@RequestMapping(value = "/tile", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<Tile> posicionarTile(@RequestBody Tile tile /*@RequestParam(value = "lado") Lado ladoTile*/) {
		return new ResponseEntity<Tile>(tile, HttpStatus.OK);
	}
	
	@RequestMapping("/jogador")
	public ResponseEntity<Jogador[]> recuperarJogadores() {
		return sv.resgatarJogadores();
	}

	@RequestMapping(value = "jogador/{cor}", method = RequestMethod.GET)
	public ResponseEntity<Jogador> recuperarJogador(@PathVariable(value = "cor") Cor cor) {
		return sv.resgatarJogador(cor);
	}

}
