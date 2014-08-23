package br.com.padroesdeprojeto.fachada;

import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.recurso.AbstractFactoryRecurso;

/**
 * Esta classe controla todas as ações de cadastro, alteração, deleção e
 * recuperação de informações dos recursos do sistema.
 * 
 * @author Leonardo Soares.
 * 
 */
public class Fachada implements FachadaIF {

	@Override
	public void addProfessor(String idProfessor, String nome)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoProfessor().addProfessor(nome,
				idProfessor);
	}

	@Override
	public void alteraProfessor(String idProfessor, String novoNome)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoProfessor().alteraProfessor(
				idProfessor, novoNome);
	}

	@Override
	public void removeProfessor(String matricula) throws H2Exception {
		AbstractFactoryRecurso.createRecursoProfessor().removeProfessor(
				matricula);
	}

	@Override
	public String getProfessor(String identificador) throws H2Exception {
		return AbstractFactoryRecurso.createRecursoProfessor().getProfessor(
				identificador);
	}

	@Override
	public void addDisciplinaAoPeriodo(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorperiodo) throws H2Exception {
		AbstractFactoryRecurso.createRecursoDisciplina().addDisciplina(
				identificadorDisciplina, nomeDisciplina, cargaHoraria,
				identificadorCurso, identificadorperiodo);

	}

	@Override
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) throws H2Exception {
		AbstractFactoryRecurso.createRecursoDisciplina().alteraDisciplina(
				idCurso, sigla, atributo, novoValor);

	}

	@Override
	public void removeDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoDisciplina().removeDisciplna(
				idCurso, idDisciplina);

	}

	@Override
	public String getDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSala(String idSala, String bloco) throws H2Exception {
		AbstractFactoryRecurso.createRecursoSala().addSala(idSala, bloco);
	}

	@Override
	public void alteraSala(String idSala, String novoBloco) throws H2Exception {
		AbstractFactoryRecurso.createRecursoSala()
				.alteraSala(idSala, novoBloco);
	}

	@Override
	public String getSala(String idSala) throws H2Exception {
		return AbstractFactoryRecurso.createRecursoSala().getSala(idSala);
	}

	@Override
	public void removeSala(String idSala) throws H2Exception {
		AbstractFactoryRecurso.createRecursoSala().removeSala(idSala);
	}

	@Override
	public void addPeriodo(String identificadorPeriodo, String idCurso)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoPeriodo().addPeriodo(
				identificadorPeriodo, idCurso);
	}

	@Override
	public void removePeriodo(String idCurso, String nomePeriodo)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoPeriodo().removePeriodo(idCurso,
				nomePeriodo);
	}

	@Override
	public String getPeriodo(String idPeriodo, String idCurso)
			throws H2Exception {
		return AbstractFactoryRecurso.createRecursoPeriodo().getPeriodo(
				idPeriodo, idCurso);
	}

	@Override
	public void addTurma(String idTurma, String idCurso,
			String identificadorProfessor, String identificadorDisciplina,
			String identificadorSala, String identificadorPeriodo)
			throws H2Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void alteraTurma(String idTurma, String campo, String novoValor)
			throws H2Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void removerTurma(String idTurma) throws H2Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTurma(String idTurma) throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCurso(String identificadorCurso, String nome)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoCurso().addCurso(
				identificadorCurso, nome);
	}

	@Override
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {
		AbstractFactoryRecurso.createRecursoCurso().alterarCurso(identificador,
				novoValor);
	}

	@Override
	public void removeCurso(String identificador) throws H2Exception {
		AbstractFactoryRecurso.createRecursoCurso().removeCurso(identificador);
	}

	@Override
	public String getCurso(String idCurso) throws H2Exception {
		return AbstractFactoryRecurso.createRecursoCurso().getCurso(idCurso);
	}

	@Override
	public String alocaTurmaAoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horafim) throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String desalocaTurmaDoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horaFim) throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHorario(String idTurma) throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTurmas(String diaDaSemana, int horaInicio, int horaFim)
			throws H2Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
