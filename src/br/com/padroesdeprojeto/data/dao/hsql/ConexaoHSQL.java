package br.com.padroesdeprojeto.data.dao.hsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta classe cria o banco de dados, a conex�o, as tabelas e executa os
 * comandos de altera��o e recupera��o dos dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class ConexaoHSQL {

	// instancia da classe
	private static ConexaoHSQL conexao = null;

	private final String DBNAME = "h2dbhsql";
	private final String DRIVER = "org.hsqldb.jdbcDriver";
	private final String PATH = System.getProperty("user.dir") + "\\data\\" + DBNAME;
	private final String JDBC_URL = "jdbc:hsqldb:file:/" + PATH;
	private final String USER = "SA";
	private final String PASSWORD = "";

	/* conex�o do banco */
	private Connection conn = null;

	/// CRIAR UMA ENUM PARA AS TABELAS
	
	/* sql da tabela PROFESSOR */
	private final String TABELA_PROFESSOR = "PROFESSOR ("
			+ "MATRICULA VARCHAR(30) NOT NULL, "
			+ "NOME VARCHAR(50) NOT NULL,"
			+ "CONSTRAINT professor_pk_id PRIMARY KEY (MATRICULA))";
	
	/* sql da tabela curso */
	private final String TABELA_CURSO = "CURSO ("
			+ "SIGLA VARCHAR(30) NOT NULL, " 
			+ "NOME VARCHAR(50) NOT NULL, "
			+ "CONSTRAINT curso_pk_id PRIMARY KEY (SIGLA))";

	/* sql da tabela sala */
	private final String TABELA_SALA = "SALA ("
			+ "CODIGO VARCHAR(30) NOT NULL, "
			+ "BLOCO VARCHAR(50) NOT NULL, "
			+ "CONSTRAINT sala_pk_id PRIMARY KEY (CODIGO))";
	
	/* sql da tabela periodo */
	private final String TABELA_PERIODO= "PERIODO ("
			+ "ID_PERIODO VARCHAR(30) NOT NULL, "
			+ "SIGLA_CURSO VARCHAR(30), " 
			+ "CONSTRAINT periodo_pk_id PRIMARY KEY (ID_PERIODO), "
			+ "CONSTRAINT curso_fk_periodo FOREIGN KEY (SIGLA_CURSO)"
			+ "REFERENCES CURSO(SIGLA) ON DELETE CASCADE)";
	
	/* sql da tabela disciplina */
	private final String TABELA_DISCIPLINA = "DISCIPLINA ("
			+ "SIGLA_DISCIPLINA VARCHAR(30) NOT NULL, "
			+ "PERIODO VARCHAR(30) NOT NULL, "
			+ "NOME VARCHAR(50) NOT NULL, "
			+ "SIGLA_CURSO VARCHAR(30), " 
			+ "CARG_HORARIA INT NOT NULL, "
			+ "CONSTRAINT disciplina_pk_id PRIMARY KEY (SIGLA_DISCIPLINA), "
			+ "CONSTRAINT peri_fk_disciplina FOREIGN KEY (PERIODO)"
			+ "REFERENCES PERIODO(ID_PERIODO) ON DELETE CASCADE, "
			+ "CONSTRAINT curso_fk_disciplina FOREIGN KEY (SIGLA_CURSO)"
			+ "REFERENCES CURSO(SIGLA) ON DELETE CASCADE)";
	
	/* sql da tabela disciplina */
	private final String TABELA_TURMA = "TURMA ("
			+ "ID_TURMA VARCHAR(30) NOT NULL, "
			+ "ID_CURSO VARCHAR(30) NOT NULL, "
			+ "ID_PROF VARCHAR(30) NOT NULL, "
			+ "ID_DISC VARCHAR(30) NOT NULL,"
			+ "ID_SALA VARCHAR(30) NOT NULL, "
			+ "ID_PERIODO VARCHAR(30) NOT NULL, "
			+ "CONSTRAINT turma_pk_id PRIMARY KEY (ID_TURMA), " // PK TURMA
			+ "CONSTRAINT curso_fk_turma FOREIGN KEY (ID_CURSO) " // FK CURSO
			+ "REFERENCES CURSO(SIGLA) ON DELETE CASCADE, "
			+ "CONSTRAINT prof_fk_turma FOREIGN KEY (ID_PROF) " // FK PROFESSOR
			+ "REFERENCES PROFESSOR(MATRICULA) ON DELETE CASCADE, "
			+ "CONSTRAINT disc_fk_turma FOREIGN KEY (ID_DISC) " // FK DISCIPLINA
			+ "REFERENCES DISCIPLINA(SIGLA_DISCIPLINA) ON DELETE CASCADE, "
			+ "CONSTRAINT sala_fk_turma FOREIGN KEY (ID_SALA) " // FK DISCIPLINA
			+ "REFERENCES SALA(CODIGO) ON DELETE CASCADE, "
			+ "CONSTRAINT peri_fk_turma FOREIGN KEY (ID_PERIODO) " // FK PERIODO
			+ "REFERENCES PERIODO(ID_PERIODO) ON DELETE CASCADE)";
	
	/* sql da tabela horario */
	private final String TABELA_HORARIO = "HORARIO ("
			+ "ID_HORARIO INT IDENTITY, "
			+ "ID_TURMA_HOR VARCHAR(30) NOT NULL,"
			+ "DIA_SEMANA VARCHAR(50) NOT NULL, "
			+ "H_INICIO INT NOT NULL, "
			+ "H_FIM INT NOT NULL, "
			+ "CONSTRAINT turma_fk_horario FOREIGN KEY (ID_TURMA_HOR) " // FK CURSO
			+ "REFERENCES TURMA(ID_TURMA) ON DELETE CASCADE)";
	
	/* m�todo construtor que cria uma conex�o e as tabelas */
	private ConexaoHSQL() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.err
					.println("N�o foi possivel se conectar com o banco de dados");
			System.out.println(e);
		}
		//createTables();
	}

	/**
	 * Este m�todo retorna uma conex�o, se ela ainda n�o existir, � criada uma
	 * nova.
	 * 
	 * @return Uma inst�ncia da conex�o com o banco.
	 */
	public static ConexaoHSQL getInstance() {
		if (conexao == null) {
			conexao = new ConexaoHSQL();
		}
		return conexao;
	}

	/**
	 * Este m�todo cria todas as tabelas do banco.
	 */
	public void createTables() {
		try {
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_PROFESSOR);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_CURSO);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_SALA);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_PERIODO);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_DISCIPLINA);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_TURMA);
			conn.createStatement().execute("CREATE TABLE IF NOT EXISTS " + TABELA_HORARIO);
			// Add more Tables
		} catch (SQLException e) {
			System.err.print("Ocorreu um erro na cria��o das tabelas: " + e);
		}
	}

	/**
	 * Este m�todo deleta todas as tabelas do banco.
	 */
	public void dropTables() {
		try {
			conn.createStatement().execute("DROP TABLE IF EXISTS HORARIO");
			conn.createStatement().execute("DROP TABLE IF EXISTS TURMA");
			conn.createStatement().execute("DROP TABLE IF EXISTS DISCIPLINA");
			conn.createStatement().execute("DROP TABLE IF EXISTS PERIODO");
			conn.createStatement().execute("DROP TABLE IF EXISTS PROFESSOR");
			conn.createStatement().execute("DROP TABLE IF EXISTS CURSO");
			conn.createStatement().execute("DROP TABLE IF EXISTS SALA");
			// Add more Tables
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Este m�todo retorna a conex�o com o banco de dados.
	 * 
	 * @return Conex�o com o banco de dados.
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * Este m�todo retorna um statement da conex�o para a declara��o dos
	 * comandos SQL.
	 * 
	 * @return Uma inst�ncia de statement.
	 */
	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			System.err.println("N�o foi possivel criar o statement" + e);
		}
		return null;
	}

	/**
	 * Este m�todo executa um SQL em um statement para altera��o do banco de
	 * dados.
	 * 
	 * @param SQL
	 *            Instru��o SQL.
	 */
	public void executeSQLStatement(String SQL) {
		try {
			getStatement().execute(SQL);
		} catch (SQLException e) {
			System.err.println("Ocorreu um erro nos valores passados."
					+ "\nVerifique e tente novamente!" + e);
		}
	}

	/**
	 * Este m�todo executa um SQL em um statement para recupera��o de registro
	 * no banco de dados e atribui-os a um conjunto de resultados.
	 * 
	 * @param SQL
	 *            Instru��o SQL.
	 * @return Um conjunto de resultados (ResultSet).
	 */
	public ResultSet getResultSet(String SQL) {
		try {
			return getStatement().executeQuery(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Fecha a conex�o.
	 */
	public void closeConetion() {
		if (getConnection() != null) {
			try {
				conn.close();
				conexao = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
