package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a manipula��o dos recursos de curso.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoCurso {

	/**
	 * Este m�todo serve controlador para a adi��o de novos cursos no sistema.
	 * 
	 * @param id
	 *            Identificador do curso.
	 * @param nome
	 *            nome do curso
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id cadastrado na base de dados
	 */
	public void addCurso(String id, String nome) throws H2Exception {

		Curso curso = new Curso();
		String[] params = { id, nome };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		curso.setId(id);

		curso.setNome(nome);

		// verifica se n�o existe curso com a mesma sigla no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(id),
				H2ErrorMessages.CURSOJACADASTRADO.getValor())) {

			// insere um novo curso no banco de dados.
			AbstractFactoryDao.createCursoDaoIF().insere(curso);
		}
	}

	/**
	 * Este m�todo serve como controlador para a altera��o de um curso na base
	 * de dados
	 * 
	 * @param identificador
	 *            id do Curso.
	 * @param novoValor
	 *            O novo nome para o cursp.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o identificado cadastrado na base de dados
	 */
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {

		String[] params = { identificador, novoValor };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera um curso do banco a partir da seu identificador.
		Curso curso = AbstractFactoryDao.createCursoDaoIF().getCursoBySilga(
				identificador);

		// Verfica se o curso existe na base de dados
		H2Validation.validaObjetosNaoNulos(curso,
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Altera o nome do curso.
		curso.setNome(novoValor);

		// Altera o curso no banco de dados.
		AbstractFactoryDao.createCursoDaoIF().altera(curso);
	}

	/**
	 * Este m�todo como controlador para a remo��o de cursos na base de dados
	 * 
	 * @param identificador
	 *            id do Curso
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o id cadastrado na base de dados
	 */
	public void removeCurso(String identificador) throws H2Exception {

		// valida se a entrada do identificador esta de acordo com as regras.
		H2Validation.validaCampos(identificador,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// verifica se existe cursos com o id igual ao do par�metro
		// no banco
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(identificador),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// remove o curso do banco
		AbstractFactoryDao.createCursoDaoIF().deleta(identificador);
	}

	/**
	 * Este m�todo como controlador para a recupera��o de cursos na base de
	 * dados.
	 * 
	 * @param idCurso
	 *            id do Curso
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getCurso(String idCurso) throws H2Exception {

		// valida se a entrada do id est� de acordo com as regras.
		H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna um curso com o id igual a do
		// par�metro no banco, se n�o existir o valor inst�ncia ser� nulo.
		Curso curso = AbstractFactoryDao.createCursoDaoIF().getCursoBySilga(
				idCurso);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(curso,
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// "ToString"
		return curso.toString();
	}

}
