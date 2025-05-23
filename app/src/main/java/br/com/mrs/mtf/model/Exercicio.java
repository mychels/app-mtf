package br.com.mrs.mtf.model;


public class Exercicio{

	private long idExercicio;
	private TipoExercicio tipoexercicio;
	private Grupo grupo;
	private String nomeExercicio;
	private String execucaoExercicio;


	public Exercicio() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getIdExercicio() {
		return idExercicio;
	}




	public void setIdExercicio(long idExercicio) {
		this.idExercicio = idExercicio;
	}




	public TipoExercicio getTipoexercicio() {
		return tipoexercicio;
	}




	public void setTipoexercicio(TipoExercicio tipoexercicio) {
		this.tipoexercicio = tipoexercicio;
	}




	public Grupo getGrupo() {
		return grupo;
	}




	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}




	public String getNomeExercicio() {
		return nomeExercicio;
	}




	public void setNomeExercicio(String nomeExercicio) {
		this.nomeExercicio = nomeExercicio;
	}




	public String getExecucaoExercicio() {
		return execucaoExercicio;
	}




	public void setExecucaoExercicio(String execucaoExercicio) {
		this.execucaoExercicio = execucaoExercicio;
	}


	@Override
	public String toString() {
		return nomeExercicio;
	}
}