package br.com.padroesdeprojeto.bean;

import br.com.padroesdeprojeto.exceptions.H2Exception;

/**
 * Esta classe serve como modelo para a gravação dos objetos Professor na base de
 * dados. 
 * 
 * @author Leonardo Soares.
 * 
 */
public class Professor {

	private String nome = "";
	private String matricula = "";

	public Professor() {
	}

	public Professor(String matricula, String nome) {
		this.nome = nome;
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws H2Exception {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) throws H2Exception {
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return matricula + " - " + nome;
	}

}
