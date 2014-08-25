package br.com.padroesdeprojeto.testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.derby.ConexaoDB;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;

public class TestesCRUDTurma {

	Fachada fachada;
	
	@Before
	public void setUp() throws Exception {
		fachada = new Fachada();
		ConexaoDB.getInstance().dropTables();
		ConexaoDB.getInstance().createTables();
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
		fachada.addTurma("TPPADS", "ADS", "001", "PP", "S13B", "2013.1");
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
	
	private void addObjetosPTurma() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addProfessor("001", "Mirna");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.addSala("S12C", "C");
	}
}
