package rest.carcassonne;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import br.ufpb.dcx.aps.carcassone.Cor;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;

@Api(value = "partida")
@RestController
@RequestMapping("/partida")
public class JogoController {
	
	Services sv = new Services();
	
	@ApiOperation(value = "Cria a partida utilizando o jogadores passados por um objeto JSON")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> criarPartida(@RequestBody Cor... sequencia){
    	//return sv.criarPartida(sequencia);
		return new ResponseEntity<>(HttpStatus.OK);
    }
    
	@RequestMapping(value = "/relatorio", method = RequestMethod.GET)
	public ResponseEntity<RelatorioPartida> relatorioPartida() {
		return sv.relatorioPartida();
	}

	@RequestMapping(value = "relatorio/turno", method = RequestMethod.GET)
	public ResponseEntity<RelatorioTurno> relatorioTurno() {
		return sv.relatorioTurno();
	}

	@RequestMapping(value = "/girartile", method = RequestMethod.PUT)
	public ResponseEntity<Tile> girarTile() {
		return sv.girarTile();
	}

	@RequestMapping(value = "/tile", method = RequestMethod.GET)
	public ResponseEntity<Tile> pegarTile() {
		return sv.pegarTile();
	}
	
	@RequestMapping(value = "/tile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Tile> posicionarTile(@RequestBody Tile tile /*@RequestParam(value = "lado") Lado ladoTile*/) {
		return new ResponseEntity<Tile>(tile, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/jogador", method = RequestMethod.GET)
	public ResponseEntity<Jogador[]> recuperarJogadores() {
		return sv.resgatarJogadores();
	}

	@RequestMapping(value = "jogador/{cor}", method = RequestMethod.GET)
	public ResponseEntity<Jogador> recuperarJogador(@PathVariable(value = "cor") Cor cor) {
		return sv.resgatarJogador(cor);
	}

}
