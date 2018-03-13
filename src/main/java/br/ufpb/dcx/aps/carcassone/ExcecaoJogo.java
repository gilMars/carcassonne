package br.ufpb.dcx.aps.carcassone;

@SuppressWarnings("serial")
public class ExcecaoJogo extends RuntimeException {

	public ExcecaoJogo() {
	}

	public ExcecaoJogo(String message) {
		super(message);
	}

	public ExcecaoJogo(Throwable cause) {
		super(cause);
	}

	public ExcecaoJogo(String message, Throwable cause) {
		super(message, cause);
	}

}
