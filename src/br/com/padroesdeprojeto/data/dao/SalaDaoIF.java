package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Sala;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 *
 */
public interface SalaDaoIF {
	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de salas do banco de dados.
	 * 
	 * @param s
	 *            Um objeto do tipo {@link Sala} para ser inserido no banco.
	 */
	public void insere(Sala s);

	/**
	 * Esta assinatura de método serve para implementação do método de alteração
	 * de salas do banco de dados.
	 * 
	 * @param s
	 *            Um objeto do tipo {@link Sala} com as devidas alterações onde
	 *            irá substituir os dados de outro no banco.
	 * @param codigo
	 *            Uma String que representa um índice da sala que será
	 *            alterada no banco.
	 */
	public void altera(Sala s);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de salas do banco de dados.
	 * 
	 * @param idSala
	 *            Uma String que representa um índice da sala que será
	 *            deletada no banco.
	 */
	public void deleta(String idSala);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todas as salas do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Sala}.
	 */
	public ArrayList<Sala> getAllSalas();
	
	/**
	 * Esta assinatura de método serve para implementação do método que
	 * recupera uma sala do banco de dados. 
	 * 
	 * @param idSala
	 * 			Codigo da sala a ser resgatada n banco.
	 *
	 * @return um objeto do tipo {@link Sala}
	 * 
	 * @see Sala
	 */
	public Sala getSalaById(String idSala);
	
}
