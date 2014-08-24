package br.com.padroesdeprojeto.validation;

public enum H2ErrorMessages {

	// constantes
	ATRIBUTOINVALIDO("Atributo inválido"),
	PROFESSORNAOCADASTRADO("Professor não Cadastrado"),
	PROFESSORJACADASTRADO("Professor ja Cadastrado"),
	CURSONAOCADASTRADO("Curso não Cadastrado"),
	CURSOJACADASTRADO("Curso não Cadastrado"),
	SALANAOCADASTRADA("Sala não cadastrada"),
	SALAJACADASTRADA("Sala já cadastrada"),
	PERIODONAOCADASTRADO("Periodo não Cadastrado"),
	PERIODOJACADASTRADO("Periodo já cadastrado"),
	DISCIPLINANAOCADASTRADA("Disciplina não cadastrada"),
	DISCIPLINAJACADASTRADA("Disciplina já cadastrada"),
	TURMANAOCADASTRADA("Turma não cadastrada"),
	TURMAJACADASTRADA("Turma já cadastrada");
	
	
	private final String valor;

	H2ErrorMessages(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}
	
}
