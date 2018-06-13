package rest.carcassonne;

import br.ufpb.dcx.aps.carcassone.Estado;

public class Relatorio {

	Estado status;
	
	Relatorio(Estado status) {
		this.status = status;
	}
	
	public Estado getEstado() {
		return status;
	}
}
