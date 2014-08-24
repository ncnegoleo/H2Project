package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Sala;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de sala.
 * 
 * @author Leonardo Soares Rodrigues
 *
 */
public class RecursoSala {

	/**
	 * Este método serve controlador para a adição de novas salas no sistema.
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * @param bloco
	 *            Bloco da sala.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id cadastrado na base de dados
	 */
	public void addSala(String idSala, String bloco) throws H2Exception {

		Sala sala = new Sala();
		String[] params = { idSala, bloco };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		sala.setId(idSala);

		sala.setBloco(bloco);

		// verifica se não existe professores com a mesma id.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createSalaDaoIF().getSalaById(idSala),
				H2ErrorMessages.SALAJACADASTRADA.getValor())) {

			// insere uma nova sala no banco de dados.
			AbstractFactoryDao.createSalaDaoIF().insere(sala);
		}
	}

	/**
	 * Este método serve como controlador para a alteração de uma sala na base
	 * de dados
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * @param bloco
	 *            Novo bloco da sala.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id cadastrada na base de dados
	 */
	public void alteraSala(String idSala, String novoBloco) throws H2Exception {

		String[] params = { idSala, novoBloco };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera uma sala do banco a partir da seu id.
		Sala sala = AbstractFactoryDao.createSalaDaoIF().getSalaById(idSala);

		// Verfica se a sala existe na base de dados
		H2Validation.validaObjetosNaoNulos(sala,
				H2ErrorMessages.SALANAOCADASTRADA.getValor());

		// Altera o nome do profesor
		sala.setBloco(novoBloco);

		// grava a alteração na base de dados
		AbstractFactoryDao.createSalaDaoIF().altera(sala);
	}

	/**
	 * Este método como controlador para a remoção de salas na base de dados
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id cadastrado na base de dados
	 */
	public void removeSala(String idSala) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idSala,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verfica se a sala existe na base de dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createSalaDaoIF()
				.getSalaById(idSala), H2ErrorMessages.SALANAOCADASTRADA
				.getValor());

		// Remove o professor da base de dados
		AbstractFactoryDao.createSalaDaoIF().deleta(idSala);
	}

	/**
	 * Este método como controlador para a recuperação de salas na base de
	 * dados.
	 * 
	 * @param idSala
	 *            O id da sala.
	 * 
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getSala(String idSala) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idSala,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna uma sala com a matricula igual a do
		// parâmetro no banco, se não existir o valor instância será nulo.
		Sala sala = AbstractFactoryDao.createSalaDaoIF().getSalaById(idSala);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(sala,
				H2ErrorMessages.SALANAOCADASTRADA.getValor());

		// "ToString"
		return sala.toString();
	}
}
