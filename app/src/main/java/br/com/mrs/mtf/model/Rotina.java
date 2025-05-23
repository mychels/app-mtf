package br.com.mrs.mtf.model;

public class Rotina {

	private long idRotina;
	private String nomeRotina;


	public Rotina() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdRotina() {
		return idRotina;
	}


	public void setIdRotina(long idRotina) {
		this.idRotina = idRotina;
	}


	public String getNomeRotina() {
		return nomeRotina;
	}


	public void setNomeRotina(String nomeRotina) {
		this.nomeRotina = nomeRotina;
	}


	@Override
	public String toString() {
		return nomeRotina;
	}
}