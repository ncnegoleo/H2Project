package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Professor;

/**
 * Esta interface segue server para padronizar a implementa��o dos m�todos de
 * manipula��o dos dados tornando assim mais male�vel a troca do banco de dados.
 * 
 * @author Leonardo Soares.
 * 
 */
public interface ProfessorDaoIF {
	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de inser��o
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
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de altera��o
	 * de professor do banco de dados.
	 * 
	 * @param p
	 *            Um objeto do tipo {@link Professor} com as devidas altera��es
	 *            onde ir� substituir os dados de outro.
	 * 
	 * @see Professor
	 */
	public void altera(Professor p);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de remo��o
	 * de um professor no banco.
	 * 
	 * @param idProfessor
	 *            O numero que representa a matricula do professor que ser�
	 *            alterada no banco.
	 * 
	 * @see Professor
	 */
	public void deleta(String idProfessor);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que recupera
	 * todos os professores do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Professor}.
	 * 
	 * @see Professor
	 */
	public ArrayList<Professor> getAllProfessores();

	/**
	 * Esta assinatura de m�todo serve para a implementa��o do m�todo que
	 * recupera um professor no banco de dados.
	 * 
	 * @param matricula
	 *            O numero que representa a matricula do professor que ser�
	 *            resgatado no banco.
	 * 
	 * @return Objeto do tipo {@link Professor}
	 * 
	 * @see Professor
	 */
	public Professor getProfessorByMatricula(String matricula);
}
