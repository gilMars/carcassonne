package br.ufpb.dcx.aps.carcassone;

public enum Vertice {
	NORDESTE("NE"), SUDESTE("SE"), SUDOESTE("SO"), NOROESTE("NO");
	
	private final String abreviacao;

	Vertice(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	
    public String getAbreviacao() { 
    		return abreviacao; 
    	}
}
