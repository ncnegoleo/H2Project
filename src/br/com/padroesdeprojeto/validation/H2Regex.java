package br.com.padroesdeprojeto.validation;

/**
 * Esta enum serve para a validar parametros especificos dos recursos 
 * do sistema.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public enum H2Regex {
	
	// Expressões regulares para testar os valores das entradas.
	NOME_PROFESSOR("(([^\\p{Punct}0-9]{3,}))+" +
			"((\\s[a-z]{1,2})*(\\s([^\\p{Punct}0-9]{3,})*)*)*"), 
	MATRICULA("[0-9]{1,}"), 
	PERIODO("([1-9]{1})|([0-9]{4}\\.[1-9]{1})"), 
	DEFAUT_NAME("([^\\p{Punct}]{3,}[.]*)+((\\s[a-zA-Z0-9]{1,2}[.]*)*" +
			"(\\s([^\\p{Punct}0-9]{3,}))*)*"),
	BLOCO("[A-Z]{1}"),
	SGL_PERIODO("[A-Z]+_[1-9]{1}");
	
	private final String valor;

	H2Regex(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
}
