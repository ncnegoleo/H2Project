package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Sala;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a manipula��o dos recursos de sala.
 * 
 * @author Leonardo Soares Rodrigues
 *
 */
public class RecursoSala {

	/**
	 * Este m�todo serve controlador para a adi��o de novas salas no sistema.
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * @param bloco
	 *            Bloco da sala.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id cadastrado na base de dados
	 */
	public void addSala(String idSala, String bloco) throws H2Exception {

		Sala sala = new Sala();
		String[] params = { idSala, bloco };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		sala.setId(idSala);

		sala.setBloco(bloco);

		// verifica se n�o existe professores com a mesma id.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createSalaDaoIF().getSalaById(idSala),
				H2ErrorMessages.SALAJACADASTRADA.getValor())) {

			// insere uma nova sala no banco de dados.
			AbstractFactoryDao.createSalaDaoIF().insere(sala);
		}
	}

	/**
	 * Este m�todo serve como controlador para a altera��o de uma sala na base
	 * de dados
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * @param novoBloco
	 *            Novo bloco da sala.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o id cadastrada na base de dados
	 */
	public void alteraSala(String idSala, String novoBloco) throws H2Exception {

		String[] params = { idSala, novoBloco };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera uma sala do banco a partir da seu id.
		Sala sala = AbstractFactoryDao.createSalaDaoIF().getSalaById(idSala);

		// Verfica se a sala existe na base de dados
		H2Validation.validaObjetosNaoNulos(sala,
				H2ErrorMessages.SALANAOCADASTRADA.getValor());

		// Altera o nome do profesor
		sala.setBloco(novoBloco);

		// grava a altera��o na base de dados
		AbstractFactoryDao.createSalaDaoIF().altera(sala);
	}

	/**
	 * Este m�todo como controlador para a remo��o de salas na base de dados
	 * 
	 * @param idSala
	 *            Identificador da Sala.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
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
	 * Este m�todo como controlador para a recupera��o de salas na base de
	 * dados.
	 * 
	 * @param idSala
	 *            O id da sala.
	 * 
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getSala(String idSala) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idSala,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna uma sala com a matricula igual a do
		// par�metro no banco, se n�o existir o valor inst�ncia ser� nulo.
		Sala sala = AbstractFactoryDao.createSalaDaoIF().getSalaById(idSala);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(sala,
				H2ErrorMessages.SALANAOCADASTRADA.getValor());

		// "ToString"
		return sala.toString();
	}
}
