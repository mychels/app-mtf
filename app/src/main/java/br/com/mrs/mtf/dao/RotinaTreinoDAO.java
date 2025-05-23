package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Dia;
import br.com.mrs.mtf.model.Rotina;
import br.com.mrs.mtf.model.RotinaTreino;
import br.com.mrs.mtf.model.Treino;

public class RotinaTreinoDAO {


	public static final String TABELA_ROTINATREINO = "ROTINATREINO";
	public static final String COLUNA_ID = "IDROTINATREINO";
	public static final String COLUNA_IDROTINA = "IDROTINA";
	public static final String COLUNA_IDDIA = "IDDIA";
	public static final String COLUNA_IDTREINO = "IDTREINO";


	public static final String SCRIPT_CREATE_TABLE_ROTINA_TREINO = "CREATE TABLE ROTINATREINO ( IDROTINATREINO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, IDROTINA INTEGER NOT NULL, " +
			"IDDIA INTEGER NOT NULL, IDTREINO INTEGER NOT NULL, " +
			"FOREIGN KEY ( IDROTINA ) REFERENCES  ROTINA ( IDROTINA ) ON DELETE CASCADE, " +
			"FOREIGN KEY ( IDTREINO ) REFERENCES  TREINO ( IDTREINO ) ON DELETE CASCADE ) ";

	private static final String TAG = "Banco de dados";


	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public RotinaTreinoDAO(Context context) {
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

	// adiciona um treino no dia
	public long adicionarTreinoDia(Rotina rotina, Dia dia, Treino treino){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDROTINA, rotina.getIdRotina());
			valores.put(COLUNA_IDDIA, dia.getIdDia());
			valores.put(COLUNA_IDTREINO, treino.getIdTreino());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao adicionar o treino na rotina " + e.getMessage());
		}
		return database.insert(TABELA_ROTINATREINO, null, valores);
	}


	// recupera o idrotinatreino
	public long recuperarIdRotinaTreino(long idRotina, long idDia){

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM ROTINATREINO RT " +
				"INNER JOIN ROTINA R ON R.IDROTINA = RT.IDROTINA " +
				"INNER JOIN DIA D ON D.IDDIA = RT.IDDIA " +
				"WHERE  R.IDROTINA = " + idRotina + " AND D.IDDIA = " + idDia;

		long id = 0;
		cursor = database.rawQuery(SCRIPT_BUSCA, null);

		try {
			if(cursor.moveToFirst()){

				int indexIdRotinaTreino = cursor.getColumnIndex(COLUNA_ID);

				id = cursor.getLong(indexIdRotinaTreino);
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao recuperar o idrotinatreino " + e.getMessage());
		}
		return id;
	}


	// adiciona um treino no dia
	public long atualizarTreinoDia(RotinaTreino rotinatreino, Rotina rotina, Dia dia, Treino treino){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();
		long id = 0;

		try {

			id = rotinatreino.getIdRotinaTreino();

			valores.put(COLUNA_IDROTINA, rotina.getIdRotina());
			valores.put(COLUNA_IDDIA, dia.getIdDia());
			valores.put(COLUNA_IDTREINO, treino.getIdTreino());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar o treino na rotina " + e.getMessage());
		}
		return database.update(TABELA_ROTINATREINO, valores, COLUNA_ID + " = " + id, null);
	}


	// traz o treino que esta em um dia
	public  List<RotinaTreino> listarTreinoDia(long idRotina, long idDia) {

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM ROTINATREINO RT " +
				"INNER JOIN TREINO T ON T.IDTREINO = RT.IDTREINO " +
				"WHERE RT.IDROTINA = " + idRotina + " AND RT.IDDIA = " + idDia;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);

		List<RotinaTreino> lista = new ArrayList<RotinaTreino>();

		try {

			if(cursor.moveToFirst()){

				int indexIdRotinaTreino = cursor.getColumnIndex(COLUNA_ID);
				int indexIdTreino = cursor.getColumnIndex(TreinoDAO.COLUNA_ID);
				int indexNomeTreino = cursor.getColumnIndex(TreinoDAO.COLUNA_NOMETREINO);

				do{
					RotinaTreino rotinatreino = new RotinaTreino();
					Treino treino = new Treino();

					rotinatreino.setIdRotinaTreino(cursor.getLong(indexIdRotinaTreino));
					treino.setIdTreino(cursor.getLong(indexIdTreino));
					treino.setNomeTreino(cursor.getString(indexNomeTreino));
					rotinatreino.setTreino(treino);

					lista.add(rotinatreino);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar o treino " + e.getMessage());
		}
		return lista;
	}
}