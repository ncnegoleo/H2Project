package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a manipula��o dos recursos de period.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoPeriodo {

	/**
	 * Este m�todo serve controlador para a adi��o de novos periodos no sistema.
	 * 
	 * @param nomePeriodo
	 *            Nome do periodo no formato "2013.1"
	 * @param sigla
	 *            A sigla do curso do periodo que o periodo pertence.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o nome do periodo ou curso cadastrados na base de dados
	 */
	public void addPeriodo(String nomePeriodo, String sigla) throws H2Exception {

		Periodo periodo = new Periodo();

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		periodo.setNomePeriodo(nomePeriodo);

		// Verifica se existe o curso cadastrado
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(sigla),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se j� existe o perido cadastrado no mesmo curso.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createPeriodoDaoIF().getPeriodoByName(nomePeriodo, sigla),
				H2ErrorMessages.PERIODOJACADASTRADO.getValor())) {

			// insere um novo periodo no banco
			AbstractFactoryDao.createPeriodoDaoIF().insere(periodo, sigla);
		}
	}

	/**
	 * Este m�todo como controlador para a remo��o de periodos na base de dados
	 *
	 * @param sigla
	 *            A sigla do curso do periodo que o periodo pertence.
	 * @param nomePeriodo
	 *            Nome do periodo no formato "2013.1"
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o nome do periodo cadastrado na base de dados
	 */
	public void removePeriodo(String sigla, String nomePeriodo)
			throws H2Exception {

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se existe o periodo cadastrado
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createPeriodoDaoIF().getPeriodoByName(nomePeriodo, sigla),
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		// Deleta o perido do banco
		AbstractFactoryDao.createPeriodoDaoIF().deleta(nomePeriodo, sigla);
	}

	/**
	 * Este m�todo como controlador para a recupera��o de periodos na base de
	 * dados.
	 * 
	 * @param sigla
	 *            A sigla do curso do periodo que o periodo pertence.
	 * @param nomePeriodo
	 *            Nome do periodo no formato "2013.1"
	 * 
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o nome do perido cadastrado na base de dados
	 */
	public String getPeriodo(String nomePeriodo, String sigla)
			throws H2Exception {

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna um periodo com a nome e o curso iguais aos dos
		// par�metros no banco, se n�o existir o valor inst�ncia ser� nulo.
		Periodo periodo = AbstractFactoryDao.createPeriodoDaoIF()
				.getPeriodoByName(nomePeriodo, sigla);

		// Verifica se existe o periodo cadastrado
		H2Validation.validaObjetosNaoNulos(periodo,
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		// "ToString"
		return periodo.toString();

	}
}
