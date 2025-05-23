package br.com.mrs.mtf.dao;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Serie;
import br.com.mrs.mtf.model.TreinoExercicio;

public class SerieDAO {


	public static final String TABELA_SERIE = "SERIE";
	public static final String COLUNA_ID = "IDSERIE";
	public static final String COLUNA_IDTREINOEXERCICIO = "IDTREINOEXERCICIO";
	public static final String COLUNA_QTDREPETICAO = "QTDREPETICAO";
	public static final String COLUNA_QTDCARGA = "QTDCARGA";
	public static final String COLUNA_QTDDESCANSO = "QTDDESCANSO";

	public static final String SCRIPT_CREATE_TABLE_SERIE = "CREATE TABLE SERIE ( IDSERIE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"IDTREINOEXERCICIO INTEGER NOT NULL, QTDREPETICAO TEXT, QTDCARGA TEXT,  QTDDESCANSO TEXT, " +
			"FOREIGN KEY ( IDTREINOEXERCICIO ) REFERENCES  TREINOEXERCICIO ( IDTREINOEXERCICIO ) ON DELETE CASCADE )";

	private static final String TAG = "Banco de dados";
	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public SerieDAO(Context context) {
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


	// adiciona uma serie no exercicio
	public long adicionarSerie(TreinoExercicio treinoexercicio, Serie serie){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDTREINOEXERCICIO, treinoexercicio.getIdTreinoExercicio());
			valores.put(COLUNA_QTDREPETICAO, serie.getQtdRepeticao());
			valores.put(COLUNA_QTDCARGA, serie.getQtdCarga());
			valores.put(COLUNA_QTDDESCANSO, serie.getQtdDescanso());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao adicionar a serie no exercicio " + e.getMessage());
		}
		return database.insert(TABELA_SERIE, null, valores);
	}


	// traz uma lista com todas as series do exercicio 
	public  List<Serie> listarSeries(long idTreinoExercicio) {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM SERIE S " +
				"INNER JOIN TREINOEXERCICIO TE ON TE.IDTREINOEXERCICIO = S.IDTREINOEXERCICIO WHERE S.IDTREINOEXERCICIO = " + idTreinoExercicio;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);

		List<Serie> lista = new ArrayList<Serie>();

		try {
			if(cursor.moveToFirst()){

				int indexIdTreinoExercicio = cursor.getColumnIndex(TreinoExercicioDAO.COLUNA_ID);

				int indexIdSerie = cursor.getColumnIndex(COLUNA_ID);
				int indexQtdRepeticao = cursor.getColumnIndex(COLUNA_QTDREPETICAO);
				int indexQtdCarga = cursor.getColumnIndex(COLUNA_QTDCARGA);
				int indexQtdDescanso = cursor.getColumnIndex(COLUNA_QTDDESCANSO);
				do{

					TreinoExercicio treinoexercicio = new TreinoExercicio();
					Serie serie = new Serie();

					treinoexercicio.setIdTreinoExercicio(cursor.getLong(indexIdTreinoExercicio));

					serie.setIdSerie(cursor.getLong(indexIdSerie));
					serie.setQtdRepeticao(cursor.getString(indexQtdRepeticao));
					serie.setQtdCarga(cursor.getString(indexQtdCarga));
					serie.setQtdDescanso(cursor.getString(indexQtdDescanso));

					serie.setTreinoexercicio(treinoexercicio);

					lista.add(serie);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar as series do exercicio " + e.getMessage());
		}
		return lista;
	}


	// remove uma serie de um exercicio
	public void removerSerie(Serie serie){

		database = dbHelper.getWritableDatabase();
		long id;

		try {
			database.rawQuery(SCRIPT_FOREIGN_KEY, null);

			id = serie.getIdSerie();

			database.delete(TABELA_SERIE, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a serie " + e.getMessage());
		}	
	}
}