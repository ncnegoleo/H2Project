package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.model.Turma;
import br.com.padroesdeprojeto.validation.H2Regex;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de turma.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoTurma {

	public void addTurma(String nomeTurma, String matriculaProf,
			String codigoDisciplina, String idSala, String idPeriodo)
			throws H2Exception {

		Turma turma = new Turma();

		if (H2Validation.validaCamposRegex(nomeTurma,
				H2Regex.DEFAUT_NAME.getValor(), "Atributo inválido"))
			turma.setNomeTurma(nomeTurma);

		// valida se a entrada da matricula esta de acordo com as regras
		if (H2Validation.validaCamposRegex(matriculaProf,
				H2Regex.MATRICULA.getValor(), "Atributo inválido"))
			turma.setMatriculaProf(matriculaProf);

		// Valida se a entrada da disciplina.
		if (H2Validation.validaCampos(codigoDisciplina, "Atributo inválido"))
			turma.setCodigoDisciplina(codigoDisciplina);

		// Valida se a entrada do nome da sala está de acordo com as regras.
		if (H2Validation.validaCampos(idSala, "Atributo inválido"))
			turma.setIdSala(idSala);

		if (H2Validation.validaCamposRegex(idPeriodo,
				H2Regex.PERIODO.getValor(), "Atributo inválido"))
			turma.setIdPeriodo(idPeriodo);

		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createProfessorDaoIF().getProfessorByMatricula(matriculaProf),
				"Recurso não cadastrado"));

		if (H2Validation.validaObjetosNaoNulos(AbstractFactoryDao.createSalaDaoIF()
				.getSalaById(idSala), "Recurso não cadastrado"));

		//if (H2Validation.validaObjetos(AbstractFactoryDao.createPeriodoDaoIF()
			//	.getPeriodoByName(idPeriodo), "Recurso não cadastrado"));

		if (AbstractFactoryDao.createTurmaDaoIF().getTurmaByName(nomeTurma) == null) {
			AbstractFactoryDao.createTurmaDaoIF().insere(turma);
			return;
		}
		throw new H2Exception("Turma já cadastrada");
	}

	public void alterarTurma(String idTurma, String atributo, String novoValor)
			throws H2Exception {

		if (H2Validation.validaCampos(idTurma, "Atributo inválido")) {
		}

		Turma turma = AbstractFactoryDao.createTurmaDaoIF().getTurmaByName(
				idTurma);

		if (H2Validation.validaObjetosNaoNulos(turma, "Turma não Cadastrada")) {
		}

		int id = turma.getId();

		// verifica se o atributo passado é o nome do professor
		if ("nome".equals(atributo)) {
			// verifica se o novo valor é válido
			if (H2Validation.validaCampos(novoValor,
					"Atributo inválido")) {
				// altera o nome da instância professor
				turma.setNomeTurma(novoValor);
			}
		} else if ("professor".equals(atributo)) {
			// verifica se o novo valor é válido
			if (H2Validation.validaCamposRegex(novoValor,
					H2Regex.MATRICULA.getValor(), "Atributo inválido")) {
				// altera o nome da instância professor
				turma.setMatriculaProf(novoValor);
			}
		} else if ("disciplina".equals(atributo)) {
			// verifica se o novo valor é válido
			if (H2Validation.validaCampos(novoValor,
					"Atributo inválido")) {
				// altera o nome da instância professor
				turma.setCodigoDisciplina(novoValor);
			}
		} else if ("sala".equals(atributo)) {
			// verifica se o novo valor é válido
			if (H2Validation.validaCampos(novoValor,
					"Atributo inválido")) {
				// altera o nome da instância professor
				turma.setIdSala(novoValor);
			}
		} else if ("período".equals(atributo)) {
			// verifica se o novo valor é válido
			if (H2Validation.validaCampos(novoValor,
					"Atributo inválido")) {
				// altera o nome da instância professor
				turma.setIdPeriodo(novoValor);
			}
		} else {
			throw new H2Exception("Atributo inválido");
		}

		AbstractFactoryDao.createTurmaDaoIF().altera(turma, id);
	}
	
	public void removeTurma(String idTurma) throws H2Exception {
		
		if (H2Validation.validaCampos(idTurma, "Atributo inválido")) {}
		
		Turma turma = AbstractFactoryDao
				.createTurmaDaoIF().getTurmaByName(idTurma);
		
		if(H2Validation.validaObjetosNaoNulos(turma, "Turma não existente")){}
		
		AbstractFactoryDao.createTurmaDaoIF().deleta(turma.getId());
		
	}
	
	public String getTurma(String idTurma) throws H2Exception {
		
		if (H2Validation.validaCampos(idTurma, "Atributo inválido")) {}
		
		Turma turma = AbstractFactoryDao
				.createTurmaDaoIF().getTurmaByName(idTurma);
		
		if(H2Validation.validaObjetosNaoNulos(turma, "Turma não existente")){}
		
		return turma.toString();
	}

}
