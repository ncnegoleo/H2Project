package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.bean.Professor;
import br.com.padroesdeprojeto.bean.Sala;
import br.com.padroesdeprojeto.bean.Turma;

/**
 * Esta classe serve para a criação de objetos e para a manipulação dos dados dos
 * respectivos objetos.
 * 
 * @author Leonardo Soares.
 * 
 */
public class AbstractFactoryRecurso {
	
	/**
	 * Este método retorna uma instância de RecursoProfessor para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoProfessor}.
	 * 
	 * @see Professor
	 * @see RecursoProfessor
	 */
	public static RecursoProfessor createRecursoProfessor() {
		return new RecursoProfessor();
	}
	
	/**
	 * Este método retorna uma instância de RecursoCurso para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoCurso}.
	 * 
	 * @see Curso
	 * @see RecursoCurso
	 */
	public static RecursoCurso createRecursoCurso() {
		return new RecursoCurso();
	}
	
	/**
	 * Este método retorna uma instância de RecursoSala para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoSala}.
	 * 
	 * @see Sala
	 * @see RecursoSala
	 */
	public static RecursoSala createRecursoSala() {
		return new RecursoSala();
	}
	
	/**
	 * Este método retorna uma instância de RecursoPeriodo para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoPeriodo}.
	 * 
	 * @see Periodo
	 * @see RecursoPeriodo
	 */
	public static RecursoPeriodo createRecursoPeriodo() {
		return new RecursoPeriodo();
	}
	
	/**
	 * Este método retorna uma instância de RecursoDisciplina para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoDisciplina}.
	 * 
	 * @see Disciplina
	 * @see RecursoDisciplina
	 */
	public static RecursoDisciplina createRecursoDisciplina() {
		return new RecursoDisciplina();
	}
	
	/**
	 * Este método retorna uma instância de RecursoTurma para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoTurma}.
	 * 
	 * @see Turma
	 * @see RecursoTurma
	 */
	public static RecursoTurma createRecursoTurma() {
		return new RecursoTurma();
	}
	
	/**
	 * Este método retorna uma instância de RecursoHorario para que seja possível
	 * manipular seus métodos staticamente.
	 * 
	 * @return Um novo objeto {@link RecursoHorario}.
	 * 
	 * @see Horario
	 * @see RecursoHorario
	 */
	public static RecursoHorario createRecursoHorario() {
		return new RecursoHorario();
	}
}
