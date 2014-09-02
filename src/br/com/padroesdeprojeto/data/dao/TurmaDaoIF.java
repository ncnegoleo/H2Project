package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Periodo;
import br.com.padroesdeprojeto.bean.Turma;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public interface TurmaDaoIF {

	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de turmas do banco de dados.
	 * 
	 * @param t
	 *            Um objeto do tipo {@link Turma} para ser inserido no banco.
	 * 
	 * @see Turma
	 */
	public void insere(Turma t);

	/**
	 * Esta assinatura de método serve para implementação do método de alteração
	 * de turma do banco de dados.
	 * 
	 * @param t
	 *            Um objeto do tipo {@link Periodo} para ser alterado no banco.
	 * @param id
	 *            Uma String que identifica o a turma.
	 * 
	 * @see Turma
	 */
	public void altera(Turma t, String id);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de uma turma no banco.
	 * 
	 * @param id
	 *            O id que representa a turma a ser deletada no banco.
	 * 
	 * @see Turma
	 */
	public void deleta(String id);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todas as turma do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Periodo}.
	 * 
	 * @see Turma
	 */
	public ArrayList<Turma> getAllTurmas();

	/**
	 * Esta assinatura de método serve para a implementação do método que
	 * recupera uma turma no banco de dados a partir do nome dele.
	 * 
	 * @param id
	 *            O id da turma a ser recuperado do banco.
	 *            
	 * @return Objeto do tipo {@link Turma}
	 * 
	 * @see Turma
	 */
	public Turma getTurmaById(String id);
}
