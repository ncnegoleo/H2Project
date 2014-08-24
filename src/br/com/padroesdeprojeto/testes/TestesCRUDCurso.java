package br.com.padroesdeprojeto.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.derby.ConexaoDB;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;

public class TestesCRUDCurso {

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
	public void testAddNovoCurso() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		String curso = fachada.getCurso("ADS");
		assertEquals("ADS - Analise e Desenvolvimento de Sistemas", curso);
	}

	@Test(expected = H2Exception.class)
	public void testAddCursoExistente() throws H2Exception {
		testAddNovoCurso();
		fachada.addCurso("ADS", "Analistas e Desenvolvedores de Sistemas");
	}

	@Test(expected = H2Exception.class)
	public void testAddCursoIdVazio() throws H2Exception {
		fachada.addCurso("", "Tecnólogo em Construção de Edificios");
	}

	@Test(expected = H2Exception.class)
	public void testAddCursoIdNulo() throws H2Exception {
		fachada.addCurso(null, "Tecnólogo em Construção de Edificios");
	}

	@Test(expected = H2Exception.class)
	public void testAddCursoNomeVazio() throws H2Exception {
		fachada.addCurso("TCE", "");
	}

	@Test(expected = H2Exception.class)
	public void testAddCursoNomeNulo() throws H2Exception {
		fachada.addCurso("TCE", null);
	}

	@Test
	public void testAlteraCurso() throws H2Exception {
		testAddNovoCurso();
		fachada.alterarCurso("ADS", "Analistas e Desenvolvedores de Sistemas");
		String curso = fachada.getCurso("ADS");
		assertEquals("ADS - Analistas e Desenvolvedores de Sistemas", curso);
	}

	@Test(expected = H2Exception.class)
	public void testAlteraCursoInexistente() throws H2Exception {
		fachada.alterarCurso("TCE", "Tecnólogo em Construção de Edificios");
	}

	@Test(expected = H2Exception.class)
	public void testAlteraCursoIdVazio() throws H2Exception {
		fachada.alterarCurso("", "Tecnólogo em Construção de Edificios");
	}

	@Test(expected = H2Exception.class)
	public void testAlteraCursoIdNulo() throws H2Exception {
		fachada.alterarCurso(null, "Tecnólogo em Construção de Edificios");
	}

	@Test(expected = H2Exception.class)
	public void testAlteraCursoNomeVazio() throws H2Exception {
		fachada.alterarCurso("TCE", "");
	}

	@Test(expected = H2Exception.class)
	public void testAlteraCursoNomeNulo() throws H2Exception {
		fachada.alterarCurso("TCE", null);
	}

	@Test
	public void testDeletaCurso() throws H2Exception {
		testAddNovoCurso();
		fachada.removeCurso("ADS");
	}

	@Test(expected = H2Exception.class)
	public void testDeletaCursoInexistente() throws H2Exception {
		fachada.removeCurso("TCE");
	}

	@Test(expected = H2Exception.class)
	public void testDeletaCursoIdVazio() throws H2Exception {
		fachada.removeCurso("");
	}

	@Test(expected = H2Exception.class)
	public void testDeletaCursoIdNulo() throws H2Exception {
		fachada.removeCurso(null);
	}
}
