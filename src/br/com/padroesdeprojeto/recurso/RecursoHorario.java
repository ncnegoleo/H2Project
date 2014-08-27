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
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horafim
	 * @return
	 * @throws H2Exception
	 */
	public String aloca(String idTurma, String diaDaSemana, int horaInicio,
			int horafim) throws H2Exception {

		Horario horario = new Horario();
		int[] horas = { horaInicio, horafim };
		String[] params = { idTurma, diaDaSemana };

		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		H2Validation.validaHoras(horas,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		H2Validation.validaDiaSemana(diaDaSemana,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		horario.setIdTurma(idTurma);
		horario.setDiaSemana(diaDaSemana);
		horario.setHoraInicio(horaInicio);
		horario.setHoraFim(horafim);

		String msgHorario = validaHorario(horario);

		AbstractFactoryDao.createHorarioDaoIF().aloca(horario);

		return msgHorario;
	}

	/**
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horaFim
	 * @return
	 * @throws H2Exception
	 */
	public String desalocaTurmaDoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horaFim) throws H2Exception {

		Horario horario;
		Horario horario2 = new Horario();
		int[] horas = { horaInicio, horaFim };
		String[] params = { idTurma, diaDaSemana };
		String msgHorario = "ok";
		
		H2Validation.validaParametros(params,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		H2Validation.validaHoras(horas,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());
		H2Validation.validaDiaSemana(diaDaSemana,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		horario = new Horario(idTurma, diaDaSemana, horaInicio, horaFim);

		horario2 = getHorarioByIntervalo(horario);

		if (horario.getHoraInicio() <= horario2.getHoraInicio()
				&& horario.getHoraFim() >= horario2.getHoraFim()) {
			System.out.println(horario.getHoraInicio() + " <= "
					+ horario2.getHoraInicio() + " && " + horario.getHoraFim()
					+ " >= " + horario2.getHoraFim());
			AbstractFactoryDao.createHorarioDaoIF().desaloca(horario2);
		}

		return msgHorario;
	}

	/**
	 * 
	 * @param idTurma
	 * @return
	 * @throws H2Exception
	 */
	public String getHorario(String idTurma) throws H2Exception {

		String result = "";
		
		H2Validation.validaCampos(idTurma,
				H2ErrorMessages.ATRIBUTOINVALIDO.getValor());

		H2Validation.validaObjetosNaoNulos(AbstractFactoryDao
				.createTurmaDaoIF().getTurmaById(idTurma),
				H2ErrorMessages.TURMANAOCADASTRADA.getValor());

		ArrayList<Horario> horarios = AbstractFactoryDao.createHorarioDaoIF()
				.getAllHorariosByTurma(idTurma);

		for (Horario h : horarios) {
			if (H2Validation.validaObjetos(h)) {
				result += h.getDiaSemana() + ": " + h.getHoraInicio() + " às "
						+ h.getHoraFim() + " ";
			}
		}

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

		String msgHorario = "ok";

		// Recupera todos os horarios do sistema.
		ArrayList<Horario> horarios = AbstractFactoryDao.createHorarioDaoIF()
				.getAllHorarios();

		// Compara todos os horarios da base de dados com o horario que está
		// sendo alocado.
		for (Horario hs : horarios) {
			msgHorario = toCompareHorarios(hs, h);
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

		String msgHorario = "ok";
		String choqs = "";
		boolean isChoque = false;

		// Recupera as turmas dos horarios
		Turma t1 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h1.getIdTurma());
		Turma t2 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h2.getIdTurma());

		// Verifica se os dias da semana são iguais
		if (h1.getDiaSemana().equals(h2.getDiaSemana())) {

			if (H2Validation.validaConflitoHorario(h1.getHoraInicio(),
					h1.getHoraFim(), h2.getHoraInicio(), h2.getHoraFim())) {

				// Verifica se a sala são iguais.
				if (t1.getIdSala().equals(t2.getIdSala())) {
					choqs += t2.getIdSala() + " ";
					isChoque = true;
				}

				// Verifica se os professores são iguais.
				if (t1.getIdProf().equals(t2.getIdProf())) {
					choqs += t2.getIdProf() + " ";
					isChoque = true;
				}

				// Verifica se os periodos saõ iguais.
				// TODO - Codigo aqui...
			}
		}

		// Se houver choque de turmas diferentes.
		if (isChoque) {
			msgHorario = "Choque com " + choqs;
		}

		// Retorna a mensagem de confirmação
		return msgHorario;
	}

	/**
	 * 
	 * @param horario
	 * @return
	 * @throws H2Exception
	 */
	private Horario getHorarioByIntervalo(Horario horario) throws H2Exception {

		Horario h = null;

		for (int i = horario.getHoraInicio(); i <= horario.getHoraFim(); i++) {
			h = AbstractFactoryDao.createHorarioDaoIF().getHorarioByHIntevalo(
					horario, i);
			if (H2Validation.validaObjetos(h))
				return h;
		}

		throw new H2Exception("Horario não alocado");
	}
}
