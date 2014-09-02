package br.com.padroesdeprojeto.validation;

import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a validação das entradas dos recursos do sistema.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class H2Validation {

	/**
	 * Método para validar a entrada de um número inteiro e verifica se ele se
	 * encaixa no conjunto dos números naturais.
	 * 
	 * @param camp
	 *            Um numero inteiro.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 *             Caso o número não for um numero natural é lançada uma exeção.
	 */
	public static void validaNumNaturais(int camp, String msg)
			throws H2Exception {
		if (camp <= 0) {
			throw new H2Exception(msg);
		}
	}

	/**
	 * Este método Este método usa o método <b>validaNuloVazio<b> para validar
	 * as entradas String segundo as especificações do sistema e lança uma
	 * exceção caso esta não corresponder.
	 * 
	 * @param entrada
	 *            Um valor String.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada não siga as especificações do sistema é
	 *             lançada uma exeção.
	 */
	public static void validaCampos(String entrada, String msg)
			throws H2Exception {
		if (!validaNuloVazio(entrada))
			throw new H2Exception(msg);
	}

	/**
	 * Este método usa o método <b>validaCampos<b> para validar um conjunto de
	 * entradas Strings segundo as especificações do sistema e lança uma exeção
	 * caso alguma destas não corresponder.
	 * 
	 * @param params
	 *            Um array com as entradas.
	 * @param errorMsg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 *             Caso as entradas não siga as especificações do sistema é
	 *             lançada uma exeção.
	 */
	public static void validaParametros(String[] params, String errorMsg)
			throws H2Exception {
		for (String p : params) {
			validaCampos(p, errorMsg);
		}
	}

	/**
	 * Este método usa o método <b>validaObjetos<b> para validara a entrada de
	 * objetos não nulos, e caso não correspondam as regras de negocio do
	 * sistema será lançada uma exceção.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @return true se for válido.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada não siga as especificações do sistema é
	 *             lançada uma exeção.
	 */
	public static boolean validaObjetosNaoNulos(Object entrada, String msg)
			throws H2Exception {
		if (validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	/**
	 * Este método usa o método <b>validaObjetos<b> para validara a entrada de
	 * objetos nulos, e caso não correspondam as regras de negocio do sistema
	 * será lançada uma exceção.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @return true se for válido.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada não siga as especificações do sistema é
	 *             lançada uma exeção.
	 */
	public static boolean validaObjetosNulos(Object entrada, String msg)
			throws H2Exception {
		if (!validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	/**
	 * Este método verifica se algum objeto é nulo ou não.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @return true se for nulo ou false se não for.
	 */
	public static boolean validaObjetos(Object entrada) {
		if (entrada != null)
			return true;
		return false;
	}

	/**
	 * Este método valida uma entrada String numerica e tenta converter para um
	 * inteiro, caso não seja possivel retorna uma exeção.
	 * 
	 * @param entrada
	 *            Uma String numerica para a conversão.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @return O int covertido.
	 * 
	 * @throws H2Exception
	 *             Caso não seja possivel converter a String em numero será
	 *             lançada uma exceção.
	 */
	public static int validaEConveteInt(String entrada, String msg)
			throws H2Exception {
		try {
			int value = Integer.parseInt(entrada);
			return value;
		} catch (NumberFormatException e) {
			throw new H2Exception(msg);
		}
	}

	/**
	 * Este método verifica se a entrada String é nula ou vazia.
	 * 
	 * @param entrada
	 *            Uma entrada String qualquer.
	 * 
	 * @return true se não for nula nem vazia e false se for.
	 */
	private static boolean validaNuloVazio(String entrada) {
		if (!("".equals(entrada)) && (entrada != null))
			return true;
		return false;
	}

	/**
	 * Este método verifica se uma hora em int está dentro do intervalo de 0 e
	 * 23 horas.
	 * 
	 * @param hora
	 *            Um inteiro representando a hora.
	 * @param msg
	 *            Mensagem de exceção.
	 * @throws H2Exception
	 *             Caso a entrada não esteja no intervalo de 0 a 23 horas será
	 *             lançada uma exceção.
	 */
	public static void validaHora(int hora, String msg) throws H2Exception {
		if (hora < 0 || hora > 23)
			throw new H2Exception(msg);
	}

	/**
	 * Este método usa o método <b>validaEntradaHora<b> para validar um conjunto
	 * de horas.
	 * 
	 * @param horas
	 *            Um array com as horas (int).
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 */
	public static void validaHoras(int[] horas, String msg) throws H2Exception {

		validaEntradaHora(horas[0], horas[1], msg);

		for (int h : horas) {
			validaHora(h, msg);
		}
	}

	/**
	 * Verifica se a entrada da hora inicial é maior que a da hora final.
	 * 
	 * @param i
	 *            Um inteiro que representa a hora incial.
	 * @param f
	 *            Um inteiro que representa a hora final.
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 *             Se a hora inicial for maior que a hora final é labnçada uma
	 *             exeção.
	 */
	private static void validaEntradaHora(int i, int f, String msg)
			throws H2Exception {
		if (f <= i) {
			throw new H2Exception(msg);
		}
	}

	/**
	 * Este método verifica se a entrada String do dia da semana corresponde aos
	 * dias validos especificados pelo sistema.
	 * 
	 * @param diaSemana
	 *            Uma String informando o dia da Semana (Segunda, Terça, Quarta,
	 *            Quinta ou Sexta).
	 * @param msg
	 *            Mensagem de exceção.
	 * 
	 * @throws H2Exception
	 *             Se o dia informado não correspoder aos especificados é
	 *             lançada uma exeção.
	 */
	public static void validaDiaSemana(String diaSemana, String msg)
			throws H2Exception {

		DiasSemana[] diasSemanas = DiasSemana.values();

		for (DiasSemana ds : diasSemanas) {
			if (diaSemana.equals(ds.getValor())) {
				return;
			}
		}
		throw new H2Exception(msg);
	}

	/**
	 * Este método verifica se existe algum conflito nos intervalos de horas
	 * passadas.
	 * 
	 * @param h1Inicio
	 *            Um inteiro representando a hora inicial do primeiro horario.
	 * @param h1Fim
	 *            Um inteiro representando a hora final do primeiro horario.
	 * @param h2Inicio
	 *            Um inteiro representando a hora inicial do segundo horario.
	 * @param h2Fim
	 *            Um inteiro representando a hora final do segundo horario.
	 *            
	 * @return true se houver conflito ou false se não houver.
	 */
	public static boolean validaConflitoHorario(int h1Inicio, int h1Fim,
			int h2Inicio, int h2Fim) {
		for (int i = h2Inicio; i <= h2Fim; i++) {
			if (i >= h1Inicio && i <= h1Fim) {
				return true;
			}
		}
		return false;
	}
}