package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

/**
 * Esta classe serve para a manipula��o dos recursos de disciplina.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoDisciplina {

	private final String NOME = "Nome";
	private final String CARGAH = "CargaHoraria";
	private final String PERIODO = "Periodo";

	/**
	 * Este m�todo serve controlador para a adi��o de novas disciplinas no
	 * sistema.
	 * 
	 * @param identificadorDisciplina
	 *            O id da Disciplina.
	 * @param nomeDisciplina
	 *            O nome da Disciplina.
	 * @param cargaHoraria
	 *            A carga horaria em inteiro.
	 * @param identificadorCurso
	 *            O id de um curso j� cadastrado.
	 * @param identificadorPeriodo
	 *            O id de um periodo j� cadastrado.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void addDisciplina(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorPeriodo) throws H2Exception {

		Disciplina disciplina = new Disciplina();
		String[] params = { identificadorDisciplina, nomeDisciplina,
				identificadorCurso, identificadorPeriodo };

		// Verifica se os parametros s�o nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Valida se a entrada da caraga horaria esta de acordo com as regras.
		H2Validation.validaNumNaturais(cargaHoraria,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se o id do curso informado est� cadastrado no banco de dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(identificadorCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se o id do periodo informado est� cadastrado no banco de
		// dados
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createPeriodoDaoIF().getPeriodoByName(
						identificadorPeriodo, identificadorCurso),
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor());

		disciplina.setSiglaDiscipina(identificadorDisciplina);

		disciplina.setNomeDaDisciplina(nomeDisciplina);

		disciplina.setCargaHoraria(cargaHoraria);

		// Verifica se a disciplina j� exite no banco de dados
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
	 * Este m�todo como controlador para a altera��o de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso j� cadastrado.
	 * @param sigla
	 *            A sigla da Disciplina
	 * @param atributo
	 *            O nome ou a carga horaria a ser alterada.
	 * @param novoValor
	 *            Um novo valor para o atributo a ser alterado.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) throws H2Exception {

		// Verifica se o id do periodo informado est� cadastrado no banco de
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

		// Verifica se a disciplina est� cadastrada no banco
		H2Validation.validaObjetosNaoNulos(disciplina,
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		// Verifica o atributo passado e altera o valor no objeto.
		switch (atributo) {
		case NOME:
			disciplina.setNomeDaDisciplina(novoValor);
			break;
		case CARGAH:
			// Verifica o novo valor passado e converte para inteiro (se
			// possivel)
			int parsedValue = H2Validation.validaEConveteInt(novoValor,
					H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
			// Valida se a entrada da caraga horaria esta de acordo com as
			// regras.
			H2Validation.validaNumNaturais(parsedValue,
					H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
			disciplina.setCargaHoraria(parsedValue);
			break;
		case PERIODO:
			// Verifica se a disciplina est� cadastrada no banco
			H2Validation.validaObjetosNaoNulos(
					AbstractFactoryDao.createPeriodoDaoIF().getPeriodoByName(
							novoValor, disciplina.getSiglaCurso()),
					H2ErrorMessages.PERIODONAOCADASTRADO.getValor());
			disciplina.setPeriodo(novoValor);
			break;
		default:
			throw new H2Exception(H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		}

		// Altera a disciplina
		AbstractFactoryDao.createDisciplinaDaoIF().altera(disciplina, sigla,
				idCurso);
	}

	/**
	 * Este m�todo como controlador para a dele��o de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso j� cadastrado.
	 * @param idDisciplina
	 *            A sigla da Disciplina j� cadastrada
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
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

		// Verifica se o id do periodo informado est� cadastrado no banco de
		// dados
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Verifica se a disciplina est� cadastrada no banco
		H2Validation.validaObjetosNaoNulos(
				AbstractFactoryDao.createDisciplinaDaoIF()
						.getDisciplinaBySigla(idDisciplina, idCurso),
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		AbstractFactoryDao.createDisciplinaDaoIF()
				.deleta(idDisciplina, idCurso);
	}

	/**
	 * Este m�todo como controlador para a recupera��o de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 *            O id de um curso j� cadastrado.
	 * @param idDisciplina
	 *            A sigla da Disciplina j� cadastrada
	 * 
	 * @return To String do Objeto.
	 * 
	 * @throws H2Exception
	 *             Lan�ada caso algum atributo seja nulo ou vazio, ou n�o exista
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

		// Verifica se existe a dsciplina est� cadastrada no banco
		H2Validation.validaObjetosNaoNulos(disciplina,
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor());

		// "To String"
		return disciplina.toString();
	}
}
