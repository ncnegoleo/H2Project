package br.com.padroesdeprojeto.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.hsql.ConexaoHSQL;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;

public class TestesCRUDPeriodo {

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
	public void testAddNovoPeriodo() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2013.2", "ADS");
		String per = fachada.getPeriodo("2013.1", "ADS");
		assertEquals("2013.1 ADS", per);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoExistente() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2013.1", "ADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoCursoInexistente() throws H2Exception {
		fachada.addPeriodo("2013.1", "TCE");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoIdVazio() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("", "ADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoIdNulo() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo(null, "ADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoCursoVazio() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddPeriodoCursoNulo() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", null);
	}
	
	@Test
	public void testDeletaPeriodo() throws H2Exception {
		testAddNovoPeriodo();
		fachada.removePeriodo("ADS", "2013.2");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaPeriodoInexistente() throws H2Exception {
		fachada.removePeriodo("ADS", "2013.2");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaPeriodoIdVazio() throws H2Exception {
		fachada.removePeriodo("", "ADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaPeriodoIdNulo() throws H2Exception {
		fachada.removePeriodo(null, "ADS");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaPeriodoCursoVazio() throws H2Exception {
		fachada.removePeriodo("2013.1", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaPeriodoCursoNulo() throws H2Exception {
		fachada.removePeriodo("2013.2", null);
	}
}
