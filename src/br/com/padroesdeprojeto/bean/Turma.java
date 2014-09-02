package br.com.padroesdeprojeto.bean;

/**
 * Esta classe serve como modelo para a gravação dos objetos Turma na base
 * de dados.
 * 
 * @author Leonardo Soares Rodrigues.
 * 
 */
public class Turma {

	private String idTurma = "";
	private String idCurso = "";
	private String idProf = "";
	private String idDisc = "";
	private String idSala = "";
	private String idPeri = "";

	public Turma() {
	}

	public Turma(String idTurma, String idCurso, String idProf, String idDisc,
			String idSala, String idPeri) {
		this.idTurma = idTurma;
		this.idCurso = idCurso;
		this.idProf = idProf;
		this.idDisc = idDisc;
		this.idSala = idSala;
		this.idPeri = idPeri;
	}

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public String getIdProf() {
		return idProf;
	}

	public void setIdProf(String idProf) {
		this.idProf = idProf;
	}

	public String getIdDisc() {
		return idDisc;
	}

	public void setIdDisc(String idDisc) {
		this.idDisc = idDisc;
	}

	public String getIdSala() {
		return idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	public String getIdPeri() {
		return idPeri;
	}

	public void setIdPeri(String idPeri) {
		this.idPeri = idPeri;
	}

	@Override
	public String toString() {
		return "(" + idTurma + ", " + idCurso + ", " + idProf + ", "
				+ idDisc + ", " + idSala + ", " + idPeri + ")";
	}
}
