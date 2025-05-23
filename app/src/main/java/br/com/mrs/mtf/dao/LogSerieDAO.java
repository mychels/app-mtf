package br.com.mrs.mtf.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.LogSerie;
import br.com.mrs.mtf.model.Serie;

public class LogSerieDAO {


	public static final String TABELA_LOGSERIE = "LOGSERIE";
	public static final String COLUNA_ID = "IDLOGSERIE";
	public static final String COLUNA_IDSERIE = "IDSERIE";
	public static final String COLUNA_DATALOG = "DATALOG";
	public static final String COLUNA_QTDREPETICAO = "QTDREPETICAO";
	public static final String COLUNA_QTDCARGA = "QTDCARGA";


	public static final String SCRIPT_CREATE_TABLE_LOGSERIE = "CREATE TABLE LOGSERIE ( IDLOGSERIE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"IDSERIE INTEGER NOT NULL, DATALOG TEXT, QTDREPETICAO TEXT,  QTDCARGA TEXT, " +
			"FOREIGN KEY ( IDSERIE ) REFERENCES  SERIE ( IDSERIE ) ON DELETE CASCADE )";

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public LogSerieDAO(Context context) {
		this.mContext = context;
	}

	// abrindo o banco
	public void open(){
		dbHelper = new DbHelper(mContext);
	}

	// fechando o banco
	public void close() {
		dbHelper.close();
		Log.w(TAG, "Fechando o banco de dados");
	}


	// adiciona uma logserie para uma determinada serie que esta em um exercicio
	public long adicionarLogserie(Serie serie, LogSerie logserie){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDSERIE, serie.getIdSerie());
			valores.put(COLUNA_DATALOG, logserie.getDataLog());
			valores.put(COLUNA_QTDREPETICAO, logserie.getQtdRepeticao());
			valores.put(COLUNA_QTDCARGA, logserie.getQtdCarga());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao adicionar a logserie " + e.getMessage());
		}
		return database.insert(TABELA_LOGSERIE, null, valores);
	}


	// verifica se uma logserie ja foi adicionada para uma determinada serie
	public boolean verificarLogserieExiste(long idSerie, String dataAtual){

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM LOGSERIE LS " +
				"INNER JOIN SERIE S ON S.IDSERIE = LS.IDSERIE " +
				"WHERE LS.IDSERIE = " + idSerie + " AND LS.DATALOG = " + "'" + dataAtual + "'";

		boolean existe = false;
		cursor = database.rawQuery(SCRIPT_BUSCA, null);

		try {
			if(cursor.moveToFirst()){

				existe = true;

			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao verificar se a logserie existe " + e.getMessage());
		}
		return existe;
	}


	// traz uma lista com todas as logseries registradas para um exercicio
	public  List<LogSerie> listarLogseries(long idExercicio) {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT LS.* FROM LOGSERIE LS " +
				"INNER JOIN SERIE S ON S.IDSERIE = LS.IDSERIE " +
				"INNER JOIN TREINOEXERCICIO TE ON TE.IDTREINOEXERCICIO = S.IDTREINOEXERCICIO " +
				"INNER JOIN TREINO T ON T.IDTREINO = TE.IDTREINO " +
				"INNER JOIN EXERCICIO E ON E.IDEXERCICIO = TE.IDEXERCICIO " +
				"WHERE E.IDEXERCICIO = " + idExercicio;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		List<LogSerie> lista = new ArrayList<LogSerie>();

		try {
			if(cursor.moveToFirst()){

				int indexIdLogSerie = cursor.getColumnIndex(COLUNA_ID);
				int indexDataLog = cursor.getColumnIndex(COLUNA_DATALOG);
				int indexQtdRepeticao = cursor.getColumnIndex(COLUNA_QTDREPETICAO);
				int indexQtdCarga = cursor.getColumnIndex(COLUNA_QTDCARGA);
				do{

					LogSerie logserie = new LogSerie();

					logserie.setIdLogSerie(cursor.getLong(indexIdLogSerie));
					logserie.setDataLog(cursor.getString(indexDataLog));
					logserie.setQtdRepeticao(cursor.getString(indexQtdRepeticao));
					logserie.setQtdCarga(cursor.getString(indexQtdCarga));

					lista.add(logserie);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar as logseries do exercicio " + e.getMessage());
		}
		return lista;
	}


	// exclui todas as logseries de um exercicio
	public void excluirLogseries(long idExercicio){

		database = dbHelper.getWritableDatabase();

		try {

			String SCRIPT_DELETE = "DELETE FROM LOGSERIE WHERE IDLOGSERIE IN (" +
					"SELECT LS.IDLOGSERIE FROM LOGSERIE LS " +
					"INNER JOIN SERIE S ON S.IDSERIE = LS.IDSERIE " +
					"INNER JOIN TREINOEXERCICIO TE ON TE.IDTREINOEXERCICIO = S.IDTREINOEXERCICIO " +
					"INNER JOIN TREINO T ON T.IDTREINO = TE.IDTREINO " +
					"INNER JOIN EXERCICIO E ON E.IDEXERCICIO = TE.IDEXERCICIO " +
					"WHERE E.IDEXERCICIO = " + idExercicio + ")";

			database.execSQL(SCRIPT_DELETE);	

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir as logseries " + e.getMessage());
		}
	}


	// remove uma logserie de um exercicio
	public void removerLogserie(LogSerie logserie){

		database = dbHelper.getWritableDatabase();
		long id;

		try {

			id = logserie.getIdLogSerie();

			database.delete(TABELA_LOGSERIE, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a logserie " + e.getMessage());
		}	
	}
}