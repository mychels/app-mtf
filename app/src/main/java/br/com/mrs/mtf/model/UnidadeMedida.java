package br.com.mrs.mtf.model;

public class UnidadeMedida {
	
	private long idUnidadeMedida;
	private String nomeUnidadeMedida;
	
	
	public UnidadeMedida() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdUnidadeMedida() {
		return idUnidadeMedida;
	}


	public void setIdUnidadeMedida(long idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}


	public String getNomeUnidadeMedida() {
		return nomeUnidadeMedida;
	}


	public void setNomeUnidadeMedida(String nomeUnidadeMedida) {
		this.nomeUnidadeMedida = nomeUnidadeMedida;
	}


	@Override
	public String toString() {
		return nomeUnidadeMedida;
	}	
}