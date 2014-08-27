package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.data.dao.HorarioDaoIF;

public class HSQLHorario implements HorarioDaoIF {

	@Override
	public void aloca(Horario h) {

		// sql para alucar uma turma
		String SQL_STATEMENT = "INSERT INTO HORARIO"
				+ "(ID_TURMA_HOR, DIA_SEMANA, H_INICIO, H_FIM) VALUES" + "('"
				+ h.getIdTurma() + "', '" + h.getDiaSemana() + "', "
				+ h.getHoraInicio() + ", " + h.getHoraFim() + ")";

		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public void desaloca(Horario h) {

		// sql para remove um curso
		String SQL_STATEMENT = "DELETE FROM HORARIO WHERE ID_TURMA_HOR = '"
				+ h.getIdTurma() + "' AND DIA_SEMANA = '" + h.getDiaSemana()
				+ "' AND H_INICIO = " + h.getHoraInicio() + " AND H_FIM = "
				+ h.getHoraFim();

		// executa o sql no SGBD
		ConexaoHSQL.getInstance().executeSQLStatement(SQL_STATEMENT);

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();
	}

	@Override
	public ArrayList<Horario> getAllHorariosByTurma(String id) {

		// sql para selecionar todos os horarios da turma
		String SQL_SELECT_STATEMENT = "SELECT * FROM HORARIO WHERE ID_TURMA_HOR = '"
				+ id
				+ "' ORDER BY "
				+ "CASE DIA_SEMANA WHEN 'Segunda' THEN 1 "
				+ "WHEN 'Terça' THEN 2 WHEN 'Quarta' THEN 3 "
				+ "WHEN 'Quinta' THEN 4  ELSE 5 END ";

		ArrayList<Horario> horarios = new ArrayList<Horario>();

		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_SELECT_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {

				// atribui os registros encontrados em objeto que adicionados
				// numa lista
				horarios.add(new Horario(resultSet.getString(2), resultSet
						.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
			}

			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();

		// retorna os horarios
		return horarios;
	}

	@Override
	public ArrayList<Horario> getAllHorarios() {

		// sql para selecionar todos os horarios da turma
		String SQL_SELECT_STATEMENT = "SELECT * FROM HORARIO";

		ArrayList<Horario> horarios = new ArrayList<Horario>();

		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_SELECT_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {

				// atribui os registros encontrados em objeto que adicionados
				// numa lista
				horarios.add(new Horario(resultSet.getString(2), resultSet
						.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
			}

			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();

		// retorna os horarios
		return horarios;
	}

	@Override
	public Horario getHorarioByHIntevalo(Horario h, int hora) {

		// sql para selecionar todos os horarios da turma
		String SQL_SELECT_STATEMENT = "SELECT * FROM HORARIO WHERE "
				+ "ID_TURMA_HOR = '" + h.getIdTurma() + "' AND "
				+ "DIA_SEMANA = '" + h.getDiaSemana() + "' AND "
				+ "H_INICIO <= " + hora + " AND " + "H_FIM >= " + hora;

		Horario horario = null;

		// executa o sql no SGBD
		ResultSet resultSet = ConexaoHSQL.getInstance().getResultSet(
				SQL_SELECT_STATEMENT);

		// procura e retorna todos os registros //
		try {
			while (resultSet.next()) {

				// atribui o registro encontrados em um objeto
				horario = new Horario(resultSet.getString(2),
						resultSet.getString(3), resultSet.getInt(4),
						resultSet.getInt(5));
			}

			// fecha o resultSet
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// fecha a conexão
		ConexaoHSQL.getInstance().closeConetion();

		// retorna os horarios
		return horario;
	}

}
