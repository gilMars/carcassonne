package rest.carcassonne;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import br.ufpb.dcx.aps.carcassone.Cor;
import br.ufpb.dcx.aps.carcassone.Jogador;
import br.ufpb.dcx.aps.carcassone.Lado;
import br.ufpb.dcx.aps.carcassone.tabuleiro.TabuleiroFlexivel;
import br.ufpb.dcx.aps.carcassone.tabuleiro.Tile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "/partida", description = "Gerenciada da partida do jogo Carcassonne")
@RestController
@RequestMapping("/partida")
public class JogoController {
	
	Services sv = new Services();
	
	@ApiOperation("Cria a partida passando um array de jogadores")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> criarPartida(@ApiParam(value = "Deve passar um array com as cores dos jogadores")@RequestBody Cor... sequencia){
    	return sv.criarPartida(sequencia);
    }
    
	@ApiOperation("Retorna o relatório da partida")
	@RequestMapping(value = "/relatorio", method = RequestMethod.GET)
	public ResponseEntity<RelatorioPartida> relatorioPartida() {
		return sv.relatorioPartida();
	}

	@ApiOperation("Retorna o relatório do turno")
	@RequestMapping(value = "relatorio/turno", method = RequestMethod.GET)
	public ResponseEntity<RelatorioTurno> relatorioTurno() {
		return sv.relatorioTurno();
	}

	@ApiOperation("Gira o tile atual")
	@RequestMapping(value = "/girartile", method = RequestMethod.PUT)
	public ResponseEntity<Tile> girarTile() {
		return sv.girarTile();
	}

	@ApiOperation("Retorna o tile atual")
	@RequestMapping(value = "/tile", method = RequestMethod.GET)
	public ResponseEntity<Tile> pegarTile() {
		return sv.pegarTile();
	}
	
	@ApiOperation("Posiciona o tile atual no tabuleiro")
	@RequestMapping(value = "/tile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> posicionarTile(@RequestBody Tile tileReferencia, @RequestParam(value = "ladoReferencia") Lado ladoReferencia) {
		return sv.posicionarTile(tileReferencia,ladoReferencia);
	}
	
	@ApiOperation("Retorna todos os jogadores participantes da partida")
	@RequestMapping(value = "/jogador", method = RequestMethod.GET)
	public ResponseEntity<Jogador[]> recuperarJogadores() {
		return sv.resgatarJogadores();
	}

	@ApiOperation("Retorna as informações de um jogador específico")
	@RequestMapping(value = "jogador/{cor}", method = RequestMethod.GET)
	public ResponseEntity<Jogador> recuperarJogador(@PathVariable(value = "cor") Cor cor) {
		return sv.resgatarJogador(cor);
	}
	
	@RequestMapping(value = "/tabuleiro", method = RequestMethod.GET)
	public ResponseEntity<TabuleiroFlexivel> getTabuleiro() {
		return sv.getTabuleiro();
	}

}
