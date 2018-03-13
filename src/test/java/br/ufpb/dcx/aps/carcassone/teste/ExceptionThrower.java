package br.ufpb.dcx.aps.carcassone.teste;

@FunctionalInterface
public interface ExceptionThrower {
	void throwException() throws Throwable;
}
