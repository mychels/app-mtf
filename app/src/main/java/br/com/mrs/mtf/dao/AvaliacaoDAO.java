package br.com.mrs.mtf.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.mrs.mtf.factory.DbHelper;
import br.com.mrs.mtf.model.Avaliacao;


public class AvaliacaoDAO {

	public static final String TABELA_AVALIACAO = "AVALIACAO";
	public static final String COLUNA_ID = "IDAVALIACAO";
	public static final String COLUNA_DATAAVALIACAO = "DATAAVALIACAO";
	public static final String COLUNA_ALTURA = "ALTURA";
	public static final String COLUNA_PESO = "PESO";
	public static final String COLUNA_PEITO = "PEITO";
	public static final String COLUNA_OMBRO = "OMBRO";
	public static final String COLUNA_BRACORELAXADO = "BRACORELAXADO";
	public static final String COLUNA_BRACOCONTRAIDO = "BRACOCONTRAIDO";
	public static final String COLUNA_ANTEBRACO = "ANTEBRACO";
	public static final String COLUNA_CINTURA = "CINTURA";
	public static final String COLUNA_ABDOMEN = "ABDOMEN";
	public static final String COLUNA_QUADRIL = "QUADRIL";
	public static final String COLUNA_COXASUPERIOR = "COXASUPERIOR";
	public static final String COLUNA_COXAMEDIA = "COXAMEDIA";
	public static final String COLUNA_COXAINFERIOR = "COXAINFERIOR";
	public static final String COLUNA_PERNA = "PERNA";
	public static final String COLUNA_DCBICIPITAL = "DCBICIPITAL";
	public static final String COLUNA_DCTRICIPITAL = "DCTRICIPITAL";
	public static final String COLUNA_DCSUBESCAPULAR = "DCSUBESCAPULAR";
	public static final String COLUNA_DCAXILARMEDIA = "DCAXILARMEDIA";
	public static final String COLUNA_DCABDOMINAL = "DCABDOMINAL";
	public static final String COLUNA_DCSUPRAILIACA = "DCSUPRAILIACA";
	public static final String COLUNA_DCPEITORAL = "DCPEITORAL";
	public static final String COLUNA_DCCOXASUPERIOR = "DCCOXASUPERIOR";
	public static final String COLUNA_DCCOXAMEDIA = "DCCOXAMEDIA";
	public static final String COLUNA_DCCOXAINFERIOR = "DCCOXAINFERIOR";
	public static final String COLUNA_DCPANTURRILHAMEDIAL = "DCPANTURRILHAMEDIAL";

	private static final String TAG = "Banco de dados";


	public static final String SCRIPT_CREATE_TABLE_AVALIACAO = "CREATE TABLE AVALIACAO ( IDAVALIACAO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"DATAAVALIACAO TEXT, ALTURA TEXT, PESO TEXT, PEITO TEXT, OMBRO TEXT, BRACORELAXADO TEXT, BRACOCONTRAIDO TEXT, ANTEBRACO TEXT, CINTURA TEXT, " +
			"ABDOMEN TEXT, QUADRIL TEXT, COXASUPERIOR TEXT, COXAMEDIA TEXT, COXAINFERIOR TEXT, PERNA TEXT, DCBICIPITAL TEXT, DCTRICIPITAL TEXT, " +
			"DCSUBESCAPULAR TEXT, DCAXILARMEDIA TEXT, DCABDOMINAL TEXT, DCSUPRAILIACA TEXT, DCPEITORAL TEXT, DCCOXASUPERIOR TEXT, DCCOXAMEDIA TEXT, " +
			"DCCOXAINFERIOR TEXT, DCPANTURRILHAMEDIAL TEXT )";



	private Context mContext;
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private Cursor cursor;



	// construtor da classe
	public AvaliacaoDAO(Context context) {
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

	// cadastra uma avaliacao
	public long cadastrarAvaliacao(Avaliacao avaliacao) {

		database = dbHelper.getWritableDatabase();
		ContentValues valores = new ContentValues();

		try {

			valores.put(COLUNA_DATAAVALIACAO, avaliacao.getDataAvaliacao());
			valores.put(COLUNA_ALTURA, avaliacao.getAltura());
			valores.put(COLUNA_PESO, avaliacao.getPeso());
			valores.put(COLUNA_PEITO, avaliacao.getPeito());
			valores.put(COLUNA_OMBRO, avaliacao.getOmbro());
			valores.put(COLUNA_BRACORELAXADO, avaliacao.getBracoRelaxado());
			valores.put(COLUNA_BRACOCONTRAIDO, avaliacao.getBracoContraido());
			valores.put(COLUNA_ANTEBRACO, avaliacao.getAntebraco());
			valores.put(COLUNA_CINTURA, avaliacao.getCintura());
			valores.put(COLUNA_ABDOMEN, avaliacao.getAbdomen());
			valores.put(COLUNA_QUADRIL, avaliacao.getQuadril());
			valores.put(COLUNA_COXASUPERIOR, avaliacao.getCoxaSuperior());
			valores.put(COLUNA_COXAMEDIA, avaliacao.getCoxaMedia());
			valores.put(COLUNA_COXAINFERIOR, avaliacao.getCoxaInferior());
			valores.put(COLUNA_PERNA, avaliacao.getPerna());
			valores.put(COLUNA_DCBICIPITAL, avaliacao.getDcBicipital());
			valores.put(COLUNA_DCTRICIPITAL, avaliacao.getDcTricipital());
			valores.put(COLUNA_DCSUBESCAPULAR, avaliacao.getDcSubescapular());
			valores.put(COLUNA_DCAXILARMEDIA, avaliacao.getDcAxilarmedia());
			valores.put(COLUNA_DCABDOMINAL, avaliacao.getDcAbdominal());
			valores.put(COLUNA_DCSUPRAILIACA, avaliacao.getDcSuprailiaca());
			valores.put(COLUNA_DCPEITORAL, avaliacao.getDcPeitoral());
			valores.put(COLUNA_DCCOXASUPERIOR, avaliacao.getDcCoxasuperior());
			valores.put(COLUNA_DCCOXAMEDIA, avaliacao.getDcCoxamedia());
			valores.put(COLUNA_DCCOXAINFERIOR, avaliacao.getDcCoxainferior());
			valores.put(COLUNA_DCPANTURRILHAMEDIAL, avaliacao.getDcPanturrilhamedial());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao cadastrar a avaliação " + e.getMessage());
		}
		return database.insert(TABELA_AVALIACAO, null, valores);
	}


	// busca uma avaliacao pelo id
	public Avaliacao buscarAvaliacao(long idAvaliacao){

		database = dbHelper.getReadableDatabase();

		String SCRIPT_BUSCA = "SELECT * FROM AVALIACAO A " +
				"WHERE A.IDAVALIACAO = " + idAvaliacao;

		cursor = database.rawQuery(SCRIPT_BUSCA, null);
		cursor.moveToFirst();

		try {

			int indexId = cursor.getColumnIndex(COLUNA_ID);
			int indexDataAvaliacao = cursor.getColumnIndex(COLUNA_DATAAVALIACAO);
			int indexAltura = cursor.getColumnIndex(COLUNA_ALTURA);
			int indexPeso = cursor.getColumnIndex(COLUNA_PESO);
			int indexPeito = cursor.getColumnIndex(COLUNA_PEITO);
			int indexOmbro = cursor.getColumnIndex(COLUNA_OMBRO);
			int indexBracoRelaxado = cursor.getColumnIndex(COLUNA_BRACORELAXADO);
			int indexBracoContraido = cursor.getColumnIndex(COLUNA_BRACOCONTRAIDO);
			int indexAntebraco = cursor.getColumnIndex(COLUNA_ANTEBRACO);
			int indexCintura = cursor.getColumnIndex(COLUNA_CINTURA);
			int indexAbdomen = cursor.getColumnIndex(COLUNA_ABDOMEN);
			int indexQuadril = cursor.getColumnIndex(COLUNA_QUADRIL);
			int indexCoxaSuperior = cursor.getColumnIndex(COLUNA_COXASUPERIOR);
			int indexCoxaMedia = cursor.getColumnIndex(COLUNA_COXAMEDIA);
			int indexCoxaInferior = cursor.getColumnIndex(COLUNA_COXAINFERIOR);
			int indexPerna = cursor.getColumnIndex(COLUNA_PERNA);
			int indexDcBicipital = cursor.getColumnIndex(COLUNA_DCBICIPITAL);
			int indexDcTricipital = cursor.getColumnIndex(COLUNA_DCTRICIPITAL);
			int indexDcSubescapular = cursor.getColumnIndex(COLUNA_DCSUBESCAPULAR);
			int indexDcAxilarMedia = cursor.getColumnIndex(COLUNA_DCAXILARMEDIA);
			int indexDcAbdominal = cursor.getColumnIndex(COLUNA_DCABDOMINAL);
			int indexDcSupraIliaca = cursor.getColumnIndex(COLUNA_DCSUPRAILIACA);
			int indexDcPeitoral = cursor.getColumnIndex(COLUNA_DCPEITORAL);
			int indexDcCoxaSuperior = cursor.getColumnIndex(COLUNA_DCCOXASUPERIOR);
			int indexDcCoxaMedia = cursor.getColumnIndex(COLUNA_DCCOXAMEDIA);
			int indexDcCoxaInferior = cursor.getColumnIndex(COLUNA_DCCOXAINFERIOR);
			int indexDcPanturrilhaMedial = cursor.getColumnIndex(COLUNA_DCPANTURRILHAMEDIAL);


			Avaliacao avaliacao = new Avaliacao();

			avaliacao.setIdAvaliacao(cursor.getLong(indexId));
			avaliacao.setDataAvaliacao(cursor.getString(indexDataAvaliacao));
			avaliacao.setAltura(cursor.getString(indexAltura));
			avaliacao.setPeso(cursor.getString(indexPeso));
			avaliacao.setPeito(cursor.getString(indexPeito));
			avaliacao.setOmbro(cursor.getString(indexOmbro));
			avaliacao.setBracoRelaxado(cursor.getString(indexBracoRelaxado));
			avaliacao.setBracoContraido(cursor.getString(indexBracoContraido));
			avaliacao.setAntebraco(cursor.getString(indexAntebraco));
			avaliacao.setCintura(cursor.getString(indexCintura));
			avaliacao.setAbdomen(cursor.getString(indexAbdomen));
			avaliacao.setQuadril(cursor.getString(indexQuadril));
			avaliacao.setCoxaSuperior(cursor.getString(indexCoxaSuperior));
			avaliacao.setCoxaMedia(cursor.getString(indexCoxaMedia));
			avaliacao.setCoxaInferior(cursor.getString(indexCoxaInferior));
			avaliacao.setPerna(cursor.getString(indexPerna));
			avaliacao.setDcBicipital(cursor.getString(indexDcBicipital));
			avaliacao.setDcTricipital(cursor.getString(indexDcTricipital));
			avaliacao.setDcSubescapular(cursor.getString(indexDcSubescapular));
			avaliacao.setDcAxilarmedia(cursor.getString(indexDcAxilarMedia));
			avaliacao.setDcAbdominal(cursor.getString(indexDcAbdominal));
			avaliacao.setDcSuprailiaca(cursor.getString(indexDcSupraIliaca));
			avaliacao.setDcPeitoral(cursor.getString(indexDcPeitoral));
			avaliacao.setDcCoxasuperior(cursor.getString(indexDcCoxaSuperior));
			avaliacao.setDcCoxamedia(cursor.getString(indexDcCoxaMedia));
			avaliacao.setDcCoxainferior(cursor.getString(indexDcCoxaInferior));
			avaliacao.setDcPanturrilhamedial(cursor.getString(indexDcPanturrilhaMedial));

			return avaliacao;

		} catch (Exception e) {
			Log.w(TAG, "Erro ao buscar a avaliação " + e.getMessage());
		}
		return null;
	}

	// traz uma lista com todas as avaliacoes
	public  List<Avaliacao> listarAvaliacoes() {

		database = dbHelper.getReadableDatabase();
		String SCRIPT_BUSCA = "SELECT * FROM AVALIACAO";
		cursor = database.rawQuery(SCRIPT_BUSCA,null);
		List<Avaliacao> lista = new ArrayList<Avaliacao>();

		try {		

			if(cursor.moveToFirst()){

				int indexId = cursor.getColumnIndex(COLUNA_ID);
				int indexDataAvaliacao = cursor.getColumnIndex(COLUNA_DATAAVALIACAO);

				do{
					Avaliacao avaliacao = new Avaliacao();

					avaliacao.setIdAvaliacao(cursor.getLong(indexId));
					avaliacao.setDataAvaliacao(cursor.getString(indexDataAvaliacao));

					lista.add(avaliacao);

				}while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.w(TAG, "Erro ao listar as avaliações " + e.getMessage());
		}
		return lista;
	}


	// atualiza uma avaliacao
	public long atualizarAvaliacao(Avaliacao avaliacao) {

		database = dbHelper.getWritableDatabase();
		long id = 0;
		ContentValues valores = new ContentValues();

		try {

			id = avaliacao.getIdAvaliacao();

			valores.put(COLUNA_DATAAVALIACAO, avaliacao.getDataAvaliacao());
			valores.put(COLUNA_ALTURA, avaliacao.getAltura());
			valores.put(COLUNA_PESO, avaliacao.getPeso());
			valores.put(COLUNA_PEITO, avaliacao.getPeito());
			valores.put(COLUNA_OMBRO, avaliacao.getOmbro());
			valores.put(COLUNA_BRACORELAXADO, avaliacao.getBracoRelaxado());
			valores.put(COLUNA_BRACOCONTRAIDO, avaliacao.getBracoContraido());
			valores.put(COLUNA_ANTEBRACO, avaliacao.getAntebraco());
			valores.put(COLUNA_CINTURA, avaliacao.getCintura());
			valores.put(COLUNA_ABDOMEN, avaliacao.getAbdomen());
			valores.put(COLUNA_QUADRIL, avaliacao.getQuadril());
			valores.put(COLUNA_COXASUPERIOR, avaliacao.getCoxaSuperior());
			valores.put(COLUNA_COXAMEDIA, avaliacao.getCoxaMedia());
			valores.put(COLUNA_COXAINFERIOR, avaliacao.getCoxaInferior());
			valores.put(COLUNA_PERNA, avaliacao.getPerna());
			valores.put(COLUNA_DCBICIPITAL, avaliacao.getDcBicipital());
			valores.put(COLUNA_DCTRICIPITAL, avaliacao.getDcTricipital());
			valores.put(COLUNA_DCSUBESCAPULAR, avaliacao.getDcSubescapular());
			valores.put(COLUNA_DCAXILARMEDIA, avaliacao.getDcAxilarmedia());
			valores.put(COLUNA_DCABDOMINAL, avaliacao.getDcAbdominal());
			valores.put(COLUNA_DCSUPRAILIACA, avaliacao.getDcSuprailiaca());
			valores.put(COLUNA_DCPEITORAL, avaliacao.getDcPeitoral());
			valores.put(COLUNA_DCCOXASUPERIOR, avaliacao.getDcCoxasuperior());
			valores.put(COLUNA_DCCOXAMEDIA, avaliacao.getDcCoxamedia());
			valores.put(COLUNA_DCCOXAINFERIOR, avaliacao.getDcCoxainferior());
			valores.put(COLUNA_DCPANTURRILHAMEDIAL, avaliacao.getDcPanturrilhamedial());

		} catch (Exception e) {
			Log.w(TAG, "Erro ao atualizar a avaliação " + e.getMessage());
		}
		return database.update(TABELA_AVALIACAO, valores, COLUNA_ID + " = " + id, null);
	}

	// exclui uma avaliacao
	public void excluirAvaliacao(Avaliacao avaliacao){

		database = dbHelper.getWritableDatabase();
		long id = 0;

		try {

			id = avaliacao.getIdAvaliacao();

			database.delete(TABELA_AVALIACAO, COLUNA_ID + " = " + id, null);

		} catch (Exception e) {
			Log.w(TAG, "Erro ao excluir a avaliação " + e.getMessage());
		}
	}	
}