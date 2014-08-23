package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.model.Professor;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve como controladora para a manipulação dos recursos de
 * professor.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoProfessor {

	/**
	 * Este método serve controlador para a adição de novos professores no
	 * sistema.
	 * 
	 * @param nome
	 *            Nome do professor.
	 * @param matricula
	 *            Matricula do Professor
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             a matricula cadastrada na base de dados
	 */
	public void addProfessor(String nome, String matricula) throws H2Exception {

		Professor professor = new Professor();
		String[] params = {nome,matricula};

		H2Validation.validaParametros(params, H2ErrorMessages.
				ATRIBUTOINVALIDO.getValor());
		
		professor.setNome(nome);
		
		professor.setMatricula(matricula);
		
		// verifica se não existe professores com a mesma matricula no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao.
				createProfessorDaoIF().getProfessorByMatricula(
				matricula), H2ErrorMessages.PROFESSORJACADASTRADO.getValor())) {
			// insere um novo professor no banco de dados.
			AbstractFactoryDao.createProfessorDaoIF().insere(professor);
			return;
		}

		//throw new H2Exception("Professor já cadastrado");
	}

	/**
	 * Este método serve como controlador para a alteração de um professor na
	 * base de dados
	 * 
	 * @param matricula
	 *            Matricula do Professor.
	 * @param novoNome
	 *            O novo nome do Proessor
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             a matricula cadastrada na base de dados
	 */
	public void alteraProfessor(String matricula, String novoNome)
			throws H2Exception {
		
		String[] params = {matricula, novoNome};

		H2Validation.validaParametros(params, H2ErrorMessages.
				ATRIBUTOINVALIDO.getValor());

		// Recupera um professor do banco a partir da sua matricula.
		Professor professor = AbstractFactoryDao.createProfessorDaoIF()
				.getProfessorByMatricula(matricula);

		// Verfica se o professor existe na base de dados
		if (H2Validation.validaObjetosNaoNulos(professor, H2ErrorMessages.
				PROFESSORNAOCADASTRADO.getValor()));

		// Altera o nome do profesor
		professor.setNome(novoNome);

		// grava a alteração na base de dados
		AbstractFactoryDao.createProfessorDaoIF().altera(professor);
	}

	/**
	 * Este método como controlador para a remoção de professores na base de
	 * dados
	 * 
	 * @param matricula
	 *            Matricula do Professor
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             a matricula cadastrada na base de dados
	 */
	public void removeProfessor(String matricula) throws H2Exception {

		// valida se a entrada da matricula esta de acordo com as regras.
		if (H2Validation.validaCampos(matricula, "Atributo inválido"));

		// Verfica se o professor existe na base de dados
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createProfessorDaoIF().getProfessorByMatricula(matricula),
				"Matrícula não existente"));

		// Remove o professor da base de dados
		AbstractFactoryDao.createProfessorDaoIF().deleta(matricula);
	}

	/**
	 * Este método como controlador para a recuperação de professores na base de
	 * dados.
	 * 
	 * @param matricula
	 *            A matricula do professor
	 *            
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getProfessor(String matricula) throws H2Exception {

		// valida se a entrada da matricula esta de acordo com as regras.
		if (H2Validation.validaCampos(matricula, "Atributo inválido"));

		// se existir, retorna um professores com a matricula igual a do
		// parâmetro no banco, se não existir o valor instância será nulo.
		Professor professor = AbstractFactoryDao.createProfessorDaoIF()
				.getProfessorByMatricula(matricula);

		// Verfica se o professor existe na base de dados
		if (H2Validation.validaObjetosNaoNulos(professor, "Professor não cadastrado."));

		// "ToString"
		return professor.toString();
	}
}
