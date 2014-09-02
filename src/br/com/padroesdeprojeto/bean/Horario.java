package br.com.padroesdeprojeto.bean;

/**
 * Esta classe serve como modelo para a gravação dos objetos Horario na base
 * de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public class Horario {
	
	private String idTurma = "";
	private String diaSemana = "";
	private int horaInicio = 0;
	private int horaFim = 0;
	
	public Horario() {}

	public Horario(String idTurma, String diaSemana, int horaInicio, int horaFim) {
		super();
		this.idTurma = idTurma;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}
}
