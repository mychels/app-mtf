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
import br.com.mrs.mtf.model.Grupo;
import br.com.mrs.mtf.model.TipoExercicio;

public class ExercicioDAO {

	public static final String TABELA_EXERCICIO = "EXERCICIO";
	public static final String COLUNA_ID = "IDEXERCICIO";
	public static final String COLUNA_IDTIPOEXERCICIO = "IDTIPOEXERCICIO";
	public static final String COLUNA_IDGRUPO = "IDGRUPO";
	public static final String COLUNA_NOMEEXERCICIO = "NOMEEXERCICIO";
	public static final String COLUNA_EXECUCAOEXERCICIO = "EXECUCAOEXERCICIO";


	public static final String SCRIPT_CREATE_TABLE_EXERCICIO = "CREATE TABLE EXERCICIO ( IDEXERCICIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"IDTIPOEXERCICIO INTEGER NOT NULL, IDGRUPO INTEGER NOT NULL, NOMEEXERCICIO TEXT, EXECUCAOEXERCICIO TEXT, " +
			"FOREIGN KEY ( IDTIPOEXERCICIO ) REFERENCES  TIPOEXERCICIO ( IDTIPOEXERCICIO ), " +
			"FOREIGN KEY ( IDGRUPO ) REFERENCES  GRUPO ( IDGRUPO ) )";

	private static final String TAG = "Banco de dados";
	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	// construtor da classe
	public ExercicioDAO(Context context) {
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


	// cadastra um exercicio
	public long cadastrarExercicio(TipoExercicio tipoexercicio, Grupo grupo, Exercicio exercicio) {

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDTIPOEXERCICIO, tipoexercicio.getIdTipoExercicio());
			valores.put(COLUNA_IDGRUPO, grupo.getIdGrupo());
			valores.put(COLUNA_NOMEEXERCICIO, exercicio.getNomeExercicio());
			valores.put(COLUNA_EXECUCAOEXERCICIO, exercicio.getExecucaoExercicio());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar o exercicio " + e.getMessage());
		}
		return database.insert(TABELA_EXERCICIO, null, valores);
	}

	// busca um exercicio pelo id
	public Exercicio buscarExercicio(long idExercicio){

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM EXERCICIO E " +
				"INNER JOIN TIPOEXERCICIO TE ON TE.IDTIPOEXERCICIO = E.IDTIPOEXERCICIO " +
				"INNER JOIN GRUPO G ON G.IDGRUPO = E.IDGRUPO " +
				"WHERE E.IDEXERCICIO = " + idExercicio;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);
			int indexNomeExercicio = cursor.getColumnIndex(COLUNA_NOMEEXERCICIO);
			int indexExecucaoExercicio = cursor.getColumnIndex(COLUNA_EXECUCAOEXERCICIO);

			int indexIdTipoExercicio = cursor.getColumnIndex(TipoExercicioDAO.COLUNA_ID);
			int indexNomeTipoexercicio = cursor.getColumnIndex(TipoExercicioDAO.COLUNA_NOMETIPOEXERCICIO);

			int indexIdGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_ID);
			int indexNomeGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_NOMEGRUPO);


			Exercicio exercicio = new Exercicio();
			TipoExercicio tipoexercicio = new TipoExercicio();
			Grupo grupo = new Grupo();


			exercicio.setIdExercicio(cursor.getLong(indexId));
			exercicio.setNomeExercicio(cursor.getString(indexNomeExercicio));
			exercicio.setExecucaoExercicio(cursor.getString(indexExecucaoExercicio));

			tipoexercicio.setIdTipoExercicio(cursor.getLong(indexIdTipoExercicio));
			tipoexercicio.setNomeTipoExercicio(cursor.getString(indexNomeTipoexercicio));

			grupo.setIdGrupo(cursor.getLong(indexIdGrupo));
			grupo.setNomeGrupo(cursor.getString(indexNomeGrupo));

			exercicio.setTipoexercicio(tipoexercicio);
			exercicio.setGrupo(grupo);

			return exercicio;

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar o exercicio " + e.getMessage());
		}
		return null;
	}

	// traz uma lista com todos os exercicios
	public  List<Exercicio> listarTodosExercicios() {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM EXERCICIO E " +
				"INNER JOIN GRUPO G ON G.IDGRUPO = E.IDGRUPO";

		cursor = database.rawQuery(SCRIPT_BUSCA,null);

		List<Exercicio> lista = new ArrayList<Exercicio>();

		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeExercicio = cursor.getColumnIndex(COLUNA_NOMEEXERCICIO);
				int indexExecucaoExercicio = cursor.getColumnIndex(COLUNA_EXECUCAOEXERCICIO);

				int indexIdGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_ID);
				int indexNomeGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_NOMEGRUPO);
				do{

					Exercicio exercicio = new Exercicio();
					Grupo grupo = new Grupo();

					exercicio.setIdExercicio(cursor.getLong(indexId));
					exercicio.setNomeExercicio(cursor.getString(indexNomeExercicio));
					exercicio.setExecucaoExercicio(cursor.getString(indexExecucaoExercicio));

					grupo.setIdGrupo(cursor.getLong(indexIdGrupo));
					grupo.setNomeGrupo(cursor.getString(indexNomeGrupo));
					exercicio.setGrupo(grupo);

					lista.add(exercicio);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar os exercicios " + e.getMessage());
		}
		return lista;

	}

	// traz uma lista de exercicios pelo grupo
	public  List<Exercicio> listarExerciciosGrupo(long idGrupo) {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM EXERCICIO E " +
				"INNER JOIN GRUPO G ON G.IDGRUPO = E.IDGRUPO " +
				"WHERE E.IDGRUPO = " + idGrupo;

		cursor = database.rawQuery(SCRIPT_BUSCA,null);

		List<Exercicio> lista = new ArrayList<Exercicio>();
		try {
			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexNomeExercicio = cursor.getColumnIndex(COLUNA_NOMEEXERCICIO);
				int indexExecucaoExercicio = cursor.getColumnIndex(COLUNA_EXECUCAOEXERCICIO);

				int indexIdGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_ID);
				int indexNomeGrupo = cursor.getColumnIndex(GrupoDAO.COLUNA_NOMEGRUPO);

				do{

					Exercicio exercicio = new Exercicio();
					Grupo grupo = new Grupo();

					exercicio.setIdExercicio(cursor.getLong(indexId));
					exercicio.setNomeExercicio(cursor.getString(indexNomeExercicio));
					exercicio.setExecucaoExercicio(cursor.getString(indexExecucaoExercicio));

					grupo.setIdGrupo(cursor.getLong(indexIdGrupo));
					grupo.setNomeGrupo(cursor.getString(indexNomeGrupo));
					exercicio.setGrupo(grupo);

					lista.add(exercicio);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar exercicios pelo grupo " + e.getMessage());
		}
		return lista;

	}

	// atualiza um exercicio
	public long atualizarExercicio(TipoExercicio tipoexercicio, Grupo grupo, Exercicio exercicio) {

		database = dbHelper.getWritableDatabase();
		long id = 0;
		ContentValues valores = new ContentValues();

		try {
			id = exercicio.getIdExercicio();

			valores.put(COLUNA_IDTIPOEXERCICIO, tipoexercicio.getIdTipoExercicio());
			valores.put(COLUNA_IDGRUPO, grupo.getIdGrupo());
			valores.put(COLUNA_NOMEEXERCICIO, exercicio.getNomeExercicio());
			valores.put(COLUNA_EXECUCAOEXERCICIO, exercicio.getExecucaoExercicio());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar o exercicio " + e.getMessage());
		}
		return database.update(TABELA_EXERCICIO, valores, COLUNA_ID + " = " + id, null);
	}


	// exclui um exercicio
	public void excluirExercicio(Exercicio exercicio){

		database = dbHelper.getWritableDatabase();
		long id;

		try {
			database.rawQuery(SCRIPT_FOREIGN_KEY, null);

			id = exercicio.getIdExercicio();

			database.delete(TABELA_EXERCICIO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir o exercicio " + e.getMessage());
		}
	}	


	// traz a quantidade de exercicios cadastrados
	public int recuperarQtdExercicios(){

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM EXERCICIO";
		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();
		int qtd = 0;

		try {

			qtd = cursor.getCount();

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a quantidade de exercicios " + e.getMessage());
		}
		return qtd;
	}
}