package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.SalaDaoIF;
import br.com.padroesdeprojeto.bean.Sala;

/**
 * Esta classe faz a interação da entrada dos dados da sala com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class HSQLSala implements SalaDaoIF {

	@Override
	public void insere(Sala s) {
		
		// sql para adicionar uma nova sala
		String SQL_STATEMENT = "INSERT INTO SALA(CODIGO, BLOCO) VALUES('"
				+ s.getId() + "', '" + s.getBloco() + "')";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void altera(Sala s) {
		
		// sql para alterar uma sala
		String SQL_STATEMENT = "UPDATE SALA SET BLOCO = '" + s.getBloco() 
				+ "' WHERE CODIGO = '" + s.getId() + "'";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void deleta(String id) {
		
		// sql para remove uma sala
		String SQL_STATEMENT = "DELETE FROM SALA WHERE CODIGO = '" + id + "'";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Sala> getAllSalas() {
		
		// sql para selecionar todas as salas
		String SQL_STATEMENT = "SELECT * FROM SALA";
		
		
		ArrayList<Sala> salas = new ArrayList<Sala>();
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		/* Procura e retorna todos os registros */
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que adicionados 
				// numa lista
				salas.add(new Sala(resultSet.getString(1), resultSet.getString(2)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna as salas
		return salas;
	}

	@Override
	public Sala getSalaById(String id) {
		
		// sql para selecionar um professor com o id referenciado
		String SQL_STATEMENT = "SELECT * FROM SALA WHERE CODIGO = '" + id
				+ "'";
		
		Sala sala = null;
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// Procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registro encontrados em objeto
				sala = new Sala(resultSet.getString(1), resultSet.getString(2));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna o professor
		return sala;
	}
}