package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.bean.Periodo;

/**
 * Esta interface segue server para padronizar a implementa��o dos m�todos de
 * manipula��o dos dados tornando assim mais male�vel a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public interface PeriodoDaoIF {

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de inser��o
	 * de periodo do banco de dados.
	 * 
	 * @param p
	 *            Um objeto do tipo {@link Periodo} para ser inserido no banco.
	 * @param idCurso
	 *            Uma String que identifica o curso ao qual o periodo ser�
	 *            inserido.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public void insere(Periodo p, String idCurso);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de remo��o
	 * de um periodo no banco.
	 * 
	 * @param nomePeriodo
	 *            O id que representa o periodo a ser deletado no banco.
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual o periodo ser�
	 *            deletado.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public void deleta(String nomePeriodo, String siglaCurso);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo que recupera
	 * todos os periodos do banco de dados em uma lista.
	 * 
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual os periodos ser�o
	 *            recuperados os periodos.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Periodo}.
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public ArrayList<Periodo> getAllPeriodos(String siglaCurso);

	/**
	 * Esta assinatura de m�todo serve para a implementa��o do m�todo que
	 * recupera um periodo no banco de dados a partir do nome e da sigla do
	 * curso.
	 * 
	 * @param nome
	 *            O nome do periodo a ser recuperado do banco.
	 * @param siglaCurso
	 *            Uma String que identifica o curso ao qual o periodo ser�
	 *            recuperado.
	 * 
	 * @return Objeto do tipo {@link Periodo}
	 * 
	 * @see Periodo
	 * @see Curso
	 */
	public Periodo getPeriodoByName(String nome, String siglaCurso);
}
