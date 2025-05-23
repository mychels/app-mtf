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
import br.com.mrs.mtf.model.Dieta;
import br.com.mrs.mtf.model.DietaRefeicao;
import br.com.mrs.mtf.model.Refeicao;
import br.com.mrs.mtf.model.UnidadeMedida;


public class DietaRefeicaoDAO {


	public static final String TABELA_DIETAREFEICAO = "DIETAREFEICAO";
	public static final String COLUNA_ID = "IDDIETAREFEICAO";
	public static final String COLUNA_IDDIETA = "IDDIETA";
	public static final String COLUNA_IDREFEICAO = "IDREFEICAO";
	public static final String COLUNA_IDALIMENTO = "IDALIMENTO";
	public static final String COLUNA_QTDALIMENTO = "QTDALIMENTO";
	public static final String COLUNA_IDUNIDADEMEDIDA = "IDUNIDADEMEDIDA";


	public static final String SCRIPT_CREATE_TABLE_DIETAREFEICAO = "CREATE TABLE DIETAREFEICAO ( IDDIETAREFEICAO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"IDDIETA INTEGER NOT NULL, IDREFEICAO INTEGER NOT NULL, IDALIMENTO INTEGER NOT NULL, QTDALIMENTO TEXT, IDUNIDADEMEDIDA INTEGER NOT NULL, " +
			"FOREIGN KEY ( IDDIETA ) REFERENCES  DIETA ( IDDIETA ) ON DELETE CASCADE, " +
			"FOREIGN KEY ( IDALIMENTO ) REFERENCES  ALIMENTO ( IDALIMENTO ) ON DELETE CASCADE, " +
			"FOREIGN KEY ( IDUNIDADEMEDIDA ) REFERENCES  UNIDADEMEDIDA ( IDUNIDADEMEDIDA ) ON DELETE CASCADE )";

	private static final String TAG = "Banco de dados";

	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;


	public DietaRefeicaoDAO(Context context) {
		this.mContext = context;;
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

	// adiciona um alimento em uma refeicao
	public long adicionarAlimentoRefeicao(Dieta dieta, Refeicao refeicao, Alimento alimento, DietaRefeicao dietarefeicao, UnidadeMedida unidade){

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_IDDIETA, dieta.getIdDieta());
			valores.put(COLUNA_IDREFEICAO, refeicao.getIdRefeicao());
			valores.put(COLUNA_IDALIMENTO, alimento.getIdAlimento());
			valores.put(COLUNA_QTDALIMENTO, dietarefeicao.getQtdAlimento());
			valores.put(COLUNA_IDUNIDADEMEDIDA, unidade.getIdUnidadeMedida());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao adicionar alimento na refeição " + e.getMessage());
		}
		return database.insert(TABELA_DIETAREFEICAO, null, valores);
	}



	// traz uma lista com os alimentos que estam em uma refeicao
	public  List<DietaRefeicao> listarAlimentosRefeicao(long idDieta, long idRefeicao) {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM DIETAREFEICAO DR " +
				"INNER JOIN ALIMENTO A ON A.IDALIMENTO = DR.IDALIMENTO " +
				"INNER JOIN UNIDADEMEDIDA UM ON UM.IDUNIDADEMEDIDA = DR.IDUNIDADEMEDIDA " +
				"WHERE DR.IDDIETA = " + idDieta + " AND DR.IDREFEICAO = " + idRefeicao;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);

		List<DietaRefeicao> lista = new ArrayList<DietaRefeicao>();

		try {

			if(cursor.moveToFirst()){

				int indexIdDietaRefeicao = cursor.getColumnIndex(COLUNA_ID);
				int indexQtdAlimento = cursor.getColumnIndex(COLUNA_QTDALIMENTO);

				int indexNomeAlimento = cursor.getColumnIndex(AlimentoDAO.COLUNA_NOMEALIMENTO);
				int indexNomeUnidade = cursor.getColumnIndex(UnidadeMedidaDAO.COLUNA_NOMEUNIDADEMEDIDA);
				do{

					DietaRefeicao dietarefeicao = new DietaRefeicao();
					Alimento alimento = new Alimento();
					UnidadeMedida unidade = new UnidadeMedida();


					dietarefeicao.setIdDietaRefeicao(cursor.getLong(indexIdDietaRefeicao));
					dietarefeicao.setQtdAlimento(cursor.getString(indexQtdAlimento));

					alimento.setNomeAlimento(cursor.getString(indexNomeAlimento));
					unidade.setNomeUnidadeMedida(cursor.getString(indexNomeUnidade));

					dietarefeicao.setAlimento(alimento);
					dietarefeicao.setUnidade(unidade);

					lista.add(dietarefeicao);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar os alimentos da refeição " + e.getMessage());
		}
		return lista;
	}

	// remove um alimento da refeicao
	public void removerAlimentoRefeicao(DietaRefeicao dietarefeicao){

		database = dbHelper.getWritableDatabase();
		long id = 0;

		try {

			id = dietarefeicao.getIdDietaRefeicao();
			database.delete(TABELA_DIETAREFEICAO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao remover o alimento da refeição " + e.getMessage());
		}
	}
}