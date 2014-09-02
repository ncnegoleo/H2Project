package br.com.padroesdeprojeto.validation;

import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a valida��o das entradas dos recursos do sistema.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class H2Validation {

	/**
	 * M�todo para validar a entrada de um n�mero inteiro e verifica se ele se
	 * encaixa no conjunto dos n�meros naturais.
	 * 
	 * @param camp
	 *            Um numero inteiro.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @throws H2Exception
	 *             Caso o n�mero n�o for um numero natural � lan�ada uma exe��o.
	 */
	public static void validaNumNaturais(int camp, String msg)
			throws H2Exception {
		if (camp <= 0) {
			throw new H2Exception(msg);
		}
	}

	/**
	 * Este m�todo Este m�todo usa o m�todo <b>validaNuloVazio<b> para validar
	 * as entradas String segundo as especifica��es do sistema e lan�a uma
	 * exce��o caso esta n�o corresponder.
	 * 
	 * @param entrada
	 *            Um valor String.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada n�o siga as especifica��es do sistema �
	 *             lan�ada uma exe��o.
	 */
	public static void validaCampos(String entrada, String msg)
			throws H2Exception {
		if (!validaNuloVazio(entrada))
			throw new H2Exception(msg);
	}

	/**
	 * Este m�todo usa o m�todo <b>validaCampos<b> para validar um conjunto de
	 * entradas Strings segundo as especifica��es do sistema e lan�a uma exe��o
	 * caso alguma destas n�o corresponder.
	 * 
	 * @param params
	 *            Um array com as entradas.
	 * @param errorMsg
	 *            Mensagem de exce��o.
	 * 
	 * @throws H2Exception
	 *             Caso as entradas n�o siga as especifica��es do sistema �
	 *             lan�ada uma exe��o.
	 */
	public static void validaParametros(String[] params, String errorMsg)
			throws H2Exception {
		for (String p : params) {
			validaCampos(p, errorMsg);
		}
	}

	/**
	 * Este m�todo usa o m�todo <b>validaObjetos<b> para validara a entrada de
	 * objetos n�o nulos, e caso n�o correspondam as regras de negocio do
	 * sistema ser� lan�ada uma exce��o.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @return true se for v�lido.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada n�o siga as especifica��es do sistema �
	 *             lan�ada uma exe��o.
	 */
	public static boolean validaObjetosNaoNulos(Object entrada, String msg)
			throws H2Exception {
		if (validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	/**
	 * Este m�todo usa o m�todo <b>validaObjetos<b> para validara a entrada de
	 * objetos nulos, e caso n�o correspondam as regras de negocio do sistema
	 * ser� lan�ada uma exce��o.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @return true se for v�lido.
	 * 
	 * @throws H2Exception
	 *             Caso a entrada n�o siga as especifica��es do sistema �
	 *             lan�ada uma exe��o.
	 */
	public static boolean validaObjetosNulos(Object entrada, String msg)
			throws H2Exception {
		if (!validaObjetos(entrada))
			return true;
		throw new H2Exception(msg);
	}

	/**
	 * Este m�todo verifica se algum objeto � nulo ou n�o.
	 * 
	 * @param entrada
	 *            Um objeto qualquer.
	 * @return true se for nulo ou false se n�o for.
	 */
	public static boolean validaObjetos(Object entrada) {
		if (entrada != null)
			return true;
		return false;
	}

	/**
	 * Este m�todo valida uma entrada String numerica e tenta converter para um
	 * inteiro, caso n�o seja possivel retorna uma exe��o.
	 * 
	 * @param entrada
	 *            Uma String numerica para a convers�o.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @return O int covertido.
	 * 
	 * @throws H2Exception
	 *             Caso n�o seja possivel converter a String em numero ser�
	 *             lan�ada uma exce��o.
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
	 * Este m�todo verifica se a entrada String � nula ou vazia.
	 * 
	 * @param entrada
	 *            Uma entrada String qualquer.
	 * 
	 * @return true se n�o for nula nem vazia e false se for.
	 */
	private static boolean validaNuloVazio(String entrada) {
		if (!("".equals(entrada)) && (entrada != null))
			return true;
		return false;
	}

	/**
	 * Este m�todo verifica se uma hora em int est� dentro do intervalo de 0 e
	 * 23 horas.
	 * 
	 * @param hora
	 *            Um inteiro representando a hora.
	 * @param msg
	 *            Mensagem de exce��o.
	 * @throws H2Exception
	 *             Caso a entrada n�o esteja no intervalo de 0 a 23 horas ser�
	 *             lan�ada uma exce��o.
	 */
	public static void validaHora(int hora, String msg) throws H2Exception {
		if (hora < 0 || hora > 23)
			throw new H2Exception(msg);
	}

	/**
	 * Este m�todo usa o m�todo <b>validaEntradaHora<b> para validar um conjunto
	 * de horas.
	 * 
	 * @param horas
	 *            Um array com as horas (int).
	 * @param msg
	 *            Mensagem de exce��o.
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
	 * Verifica se a entrada da hora inicial � maior que a da hora final.
	 * 
	 * @param i
	 *            Um inteiro que representa a hora incial.
	 * @param f
	 *            Um inteiro que representa a hora final.
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @throws H2Exception
	 *             Se a hora inicial for maior que a hora final � labn�ada uma
	 *             exe��o.
	 */
	private static void validaEntradaHora(int i, int f, String msg)
			throws H2Exception {
		if (f <= i) {
			throw new H2Exception(msg);
		}
	}

	/**
	 * Este m�todo verifica se a entrada String do dia da semana corresponde aos
	 * dias validos especificados pelo sistema.
	 * 
	 * @param diaSemana
	 *            Uma String informando o dia da Semana (Segunda, Ter�a, Quarta,
	 *            Quinta ou Sexta).
	 * @param msg
	 *            Mensagem de exce��o.
	 * 
	 * @throws H2Exception
	 *             Se o dia informado n�o correspoder aos especificados �
	 *             lan�ada uma exe��o.
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
	 * Este m�todo verifica se existe algum conflito nos intervalos de horas
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
	 * @return true se houver conflito ou false se n�o houver.
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