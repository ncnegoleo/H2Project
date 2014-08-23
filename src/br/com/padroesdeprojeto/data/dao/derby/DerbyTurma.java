package br.com.padroesdeprojeto.data.dao.derby;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.TurmaDaoIF;
import br.com.padroesdeprojeto.model.Turma;

/**
 * Esta classe faz a interação da entrada dos dados da sala com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class DerbyTurma implements TurmaDaoIF{

	@Override
	public void insere(Turma t) {
		String SQL_STATEMENT = "INSERT INTO TURMA(NOME, MATRICULA_PROF, "
				+ "SIGLA_DISCIPLINA, CODIGO_SALA, NM_PERIODO) VALUES('"
				+ t.getNomeTurma() + "', " + "'" + t.getMatriculaProf() + "', '"
				+ t.getCodigoDisciplina() + "', '" + t.getIdSala() + "', '"
				+ t.getIdPeriodo() + "')";
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void altera(Turma t, int id) {
		String SQL_STATEMENT = "UPDATE TURMA SET NOME = '" + t.getNomeTurma()
				+ "', MATRICULA_PROF = '" + t.getMatriculaProf() 
				+ "', SIGLA_DISCIPLINA = '" + t.getCodigoDisciplina() 
				+ "', CODIGO_SALA = '" + t.getIdSala() 
				+ "', NM_PERIODO = '" + t.getIdPeriodo() 
				+ "' WHERE ID_TURMA = "+ id;
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void deleta(int id) {
		String SQL_STATEMENT = "DELETE FROM TURMA WHERE ID_TURMA = "+ id;
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Turma> getAllTurmas() {
		String SQL_STATEMENT = "SELECT * FROM TURMA";
		ArrayList<Turma> turmas = new ArrayList<Turma>();
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		/* Procura e retorna todos os registros */
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que são
				// adicionados
				// numa lista
				turmas.add(new Turma(resultSet.getInt(1), 
						resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6)));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConexaoDB.getInstance().closeConetion();
		return turmas;
	}

	@Override
	public Turma getTurmaByName(String nome) {
		String SQL_STATEMENT = "SELECT * FROM TURMA WHERE NOME = '"
				+ nome + "'";
		Turma turma = null;
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		/* Procura e retorna todos os registros */
		try {
			while (resultSet.next()) {
				// atribui os registro encontrados em objeto
				turma = new Turma(resultSet.getInt(1), 
						resultSet.getString(2), resultSet.getString(3), 
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ConexaoDB.getInstance().closeConetion();
		return turma;
	}

	@Override
	public int countTurmas() {
		ArrayList<Turma> turmas = getAllTurmas();
		return turmas.size();
	}

}
