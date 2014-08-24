package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Periodo;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public interface PeriodoDaoIF {

	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de periodo do banco de dados.
	 * 
	 * @param p
	 *            Um objeto do tipo {@link Periodo} para ser inserido no banco.
	 * @param idCurso
	 *            Uma String que identifica o curso ao qual o periodo será
	 *            inserido.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public void insere(Periodo p, String idCurso);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de um periodo no banco.
	 * 
	 * @param nomePeriodo
	 *            O id que representa o periodo a ser deletado no banco.
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual o periodo será
	 *            deletado.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public void deleta(String nomePeriodo, String siglaCurso);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todos os periodos do banco de dados em uma lista.
	 * 
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual os periodos serão
	 *            recuperados os periodos.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Periodo}.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public ArrayList<Periodo> getAllPeriodos(String siglaCurso);

	/**
	 * Esta assinatura de método serve para a implementação do método que
	 * recupera um periodo no banco de dados a partir do nome e da sigla do
	 * curso.
	 * 
	 * @param nome
	 *            O nome do periodo a ser recuperado do banco.
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual o periodo será
	 *            recuperado.
	 * 
	 * @return Objeto do tipo {@link Periodo}
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public Periodo getPeriodoByName(String nome, String siglaCurso);
}
