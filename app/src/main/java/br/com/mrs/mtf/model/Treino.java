package br.com.mrs.mtf.model;

public class Treino {

	private long idTreino;
	private String nomeTreino;


	public Treino() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdTreino() {
		return idTreino;
	}


	public void setIdTreino(long idTreino) {
		this.idTreino = idTreino;
	}


	public String getNomeTreino() {
		return nomeTreino;
	}


	public void setNomeTreino(String nomeTreino) {
		this.nomeTreino = nomeTreino;
	}


	@Override
	public String toString() {
		return nomeTreino;
	}
}