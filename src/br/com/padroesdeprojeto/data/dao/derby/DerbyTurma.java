package br.com.padroesdeprojeto.data.dao.derby;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.TurmaDaoIF;

/**
 * Esta classe faz a interação da entrada dos dados da sala com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class DerbyTurma implements TurmaDaoIF {

	@Override
	public void insere(Turma t) {
		
		// sql para adicionar uma nova turma.
		String SQL_STATEMENT = "INSERT INTO TURMA (ID_TURMA, ID_CURSO, "
				+ "ID_PROF, ID_DISC, ID_SALA, ID_PERIODO) VALUES('"
				+ t.getIdTurma() + "','"+ t.getIdCurso() + "','"
				+ t.getIdProf() + "','"+ t.getIdDisc() + "','"
				+ t.getIdSala() + "','" + t.getIdPeri() + "')";
		
		// executa o sql no SGBD.
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão.
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void altera(Turma t, String id) {
		
		// sql para alterar uma nova turma.
		String SQL_STATEMENT = "UPDATE TURMA SET ID_PROF = " 
		+ t.getIdProf() + ", ID_DISC = " + t.getIdDisc() 
		+ ", ID_SALA = " + t.getIdSala() 
		+ ", ID_PERIODO = " + t.getIdPeri() 
		+ " WHERE ID_TURMA = '" + id +"'";
		
		// executa o sql no SGBD.
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão.
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void deleta(String id) {
		
		// sql para deletar uma turma.
		String SQL_STATEMENT = "DELETE FROM TURMA WHERE ID_TURMA = " + id;
		
		// executa o sql no SGBD.
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão.
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Turma> getAllTurmas() {
		
		// sql para selecionar todas disciplinas
		String SQL_STATEMENT = "SELECT * FROM TURMA";
		
		ArrayList<Turma> turmas = new ArrayList<Turma>();
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que são
				// adicionados numa lista
				turmas.add(new Turma(resultSet.getString(1), 
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), 
						resultSet.getString(6)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
		
		// retorna as turmas
		return turmas;
	}

	@Override
	public Turma getTurmaById(String id) {
		
		// sql para selecionar uma disciplina com o id referenciado
		String SQL_STATEMENT = "SELECT * FROM TURMA WHERE ID_TURMA = '" + id
				+ "'";
		Turma turma = null;
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna o registro //
		try {
			while (resultSet.next()) {
				turma = new Turma(resultSet.getString(1), 
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), 
						resultSet.getString(6));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
		
		// retorna a turma
		return turma;
	}

}
