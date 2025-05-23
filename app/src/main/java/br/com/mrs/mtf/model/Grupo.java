package br.com.mrs.mtf.model;


public class Grupo {

	private long idGrupo;
	private String nomeGrupo;


	public Grupo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdGrupo() {
		return idGrupo;
	}


	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}


	public String getNomeGrupo() {
		return nomeGrupo;
	}


	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}


	@Override
	public String toString() {
		return nomeGrupo;
	}
}