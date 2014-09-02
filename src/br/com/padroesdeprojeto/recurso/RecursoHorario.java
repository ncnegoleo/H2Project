package br.com.padroesdeprojeto.recurso;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

public class RecursoHorario {

	/**
	 * Define o horário da aula da turma (e de seus recursos). Um horário é
	 * formado pelo dia da semana, hora de início e hora de fim da aula. NÃO há
	 * opção de hora fracionada!
	 * 
	 * @param idTurma
	 *            O código que identifica a turma
	 * @param diaDaSemana
	 *            O dia da semana onde será alocada a turma
	 * @param horaInicio
	 *            A hora que inicia a aula da turma
	 * @param horaFim
	 *            A hora que termina a aula da turma
	 * @return Retorna "ok" caso não haja nenhum dos recursos da turma já
	 *         cadastrado naquele horário ou "Choque com <i>idRecurso</i>". Onde
	 *         idRecurso é o código que identifica o recurso que já estava
	 *         alocado nesse mesmo horário em outra turma. Se houver mais de um
	 *         recurso dando choque, o resultado deve ser Choque com
	 *         <i>idRecurso1, idRecurso2</i>
	 * @throws H2Exception
	 *             Caso os parâmetros sejam nulos ou vazios, deve ser lançada
	 *             uma H2Exception com a mensagem "Atributo inválido".
	 */
	public String aloca(String idTurma, String diaDaSemana, int horaInicio,
			int horaFim) throws H2Exception {

		Horario horario = new Horario();
		int[] horas = { horaInicio, horaFim };
		String[] params = { idTurma, diaDaSemana };

		// Verifica os parametros String passados como parametro.
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica as horas passadas como parametro.
		H2Validation.validaHoras(horas,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica os dias da semana (Exemplo: Seguna ou Terça ou Quarta...)
		H2Validation.validaDiaSemana(diaDaSemana,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se a turma passada como parametro está realmente cadastrada.
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// Sets para o novo horario.
		horario.setIdTurma(idTurma);
		horario.setDiaSemana(diaDaSemana);
		horario.setHoraInicio(horaInicio);
		horario.setHoraFim(horaFim);

		// Verifica o horario passado e compara como os outros do banco.
		String msgHorario = validaHorario(horario);

		// aloca a turma no horario
		AbstractFactoryDao.createHorarioDaoIF().aloca(horario);

		// retorna a mesagem que pode ser ok ou choques de horario.
		return msgHorario;
	}

	/**
	 * Remove a turma (e seus recursos) do horário passado como parâmetro. NÃO
	 * há opção de hora fracionada!
	 * 
	 * @param idTurma
	 *            O código que identifica a turma
	 * @param diaDaSemana O dia da semana da aula
	 * @param horaInicio
	 *            A hora que inicia a aula
	 * @param horaFim
	 *            A hora que termina a aula
	 * @return Retorna "ok" caso não haja nenhum dos recursos da turma já
	 *         cadastrado naquele horário ou "Choque com <i>idRecurso</i>". Onde
	 *         idRecurso é o código que identifica o recurso que já estava
	 *         alocado nesse mesmo horário em outra turma. Se houver mais de um
	 *         recurso dando choque, o resultado deve ser Choque com
	 *         <i>idRecurso1, idRecurso2</i>
	 * @throws H2Exception
	 *             Caso os parâmetros sejam nulos ou vazios, deve ser lançada
	 *             uma H2Exception com a mensagem "Atributo inválido".
	 */
	public String desaloca(String idTurma, String diaDaSemana, int horaInicio,
			int horaFim) throws H2Exception {

		Horario hor1;
		Horario hor2 = new Horario();
		int[] horas = { horaInicio, horaFim };
		String[] params = { idTurma, diaDaSemana };
		String msgHorario = "";

		// Verifica os parametros String passados como parametro.
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica as horas passadas como parametro.
		H2Validation.validaHoras(horas,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica os dias da semana (Exemplo: Seguna ou Terça ou Quarta...).
		H2Validation.validaDiaSemana(diaDaSemana,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se a turma passada como parametro está realmente cadastrada.
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// Cria um novo objeto horario com os parametros passados.
		hor1 = new Horario(idTurma, diaDaSemana, horaInicio, horaFim);

		// Recupera um horario alocado a apartir de outro horario, onde as horas
		// do intervalo das horas do hoario do parametro se
		// chocam com as do horario a ser recuperado.
		hor2 = getHorarioByIntervalo(hor1);

		// Se o intervalo do horario 1 cobrir o intervalo do horario 2.
		if (hor1.getHoraInicio() <= hor2.getHoraInicio()
				&& hor1.getHoraFim() >= hor2.getHoraFim()) {

			// Desaloca todo o horario.
			AbstractFactoryDao.createHorarioDaoIF().desaloca(hor2);

			// Verifica se existe choque de horario no novo intervalo e atrubui
			// possiveis mensagens.
			msgHorario = validaHorario(hor2);

		// Se o inicio do intervalo do horario 1 for maior que o inicio e
		// menor que o final do horario 2.
		} else if (hor1.getHoraInicio() > hor2.getHoraInicio()
				&& hor1.getHoraFim() >= hor2.getHoraFim()) {

			// Desaloca o horario.
			AbstractFactoryDao.createHorarioDaoIF().desaloca(hor2);

			// Muda a hora final do horario 2 para hora inicial do horario 1
			hor2.setHoraFim(hor1.getHoraInicio());

			// Verifica se existe choque de horario no novo intervalo e atrubui
			// possiveis mensagens.
			msgHorario = validaHorario(hor2);

			// Aloca novamente o horario com o novo intervalo
			AbstractFactoryDao.createHorarioDaoIF().aloca(hor2);

		// Se o final do intervalo do horario 1 for maior que o inicio e
		// menor que o final do horario 2.
		} else if (hor1.getHoraInicio() <= hor2.getHoraInicio()
				&& hor1.getHoraFim() < hor2.getHoraFim()) {

			// Desaloca o horario
			AbstractFactoryDao.createHorarioDaoIF().desaloca(hor2);

			// Muda a hora inicial do horario 2 para final do horario 1.
			hor2.setHoraInicio(hor1.getHoraFim());

			// Verifica se existe choque de horario no novo intervalo e atrubui
			// possiveis mensagens.
			msgHorario = validaHorario(hor2);

			// Aloca novamente o horario com o novo intervalo.
			AbstractFactoryDao.createHorarioDaoIF().aloca(hor2);

		// Se o todo intervalo do horario estiver entre o inicio e o fim
		// (mas não igual) do intervalo do horario 2.
		} else {
			throw new H2Exception(H2ErrorMessages.INTERVALOINVALIDO.getValor());
		}

		// Retorna a mesagem que pode ser ok ou choques de horario.
		return msgHorario;
	}

	/**
	 * Método que apresenta o horário da turma em formato String. Caso a turma
	 * não esteja alocada em nenhum horário, deve ser lançada uma exceção com a
	 * mensagem "Turma sem horário". O horário deve seguir o seguinte formato:
	 * 
	 * Segunda: 10 às 12 Terça: 14 às 16 Sexta: 8 às 10
	 * 
	 * Lembrando que não a opção de hora fracionada.
	 * 
	 * @param idTurma
	 *            O código que identifica a turma
	 * @return O horário da turma
	 * @throws H2Exception
	 *             Caso a turma não tenha sido alocada ainda. Ou caso algum dos parâmetros seja inválido.
	 */
	public String getHorario(String idTurma) throws H2Exception {

		String result = "";

		// Verifica se o idTurma esta de acordo com as regras.
		H2Validation.validaCampos(idTurma,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		// Verifica se existe a turma cadastrada no banco.
		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		// Recupera todos os horarios refrentes a turma.
		ArrayList<Horario> horarios = AbstractFactoryDao.createHorarioDaoIF()
				.getAllHorariosByTurma(idTurma);

		// percorre todos os horarios
		for (Horario h : horarios) {

			// Valida cada horario verificando se não são nulos.
			if (H2Validation.validaObjetos(h)) {

				// Atribui a mensagem referente ao dia da semana e as horas do
				// horario.
				result += h.getDiaSemana() + ": " + h.getHoraInicio() + " às "
						+ h.getHoraFim() + " ";
			}
		}

		// retorna a mesagem que pode ser ok ou choques de horario.
		return result;
	}

	/**
	 * Este método compara e o horario que está sendo alocado com os todos os
	 * horarios passados para verificar possiveis choques de horarios.
	 * 
	 * @param h
	 *            O horario que possivelmente será alocado.
	 * @return Mesagem de confirmação ou mensagem informando os choques nos
	 *         horarios.
	 * 
	 */
	private String validaHorario(Horario h) {

		String msgHorario = "Choque com ";

		// Recupera todos os horarios do sistema.
		ArrayList<Horario> horarios = AbstractFactoryDao.createHorarioDaoIF()
				.getAllHorarios();

		// Compara todos os horarios da base de dados com o horario que está
		// sendo alocado.
		for (Horario hs : horarios) {
			msgHorario += toCompareHorarios(hs, h);
		}

		// Verifica se a mensagem foi atribuida ou não.
		if (msgHorario.equals("Choque com ")) {
			msgHorario = "ok";
		}

		return msgHorario;
	}

	/**
	 * Este método compara dois horarios e verifica se eles conflitam e algum
	 * momento, segundo os requesitos do sistema. Caso haja dois horarios no
	 * mesmo dia da semana e com conflito nas horas é feita outras verificações
	 * em realção a turmas desses horarios, se for a mesma turma ou tiver na
	 * mesma sala e/ou tiver o mesmo professor dará chorque de turma.
	 * 
	 * @param h1
	 *            O primeiro horario, este é comparado com o segundo horario.
	 * @param h2
	 *            O segundo horario
	 * @return Mesagem de confirmação ou mensagem informando os choques nos
	 *         horarios.
	 */
	private String toCompareHorarios(Horario h1, Horario h2) {

		String choqs = "";

		// Recupera as turmas dos horarios
		Turma t1 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h1.getIdTurma());
		Turma t2 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h2.getIdTurma());

		// Verifica se os dias da semana são iguais
		if (h1.getDiaSemana().equals(h2.getDiaSemana())) {

			if (H2Validation.validaConflitoHorario(h1.getHoraInicio(),
					h1.getHoraFim(), h2.getHoraInicio(), h2.getHoraFim())) {
				
				//choqs += t1.getIdTurma() + "=>"; // Sugestão de mostrar qual turma está dando choque.
				
				// Verifica se a sala são iguais.
				if (t1.getIdSala().equals(t2.getIdSala())) {
					choqs += (choqs.equals("")) ? "" : ", ";
					choqs += t2.getIdSala();
				}

				// Verifica se os professores são iguais.
				if (t1.getIdProf().equals(t2.getIdProf())) {
					choqs += (choqs.equals("")) ? "" : ", ";
					choqs += t2.getIdProf();
				}

				// Verifica se os periodos são iguais.
				if (t1.getIdPeri().equals(t2.getIdPeri())) {
					choqs += (choqs.equals("")) ? "" : ", ";
					choqs += t2.getIdPeri();
				}
			}
		}

		// Retorna a mensagem de confirmação
		return choqs;
	}

	/**
	 * Este método recupera um horario da base de dados cuja o intervalo das
	 * horas do horario informado colida com o intervalo entre a hora inicial e
	 * a hora final deste horario.
	 * 
	 * @param horario
	 *            O horario cuja as horas serão comparadas.
	 * @return O horario que houve a clisão dos horarios.
	 * @throws H2Exception
	 *             Caso não encontre um horario retornará uma exeção.
	 */
	private Horario getHorarioByIntervalo(Horario horario) throws H2Exception {

		Horario h = null;

		// Percorre todas as horas do horario.
		for (int i = horario.getHoraInicio(); i <= horario.getHoraFim(); i++) {

			// Se alguma hora colidir com alguma hora de algum horario no banco
			// esse horario é retornado.
			h = AbstractFactoryDao.createHorarioDaoIF().getHorarioByHIntevalo(
					horario, i);

			// Se encontrar o horario, o retorna.
			if (H2Validation.validaObjetos(h))
				return h;
		}

		// Se o horario não colidir retorna a exeção
		throw new H2Exception(H2ErrorMessages.HORARIONAOALOCADO.getValor());
	}
}
