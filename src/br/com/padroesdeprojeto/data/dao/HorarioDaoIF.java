package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Horario;

public interface HorarioDaoIF {

	/**
	 * Esta assinatura de método serve para implementação do método de alocação
	 * de turmas no horario definido.
	 * 
	 * @param h
	 *            Um objeto do tipo {@link Horario} para ser inserido.
	 */
	public void aloca(Horario h);

	/**
	 * Esta assinatura de método serve para implementação do método de
	 * desalocação de turmas no horario definido.
	 * 
	 * @param h
	 *            Um objeto do tipo {@link Horario} para ser inserido.
	 */
	public void desaloca(Horario h);

	/**
	 * Recupera todos os horarios que uma determinada turma está alocada.
	 * 
	 * @param id
	 *            O id da turma.
	 */
	public ArrayList<Horario> getAllHorarios(String id);
	
}
