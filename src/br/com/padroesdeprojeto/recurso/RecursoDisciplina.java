package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de disciplina.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoDisciplina {

	/**
	 * Este método serve controlador para a adição de novas disciplinas no
	 * sistema.
	 * 
	 * @param identificadorDisciplina
	 *            O id da Disciplina.
	 * @param nomeDisciplina
	 *            O nome da Disciplina.
	 * @param cargaHoraria
	 *            A carga horaria em inteiro.
	 * @param identificadorCurso
	 *            O id de um curso já cadastrado.
	 * @param identificadorperiodo
	 *            O id de um periodo já cadastrado.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void addDisciplina(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorPeriodo) throws H2Exception {

		Disciplina disciplina = new Disciplina();
		String[] params = { identificadorDisciplina, nomeDisciplina,
				identificadorCurso, identificadorPeriodo };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Valida se a entrada da caraga horaria esta de acordo com as regras.
		H2Validation.validaNumNaturais(cargaHoraria,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se o id do curso informado está cadastrado no banco de dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(identificadorCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se o id do periodo informado está cadastrado no banco de
		// dados
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createPeriodoDaoIF().getPeriodoByName(
						identificadorPeriodo, identificadorCurso),
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		disciplina.setSiglaDiscipina(identificadorDisciplina);

		disciplina.setNomeDaDisciplina(nomeDisciplina);

		disciplina.setCargaHoraria(cargaHoraria);

		// Verifica se a disciplina já exite no banco de dados
		if (H2Validation.validaObjetosNulos(
				AbstractFactoryDao.createDisciplinaDaoIF()
						.getDisciplinaBySigla(identificadorDisciplina,
								identificadorCurso),
				H2ErrorMessages.DISCIPLINAJACADASTRADA.getValor())) {

			// Cadastra a nova disciplina
			AbstractFactoryDao.createDisciplinaDaoIF().insere(disciplina,
					identificadorCurso, identificadorPeriodo);
		}
	}

	/**
	 * Este método como controlador para a alteração de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso já cadastrado.
	 * @param sigla
	 *            A sigla da Disciplina
	 * @param atributo
	 *            O nome ou a carga horaria a ser alterada.
	 * @param novoValor
	 *            Um novo valor para o atributo a ser alterado.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) throws H2Exception {

		// Verifica se o id do periodo informado está cadastrado no banco de
		// dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Valida se a entrada do id disciplina.
		H2Validation.validaCampos(sigla,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Valida se a entrada o atributo disciplina.
		H2Validation.validaCampos(atributo,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera uma disciolina do banco referente aos parametros passados
		Disciplina disciplina = AbstractFactoryDao.createDisciplinaDaoIF()
				.getDisciplinaBySigla(sigla, idCurso);

		// Verifica se a disciplina está cadastrada no banco
		H2Validation.validaObjetosNaoNulos(disciplina,
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		// Verifica o atributo passado e altera o valor no objeto.
		switch (atributo) {
		case "Nome":
			disciplina.setNomeDaDisciplina(novoValor);
			break;
		case "CargaHoraria":
			// Verifica o novo valor passad e converte para inteiro (se
			// possivel)
			int parsedValue = H2Validation.validaEConveteInt(novoValor,
					H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
			// Valida se a entrada da caraga horaria esta de acordo com as
			// regras.
			H2Validation.validaNumNaturais(parsedValue,
					H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
			disciplina.setCargaHoraria(parsedValue);
			break;
		default:
			throw new H2Exception(H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		}

		// Altera a disciplina
		AbstractFactoryDao.createDisciplinaDaoIF().altera(disciplina, sigla,
				idCurso);
	}

	/**
	 * Este método como controlador para a deleção de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso já cadastrado.
	 * @param idDisciplina
	 *            A sigla da Disciplina já cadastrada
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void removeDisciplna(String idCurso, String idDisciplina)
			throws H2Exception {

		// Valida se a entrada do id do curso.
		H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Valida se a entrada do id da disciplina.
		H2Validation.validaCampos(idDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se o id do periodo informado está cadastrado no banco de
		// dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se a disciplina está cadastrada no banco
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createDisciplinaDaoIF()
						.getDisciplinaBySigla(idDisciplina, idCurso),
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		AbstractFactoryDao.createDisciplinaDaoIF()
				.deleta(idDisciplina, idCurso);
	}

	/**
	 * Este método como controlador para a recuperação de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso já cadastrado.
	 * @param idDisciplina
	 *            A sigla da Disciplina já cadastrada
	 * 
	 * @return To String do Objeto.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public String getDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {

		// Valida se a entrada do id do curso.
		H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Valida se a entrada do id da disciplina.
		H2Validation.validaCampos(idDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera uma disciplina do banco a partir dos parametros
		Disciplina disciplina = AbstractFactoryDao.createDisciplinaDaoIF()
				.getDisciplinaBySigla(idDisciplina, idCurso);

		// Verifica se existe a dsciplina está cadastrada no banco
		H2Validation.validaObjetosNaoNulos(disciplina,
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		// "To String"
		return disciplina.toString();
	}

}
