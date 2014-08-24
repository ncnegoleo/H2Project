package br.com.padroesdeprojeto.bean;


public class Curso {

	private String nome = "";
	private String id = "";

	public Curso() {
	}
	
	public Curso(String id, String nome) {
		this.nome = nome;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " - " + nome;
	}

}
