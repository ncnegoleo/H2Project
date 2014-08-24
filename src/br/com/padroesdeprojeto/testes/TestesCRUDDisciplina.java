package br.com.padroesdeprojeto.testes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.derby.ConexaoDB;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;

public class TestesCRUDDisciplina {

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
	public void testAddNovaDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		String disc = fachada.getDisciplina("ADS", "PP");
		assertEquals("PP - Padrões de Projeto", disc);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaIdDisciplinaVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("", "Padrões de Projeto", 100, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaIdDisciplinaNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo(null, "Padrões de Projeto", 100, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaNomeDisciplinaVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "", 100, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaNomeDisciplinaNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", null, 100, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaCHZero() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 0, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaCHMenorZero() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", -10, "ADS", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaCursoVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaCursoNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, null, "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaCursoInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "TCE", "2013.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaPeriodoVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaPeriodoNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", null);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaPeriodoInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2015.1");
	}
	
	private void addCursoEPeriodo() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", "ADS");
	}

}
