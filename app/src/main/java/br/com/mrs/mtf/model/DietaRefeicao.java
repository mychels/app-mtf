package br.com.mrs.mtf.model;

public class DietaRefeicao {

	private long idDietaRefeicao;
	private Dieta dieta;
	private Refeicao refeicao;
	private Alimento alimento;
	private String qtdAlimento;
	private UnidadeMedida unidade;


	public DietaRefeicao() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdDietaRefeicao() {
		return idDietaRefeicao;
	}


	public void setIdDietaRefeicao(long idDietaRefeicao) {
		this.idDietaRefeicao = idDietaRefeicao;
	}


	public Dieta getDieta() {
		return dieta;
	}


	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}


	public Refeicao getRefeicao() {
		return refeicao;
	}


	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}


	public Alimento getAlimento() {
		return alimento;
	}


	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}


	public String getQtdAlimento() {
		return qtdAlimento;
	}


	public void setQtdAlimento(String qtdAlimento) {
		this.qtdAlimento = qtdAlimento;
	}


	public UnidadeMedida getUnidade() {
		return unidade;
	}


	public void setUnidade(UnidadeMedida unidade) {
		this.unidade = unidade;
	}


	@Override
	public String toString() {
		return "DietaRefeicao [idDietaRefeicao=" + idDietaRefeicao + ", dieta="
				+ dieta + ", refeicao=" + refeicao + ", alimento=" + alimento
				+ ", qtdAlimento=" + qtdAlimento + ", unidade=" + unidade + "]";
	}
}