package br.com.padroesdeprojeto.bean;

/**
 * Esta classe serve como modelo para a gravação dos objetos Sala na base de
 * dados.
 * @author NegoLeo
 *
 */
public class Sala {


	private String id = "";
	private String bloco = "";
	
	public Sala(){
	}
	
	public Sala(String id, String bloco) {
		this.id = id;
		this.bloco = bloco;
	}
	

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + " - " + bloco;
	}
}