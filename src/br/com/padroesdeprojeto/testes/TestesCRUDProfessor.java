package br.com.padroesdeprojeto.testes;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.derby.ConexaoDB;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;
import br.com.padroesdeprojeto.fachada.FachadaIF;

public class TestesCRUDProfessor {
	
	FachadaIF fachada;

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
	public void testAddNovoProfessor() throws H2Exception {
		fachada.addProfessor("001", "Mirna");
		String prof = fachada.getProfessor("001");
		assertEquals("001 - Mirna", prof);
	}

	@Test(expected = H2Exception.class)
	public void testAddProfessorExistente() throws H2Exception {
		testAddNovoProfessor();
		fachada.addProfessor("001", "Bruno");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddProfessorMatriculaVazia() throws H2Exception {
		fachada.addProfessor("", "Mirna");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddProfessorMatriculaNula() throws H2Exception {
		fachada.addProfessor(null, "Mirna");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddProfessorNomeVazio() throws H2Exception {
		fachada.addProfessor("001", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddProfessorNomeNulo() throws H2Exception {
		fachada.addProfessor("001", null);
	}
	
	@Test
	public void testAlteraProfessor() throws H2Exception {
		testAddNovoProfessor();
		fachada.alteraProfessor("001", "Hugo");
		String prof = fachada.getProfessor("001");
		assertEquals("001 - Hugo", prof);
	}

	@Test(expected = H2Exception.class)
	public void testAlteraProfessorInexistente() throws H2Exception {
		fachada.alteraProfessor("003", "Hugo");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraProfessorMatriculaVazia() throws H2Exception {
		fachada.alteraProfessor("", "Hugo");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraProfessorMatriculaNula() throws H2Exception {
		fachada.alteraProfessor(null, "Hugo");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraProfessorNomeVazio() throws H2Exception {
		fachada.alteraProfessor("001", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraProfessorNomeNulo() throws H2Exception {
		fachada.alteraProfessor("001", null);
	}
	
	@Test
	public void testDeletaProfessor() throws H2Exception {
		testAddNovoProfessor();
		fachada.removeProfessor("001");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaProfessorInexistente() throws H2Exception {
		fachada.removeProfessor("003");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaProfessorMatriculaVazia() throws H2Exception {
		fachada.removeProfessor("");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaProfessorMatriculaNula() throws H2Exception {
		fachada.removeProfessor(null);
	}
}
