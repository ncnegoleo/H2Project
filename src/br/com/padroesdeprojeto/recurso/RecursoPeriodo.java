package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de period.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoPeriodo {

	/**
	 * Este método serve controlador para a adição de novos periodos no sistema.
	 * 
	 * @param nomePeriodo
	 *            Nome do periodo no formato "2013.1"
	 * @param sigla
	 *            A sigla do curso do periodo que o periodo pertence.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o nome do periodo ou curso cadastrados na base de dados
	 */
	public void addPeriodo(String nomePeriodo, String sigla) throws H2Exception {

		Periodo periodo = new Periodo();

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		periodo.setNomePeriodo(nomePeriodo);

		// Verifica se existe o curso cadastrado
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(sigla),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se já existe o perido cadastrado no mesmo curso.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createPeriodoDaoIF().getPeriodoByName(nomePeriodo, sigla),
				H2ErrorMessages.PERIODOJACADASTRADO.getValor())) {

			// insere um novo periodo no banco
			AbstractFactoryDao.createPeriodoDaoIF().insere(periodo, sigla);
		}
	}

	/**
	 * Este método como controlador para a remoção de periodos na base de dados
	 *
	 * @param sigla
	 *            A sigla do curso do periodo que o periodo pertence.
	 * @param nomePeriodo
	 *            Nome do periodo no formato "2013.1"
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o nome do periodo cadastrado na base de dados
	 */
	public void removePeriodo(String sigla, String nomePeriodo)
			throws H2Exception {

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros são nulos ou vazios
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
	 * Este método como controlador para a recuperação de periodos na base de
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
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o nome do perido cadastrado na base de dados
	 */
	public String getPeriodo(String nomePeriodo, String sigla)
			throws H2Exception {

		String[] params = { nomePeriodo, sigla };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna um periodo com a nome e o curso iguais aos dos
		// parâmetros no banco, se não existir o valor instância será nulo.
		Periodo periodo = AbstractFactoryDao.createPeriodoDaoIF()
				.getPeriodoByName(nomePeriodo, sigla);

		// Verifica se existe o periodo cadastrado
		H2Validation.validaObjetosNaoNulos(periodo,
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		// "ToString"
		return periodo.toString();

	}
}
