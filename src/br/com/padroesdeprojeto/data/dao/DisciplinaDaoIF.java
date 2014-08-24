package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Disciplina;

/**
 * Esta interface segue server para padronizar a implementação dos métodos de
 * manipulação dos dados tornando assim mais maleável a troca do banco de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public interface DisciplinaDaoIF {

	/**
	 * Esta assinatura de método serve para implementação do método de inserção
	 * de disciplina do banco de dados.
	 * 
	 * @param d
	 *            Um objeto do tipo {@link Disciplina} para ser inserido no
	 *            banco.
	 * @param siglaCurso
	 * 			O id do curso da disciplina
	 * @param idDisciplina
	 * 			O id do Periodo a ser cadastrado
	 * 
	 */
	public void insere(Disciplina d, String siglaCurso, String idPeriodo);

	/**
	 * Esta assinatura de método serve para implementação do método de alteração
	 * de disciplina do banco de dados.
	 * 
	 * @param d
	 *            Um objeto do tipo {@link Disciplina} com as devidas alterações
	 *            onde irá substituir os dados de outro no banco.
	 * @param id
	 *            Um numero que representa um índice da disciplina que será
	 *            alterada no banco.
	 * @param siglaCurso
	 *            A sigla do curso.
	 */
	public void altera(Disciplina d, String id, String siglaCurso);

	/**
	 * Esta assinatura de método serve para implementação do método de remoção
	 * de disciplina do banco de dados.
	 * 
	 * @param id
	 *            Uma String que representa um índice da disciplina que será
	 *            deletada no banco.
	 * @param siglaCurso
	 *            A sigla do curso.
	 */
	public void deleta(String id, String siglaCurso);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todas as disciplina do banco de dados em uma lista.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Disciplina}.
	 */
	public ArrayList<Disciplina> getAllDiciplinas(String siglaCurso);

	/**
	 * Esta assinatura de método serve para implementação do método que recupera
	 * todas as disciplina do banco de dados em uma lista a partir da sigla da
	 * disciplina e da sigla do curso.
	 * 
	 * @param sigla
	 *            A sigla da disciplina.
	 * @param siglaCurso
	 *            A sigla do curso.
	 * 
	 * @return Lista (ArrayList) de objetos do tipo {@link Disciplina}.
	 */
	public Disciplina getDisciplinaBySigla(String sigla, String siglaCurso);
}
