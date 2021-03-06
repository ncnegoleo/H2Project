package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.CursoDaoIF;
import br.com.padroesdeprojeto.bean.Curso;

/**
 * Esta classe faz a intera��o da entrada dos dados do curso com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class HSQLCurso implements CursoDaoIF {

	@Override
	public void insere(Curso c) {
		
		// sql para adicionar um novo curso
		String SQL_STATEMENT = "INSERT INTO CURSO(SIGLA, NOME) VALUES(" + "'"
				+ c.getId() + "', " + "'" + c.getNome() + "')";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conex�o
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void altera(Curso c) {
		
		// sql para alterar um curso
		String SQL_STATEMENT = "UPDATE CURSO SET SIGLA = '" + c.getId()
				+ "', NOME = '" + c.getNome() + "' WHERE SIGLA = '" + c.getId() + "'";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conex�o
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void deleta(String id) {
		
		// sql para remove um curso
		String SQL_STATEMENT = "DELETE FROM CURSO WHERE SIGLA = '" + id + "'";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conex�o
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Curso> getAllCursos() {
		
		// sql para selecionar todos os cursos
		String SQL_STATEMENT = "SELECT * FROM CURSO";
		
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				
				// atribui os registros encontrados em objeto que adicionados 
				// numa lista
				cursos.add(new Curso(resultSet.getString(1), resultSet
						.getString(2)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conex�o
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna os cursos
		return cursos;
	}

	@Override
	public Curso getCursoBySilga(String id) {

		// sql para selecionar um curso com o id referenciado
		String SQL_STATEMENT = "SELECT * FROM CURSO WHERE SIGLA = '"
				+ id + "'";

		Curso curso = null;

		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// Procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registro encontrados em objeto
				curso = new Curso(resultSet.getString(1),
						resultSet.getString(2));
			}

			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conex�o
		ConexaoHSQL.getInstance().closeConetion();

		// retorna o curso
		return curso;
	}

}
