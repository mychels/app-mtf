package br.com.mrs.mtf.dao;

import java.util.ArrayList; 
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Exercicio;
import br.com.mrs.mtf.model.Treino;
import br.com.mrs.mtf.model.TreinoExercicio;

public class TreinoExercicioDAO {

	public static final String TABELA_TREINOEXERCICIO = "TREINOEXERCICIO";
	public static final String COLUNA_ID = "IDTREINOEXERCICIO";
	public static final String COLUNA_IDTREINO = "IDTREINO";
	public static final String COLUNA_IDEXERCICIO = "IDEXERCICIO";


	public static final String SCRIPT_CREATE_TABLE_TREINOEXERCICIO = "CREATE TABLE TREINOEXERCICIO ( IDTREINOEXERCICIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"IDTREINO INTEGER NOT NULL,  IDEXERCICIO INTEGER NOT NULL, " +
			"FOREIGN KEY ( IDTREINO ) REFERENCES  TREINO ( IDTREINO ) ON DELETE CASCADE, " +
			"FOREIGN KEY ( IDEXERCICIO ) REFERENCES  EXERCICIO ( IDEXERCICIO ) ON DELETE CASCADE )";

	private static final String TAG = "Banco de dados";
	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public TreinoExercicioDAO(Context context) {
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


	// adiciona um exercicio no treino
	public long adicionarExercicioTreino(Treino treino, Exercicio exercicio){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDTREINO, treino.getIdTreino());
			valores.put(COLUNA_IDEXERCICIO, exercicio.getIdExercicio());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao adicionar o exercicio no treino " + e.getMessage());
		}
		return database.insert(TABELA_TREINOEXERCICIO, null, valores);
	}


	// atualiza a insercao de um exercicio no treino
	public long atualizarExercicioTreino(TreinoExercicio treinoexercicio, Treino treino, Exercicio exercicio){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();
		long id = 0;

		try {
			id = treinoexercicio.getIdTreinoExercicio();

			valores.put(COLUNA_IDTREINO, treino.getIdTreino());
			valores.put(COLUNA_IDEXERCICIO, exercicio.getIdExercicio());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar o exercício no treino " + e.getMessage());
		}
		return database.update(TABELA_TREINOEXERCICIO, valores, COLUNA_ID + " = " + id, null);
	}


	// traz uma lista com todos os exercicios do treino 
	public  List<TreinoExercicio> listarExerciciosTreino(long idTreino) {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM TREINOEXERCICIO TE " +
				"INNER JOIN EXERCICIO E ON E.IDEXERCICIO = TE.IDEXERCICIO " +
				"WHERE TE.IDTREINO = " + idTreino;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		List<TreinoExercicio> lista = new ArrayList<TreinoExercicio>();

		try {
			if(cursor.moveToFirst()){

				int indexIdTreinoExercicio = cursor.getColumnIndex(COLUNA_ID);

				int indexIdExercicio = cursor.getColumnIndex(ExercicioDAO.COLUNA_ID);
				int indexNomeExercicio = cursor.getColumnIndex(ExercicioDAO.COLUNA_NOMEEXERCICIO);

				do{

					TreinoExercicio treinoexercicio = new TreinoExercicio();
					Exercicio exercicio = new Exercicio();

					treinoexercicio.setIdTreinoExercicio(cursor.getLong(indexIdTreinoExercicio));

					exercicio.setIdExercicio(cursor.getLong(indexIdExercicio));
					exercicio.setNomeExercicio(cursor.getString(indexNomeExercicio));
					treinoexercicio.setExercicio(exercicio);

					lista.add(treinoexercicio);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os exercicios do treino " + e.getMessage());
		}
		return lista;
	}


	// exclui um exercicio do treino
	public void excluirExercicioTreino(TreinoExercicio treinoexercicio){

		database = dbHelper.getWritableDatabase();
		long id;		

		try {
			database.rawQuery(SCRIPT_FOREIGN_KEY, null);

			id = treinoexercicio.getIdTreinoExercicio();

			database.delete(TABELA_TREINOEXERCICIO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao exclui o exercicio do treino " + e.getMessage());
		}
	}


	// recupera o ultimo idtreinoexercicio
	public long recuperarUltimoIdTreinoExercicio(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM TREINOEXERCICIO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToLast();
		long id = 0;

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);

			id = cursor.getLong(indexId);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao recuperar o ultimo idtreinoexercicio " + e.getMessage());
		}
		return id;
	}
}