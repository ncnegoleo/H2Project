package recurso.cruds;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.padroesdeprojeto.data.dao.hsql.ConexaoHSQL;
import br.com.padroesdeprojeto.fachada.Fachada;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

public class RecusoHorarioTest {

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
	public void testAlocaTurma() throws H2Exception {
		addRecursosHorario();

		String aloca = fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		assertEquals("ok", aloca);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaIdVazio() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("", "Sexta", 18, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaIdNulo() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario(null, "Sexta", 18, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaIdInvalido() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TINGLADS", "Sexta", 18, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaDiaVazio() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "", 18, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaDiaNulo() throws H2Exception {
		addRecursosHorario();

		 fachada.alocaTurmaAoHorario("TPPADS", null, 18, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaDiaInvalido() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Domingo", 18, 21);
	}
	
	@Test(expected = H2Exception.class)
	public void testAlocaTurmaHoraIniInvalida() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", -1, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaHoraIniInvalida2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 24, 21);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaHoraFinInvalida() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, -1);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaHoraFinInvalida2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 24);
	}

	@Test(expected = H2Exception.class)
	public void testAlocaTurmaHoraFinMenorQIni() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 12);
	}

	@Test
	public void testAlocaTurmaSemChoq() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		String aloca = fachada.alocaTurmaAoHorario("TBDADS", "Sexta", 18, 21);
		assertEquals("ok", aloca);
	}
	
	@Test
	public void testAlocaTurmaChoque() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		String aloca = fachada.alocaTurmaAoHorario("TVTMU", "Sexta", 18, 21);
		assertEquals("Choque com S01A, 001", aloca);
	}
	
	@Test
	public void testDesalocaTurma() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 18, 21);
		assertEquals("ok", desaloca);
	}
	
	@Test
	public void testDesalocaTurma2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 15, 19);
		assertEquals("ok", desaloca);
	}
	
	@Test
	public void testDesalocaTurma3() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 19, 22);
		assertEquals("ok", desaloca);
	}
	
	public void testDesalocaTurmaSemChoq() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.alocaTurmaAoHorario("TVTMU", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 15, 22);
		assertEquals("ok", desaloca);
	}
	
	public void testDesalocaTurmaComChoq() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.alocaTurmaAoHorario("TVTMU", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 15, 18);
		assertEquals("Choque com S01A, 001", desaloca);
	}
	
	
	public void testDesalocaTurmaComChoq2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.alocaTurmaAoHorario("TVTMU", "Sexta", 18, 21);
		String desaloca = fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 19, 22);
		assertEquals("Choque com S01A, 001", desaloca);
	}
	
	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaIdVazio() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("", "Sexta", 18, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaIdNulo() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario(null, "Sexta", 18, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaIdInvalido() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TINGADS", "Sexta", 18, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaDiaVazio() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "", 18, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaDiaNulo() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", null, 18, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaDiaInvalido() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Domingo", 18, 22);
	}
	
	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaHoraIniInvalida() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", -1, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaHoraIniInvalida2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 24, 22);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaHoraFinInvalida() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 18, -1);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaHoraFinInvalida2() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 18, 24);
	}

	@Test(expected = H2Exception.class)
	public void testDesalocaTurmaHoraFinMenorQIni() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Sexta", 18, 21);
		fachada.desalocaTurmaDoHorario("TPPADS", "Sexta", 22, 18);
	}

	@Test
	public void testGetHorario() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Segunda", 18, 22);
		fachada.alocaTurmaAoHorario("TPPADS", "Quarta", 18, 20);
		String horario = fachada.getHorario("TPPADS");
		assertEquals("Segunda: 18 �s 22 Quarta: 18 �s 20 ", horario);
	}
	
	@Test(expected = H2Exception.class)
	public void testGetHorarioTurmaInvalida() throws H2Exception {
		addRecursosHorario();

		fachada.alocaTurmaAoHorario("TPPADS", "Segunda", 18, 22);
		fachada.getHorario("TINGADS");
	}
	
	private void addRecursosHorario() throws H2Exception {

		fachada.addProfessor("001", "Mirna");
		fachada.addProfessor("002", "Hugo");
		fachada.addProfessor("003", "Bruno");

		fachada.addCurso("ADS", "Analise e Desenvolvimento de Sistemas");
		fachada.addCurso("TCE", "Tecnologo em Construção de Edificios");
		fachada.addCurso("TMU", "Tecnico em Musica");

		fachada.addSala("S01A", "A");
		fachada.addSala("S11B", "B");
		fachada.addSala("S21C", "C");

		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2014.1", "ADS");
		fachada.addPeriodo("2015.1", "TMU");

		fachada.addDisciplinaAoPeriodo("PPADS", "Padrões de Projeto", 100,
				"ADS", "2013.1");
		fachada.addDisciplinaAoPeriodo("BDADS", "Banco de Dados", 150, "ADS",
				"2014.1");
		fachada.addDisciplinaAoPeriodo("VTMU", "Violão", 50, "TMU", "2015.1");

		fachada.addTurma("TPPADS", "ADS", "001", "PPADS", "S01A", "2013.1");
		fachada.addTurma("TVTMU", "TMU", "001", "VTMU", "S01A", "2015.1");
		fachada.addTurma("TBDADS", "ADS", "002", "BDADS", "S21C", "2014.1");
	}
}
