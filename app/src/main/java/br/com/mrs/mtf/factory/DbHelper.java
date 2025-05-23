package br.com.mrs.mtf.factory;


import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.dao.AvaliacaoDAO;
import br.com.mrs.mtf.dao.DiaDAO;
import br.com.mrs.mtf.dao.DietaDAO;
import br.com.mrs.mtf.dao.DietaRefeicaoDAO;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.dao.GrupoDAO;
import br.com.mrs.mtf.dao.LogSerieDAO;
import br.com.mrs.mtf.dao.RefeicaoDAO;
import br.com.mrs.mtf.dao.RotinaDAO;
import br.com.mrs.mtf.dao.RotinaTreinoDAO;
import br.com.mrs.mtf.dao.SerieDAO;
import br.com.mrs.mtf.dao.TipoExercicioDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.dao.TreinoExercicioDAO;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	// banco de dados
	private static final String NOME_BANCO = "bancoaplicativo.db";
	private static final int VERSAO_BANCO = 1;
	private static final String TAG = "Banco de dados";
	private static final String SCRIPT_FOREIGN_KEY = "PRAGMA foreign_keys=ON;";


	// construtor da classe
	public DbHelper(Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);
		Log.w(TAG, "Acessando o banco de dados");
	}


	// cria o banco de dados
	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			Log.w(TAG, "Iniciando a criação do banco de dados");

			// habilitando o banco de dados para aceitar chaves estrangeiras
			db.execSQL(SCRIPT_FOREIGN_KEY);

			// cria a tabela alimento
			db.execSQL(AlimentoDAO.SCRIPT_CREATE_TABLE_ALIMENTO);

			// cria a tabela unidademedida
			db.execSQL(UnidadeMedidaDAO.SCRIPT_CREATE_TABLE_UNIDADEMEDIDA);

			// cria a tabela grupo
			db.execSQL(GrupoDAO.SCRIPT_CREATE_TABLE_GRUPO);
			db.execSQL(GrupoDAO.grupo1);
			db.execSQL(GrupoDAO.grupo2);
			db.execSQL(GrupoDAO.grupo3);
			db.execSQL(GrupoDAO.grupo4);
			db.execSQL(GrupoDAO.grupo5);
			db.execSQL(GrupoDAO.grupo6);
			db.execSQL(GrupoDAO.grupo7);
			db.execSQL(GrupoDAO.grupo8);
			db.execSQL(GrupoDAO.grupo9);

			// cria a tabela tipoexercicio
			db.execSQL(TipoExercicioDAO.SCRIPT_CREATE_TABLE_TIPOEXERCICIO);
			db.execSQL(TipoExercicioDAO.tipo1);
			db.execSQL(TipoExercicioDAO.tipo2);

			// cria a tabela exercicio
			db.execSQL(ExercicioDAO.SCRIPT_CREATE_TABLE_EXERCICIO);

			// cria a tabela treino
			db.execSQL(TreinoDAO.SCRIPT_CREATE_TABLE_TREINO);

			// cria a tabela treinoexercicio
			db.execSQL(TreinoExercicioDAO.SCRIPT_CREATE_TABLE_TREINOEXERCICIO);

			// cria a tabela serie
			db.execSQL(SerieDAO.SCRIPT_CREATE_TABLE_SERIE);

			// cria a tabela dia
			db.execSQL(DiaDAO.SCRIPT_CREATE_TABLE_DIA);
			db.execSQL(DiaDAO.segunda);
			db.execSQL(DiaDAO.terca);
			db.execSQL(DiaDAO.quarta);
			db.execSQL(DiaDAO.quinta);
			db.execSQL(DiaDAO.sexta);
			db.execSQL(DiaDAO.sabado);

			// cria a tabela rotina
			db.execSQL(RotinaDAO.SCRIPT_CREATE_TABLE_ROTINA);

			// cria a tabela rotinatreino
			db.execSQL(RotinaTreinoDAO.SCRIPT_CREATE_TABLE_ROTINA_TREINO);

			//cria a tabela avaliacao
			db.execSQL(AvaliacaoDAO.SCRIPT_CREATE_TABLE_AVALIACAO);

			// cria a tabela dieta
			db.execSQL(DietaDAO.SCRIPT_CREATE_TABLE_DIETA);

			// cria a tabela refeicao
			db.execSQL(RefeicaoDAO.SCRIPT_CREATE_TABLE_REFEICAO);
			db.execSQL(RefeicaoDAO.refeicao1);
			db.execSQL(RefeicaoDAO.refeicao2);
			db.execSQL(RefeicaoDAO.refeicao3);
			db.execSQL(RefeicaoDAO.refeicao4);
			db.execSQL(RefeicaoDAO.refeicao5);
			db.execSQL(RefeicaoDAO.refeicao6);

			// cria a tabela dietarefeicao
			db.execSQL(DietaRefeicaoDAO.SCRIPT_CREATE_TABLE_DIETAREFEICAO);

			// cria a tabela logserie
			db.execSQL(LogSerieDAO.SCRIPT_CREATE_TABLE_LOGSERIE);

			Log.w(TAG, "Terminando a criação do banco de dados");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub	
	}
}