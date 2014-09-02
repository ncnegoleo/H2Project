package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Professor;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve como controladora para a manipula��o dos recursos de
 * professor.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoProfessor {

	/**
	 * Este m�todo serve controlador para a adi��o de novos professores no
	 * sistema.
	 * 
	 * @param nome
	 *            Nome do professor.
	 * @param matricula
	 *            Matricula do Professor
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             a matricula cadastrada na base de dados
	 */
	public void addProfessor(String nome, String matricula) throws H2Exception {

		Professor professor = new Professor();
		String[] params = { nome, matricula };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		professor.setNome(nome);

		professor.setMatricula(matricula);

		// verifica se n�o existe professores com a mesma matricula no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createProfessorDaoIF().getProfessorByMatricula(matricula),
				H2ErrorMessages.PROFESSORJACADASTRADO.getValor())) {

			// insere um novo professor no banco de dados.
			AbstractFactoryDao.createProfessorDaoIF().insere(professor);
		}
	}

	/**
	 * Este m�todo serve como controlador para a altera��o de um professor na
	 * base de dados
	 * 
	 * @param matricula
	 *            Matricula do Professor.
	 * @param novoNome
	 *            O novo nome do Proessor
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             a matricula cadastrada na base de dados
	 */
	public void alteraProfessor(String matricula, String novoNome)
			throws H2Exception {

		String[] params = { matricula, novoNome };

		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera um professor do banco a partir da sua matricula.
		Professor professor = AbstractFactoryDao.createProfessorDaoIF()
				.getProfessorByMatricula(matricula);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(professor,
				H2ErrorMessages.PROFESSORNAOCADASTRADO.getValor());

		// Altera o nome do profesor
		professor.setNome(novoNome);

		// grava a altera��o na base de dados
		AbstractFactoryDao.createProfessorDaoIF().altera(professor);
	}

	/**
	 * Este m�todo como controlador para a remo��o de professores na base de
	 * dados
	 * 
	 * @param matricula
	 *            Matricula do Professor
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             a matricula cadastrada na base de dados
	 */
	public void removeProfessor(String matricula) throws H2Exception {

		// valida se a entrada da matricula esta de acordo com as regras.
		H2Validation.validaCampos(matricula,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createProfessorDaoIF().getProfessorByMatricula(matricula),
				H2ErrorMessages.PROFESSORNAOCADASTRADO.getValor());

		// Remove o professor da base de dados
		AbstractFactoryDao.createProfessorDaoIF().deleta(matricula);
	}

	/**
	 * Este m�todo como controlador para a recupera��o de professores na base de
	 * dados.
	 * 
	 * @param matricula
	 *            A matricula do professor
	 * 
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getProfessor(String matricula) throws H2Exception {

		// valida se a entrada da matricula esta de acordo com as regras.
		H2Validation.validaCampos(matricula,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna um professores com a matricula igual a do
		// par�metro no banco, se n�o existir o valor inst�ncia ser� nulo.
		Professor professor = AbstractFactoryDao.createProfessorDaoIF()
				.getProfessorByMatricula(matricula);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(professor,
				H2ErrorMessages.PROFESSORNAOCADASTRADO.getValor());

		// "ToString"
		return professor.toString();
	}
}
