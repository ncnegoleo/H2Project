package br.com.padroesdeprojeto.recurso;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Horario;
import br.com.padroesdeprojeto.bean.Turma;
import br.com.padroesdeprojeto.data.dao.AbstractFactoryDao;
import br.com.padroesdeprojeto.validation.H2ErrorMessages;
import br.com.padroesdeprojeto.validation.H2Validation;
import br.com.padroesdeprojeto.validation.exceptions.H2Exception;

public class RecursoHorario {

	public String aloca(String idTurma, String diaDaSemana, int horaInicio,
			int horafim) throws H2Exception {

		Horario horario = new Horario();
		int[] horas = { horaInicio, horafim };
		String[] params = { idTurma, diaDaSemana };
		String chkHorario = "";

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

		chkHorario = validaHorarios(AbstractFactoryDao.createHorarioDaoIF()
				.getAllHorarios(),horario);

		if(chkHorario.equals("ok")) {
			AbstractFactoryDao.createHorarioDaoIF().aloca(horario);
		}

		return chkHorario;
	}

	private String validaHorarios(ArrayList<Horario> horarios, Horario h)
			throws H2Exception {
		
		String chkHorario = "ok";
		
		for (Horario hs : horarios) {
			chkHorario = toCompareHorarios(hs, h);
		}
		
		return chkHorario;
	}

	private String toCompareHorarios(Horario h1, Horario h2) throws H2Exception {
		
		String chkHorario = "ok";
		String choqs = "";
		boolean isChoque = false;
		
		Turma t1 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h1.getIdTurma());
		Turma t2 = AbstractFactoryDao.createTurmaDaoIF().getTurmaById(
				h2.getIdTurma());
		
		if (h1.getDiaSemana().equals(h2.getDiaSemana())) {
			for (int i = h2.getHoraInicio(); i <= h2.getHoraFim(); i++) {
				if (i >= h1.getHoraInicio() && i <= h1.getHoraFim()) {
					if (h1.getIdTurma().equals(h2.getIdTurma())) {
						chkHorario = "Choque com Horario";
					} else {
						
						if (t1.getIdSala().equals(t2.getIdSala())) {
							choqs += t2.getIdSala() +" ";
							isChoque = true;
						}
						
						if (t1.getIdProf().equals(t2.getIdProf())) {
							choqs += t2.getIdProf() +" ";
							isChoque = true;
						}
					}
				}
			}
		}
		
		if(isChoque) {
			chkHorario = "Choque com " + choqs;
		}
		
		return chkHorario;
	}
}
