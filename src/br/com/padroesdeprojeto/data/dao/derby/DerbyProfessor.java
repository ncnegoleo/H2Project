package br.com.padroesdeprojeto.data.dao.derby;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.ProfessorDaoIF;
import br.com.padroesdeprojeto.model.Professor;

/**
 * Esta classe faz a interação da entrada dos dados do professor com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class DerbyProfessor implements ProfessorDaoIF {

	@Override
	public void insere(Professor p) {
		
		// sql para adicionar um novo professor
		String SQL_STATEMENT = "INSERT INTO PROFESSOR(MATRICULA, NOME) VALUES("
				+ "'" + p.getMatricula() + "', " + "'" + p.getNome() + "')";
		
		// executa o sql no SGBD
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void altera(Professor p) {
		
		// sql para alterar um professor
		String SQL_STATEMENT = "UPDATE PROFESSOR SET NOME = '" + p.getNome()
				+ "' " + "WHERE MATRICULA = '" + p.getMatricula() + "'";
		
		// executa o sql no SGBD
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public void deleta(String matricula) {
		
		// sql para remove um professor
		String SQL_STATEMENT = "DELETE FROM PROFESSOR WHERE MATRICULA = '"
				+ matricula + "'";
		
		// executa o sql no SGBD
		ConexaoDB.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Professor> getAllProfessores() {
		
		// sql para selecionar todos os professores
		String SQL_STATEMENT = "SELECT * FROM PROFESSOR";
		
		ArrayList<Professor> professores = new ArrayList<Professor>();
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que adicionados 
				// numa lista
				professores.add(new Professor(resultSet.getString(1), resultSet
						.getString(2)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
		
		// retorna os professores
		return professores;
	}

	@Override
	public Professor getProfessorByMatricula(String matricula) {
		
		// sql para selecionar um professor com a matricula referenciada
		String SQL_STATEMENT = "SELECT * FROM PROFESSOR WHERE MATRICULA = '"
				+ matricula + "'";
		
		Professor professor = null;
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoDB.getInstance().getResultSet(
				SQL_STATEMENT);

		// Procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registro encontrados em objeto
				professor = new Professor(resultSet.getString(1),
						resultSet.getString(2));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoDB.getInstance().closeConetion();
		
		// retorna o professor
		return professor;
	}

}
