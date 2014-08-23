package br.com.padroesdeprojeto.validation;

public enum H2ErrorMessages {

	// constantes
	ATRIBUTOINVALIDO("Atributo inválido"),
	PROFESSORNAOCADASTRADO("Professor não Cadastrado"),
	PROFESSORJACADASTRADO("Professor ja Cadastrado"),
	CURSONAOCADASTRADO("Curso não Cadastrado"),
	CURSOJACADASTRADO("Curso não Cadastrado"),
	SALANAOCADASTRADA("Sala não cadastrada"),
	PERIODONAOCADASTRADO("Periodo não Cadastrado"),
	DISCIPLINAJACADASTRADA("Disciplina já cadastrada"),
	DISCIPLINANAOCADASTRADA("Disciplina não cadastrada");
	
	
	private final String valor;

	H2ErrorMessages(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
	
}
