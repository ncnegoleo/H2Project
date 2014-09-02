package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a manipula��o dos recursos de turma.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoTurma {

	private final String PROF = "Professor";
	private final String DISC = "Disciplina";
	private final String SALA = "Sala";
	private final String PERI = "Periodo";

	/**
	 * Este m�todo serve controlador para a adi��o de novas turmas no sistema.
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
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
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

		// Verifica se os parametros s�o nulos ou vazios
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

		// verifica se n�o existe professores com a mesma matricula no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMAJACADASTRADA.getValor())) {

			// insere um novo professor no banco de dados.
			AbstractFactoryDao.createTurmaDaoIF().insere(turma);
		}
	}

	/**
	 * Este m�todo como controlador para a altera��o de disciplinas na base de
	 * dados
	 * 
	 * @param idTurma
	 *            A sigla da Disciplina
	 * @param campo
	 *            O nome ou a carga horaria a ser alterada.
	 * @param novoValor
	 *            Um novo valor para o atributo a ser alterado.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void alteraTurma(String idTurma, String campo, String novoValor)
			throws H2Exception {

		String[] params = { idTurma, campo, novoValor };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera uma turma do banco referente aos parametros passados
		Turma turma = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				idTurma);

		// Verifica se a turma est� cadastrada no banco
		H2Validation.validaObjetosNaoNulos(turma,
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		switch (campo) {
		case PROF:
			// Verifica se o professor est� cadastrada no banco
			H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
					.createProfessorDaoIF().getProfessorByMatricula(novoValor),
					H2ErrorMessages.PROFESSORNAOCADASTRADO.getValor());
			turma.setIdProf(novoValor);
			break;
		case DISC:
			// Verifica se o professor est� cadastrada no banco
			H2Validation
					.validaObjetosNaoNulos(
							AbstractFactoryDao.createDisciplinaDaoIF()
									.getDisciplinaBySigla(novoValor,
											turma.getIdCurso()),
							H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());
			turma.setIdDisc(novoValor);
			break;
		case SALA:
			// Verifica se o professor est� cadastrada no banco
			H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
					.createSalaDaoIF().getSalaById(novoValor),
					H2ErrorMessages.SALANAOCADASTRADA.getValor());
			turma.setIdSala(novoValor);
			break;
		case PERI:
			// Verifica se o professor est� cadastrada no banco
			H2Validation.validaObjetosNaoNulos(
					AbstractFactoryDao.createPeriodoDaoIF().getPeriodoByName(
							novoValor, turma.getIdCurso()),
					H2ErrorMessages.PERIODONAOCADASTRADO.getValor());
			turma.setIdPeri(novoValor);
			break;
		default:
			throw new H2Exception(H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		}

		AbstractFactoryDao.createTurmaDaoIF().altera(turma, idTurma);
	}

	/**
	 * Este m�todo como controlador para a remo��o de turmas na base de dados
	 * 
	 * @param idTurma
	 *            O id da Turma
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
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

	/**
	 * Este metodo recupera uma String com os dados da Turma.
	 * 
	 * @param idTurma
	 * 
	 * @return retorna uma String com os dados da turma
	 * 
	 * @throws H2Exception
	 *             Caso a turma n�o exista ou a entrada seja nula ou invalida �
	 *             lan�ada uma exe��o.
	 */
	public String getTurma(String idTurma) throws H2Exception {

		// valida se a entrada do id esta de acordo com as regras.
		H2Validation.validaCampos(idTurma,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna uma turma com a matricula igual a do
		// par�metro no banco, se n�o existir o valor inst�ncia ser� nulo.
		Turma turma = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				idTurma);

		// Verfica se a turma existe na base de dados
		H2Validation.validaObjetosNaoNulos(turma,
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// "ToString"
		return turma.toString();
	}
}
