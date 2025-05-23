package br.com.mrs.mtf.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Rotina;

public class RotinaDAO {

	public static final String TABELA_ROTINA = "ROTINA";
	public static final String COLUNA_ID = "IDROTINA";
	public static final String COLUNA_NOMEROTINA = "NOMEROTINA";

	public static final String SCRIPT_CREATE_TABLE_ROTINA = "CREATE TABLE ROTINA ( IDROTINA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEROTINA TEXT )";

	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";
	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public RotinaDAO(Context context) {
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

	// verifica se existe rotina
	public boolean verificarRotinaExiste(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM ROTINA";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		boolean existe = false;

		try {
			if(cursor.moveToFirst()){
				existe = true;
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao verificar se a rotina existe " + e.getMessage());
		}
		return existe;
	}

	// cadastra uma rotina
	public long cadastrarRotina(Rotina rotina){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_NOMEROTINA, rotina.getNomeRotina());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar a rotina " + e.getMessage());
		}
		return database.insert(TABELA_ROTINA, null, valores);
	}


	// busca o id da rotina
	public long recuperarIdRotina(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM ROTINA";
		long id = 0;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();

		try {
			int indexId = cursor.getColumnIndex(COLUNA_ID);

			id = cursor.getLong(indexId);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar o idrotina " + e.getMessage());
		}
		return id;
	}


	// exclui a rotina
	public void excluirRotina(Rotina rotina){

		database = dbHelper.getWritableDatabase();
		long id = 0;

		try {

			database.rawQuery(SCRIPT_FOREIGN_KEY, null);

			id = rotina.getIdRotina();

			database.delete(TABELA_ROTINA, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a rotina " + e.getMessage());
		}
	}
}