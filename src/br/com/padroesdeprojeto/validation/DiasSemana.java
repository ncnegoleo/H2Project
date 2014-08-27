package br.com.padroesdeprojeto.validation;

public enum DiasSemana {

	// constantes
	SEGUNDA("Segunda"), 
	TERCA("Terça"),
	QUARTA("Quarta"),
	QUINTA("Quinta"),
	SEXTA("Sexta");

	private final String valor;

	DiasSemana(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
