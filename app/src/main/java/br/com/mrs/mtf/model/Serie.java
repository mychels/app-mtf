package br.com.mrs.mtf.model;

public class Serie {

	private long idSerie;
	private TreinoExercicio treinoexercicio;
	private String qtdRepeticao;
	private String qtdCarga;
	private String qtdDescanso;


	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdSerie() {
		return idSerie;
	}

	public void setIdSerie(long idSerie) {
		this.idSerie = idSerie;
	}

	public TreinoExercicio getTreinoexercicio() {
		return treinoexercicio;
	}

	public void setTreinoexercicio(TreinoExercicio treinoexercicio) {
		this.treinoexercicio = treinoexercicio;
	}

	public String getQtdRepeticao() {
		return qtdRepeticao;
	}

	public void setQtdRepeticao(String qtdRepeticao) {
		this.qtdRepeticao = qtdRepeticao;
	}

	public String getQtdCarga() {
		return qtdCarga;
	}

	public void setQtdCarga(String qtdCarga) {
		this.qtdCarga = qtdCarga;
	}

	public String getQtdDescanso() {
		return qtdDescanso;
	}

	public void setQtdDescanso(String qtdDescanso) {
		this.qtdDescanso = qtdDescanso;
	}

	@Override
	public String toString() {
		return "Serie [idSerie=" + idSerie + ", treinoexercicio="
				+ treinoexercicio + ", qtdRepeticao=" + qtdRepeticao
				+ ", qtdCarga=" + qtdCarga + ", qtdDescanso=" + qtdDescanso
				+ "]";
	}
}