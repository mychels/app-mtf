package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.UnidadeMedida;

public class UnidadeMedidaDAO {

	public static final String TABELA_UNIDADEMEDIDA = "UNIDADEMEDIDA";
	public static final String COLUNA_ID = "IDUNIDADEMEDIDA";
	public static final String COLUNA_NOMEUNIDADEMEDIDA = "NOMEUNIDADEMEDIDA";

	public static final String SCRIPT_CREATE_TABLE_UNIDADEMEDIDA = "CREATE TABLE UNIDADEMEDIDA ( IDUNIDADEMEDIDA INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMEUNIDADEMEDIDA TEXT )";

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;



	public UnidadeMedidaDAO(Context context) {
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



	// cadastra uma unidade de medida
	public long cadastrarUnidade(UnidadeMedida unidade){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_NOMEUNIDADEMEDIDA, unidade.getNomeUnidadeMedida());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar a unidade " + e.getMessage());
		}
		return database.insert(TABELA_UNIDADEMEDIDA, null, valores);
	}


	// traz uma lista com todas as unidades de medida
	public List<UnidadeMedida> listarUnidades() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM UNIDADEMEDIDA";
		cursor = database.rawQuery(SCRIPT_BUSCA, null); 
		List<UnidadeMedida> lista = new ArrayList<UnidadeMedida>();

		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeUnidade = cursor.getColumnIndex(COLUNA_NOMEUNIDADEMEDIDA);
				do{
					UnidadeMedida unidade = new UnidadeMedida();

					unidade.setIdUnidadeMedida(cursor.getLong(indexId));
					unidade.setNomeUnidadeMedida(cursor.getString(indexNomeUnidade));

					lista.add(unidade);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar as unidades " + e.getMessage());
		}
		return lista;
	}		


	// exclui uma unidade
	public void excluirUnidade(UnidadeMedida unidade){

		database = dbHelper.getWritableDatabase();
		long id;

		try {
			id = unidade.getIdUnidadeMedida();

			database.delete(TABELA_UNIDADEMEDIDA, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a unidade " + e.getMessage());
		}
	}	

	// traz a quantidade de unidades cadastradas
	public int recuperarQtdUnidades(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM UNIDADEMEDIDA";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();
		int qtd = 0;

		try {

			qtd = cursor.getCount();

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a quantidade de unidades " + e.getMessage());
		}
		return qtd;
	}
}