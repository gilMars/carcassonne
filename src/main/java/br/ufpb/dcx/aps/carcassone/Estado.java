package br.ufpb.dcx.aps.carcassone;

public enum Estado {
	
	P_ANDAMENTO("Em_Andamento"), T_INICIO("Início_Turno"), T_ANDAMENTO("Tile_Posicionado"), P_FIM("Partida_Finalizada"), M_P("Meeple_Posicionado");
	
	final private String nomeEstado;

	Estado(String estado) {
		this.nomeEstado = estado;
	}
	
	public String getEstado() {
		return nomeEstado;
	}
	
	@Override
	public String toString() {
		return nomeEstado;
	}
	
}
