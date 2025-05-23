package br.com.mrs.mtf.model;

public class Alimento {

	private long idAlimento;
	private String nomeAlimento;


	public Alimento() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getNomeAlimento() {
		return nomeAlimento;
	}

	public void setNomeAlimento(String nomeAlimento) {
		this.nomeAlimento = nomeAlimento;
	}


	@Override
	public String toString() {
		return nomeAlimento;
	}
}