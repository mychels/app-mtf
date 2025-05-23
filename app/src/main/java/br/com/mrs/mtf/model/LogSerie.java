package br.com.mrs.mtf.model;

public class LogSerie {

	private long idLogSerie;
	private Serie serie;
	private String dataLog;
	private String qtdRepeticao;
	private String qtdCarga;


	public LogSerie() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getIdLogSerie() {
		return idLogSerie;
	}


	public void setIdLogSerie(long idLogSerie) {
		this.idLogSerie = idLogSerie;
	}


	public Serie getSerie() {
		return serie;
	}


	public void setSerie(Serie serie) {
		this.serie = serie;
	}


	public String getDataLog() {
		return dataLog;
	}


	public void setDataLog(String dataLog) {
		this.dataLog = dataLog;
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


	@Override
	public String toString() {
		return "LogSerie [idLogSerie=" + idLogSerie + ", serie=" + serie
				+ ", dataLog=" + dataLog + ", qtdRepeticao=" + qtdRepeticao
				+ ", qtdCarga=" + qtdCarga + "]";
	}
}