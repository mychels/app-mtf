package br.com.mrs.mtf.model;

public class RotinaTreino {

	private long idRotinaTreino;
	private Rotina rotina;
	private Dia dia;
	private Treino treino;


	public RotinaTreino() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdRotinaTreino() {
		return idRotinaTreino;
	}


	public void setIdRotinaTreino(long idRotinaTreino) {
		this.idRotinaTreino = idRotinaTreino;
	}


	public Rotina getRotina() {
		return rotina;
	}


	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}


	public Dia getDia() {
		return dia;
	}


	public void setDia(Dia dia) {
		this.dia = dia;
	}


	public Treino getTreino() {
		return treino;
	}


	public void setTreino(Treino treino) {
		this.treino = treino;
	}


	@Override
	public String toString() {
		return "RotinaTreino [idRotinaTreino=" + idRotinaTreino + ", rotina="
				+ rotina + ", dia=" + dia + ", treino=" + treino + "]";
	}
}