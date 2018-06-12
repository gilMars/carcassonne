package rest.carcassonne;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.ufpb.dcx.aps.carcassone.Partida;

@RestController
@RequestMapping("/partida")
public class JogoController {
	
	JogoService servico = new JogoService();
	
	@RequestMapping("")
	public Partida getPartida() {
		return servico.getPartida();
	}

}
