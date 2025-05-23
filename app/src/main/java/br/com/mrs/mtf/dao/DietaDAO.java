package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Dieta;

public class DietaDAO {


	public static final String TABELA_DIETA = "DIETA";
	public static final String COLUNA_ID = "IDDIETA";
	public static final String COLUNA_NOMEDIETA = "NOMEDIETA";

	public static final String SCRIPT_CREATE_TABLE_DIETA = "CREATE TABLE DIETA ( IDDIETA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEDIETA TEXT )";

	private static final String TAG = "Banco de dados";

	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public DietaDAO(Context context) {
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


	// cadastra uma dieta
	public long cadastrarDieta(Dieta dieta){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_NOMEDIETA, dieta.getNomeDieta());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar a dieta " + e.getMessage());
		}
		return database.insert(TABELA_DIETA, null, valores);
	}


	// busca uma dieta pelo id
	public Dieta buscarDieta(long idDieta){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM DIETA D " +
				"WHERE D.IDDIETA = " + idDieta;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);
			int indexNomeDieta = cursor.getColumnIndex(COLUNA_NOMEDIETA);

			Dieta dieta = new Dieta();

			dieta.setIdDieta(cursor.getLong(indexId));
			dieta.setNomeDieta(cursor.getString(indexNomeDieta));

			return dieta;

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a dieta " + e.getMessage());
		}
		return null;
	}


	// traz uma lista com todas as dietas
	public  List<Dieta> listarDietas() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM DIETA";
		cursor = database.rawQuery(SCRIPT_BUSCA, null); 
		List<Dieta> lista = new ArrayList<Dieta>();

		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeDieta = cursor.getColumnIndex(COLUNA_NOMEDIETA);

				do{
					Dieta dieta = new Dieta();

					dieta.setIdDieta(cursor.getLong(indexId));
					dieta.setNomeDieta(cursor.getString(indexNomeDieta));

					lista.add(dieta);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar as dietas " + e.getMessage());
		}
		return lista;
	}


	// atualiza uma dieta
	public long atualizarDieta(Dieta dieta) {

		database = dbHelper.getWritableDatabase();
		long id = 0;
		ContentValues valores = new ContentValues();

		try {

			id = dieta.getIdDieta();
			valores.put(COLUNA_NOMEDIETA, dieta.getNomeDieta());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar a dieta " + e.getMessage());
		}
		return database.update(TABELA_DIETA, valores, COLUNA_ID + " = " + id, null);
	}

	// exclui uma dieta
	public void excluirDieta(Dieta dieta){

		database = dbHelper.getWritableDatabase();
		long id = 0;

		try {
			id = dieta.getIdDieta();

			database.delete(TABELA_DIETA, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a dieta " + e.getMessage());
		}
	}

	// recupera o id da ultima dieta
	public long recuperarUltimoIdDieta(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM DIETA";
		long id = 0;
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToLast();

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);

			id = cursor.getLong(indexId);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao recuperar o id da ultima dieta " + e.getMessage());
		}
		return id;
	}
}