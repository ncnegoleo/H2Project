package br.com.padroesdeprojeto.data.dao;

import java.util.ArrayList;

import br.com.padroesdeprojeto.bean.Horario;

public interface HorarioDaoIF {

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de aloca��o
	 * de turmas no horario definido.
	 * 
	 * @param h
	 *            Um objeto do tipo {@link Horario} para ser inserido.
	 */
	public void aloca(Horario h);

	/**
	 * Esta assinatura de m�todo serve para implementa��o do m�todo de
	 * desaloca��o de turmas no horario definido.
	 * 
	 * @param h
	 *            Um objeto do tipo {@link Horario} para ser inserido.
	 */
	public void desaloca(Horario h);

	/**
	 * Recupera todos os horarios que uma determinada turma est� alocada.
	 * 
	 * @param id
	 *            O id da turma.
	 */
	public ArrayList<Horario> getAllHorariosByTurma(String id);

	/**
	 * Recupera todos os horarios cadastrados no banco.
	 * 
	 * @return ArrayList dos Horarios cadastrados.
	 */
	public ArrayList<Horario> getAllHorarios();

	/**
	 * Recupera o primeiro horario que cuja a hora corresponda seja inicial ou
	 * final e atributos dos objeto h coincidam com algum no banco de dados.
	 * 
	 * @param h
	 *            Objeto do tipo {@link Horario}
	 * 
	 * @param hora
	 *            hora inicial.
	 * @return Objeto do tipo {@link Horario}
	 */
	public Horario getHorarioByHIntevalo(Horario h, int hora);
}
