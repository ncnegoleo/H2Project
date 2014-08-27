package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.data.dao.DisciplinaDaoIF;
import br.com.padroesdeprojeto.bean.Disciplina;

/**
 * Esta classe faz a interação da entrada dos dados da disciplina com o banco de
 * dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class HSQLDisciplina implements DisciplinaDaoIF {

	@Override
	public void insere(Disciplina d, String siglaCurso, String idPeriodo) {

		// sql para adicionar uma nova disciplina
		String SQL_STATEMENT = "INSERT INTO DISCIPLINA(SIGLA_DISCIPLINA, PERIODO, "
				+ "NOME, SIGLA_CURSO, CARG_HORARIA) VALUES('"
				+ d.getSiglaDiscipina() + "', '"+ idPeriodo + "', '"
				+ d.getNomeDaDisciplina() + "', '" + siglaCurso + "', "
				+ d.getCargaHoraria() + ")";

		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void altera(Disciplina d, String id, String siglaCurso) {

		// sql para alterar uma disciplina
		String SQL_STATEMENT = "UPDATE DISCIPLINA SET NOME = '"
				+ d.getNomeDaDisciplina() + "' " + ", CARG_HORARIA = "
				+ d.getCargaHoraria() + ", PERIODO= '" + d.getPeriodo() 
				+ "' WHERE SIGLA_DISCIPLINA = '" + id + "' AND SIGLA_CURSO = '" 
				+ siglaCurso + "'";

		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void deleta(String id, String siglaCurso) {

		// sql para deletar uma disciplina
		String SQL_STATEMENT = "DELETE FROM DISCIPLINA WHERE SIGLA_DISCIPLINA = '"
				+ id + "' AND SIGLA_CURSO = '" + siglaCurso + "'";

		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Disciplina> getAllDiciplinas(String siglaCurso) {

		// sql para selecionar todas as disciplinas
		String SQL_STATEMENT = "SELECT * FROM DISCIPLINA WHERE SIGLA_CURSO = '"
				+ siglaCurso + "'";

		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui os registros encontrados em objeto que adicionados 
				// numa lista
				disciplinas.add(new Disciplina(resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5)));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna as disciplinas
		return disciplinas;
	}

	@Override
	public Disciplina getDisciplinaBySigla(String sigla, String siglaCurso) {
		
		// sql para recuperar uma disciplina
		String SQL_STATEMENT = "SELECT * FROM DISCIPLINA WHERE SIGLA_DISCIPLINA = '"
				+ sigla + "' AND SIGLA_CURSO = '" + siglaCurso + "'";

		Disciplina disciplina = null;
		
		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {
				// atribui o registro encontrado num objeto
				disciplina = new Disciplina(resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5));
			}
			
			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
		
		// retorna as disciplinas
		return disciplina;
	}

}
