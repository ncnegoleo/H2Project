package br.com.padroesdeprojeto.data.dao;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.bean.Professor;
import br.com.padroesdeprojeto.bean.Sala;
import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLCurso;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLDisciplina;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLHorario;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLPeriodo;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLProfessor;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLSala;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLTurma;

/**
 * Esta classe serve para a criação de objetos e para a manipulação dos dados dos
 * respectivos objetos.
 * 
 * @author Leonardo Soares.
 * 
 */
public class AbstractFactoryDao {

	/**
	 * Este método retorna uma instância de HSQLProfessor para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLProfessor}.
	 * 
	 * @see Professor
	 * @see ProfessorDaoIF
	 */
	public static ProfessorDaoIF createProfessorDaoIF() {
		return new HSQLProfessor();
	}
	
	/**
	 * Este método retorna uma instância de HSQLCurso para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLCurso}.
	 * 
	 * @see Curso
	 * @see CursoDaoIF
	 */
	public static CursoDaoIF createCursoDaoIF() {
		return new HSQLCurso();
	}
	
	/**
	 * Este método retorna uma instância de HSQLSala para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLSala}.
	 * 
	 * @see Sala
	 * @see SalaDaoIF
	 */
	public static SalaDaoIF createSalaDaoIF() {
		return new HSQLSala();
	}
	
	/**
	 * Este método retorna uma instância de HSQLPeriodo para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLPeriodo}.
	 * 
	 * @see Periodo
	 * @see PeriodoDaoIF
	 */
	public static PeriodoDaoIF createPeriodoDaoIF() {
		return new HSQLPeriodo();
	}

	
	/**
	 * Este método retorna uma instância de HSQLDisciplina para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLDisciplina}.
	 * 
	 * @see Disciplina
	 * @see DisciplinaDaoIF
	 */
	public static DisciplinaDaoIF createDisciplinaDaoIF() {
		return new HSQLDisciplina();
	}
	
	/**
	 * Este método retorna uma instância de HSQLTurma para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLTurma}.
	 * 
	 * @see Turma
	 * @see TurmaDaoIF
	 */
	public static TurmaDaoIF createTurmaDaoIF() {
		return new HSQLTurma();
	}
	
	/**
	 * Este método retorna uma instância de HSQLHorario para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link HSQLHorario}.
	 * 
	 * @see Horario
	 * @see HorarioDaoIF
	 */
	public static HSQLHorario createHorarioDaoIF() {
		return new HSQLHorario();
	}
}
