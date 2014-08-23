package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.model.Disciplina;
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
			String identificadorperiodo) throws H2Exception {

		Disciplina disciplina = new Disciplina();

		// Valida se a entrada do id disciplina.
		if (H2Validation.validaCampos(identificadorDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()))
			disciplina.setSiglaDiscipina(identificadorDisciplina);

		// Valida se a entrada a carga horaria da disciplina.
		if (H2Validation.validaCampos(nomeDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()))
			disciplina.setNomeDaDisciplina(nomeDisciplina);

		// Valida se a entrada da periodo.
		if (H2Validation.validaNumNaturais(cargaHoraria,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()))
			disciplina.setCargaHoraria(cargaHoraria);
		
		// Verifica se o id do curso informado está cadastrado no banco de dados
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createCursoDaoIF()
				.getCursoBySilga(identificadorCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor()));

		// Verifica se o id do periodo informado está cadastrado no banco de dados
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createPeriodoDaoIF()
				.getPeriodoByName(identificadorperiodo, identificadorCurso),
				H2ErrorMessages.PERIODONAOCADASTRADO.getValor()));

		// Verifica se a disciplina já exite no banco de dados
		if (AbstractFactoryDao.createDisciplinaDaoIF().getDisciplinaBySigla(
				identificadorDisciplina, identificadorCurso) == null) {
			
			// Cadastra a nova disciplina
			AbstractFactoryDao.createDisciplinaDaoIF().insere(disciplina,
					identificadorCurso, identificadorperiodo);
			
			return;
		}

		throw new H2Exception(H2ErrorMessages.DISCIPLINAJACADASTRADA.getValor());
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

		// Verifica se o id do periodo informado está cadastrado no banco de dados
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createCursoDaoIF()
				.getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor()));

		// Valida se a entrada do id disciplina.
		if (H2Validation.validaCampos(sigla,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()));
		
		// Recupera uma disciolina do banco referente aos parametros passados
		Disciplina disciplina = AbstractFactoryDao.createDisciplinaDaoIF()
				.getDisciplinaBySigla(sigla, idCurso);
		
		// Verifica se a disciplina está cadastrada no banco
		if (H2Validation.validaObjetosNaoNulos(disciplina, H2ErrorMessages.
				DISCIPLINANAOCADASTRADA.getValor()));

		// Verifica o atributo passado e altera o valor no objeto.
		switch (atributo) {
		case "Nome":
			disciplina.setNomeDaDisciplina(novoValor);
			break;
		case "CargaHoraria":
			// Verifica o novo valor passad e converte para inteiro (se possivel)
			int parsedValue = H2Validation.validaEConveteInt(novoValor, 
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
	 *Este método como controlador para a deleção de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 * 		 	   O id de um curso já cadastrado.
	 * @param idDisciplina
	 * 			   A sigla da Disciplina já cadastrada
	 * 
	 * @throws H2Exception 
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id da disciplina cadastrado na base de dados
	 */
	public void removeDisciplna(String idCurso, String idDisciplina)
			throws H2Exception {

		// Valida se a entrada do id do curso.
		if (H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()));
		
		// Valida se a entrada do id da disciplina.
		if (H2Validation.validaCampos(idDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()));
		
		// Verifica se o id do periodo informado está cadastrado no banco de dados
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createCursoDaoIF()
				.getCursoBySilga(idCurso),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor()));

		// Verifica se a disciplina está cadastrada no banco
		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createDisciplinaDaoIF()
				.getDisciplinaBySigla(idDisciplina, idCurso), H2ErrorMessages.
				DISCIPLINANAOCADASTRADA.getValor()));
		
		AbstractFactoryDao.createDisciplinaDaoIF().deleta(idDisciplina, idCurso);
	}

	/**
	 *Este método como controlador para a recuperação de disciplinas na base de
	 * dados
	 * 
	 * @param idCurso
	 * 		 	   O id de um curso já cadastrado.
	 * @param idDisciplina
	 * 			   A sigla da Disciplina já cadastrada
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
		if (H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()));
		
		// Valida se a entrada do id da disciplina.
		if (H2Validation.validaCampos(idDisciplina,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor()));

		// Recupera uma disciplina do banco a partir dos parametros
		Disciplina disciplina = AbstractFactoryDao.createDisciplinaDaoIF()
				.getDisciplinaBySigla(idDisciplina, idCurso);

		// Verifica se existe a dsciplina está cadastrada no banco
		if (H2Validation.validaObjetosNaoNulos(disciplina, 
				H2ErrorMessages.DISCIPLINANAOCADASTRADA.getValor()));

		// "To String"
		return disciplina.toString();
	}

}
