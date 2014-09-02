package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Sala;

/**
 * Esta interface segue server para padronizar a implementa��o dos m�todos de
 * manipula��o dos dados tornando assim mais male�vel a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 *
 */
public interface SalaDaoIF {
	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de inser��o
	 * de salas do banco de dados.
	 * 
	 * @param s
	 *            Um objeto do tipo {@link Sala} para ser inserido no banco.
	 */
	public void insere(Sala s);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de altera��o
	 * de salas do banco de dados.
	 * 
	 * @param s
	 *            Um objeto do tipo {@link Sala} com as devidas altera��es onde
	 *            ir� substituir os dados de outro no banco.
	 */
	public void altera(Sala s);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de remo��o
	 * de salas do banco de dados.
	 * 
	 * @param idSala
	 *            Uma String que representa um �ndice da sala que ser�
	 *            deletada no banco.
	 */
	public void deleta(String idSala);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que recupera
	 * todas as salas do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Sala}.
	 */
	public ArrayList<Sala> getAllSalas();
	
	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que
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
