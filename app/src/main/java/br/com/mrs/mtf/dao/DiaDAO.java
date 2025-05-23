package br.com.mrs.mtf.dao;


public class DiaDAO {

	public static final String TABELA_DIA = "DIA";
	public static final String COLUNA_ID = "IDDIA";
	public static final String COLUNA_NOMEDIA = "NOMEDIA";

	public static final String SCRIPT_CREATE_TABLE_DIA = "CREATE TABLE DIA ( IDDIA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEDIA TEXT )";

	public static final String segunda = "INSERT INTO DIA VALUES(1, 'SEGUNDA-FEIRA')";
	public static final String terca = "INSERT INTO DIA VALUES(2, 'TERÇA-FEIRA')";
	public static final String quarta = "INSERT INTO DIA VALUES(3, 'QUARTA-FEIRA')";
	public static final String quinta = "INSERT INTO DIA VALUES(4, 'QUINTA-FEIRA')";
	public static final String sexta = "INSERT INTO DIA VALUES(5, 'SEXTA-FEIRA')";
	public static final String sabado = "INSERT INTO DIA VALUES(6, 'SABADO')";

}