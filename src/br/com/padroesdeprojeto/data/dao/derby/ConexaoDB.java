package br.com.padroesdeprojeto.data.dao.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta classe cria o banco de dados, a conexão, as tabelas e executa os
 * comandos de alteração e recuperação dos dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public class ConexaoDB {

	// instancia da classe
	private static ConexaoDB conexao = null;

	private final String DBNAME = "h2db";
	private final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private final String JDBC_URL = "jdbc:derby:" + DBNAME + ";create=true";

	/* conexão do banco */
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
	
	/* método construtor que cria uma conexão e as tabelas */
	private ConexaoDB() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(JDBC_URL);
		} catch (ClassNotFoundException | SQLException e) {
			System.err
					.println("Não foi possivel se conectar com o banco de dados");
		}
		//createTables();
	}

	/**
	 * Este método retorna uma conexão, se ela ainda não existir, é criada uma
	 * nova.
	 * 
	 * @return Uma instância da conexão com o banco.
	 */
	public static ConexaoDB getInstance() {
		if (conexao == null) {
			conexao = new ConexaoDB();
		}
		return conexao;
	}

	/**
	 * Este método cria todas as tabelas do banco.
	 */
	public void createTables() {
		try {
			conn.createStatement().execute("CREATE TABLE " + TABELA_PROFESSOR);
			conn.createStatement().execute("CREATE TABLE " + TABELA_CURSO);
			conn.createStatement().execute("CREATE TABLE " + TABELA_SALA);
			conn.createStatement().execute("CREATE TABLE " + TABELA_PERIODO);
			conn.createStatement().execute("CREATE TABLE " + TABELA_DISCIPLINA);
			// Add more Tables
		} catch (SQLException e) {
			//System.err.print("Ocorreu um erro na criação das tabelas: " + e);
		}
	}

	/**
	 * Este método deleta todas as tabelas do banco.
	 */
	public void dropTables() {
		try {
			conn.createStatement().execute("DROP TABLE DISCIPLINA");
			conn.createStatement().execute("DROP TABLE PERIODO");
			conn.createStatement().execute("DROP TABLE PROFESSOR");
			conn.createStatement().execute("DROP TABLE CURSO");
			conn.createStatement().execute("DROP TABLE SALA");
			// Add more Tables
		} catch (SQLException e) {
			//System.out.println(e);
		}
	}

	/**
	 * Este método retorna a conexão com o banco de dados.
	 * 
	 * @return Conexão com o banco de dados.
	 */
	public Connection getConnection() {
		return conn;
	}

	/**
	 * Este método retorna um statement da conexão para a declaração dos
	 * comandos SQL.
	 * 
	 * @return Uma instância de statement.
	 */
	public Statement getStatement() {
		try {
			return conn.createStatement();
		} catch (SQLException e) {
			System.err.println("Não foi possivel criar o statement" + e);
		}
		return null;
	}

	/**
	 * Este método executa um SQL em um statement para alteração do banco de
	 * dados.
	 * 
	 * @param SQL
	 *            Instrução SQL.
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
	 * Este método executa um SQL em um statement para recuperação de registro
	 * no banco de dados e atribui-os a um conjunto de resultados.
	 * 
	 * @param SQL
	 *            Instrução SQL.
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
	 * Fecha a conexão.
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
