package br.com.padroesdeprojeto.bean;


/**
 * Esta classe serve como modelo para a gravação dos objetos Periodo na base
 * de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public class Periodo {
	
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
