package br.com.padroesdeprojeto.bean;

import java.io.Serializable;

/**
 * Esta classe serve como modelo para a gravação dos objetos Disciplina na base
 * de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public class Disciplina implements Serializable {

	private static final long serialVersionUID = -7192637324253541601L;

	private String siglaDiscipina = "";
	private String periodo = "";
	private String nomeDaDisciplina = "";
	private String siglaCurso = "";
	private int cargaHoraria = 0;

	public Disciplina() {}
	
	public Disciplina(String siglaDisciplina, String periodo,
			String nomeDisciplina, String siglaCurso, int cargaHoraria) {
		this.siglaDiscipina = siglaDisciplina;
		this.periodo = periodo;
		this.nomeDaDisciplina = nomeDisciplina;
		this.siglaCurso = siglaCurso;
		this.cargaHoraria = cargaHoraria;
	}

	public String getSiglaDiscipina() {
		return siglaDiscipina;
	}
	
	public void setSiglaDiscipina(String siglaDiscipina) {
		this.siglaDiscipina = siglaDiscipina;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getNomeDaDisciplina() {
		return nomeDaDisciplina;
	}
	
	public void setNomeDaDisciplina(String nomeDaDisciplina) {
		this.nomeDaDisciplina = nomeDaDisciplina;
	}
	
	public String getSiglaCurso() {
		return siglaCurso;
	}
	
	public void setSiglaCurso(String siglaCurso) {
		this.siglaCurso = siglaCurso;
	}
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return siglaDiscipina + " - " + nomeDaDisciplina;
	}

}
