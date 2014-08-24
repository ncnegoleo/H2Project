package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Professor;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public interface ProfessorDaoIF {
	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de professor do banco de dados.
	 * 
	 * @param p
	 *            Um objeto do tipo {@link Professor} para ser inserido no
	 *            banco.
	 * 
	 * @see Professor
	 */
	public void insere(Professor p);

	/**
	 * Esta assinatura de método serve para implementação do método de alteração
	 * de professor do banco de dados.
	 * 
	 * @param p
	 *            Um objeto do tipo {@link Professor} com as devidas alterações
	 *            onde irá substituir os dados de outro.
	 * 
	 * @see Professor
	 */
	public void altera(Professor p);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de um professor no banco.
	 * 
	 * @param idProfessor
	 *            O numero que representa a matricula do professor que será
	 *            alterada no banco.
	 * 
	 * @see Professor
	 */
	public void deleta(String idProfessor);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todos os professores do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Professor}.
	 * 
	 * @see Professor
	 */
	public ArrayList<Professor> getAllProfessores();

	/**
	 * Esta assinatura de método serve para a implementação do método que
	 * recupera um professor no banco de dados.
	 * 
	 * @param matricula
	 *            O numero que representa a matricula do professor que será
	 *            resgatado no banco.
	 * 
	 * @return Objeto do tipo {@link Professor}
	 * 
	 * @see Professor
	 */
	public Professor getProfessorByMatricula(String matricula);
}
