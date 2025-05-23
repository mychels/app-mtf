package br.com.mrs.mtf.model;



public class TreinoExercicio {

	private long idTreinoExercicio;
	private Treino treino;
	private Exercicio exercicio;


	public TreinoExercicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdTreinoExercicio() {
		return idTreinoExercicio;
	}

	public void setIdTreinoExercicio(long idTreinoExercicio) {
		this.idTreinoExercicio = idTreinoExercicio;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	@Override
	public String toString() {
		return "TreinoExercicio [idTreinoExercicio=" + idTreinoExercicio
				+ ", treino=" + treino + ", exercicio=" + exercicio + "]";
	}
}