package br.com.padroesdeprojeto.bean;

import java.io.Serializable;

public class Periodo implements Serializable {

	private static final long serialVersionUID = 6867449507036008446L;
	
	private String nomePeriodo = "";
	private String siglaCurso = "";
	
	public Periodo() {}
	
	public Periodo(String nomePeriodo, String siglaCurso) {
		this.nomePeriodo = nomePeriodo;
		this.siglaCurso = siglaCurso;
	}
	
	public String getSiglaCurso() {
		return siglaCurso;
	}
	
	public String getNomePeriodo() {
		return nomePeriodo;
	}
	
	public void setNomePeriodo(String nomePeriodo) {
		this.nomePeriodo = nomePeriodo;
	}
	
	@Override
	public String toString() {
		return nomePeriodo + " " + siglaCurso;
	}
}
