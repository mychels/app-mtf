package br.com.mrs.mtf.dao;


public class RefeicaoDAO {

	public static final String TABELA_REFEICAO = "REFEICAO";
	public static final String COLUNA_ID = "IDREFEICAO";
	public static final String COLUNA_NOMEREFEICAO = "NOMEREFEICAO";

	public static final String SCRIPT_CREATE_TABLE_REFEICAO = "CREATE TABLE REFEICAO ( IDREFEICAO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEREFEICAO TEXT )";

	public static final String refeicao1 = "INSERT INTO REFEICAO VALUES(1, 'CAFÉ DA MANHÃ')";
	public static final String refeicao2 = "INSERT INTO REFEICAO VALUES(2, 'LANCHE DA MANHÃ')";
	public static final String refeicao3 = "INSERT INTO REFEICAO VALUES(3, 'ALMOÇO')";
	public static final String refeicao4 = "INSERT INTO REFEICAO VALUES(4, 'LANCHE DA TARDE')";
	public static final String refeicao5 = "INSERT INTO REFEICAO VALUES(5, 'JANTAR')";
	public static final String refeicao6 = "INSERT INTO REFEICAO VALUES(6, 'CEIA')";

}