package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.TipoExercicio;

public class TipoExercicioDAO {

	public static final String TABELA_TIPOEXERCICIO = "TIPOEXERCICIO";
	public static final String COLUNA_ID = "IDTIPOEXERCICIO";
	public static final String COLUNA_NOMETIPOEXERCICIO = "NOMETIPOEXERCICIO";

	public static final String SCRIPT_CREATE_TABLE_TIPOEXERCICIO = "CREATE TABLE TIPOEXERCICIO ( IDTIPOEXERCICIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMETIPOEXERCICIO TEXT )";

	public static final String tipo1 = "INSERT INTO TIPOEXERCICIO VALUES(1, 'Aeróbico')";
	public static final String tipo2 = "INSERT INTO TIPOEXERCICIO VALUES(2, 'Anaeróbico')"; 

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public TipoExercicioDAO(Context context) {
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


	// traz uma lista com todos os tipos de exercicio
	public List<TipoExercicio> listarTiposExercicio() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM TIPOEXERCICIO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		List<TipoExercicio> lista = new ArrayList<TipoExercicio>();

		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeTipoexercicio = cursor.getColumnIndex(COLUNA_NOMETIPOEXERCICIO);
				do{
					TipoExercicio tipoexercicio = new TipoExercicio();

					tipoexercicio.setIdTipoExercicio(cursor.getLong(indexId));
					tipoexercicio.setNomeTipoExercicio(cursor.getString(indexNomeTipoexercicio));

					lista.add(tipoexercicio);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os tipos de exercicio " + e.getMessage());
		}
		return lista;
	}
}