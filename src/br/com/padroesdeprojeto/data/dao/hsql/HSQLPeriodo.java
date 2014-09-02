package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.PeriodoDaoIF;
import br.com.padroesdeprojeto.bean.Periodo;

/**
 * Esta classe faz a interação da entrada dos dados do periodo com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class HSQLPeriodo implements PeriodoDaoIF {

	@Override
	public void insere(Periodo p, String siglaCurso) {
		
		// sql para adicionar um novo periodo
		String SQL_STATEMENT = "INSERT INTO PERIODO(ID_PERIODO, SIGLA_CURSO) VALUES("
				+ "'" + p.getNomePeriodo() + "', " + "'" + siglaCurso + "')";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void deleta(String nomePeriodo, String siglaCurso) {
		
		// sql para deletar um periodo
		String SQL_STATEMENT = "DELETE FROM PERIODO WHERE ID_PERIODO = '" + nomePeriodo
				+ "' AND SIGLA_CURSO = '" + siglaCurso + "'";
		
		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();

	}

	@Override
	public ArrayList<Periodo> getAllPeriodos(String siglaCurso) {

		// sql para recuperar todos os periodos
		String SQL_STATEMENT = "SELECT * FROM PERIODO";

		ArrayList<Periodo> periodos = new ArrayList<Periodo>();
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// Procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que são
				// adicionados numa lista
				periodos.add(new Periodo(resultSet.getString(1), 
						resultSet.getString(2)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna os professores
		return periodos;
	}

	@Override
	public Periodo getPeriodoByName(String nome, String siglaCurso) {
		
		// sql para selecionar um periodo com o nome e o curso referenciados
		String SQL_STATEMENT = "SELECT * FROM PERIODO WHERE ID_PERIODO = '" + nome
				+ "' AND SIGLA_CURSO = '" + siglaCurso + "'";
		
		Periodo periodo = null;
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// Procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registro encontrados em objeto
				periodo = new Periodo(resultSet.getString(1), 
						resultSet.getString(2));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna o professor
		return periodo;
	}
}
