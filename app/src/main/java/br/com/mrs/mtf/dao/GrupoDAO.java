package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Grupo;

public class GrupoDAO {

	public static final String TABELA_GRUPO = "GRUPO";
	public static final String COLUNA_ID = "IDGRUPO";
	public static final String COLUNA_NOMEGRUPO = "NOMEGRUPO";

	public static final String SCRIPT_CREATE_TABLE_GRUPO = "CREATE TABLE GRUPO ( IDGRUPO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEGRUPO TEXT )";

	public static final String grupo1 = "INSERT INTO GRUPO VALUES(1, 'Ombros')"; 
	public static final String grupo2 = "INSERT INTO GRUPO VALUES(2, 'Bíceps')"; 
	public static final String grupo3 = "INSERT INTO GRUPO VALUES(3, 'Tríceps')";
	public static final String grupo4 = "INSERT INTO GRUPO VALUES(4, 'Peitos')";
	public static final String grupo5 = "INSERT INTO GRUPO VALUES(5, 'Costas')";
	public static final String grupo6 = "INSERT INTO GRUPO VALUES(6, 'Abdomen')";
	public static final String grupo7 = "INSERT INTO GRUPO VALUES(7, 'Antebraços')";
	public static final String grupo8 = "INSERT INTO GRUPO VALUES(8, 'Pernas')";
	public static final String grupo9 = "INSERT INTO GRUPO VALUES(9, 'Glúteos')"; 

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public GrupoDAO(Context context) {
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


	// traz uma lista com todos os grupos musculares
	public List<Grupo> listarGrupos() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM GRUPO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		List<Grupo> lista = new ArrayList<Grupo>();
		
		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeGrupo = cursor.getColumnIndex(COLUNA_NOMEGRUPO);
				do{
					Grupo grupo = new Grupo();

					grupo.setIdGrupo(cursor.getLong(indexId));
					grupo.setNomeGrupo(cursor.getString(indexNomeGrupo));

					lista.add(grupo);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os grupos " + e.getMessage());
		}
		return lista;
	}
}