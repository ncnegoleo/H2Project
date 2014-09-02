package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.bean.Professor;
import br.com.padroesdeprojeto.bean.Sala;
import br.com.padroesdeprojeto.bean.Turma;

/**
 * Esta classe serve para a cria��o de objetos e para a manipula��o dos dados dos
 * respectivos objetos.
 * 
 * @author Leonardo Soares.
 * 
 */
public class AbstractFactoryRecurso {
	
	/**
	 * Este m�todo retorna uma inst�ncia de RecursoProfessor para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoCurso para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoSala para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoPeriodo para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoDisciplina para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoTurma para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
	 * Este m�todo retorna uma inst�ncia de RecursoHorario para que seja poss�vel
	 * manipular seus m�todos staticamente.
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
