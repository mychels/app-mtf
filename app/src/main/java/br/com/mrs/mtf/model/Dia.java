package br.com.mrs.mtf.model;

public class Dia {

	private long idDia;
	private String nomeDia;


	public Dia() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdDia() {
		return idDia;
	}


	public void setIdDia(long idDia) {
		this.idDia = idDia;
	}


	public String getNomeDia() {
		return nomeDia;
	}


	public void setNomeDia(String nomeDia) {
		this.nomeDia = nomeDia;
	}


	@Override
	public String toString() {
		return nomeDia;
	}
}