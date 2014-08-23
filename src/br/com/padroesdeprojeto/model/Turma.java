package br.com.padroesdeprojeto.model;

import java.io.Serializable;

public class Turma implements Serializable{


	private static final long serialVersionUID = -7349247614747117868L;
	
	private int id = 0;
	private String nomeTurma = "";
	private String matriculaProf = "";
	private String codigoDisciplina = "";
	private String idSala = "";
	private String idPeriodo = "";
	
	public Turma() {}

	public Turma(int id, String nomeTurma, String matriculaProf,
			String codigoDisciplina, String idSala, String idPeriodo) {
		this.id =id;
		this.nomeTurma = nomeTurma;
		this.matriculaProf = matriculaProf;
		this.codigoDisciplina = codigoDisciplina;
		this.idSala = idSala;
		this.idPeriodo = idPeriodo;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNomeTurma() {
		return nomeTurma;
	}
	
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	
	public String getMatriculaProf() {
		return matriculaProf;
	}
	
	public void setMatriculaProf(String matriculaProf) {
		this.matriculaProf = matriculaProf;
	}
	
	public String getCodigoDisciplina() {
		return codigoDisciplina;
	}
	
	public void setCodigoDisciplina(String codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	
	public String getIdSala() {
		return idSala;
	}
	
	public void setIdPeriodo(String idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	
	public String getIdPeriodo() {
		return idPeriodo;
	}
	
	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}
	
	@Override
	public String toString() {
		return "(" + nomeTurma + ", " + codigoDisciplina + ", " + idPeriodo + ", " + idSala + ")"; 
	}
	
}
