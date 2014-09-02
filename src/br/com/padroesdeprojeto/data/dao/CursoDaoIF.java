package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Curso;

/**
 * Esta interface segue server para padronizar a implementa��o dos m�todos de
 * manipula��o dos dados tornando assim mais male�vel a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 *
 */
public interface CursoDaoIF {

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de inser��o
	 * de curso do banco de dados.
	 * 
	 * @param c
	 *            Um objeto do tipo {@link Curso} para ser inserido no banco.
	 */
	public void insere(Curso c);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de altera��o
	 * de curso do banco de dados.
	 * 
	 * @param c
	 *            Um objeto do tipo {@link Curso} com as devidas altera��es onde
	 *            ir� substituir os dados de outro no banco.
	 */
	public void altera(Curso c);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de remo��o
	 * de cursos do banco de dados.
	 * 
	 * @param id
	 *            Um numero que representa um �ndice do curso que ser� deletada
	 *            no banco.
	 */
	public void deleta(String id);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que recupera
	 * todos os cursos do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Curso}.
	 */
	public ArrayList<Curso> getAllCursos();

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que recupera
	 * curos do banco de dados a partir da sigla.
	 * 
	 * @param sigla
	 *            A sigla do curso.
	 * @return Objeto do tipo {@link Curso}
	 */
	public Curso getCursoBySilga(String sigla);
}
