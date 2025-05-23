package br.com.mrs.mtf.model;

public class Refeicao {

	private long idRefeicao;
	private String nomeRefeicao;


	public Refeicao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdRefeicao() {
		return idRefeicao;
	}

	public void setIdRefeicao(long idRefeicao) {
		this.idRefeicao = idRefeicao;
	}

	public String getNomeRefeicao() {
		return nomeRefeicao;
	}

	public void setNomeRefeicao(String nomeRefeicao) {
		this.nomeRefeicao = nomeRefeicao;
	}

	@Override
	public String toString() {
		return nomeRefeicao;
	}
}