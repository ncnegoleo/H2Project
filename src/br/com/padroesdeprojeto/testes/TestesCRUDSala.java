package br.com.padroesdeprojeto.testes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.derby.ConexaoDB;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.fachada.Fachada;

public class TestesCRUDSala {

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
	public void testAddNovaSala() throws H2Exception {
		fachada.addSala("S10", "D");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddSalaExistente() throws H2Exception {
		testAddNovaSala();
		fachada.addSala("S10", "E");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddSalaIdVazio() throws H2Exception {
		fachada.addSala("", "D");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddSalaIdNolo() throws H2Exception {
		fachada.addSala(null, "D");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddSalaBlocoVazio() throws H2Exception {
		fachada.addSala("S12", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAddSalaBlocoNulo() throws H2Exception {
		fachada.addSala("S12", null);
	}

	@Test
	public void testAlteraSala() throws H2Exception {
		testAddNovaSala();
		fachada.alteraSala("S10", "Z");
	}

	@Test(expected = H2Exception.class)
	public void testAlteraSalaInexistente() throws H2Exception {
		fachada.alteraSala("S13", "D");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraSalaIdVazio() throws H2Exception {
		fachada.alteraSala("", "H");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraSalaIdNulo() throws H2Exception {
		fachada.alteraSala(null, "H");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraSalaBlocoVazio() throws H2Exception {
		fachada.alteraSala("S12", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraSalaBlocoNulo() throws H2Exception {
		fachada.alteraSala("S12", null);
	}

	@Test
	public void testDeletaSala() throws H2Exception {
		testAddNovaSala();
		fachada.removeSala("S10");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaSalaInexistente() throws H2Exception {
		fachada.removeSala("S14");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaSalaIdVazio() throws H2Exception {
		fachada.removeSala("");;
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaSalaIdNulo() throws H2Exception {
		fachada.removeSala(null);
	}
}
