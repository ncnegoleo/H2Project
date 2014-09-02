package recurso.cruds;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.hsql.ConexaoHSQL;
import br.com.padroesdeprojeto.fachada.Fachada;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

public class RecursoTurmaTest {

	Fachada fachada;
	
	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
		ConexaoHSQL.getInstance().dropTables();
		ConexaoHSQL.getInstance().createTables();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testAddNovaTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		String turma = fachada.getTurma("TPPADS");
		assertEquals("(TPPADS, ADS, 001, PP, S12C, 2013.1)", turma);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaExistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("", "ADS", "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma(null, "ADS", "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdCursoVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "", "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdCursoNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", null, "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaCursoInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "TCE", "001", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdPorfessorVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdPorfessorNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", null, "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaPorfessorInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "005", "PP", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdDisciplinaVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdDisciplinaNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", null, "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaDisciplinaInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "ING", "S12C", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdSalaVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdSalaNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", null, "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaSalaInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S14E", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdPeriodoVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaIdPeriodoNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", null);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaTurmaPeriodoInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2015.2");
	}
	
	@Test
	public void testAlteraProfessorTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Professor", "002");
		String turma = fachada.getTurma("TPPADS");
		assertEquals("(TPPADS, ADS, 002, PP, S12C, 2013.1)", turma);
	}
	
	@Test
	public void testAlteraDisciplinaTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Disciplina", "BD");
		String turma = fachada.getTurma("TPPADS");
		assertEquals("(TPPADS, ADS, 001, BD, S12C, 2013.1)", turma);
	}
	
	@Test
	public void testAlteraSalaTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Sala", "S13B");
		String turma = fachada.getTurma("TPPADS");
		assertEquals("(TPPADS, ADS, 001, PP, S13B, 2013.1)", turma);
	}
	
	@Test
	public void testAlteraPeriodoTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Periodo", "2014.1");
		String turma = fachada.getTurma("TPPADS");
		assertEquals("(TPPADS, ADS, 001, PP, S12C, 2014.1)", turma);
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraProfessorInvalidoTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Professor", "003");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaInvalidaTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Disciplina", "ING");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraSalaInvalidaTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Sala", "S15G");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraPeriodoInvalidoTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Periodo", "2015.8");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaIdVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("", "Sala", "S12C");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaIdNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma(null, "Sala", "S12C");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaIdInvalido() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TINGADS", "Sala", "S12C");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaAtributoVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "", "002");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaAtributoNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", null, "002");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaAtributoInvalido() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Id", "TDBADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaNovoValorVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Professor", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaNovoValorNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Professor", null);
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraTurmaNovoValorInvalido() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.alteraTurma("TPPADS", "Professor", "008");
	}
	
	@Test
	public void testRemoveTurma() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.removerTurma("TPPADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testRemoveTurmaIdVazio() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.removerTurma("");
	}
	
	@Test(expected = H2Exception.class)
	public void testRemoveTurmaIdNulo() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.removerTurma(null);
	}
	
	@Test(expected = H2Exception.class)
	public void testRemoveTurmaInexistente() throws H2Exception {
		addObjetosPTurma();
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S12C", "2013.1");
		fachada.removerTurma("TDBADS");
	}
	
	private void addObjetosPTurma() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addProfessor("001", "Mirna");
		fachada.addProfessor("002", "Hugo");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2014.1", "ADS");
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.addDisciplinaAoPeriodo("BD", "Banco de Dados", 100, "ADS", "2013.1");
		fachada.addSala("S12C", "C");
		fachada.addSala("S13B", "B");
	}
}
