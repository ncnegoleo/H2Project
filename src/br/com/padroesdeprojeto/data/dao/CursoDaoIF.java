package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Curso;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 *
 */
public interface CursoDaoIF {

	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de curso do banco de dados.
	 * 
	 * @param c
	 *            Um objeto do tipo {@link Curso} para ser inserido no banco.
	 */
	public void insere(Curso c);

	/**
	 * Esta assinatura de método serve para implementação do método de alteração
	 * de curso do banco de dados.
	 * 
	 * @param c
	 *            Um objeto do tipo {@link Curso} com as devidas alterações onde
	 *            irá substituir os dados de outro no banco.
	 */
	public void altera(Curso c);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de cursos do banco de dados.
	 * 
	 * @param id
	 *            Um numero que representa um índice do curso que será deletada
	 *            no banco.
	 */
	public void deleta(String id);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todos os cursos do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Curso}.
	 */
	public ArrayList<Curso> getAllCursos();

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * curos do banco de dados a partir da sigla.
	 * 
	 * @param sigla
	 *            A sigla do curso.
	 * @return Objeto do tipo {@link Curso}
	 */
	public Curso getCursoBySilga(String sigla);
}
