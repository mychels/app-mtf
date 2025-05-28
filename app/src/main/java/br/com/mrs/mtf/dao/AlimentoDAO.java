package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Alimento;

public class AlimentoDAO {


	public static final String TABELA_ALIMENTO = "ALIMENTO";
	public static final String COLUNA_ID = "IDALIMENTO";
	public static final String COLUNA_NOMEALIMENTO = "NOMEALIMENTO";

	public static final String SCRIPT_CREATE_TABLE_ALIMENTO = "CREATE TABLE ALIMENTO ( IDALIMENTO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEALIMENTO TEXT )";

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public AlimentoDAO(Context context) {
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


	// cadastra um alimento
	public long cadastrarAlimento(Alimento alimento){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_NOMEALIMENTO, alimento.getNomeAlimento());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar o alimento " + e.getMessage());
		}
		return database.insert(TABELA_ALIMENTO, null, valores);
	}


	// traz uma lista com todos os alimentos
	public  List<Alimento> listarAlimentos() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM ALIMENTO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null); 
		List<Alimento> lista = new ArrayList<Alimento>();

		try {
			if(cursor.moveToFirst()){
				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeAlimento = cursor.getColumnIndex(COLUNA_NOMEALIMENTO);

				do{
					Alimento alimento = new Alimento();

					alimento.setIdAlimento(cursor.getLong(indexId));
					alimento.setNomeAlimento(cursor.getString(indexNomeAlimento));

					lista.add(alimento);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os alimentos " + e.getMessage());
		}
		return lista;
	}


	// exclui um alimento
	public void excluirAlimento(Alimento alimento){

		database = dbHelper.getWritableDatabase();
		long id = 0;

		try {

			id = alimento.getIdAlimento();

			database.delete(TABELA_ALIMENTO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir o alimento " + e.getMessage());
		}
	}	


	// traz a quantidade de alimentos cadastrados
	public int recuperarQtdAlimentos(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM ALIMENTO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();
		int qtd = 0;

		try {

			qtd = cursor.getCount();

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a quantidade de alimentos " + e.getMessage());
		}
		return qtd;
	}

	// buscar se ja existe um alimento cadastrado com o mesmo nome
	public boolean buscarSeAlimentoJaExiste(String nomeAlimento) {
		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM ALIMENTO WHERE NOMEALIMENTO = ?";
		cursor = database.rawQuery(SCRIPT_BUSCA, new String[]{ nomeAlimento });

		boolean existe = cursor.getCount() > 0;
		cursor.close();
		return existe;
	}
}