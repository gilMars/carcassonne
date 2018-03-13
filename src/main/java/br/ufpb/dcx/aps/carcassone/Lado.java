package br.ufpb.dcx.aps.carcassone;

public enum Lado {
	NORTE("N"), LESTE("L"), SUL("S"), OESTE("O");
	
	private final String abreviacao;

	Lado(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
    public String getAbreviacao() { 
    		return abreviacao; 
    	}

}
