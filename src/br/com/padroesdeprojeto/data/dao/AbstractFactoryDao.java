package br.com.padroesdeprojeto.data.dao;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Disciplina;
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
	 * Este método retorna uma instância de DerbyProfessor para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link DerbyProfessor}.
	 * 
	 * @see Professor
	 * @see ProfessorDaoIF
	 */
	public static ProfessorDaoIF createProfessorDaoIF() {
		return new HSQLProfessor();
	}
	
	/**
	 * Este método retorna uma instância de DerbyCurso para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Curso}.
	 * 
	 * @see Curso
	 * @see CursoDaoIF
	 */
	public static CursoDaoIF createCursoDaoIF() {
		return new HSQLCurso();
	}
	
	/**
	 * Este método retorna uma instância de DerbySala para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Sala}.
	 * 
	 * @see Sala
	 * @see SalaDaoIF
	 */
	public static SalaDaoIF createSalaDaoIF() {
		return new HSQLSala();
	}
	
	/**
	 * Este método retorna uma instância de DerbyPeriodo para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Periodo}.
	 * 
	 * @see Periodo
	 * @see PeriodoDaoIF
	 */
	public static PeriodoDaoIF createPeriodoDaoIF() {
		return new HSQLPeriodo();
	}

	
	/**
	 * Este método retorna uma instância de DerbyDisciplina para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Disciplina}.
	 * 
	 * @see Disciplina
	 * @see DisciplinaDaoIF
	 */
	public static DisciplinaDaoIF createDisciplinaDaoIF() {
		return new HSQLDisciplina();
	}
	
	/**
	 * Este método retorna uma instância de DerbyTurmapara que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Turma}.
	 * 
	 * @see Turma
	 * @see TurmaDaoIF
	 */
	public static TurmaDaoIF createTurmaDaoIF() {
		return new HSQLTurma();
	}
	
	/**
	 * Este método retorna uma instância de DerbyTurmapara que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link Horario}.
	 * 
	 * @see Horario
	 * @see HorarioDaoIF
	 */
	public static HSQLHorario createHorarioDaoIF() {
		return new HSQLHorario();
	}
}
