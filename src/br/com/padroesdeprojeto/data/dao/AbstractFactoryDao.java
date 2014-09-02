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
 * Esta classe serve para a cria��o de objetos e para a manipula��o dos dados dos
 * respectivos objetos.
 * 
 * @author Leonardo Soares.
 * 
 */
public class AbstractFactoryDao {

	/**
	 * Este m�todo retorna uma inst�ncia de HSQLProfessor para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLCurso para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLSala para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLPeriodo para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLDisciplina para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLTurma para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de HSQLHorario para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
