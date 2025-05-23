package br.com.mrs.mtf.model;

public class TipoExercicio {

	private long idTipoExercicio;
	private String nomeTipoExercicio;


	public TipoExercicio() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdTipoExercicio() {
		return idTipoExercicio;
	}


	public void setIdTipoExercicio(long idTipoExercicio) {
		this.idTipoExercicio = idTipoExercicio;
	}


	public String getNomeTipoExercicio() {
		return nomeTipoExercicio;
	}


	public void setNomeTipoExercicio(String nomeTipoExercicio) {
		this.nomeTipoExercicio = nomeTipoExercicio;
	}


	@Override
	public String toString() {
		return nomeTipoExercicio;
	}
}