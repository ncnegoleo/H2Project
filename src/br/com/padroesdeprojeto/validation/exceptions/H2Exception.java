package br.com.padroesdeprojeto.validation.exceptions;

/**
 * Esta classe lança as exceções das interações do usuario com o sistema
 * 
 * @author Leonardo Soares.
 * 
 */

@SuppressWarnings("serial")
public class H2Exception extends Exception {

	public H2Exception(String msg) {
		super(msg);
	}

}
