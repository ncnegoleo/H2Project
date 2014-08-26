package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de turma.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoTurma {

	/**
	 * Este método serve controlador para a adição de novas turmas no sistema.
	 * 
	 * @param idTurma
	 *            Id da turma a ser cadastrada
	 * @param idCurso
	 *            Id do curso a ser referenciado
	 * @param identificadorProfessor
	 *            Id do professor a ser referenciado
	 * @param identificadorDisciplina
	 *            Id da disciplina a ser referenciada
	 * @param identificadorSala
	 *            Id da sala a ser referenciada
	 * @param identificadorPeriodo
	 *            Id do periodo a ser referenciado
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id da turma cadastrado na base de dados
	 */
	public void addTurma(String idTurma, String idCurso,
			String identificadorProfessor, String identificadorDisciplina,
			String identificadorSala, String identificadorPeriodo)
			throws H2Exception {

		Turma turma = new Turma();
		String[] params = { idTurma, idCurso, identificadorProfessor,
				identificadorDisciplina, identificadorSala,
				identificadorPeriodo };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se existe o curso cadastrado
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se existe o professor cadastrado
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createProfessorDaoIF()
						.getProfessorByMatricula(identificadorProfessor),
				H2ErrorMessages.PROFESSORNAOCADASTRADO.getValor());

		// Verifica se existe o disciplina cadastrada
		H2Validation
				.validaObjetosNaoNulos(
						AbstractFactoryDao.createDisciplinaDaoIF()
								.getDisciplinaBySigla(identificadorDisciplina,
										idCurso),
						H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		// Verifica se existe a sala cadastrada
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createSalaDaoIF()
				.getSalaById(identificadorSala),
				H2ErrorMessages.SALANAOCADASTRADA.getValor());

		// Verifica se existe o professor cadastrado
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createPeriodoDaoIF().getPeriodoByName(
						identificadorPeriodo, idCurso),
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		turma.setIdTurma(idTurma);
		turma.setIdCurso(idCurso);
		turma.setIdProf(identificadorProfessor);
		turma.setIdDisc(identificadorDisciplina);
		turma.setIdSala(identificadorSala);
		turma.setIdPeri(identificadorPeriodo);

		// verifica se não existe professores com a mesma matricula no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMAJACADASTRADA.getValor())) {

			// insere um novo professor no banco de dados.
			AbstractFactoryDao.createTurmaDaoIF().insere(turma);
		}
	}
	
	
	/**
	 * Este método como controlador para a remoção de turmas na base de
	 * dados
	 * 
	 * @param idTurma
	 *            O id da Turma
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id cadastrado na base de dados
	 */
	public void removeTurma(String idTurma) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idTurma,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verfica se a turma existe na base de dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// Remove a turma da base de dados
		AbstractFactoryDao.createTurmaDaoIF().deleta(idTurma);
	}
	
	
	public String getTurma(String idTurma) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idTurma,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna uma turma com a matricula igual a do
		// parâmetro no banco, se não existir o valor instância será nulo.
		Turma turma = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				idTurma);

		// Verfica se a turma existe na base de dados
		H2Validation.validaObjetosNaoNulos(turma,
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// "ToString"
		return turma.toString();
	}
}
