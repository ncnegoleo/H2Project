package br.com.padroesdeprojeto.data.dao;

import br.com.padroesdeprojeto.data.dao.derby.DerbyCurso;
import br.com.padroesdeprojeto.data.dao.derby.DerbyDisciplina;
import br.com.padroesdeprojeto.data.dao.derby.DerbyPeriodo;
import br.com.padroesdeprojeto.data.dao.derby.DerbyProfessor;
import br.com.padroesdeprojeto.data.dao.derby.DerbySala;
import br.com.padroesdeprojeto.data.dao.derby.DerbyTurma;
import br.com.padroesdeprojeto.model.Curso;
import br.com.padroesdeprojeto.model.Disciplina;
import br.com.padroesdeprojeto.model.Periodo;
import br.com.padroesdeprojeto.model.Professor;
import br.com.padroesdeprojeto.model.Sala;
import br.com.padroesdeprojeto.model.Turma;

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
		return new DerbyProfessor();
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
		return new DerbyCurso();
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
		return new DerbySala();
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
		return new DerbyPeriodo();
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
		return new DerbyDisciplina();
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
		return new DerbyTurma();
	}
}
