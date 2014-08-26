package br.com.padroesdeprojeto.recurso;

import br.com.padroesdeprojeto.bean.Curso;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.exceptions.H2Exception;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;

/**
 * Esta classe serve para a manipulação dos recursos de curso.
 * 
 * @author Leonardo Soares Rodrigues
 * 
 */
public class RecursoCurso {

	/**
	 * Este método serve controlador para a adição de novos cursos no sistema.
	 * 
	 * @param id
	 *            Identificador do curso.
	 * @param nome
	 *            nome do curso
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou ja exista
	 *             o id cadastrado na base de dados
	 */
	public void addCurso(String id, String nome) throws H2Exception {

		Curso curso = new Curso();
		String[] params = { id, nome };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		curso.setId(id);

		curso.setNome(nome);

		// verifica se não existe curso com a mesma sigla no banco.
		if (H2Validation.validaObjetosNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(id),
				H2ErrorMessages.CURSOJACADASTRADO.getValor())) {

			// insere um novo curso no banco de dados.
			AbstractFactoryDao.createCursoDaoIF().insere(curso);
		}
	}

	/**
	 * Este método serve como controlador para a alteração de um curso na base
	 * de dados
	 * 
	 * @param identificador
	 *            id do Curso.
	 * @param novoValor
	 *            O novo nome para o cursp.
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o identificado cadastrado na base de dados
	 */
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {

		String[] params = { identificador, novoValor };

		// Verifica se os parametros são nulos ou vazios
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Recupera um curso do banco a partir da seu identificador.
		Curso curso = AbstractFactoryDao.createCursoDaoIF().getCursoBySilga(
				identificador);

		// Verfica se o curso existe na base de dados
		H2Validation.validaObjetosNaoNulos(curso,
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// Altera o nome do curso.
		curso.setNome(novoValor);

		// Altera o curso no banco de dados.
		AbstractFactoryDao.createCursoDaoIF().altera(curso);
	}

	/**
	 * Este método como controlador para a remoção de cursos na base de dados
	 * 
	 * @param identificador
	 *            id do Curso
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             o id cadastrado na base de dados
	 */
	public void removeCurso(String identificador) throws H2Exception {

		// valida se a entrada do identificador esta de acordo com as regras.
		H2Validation.validaCampos(identificador,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// verifica se existe cursos com o id igual ao do parâmetro
		// no banco
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createCursoDaoIF().getCursoBySilga(identificador),
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// remove o curso do banco
		AbstractFactoryDao.createCursoDaoIF().deleta(identificador);
	}

	/**
	 * Este método como controlador para a recuperação de cursos na base de
	 * dados.
	 * 
	 * @param idCurso
	 *            id do Curso
	 * @return ToString do Objeto
	 * 
	 * @throws H2Exception
	 *             Lançada caso algum atributo seja nulo ou vazio, ou não exista
	 *             a matricula cadastrada na base de dados
	 */
	public String getCurso(String idCurso) throws H2Exception {

		// valida se a entrada do id está de acordo com as regras.
		H2Validation.validaCampos(idCurso,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// se existir, retorna um curso com o id igual a do
		// parâmetro no banco, se não existir o valor instância será nulo.
		Curso curso = AbstractFactoryDao.createCursoDaoIF().getCursoBySilga(
				idCurso);

		// Verfica se o professor existe na base de dados
		H2Validation.validaObjetosNaoNulos(curso,
				H2ErrorMessages.CURSONAOCADASTRADO.getValor());

		// "ToString"
		return curso.toString();
	}

}