package br.com.mrs.mtf.model;

public class Dieta {

	private long idDieta;
	private String nomeDieta;


	public Dieta() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdDieta() {
		return idDieta;
	}


	public void setIdDieta(long idDieta) {
		this.idDieta = idDieta;
	}


	public String getNomeDieta() {
		return nomeDieta;
	}


	public void setNomeDieta(String nomeDieta) {
		this.nomeDieta = nomeDieta;
	}


	@Override
	public String toString() {
		return nomeDieta;
	}
}