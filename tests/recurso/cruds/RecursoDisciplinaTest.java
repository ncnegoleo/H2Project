package recurso.cruds;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.bean.Disciplina;
import br.com.padroesdeprojeto.data.dao.hsql.ConexaoHSQL;
import br.com.padroesdeprojeto.data.dao.hsql.HSQLDisciplina;
import br.com.padroesdeprojeto.fachada.Fachada;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

public class RecursoDisciplinaTest {

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
	public void testAddNovaDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		String disc = fachada.getDisciplina("ADS", "PP");
		assertEquals("PP - Padrões de Projeto", disc);
	}
	
	@Test(expected = H2Exception.class)
	public void testAddNovaDisciplinaExistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.addDisciplinaAoPeriodo("PP", "Padrones de Proheto", 100, "ADS", "2013.1");
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
	
	@Test
	public void testAlteraNomeDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "Nome", "Padron de Projeton");
		String disc = fachada.getDisciplina("ADS", "PP");
		assertEquals("PP - Padron de Projeton", disc);
	}
	
	@Test
	public void testAlteraCargaHorariaDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "CargaHoraria", "200");
		HSQLDisciplina dc = new HSQLDisciplina();
		Disciplina d = dc.getDisciplinaBySigla("PP", "ADS");
		assertEquals(200, d.getCargaHoraria());
	}
	
	@Test
	public void testAlteraPeriodoDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "Periodo", "2013.2");
		HSQLDisciplina dc = new HSQLDisciplina();
		Disciplina d = dc.getDisciplinaBySigla("PP", "ADS");
		assertEquals("2013.2", d.getPeriodo());
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraPeriodoInexistenteDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "Periodo", "2014.1");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaCursoVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("", "PP", "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaCursoNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina(null, "PP", "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaCursoInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("TCE", "PP", "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaIdVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "", "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaIdNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", null, "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaIdInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "ING", "Nome", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaAtributoVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaAtributoNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", null, "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaAtributoInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "Periodo", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaCargaHorariaInvalida() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "Cem", "Padron de Projeton");
	}
	
	@Test(expected = H2Exception.class)
	public void testAlteraDisciplinaCargaHorariaZero() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.alteraDisciplina("ADS", "PP", "0", "Padron de Projeton");
	}
	
	@Test
	public void testDeletaDisciplina() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("ADS", "PP");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaCursoVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("", "PP");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaCursoNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina(null, "PP");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaCursoInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("TCE", "PP");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaIdVazio() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("ADS", "");
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaIdNulo() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("ADS", null);
	}
	
	@Test(expected = H2Exception.class)
	public void testDeletaDisciplinaIdInexistente() throws H2Exception {
		addCursoEPeriodo();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2013.1");
		fachada.removeDisciplina("ADS", "ING");
	}
	
	private void addCursoEPeriodo() throws H2Exception {
		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2013.2", "ADS");
	}

}
