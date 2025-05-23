package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Treino;

public class TreinoDAO {


	public static final String TABELA_TREINO = "TREINO";
	public static final String COLUNA_ID = "IDTREINO";
	public static final String COLUNA_NOMETREINO = "NOMETREINO";

	public static final String SCRIPT_CREATE_TABLE_TREINO = "CREATE TABLE TREINO ( IDTREINO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOMETREINO TEXT )";

	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";
	private static final String TAG = "Banco de dados";

	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public TreinoDAO(Context context) {
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


	// cadastra um treino
	public long cadastrarTreino(Treino treino){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_NOMETREINO, treino.getNomeTreino());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar treino " + e.getMessage());
		}
		return database.insert(TABELA_TREINO, null, valores);
	}


	// busca um treino pelo id
	public Treino buscarTreino(long idTreino){

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM TREINO T " +
				"WHERE T.IDTREINO = " + idTreino;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);
			int indexNomeTreino = cursor.getColumnIndex(COLUNA_NOMETREINO);

			Treino treino = new Treino();

			treino.setIdTreino(cursor.getLong(indexId));
			treino.setNomeTreino(cursor.getString(indexNomeTreino));

			return treino;

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar o treino " + e.getMessage());
		}
		return null;
	}

	// traz uma lista com todos os treinos
	public  List<Treino> listarTreinos() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM TREINO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null); 
		List<Treino> lista = new ArrayList<Treino>();

		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeTreino = cursor.getColumnIndex(COLUNA_NOMETREINO);

				do{
					Treino treino = new Treino();

					treino.setIdTreino(cursor.getLong(indexId));
					treino.setNomeTreino(cursor.getString(indexNomeTreino));

					lista.add(treino);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os treinos " + e.getMessage());
		}
		return lista;
	}

	// atualiza um treino
	public long atualizarTreino(Treino treino) {

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();
		long id = 0;

		try {
			id = treino.getIdTreino();

			valores.put(COLUNA_NOMETREINO, treino.getNomeTreino());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar o treino " + e.getMessage());
		}
		return database.update(TABELA_TREINO, valores, COLUNA_ID + " = " + id, null);
	}


	// exclui um treino
	public void excluirTreino(Treino treino){

		database = dbHelper.getWritableDatabase();
		long id;

		try {

			database.rawQuery(SCRIPT_FOREIGN_KEY, null);

			id = treino.getIdTreino();

			database.delete(TABELA_TREINO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir o treino " + e.getMessage());
		}
	}	

	// traz a quantidade de treinos cadastrados
	public int recuperarQtdTreinos(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM TREINO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();
		int qtd = 0;

		try {

			qtd = cursor.getCount();

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a quantidade de treinos " + e.getMessage());
		}
		return qtd;
	}

	// recupera o id do ultimo treino cadastrado
	public long recuperarUltimoIdTreino(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM TREINO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToLast();
		long id = 0;

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);

			id = cursor.getLong(indexId);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar o id do ultimo treino " + e.getMessage());
		}
		return id;
	}
}