package br.ufpb.dcx.aps.carcassone.teste;

import org.junit.Assert;
import org.hamcrest.CoreMatchers;

public class Assertiva {

	public static Assertiva ocorreExcecao(ExceptionThrower expressao) {
		try {
			expressao.throwException();
		} catch (Throwable caught) {
			return new Assertiva(caught);
		}
		throw new ErroAssertivaExcecaoNaoLancada();
	}

	private final Throwable capturado;

	public Assertiva(Throwable capturado) {
        this.capturado = capturado;
    }

	@SuppressWarnings("unchecked")
	public Assertiva tipoExcecao(Class<? extends Throwable> exceptionClass) {
		Assert.assertThat(capturado, CoreMatchers.instanceOf((Class<Throwable>) exceptionClass));
		return this;
	}

	public Assertiva mensagem(String expectedMessage) {
		Assert.assertThat(capturado.getMessage(), CoreMatchers.equalTo(expectedMessage));
		return this;
	}

	public Assertiva semCausa() {
		Assert.assertThat(capturado.getCause(), CoreMatchers.nullValue());
		return this;
	}

	@SuppressWarnings("unchecked")
	public Assertiva tipoCausa(Class<? extends Throwable> exceptionClass) {
		Assert.assertThat(capturado.getCause(), CoreMatchers.notNullValue());
		Assert.assertThat(capturado.getCause(), CoreMatchers.instanceOf((Class<Throwable>) exceptionClass));
		return this;
	}
}
