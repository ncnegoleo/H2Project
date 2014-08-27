package br.com.padroesdeprojeto.validation;

import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a validação das entradas dos recursos do sistema.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */

public class H2Validation {

	public static boolean validaNumNaturais(int camp, String msg)
			throws H2Exception {
		if (camp > 0) {
			return true;
		} else {
			throw new H2Exception(msg);
		}
	}

	public static boolean validaCampos(String entrada, String msg)
			throws H2Exception {
		if (validaNuloVazio(entrada))
			return true;
		throw new H2Exception(msg);
	}

	public static void validaParametros(String[] params, String errorMsg)
			throws H2Exception {
		for (String p : params) {
			validaCampos(p, errorMsg);
		}
	}

	public static boolean validaObjetosNaoNulos(Object entrada, String msg)
			throws H2Exception {
		if (validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	public static boolean validaObjetosNulos(Object entrada, String msg)
			throws H2Exception {
		if (!validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	public static boolean validaObjetos(Object entrada) throws H2Exception {
		if (entrada != null)
			return true;
		return false;

	}

	public static int validaEConveteInt(String entrada, String msg)
			throws H2Exception {
		try {
			int value = Integer.parseInt(entrada);
			return value;
		} catch (NumberFormatException e) {
			throw new H2Exception(msg);
		}
	}

	private static boolean validaNuloVazio(String entrada) {
		if (!("".equals(entrada)) && (entrada != null))
			return true;
		return false;
	}

	public static void validaHora(int hora, String msg) throws H2Exception {
		if (hora < 0 || hora > 23)
			throw new H2Exception(msg);
	}
	
	public static void validaHoras(int[] horas, String msg) throws H2Exception {
		for (int h : horas) {
			validaHora(h, msg);
		}
	}

	public static void validaDiaSemana(String diaSemana, String msg) throws H2Exception {
		
		DiasSemana[] diasSemanas = DiasSemana.values();
		
		for (DiasSemana ds : diasSemanas) {
			if (diaSemana.equals(ds.getValor())) {
				return;
			}
		}
		throw new H2Exception(msg);
	}
}
