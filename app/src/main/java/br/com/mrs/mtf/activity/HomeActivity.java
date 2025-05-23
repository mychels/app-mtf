package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.RotinaDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.factory.DbHelper;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class HomeActivity extends Activity {
	private DbHelper dbhelper;

	private RelativeLayout avaliacao, exercicio, treino, rotina, alimento, unidade, dieta, logtreinos;
	private TreinoDAO treinoDAO;
	private int qtdtreinoscadastrados = 0;
	private RotinaDAO rotinaDAO;
	private boolean existerotina = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		criarBanco();

		treinoDAO = new TreinoDAO(this);
		rotinaDAO = new RotinaDAO(this);

		avaliacao = (RelativeLayout) findViewById(R.id.rlAvaliacao);
		avaliacao.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloAvaliacao();	
			}
		});

		exercicio = (RelativeLayout) findViewById(R.id.rlExercicio);
		exercicio.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloExercicio();	
			}
		});
		treino = (RelativeLayout) findViewById(R.id.rlTreino);
		treino.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloTreino();	
			}
		});
		rotina = (RelativeLayout) findViewById(R.id.rlRotina);
		rotina.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloRotina();
			}
		});
		alimento = (RelativeLayout) findViewById(R.id.rlAlimento);
		alimento.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloAlimento();
			}
		});
		unidade = (RelativeLayout) findViewById(R.id.rlUnidade);
		unidade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloUnidade();
			}
		});
		dieta = (RelativeLayout) findViewById(R.id.rlDieta);
		dieta.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloDieta();
			}
		});

		logtreinos = (RelativeLayout) findViewById(R.id.rlLogtreino);
		logtreinos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				acessarModuloLogTreinos();
			}
		});
	}


	// acessa a classe dbhelper
	public void criarBanco(){
		dbhelper = new DbHelper(this);
		dbhelper.getWritableDatabase();	
		dbhelper.close();
	}


	public void acessarModuloAvaliacao(){
		Intent  i = new Intent(this, AvaliacoesCadastradasActivity.class);
		startActivity(i);
	}

	public void acessarModuloExercicio(){
		Intent  i = new Intent(this, ExerciciosCadastradosActivity.class);
		startActivity(i);
	}

	public void acessarModuloTreino(){
		Intent  i = new Intent(this, TreinosCadastradosActivity.class);
		startActivity(i);
	}


	public void acessarModuloRotina(){
		Intent i;
		recuperarQtdTreinos();
		verificarRotinaExiste();

		if(qtdtreinoscadastrados == 0){
			Toast.makeText(this, "Nenhum treino cadastrado!", Toast.LENGTH_SHORT).show();
		}
		else
			if(existerotina == true){
				i = new Intent(this, VisualizarRotinaActivity.class);
				startActivity(i);	
			}
			else
				if(existerotina == false){
					i = new Intent(this, CadastrarRotinaActivity.class);
					startActivity(i);
				}
	}

	public void acessarModuloAlimento(){
		Intent  i = new Intent(this, CadastrarAlimentoActivity.class);
		startActivity(i);
	}

	public void acessarModuloUnidade(){
		Intent  i = new Intent(this, CadastrarUnidadeActivity.class);
		startActivity(i);
	}

	public void acessarModuloDieta(){
		Intent  i = new Intent(this, DietasCadastradasActivity.class);
		startActivity(i);
	}

	public void acessarModuloLogTreinos(){
		Intent  i = new Intent(this, VisualizarLogTreinoActivity.class);
		startActivity(i);
	}

	// recupera a quantidade de treinos cadastrados
	public void recuperarQtdTreinos(){
		treinoDAO.open();
		qtdtreinoscadastrados = treinoDAO.recuperarQtdTreinos();
		treinoDAO.close();
	}

	// verifica se existe uma rotina cadastrada
	public void verificarRotinaExiste(){
		rotinaDAO.open();
		existerotina = rotinaDAO.verificarRotinaExiste();
		rotinaDAO.close();
	}


	@Override
	public void onBackPressed(){
		mensagem();
	}	


	/** exibe uma mensagem de confirmação de saida, 
	 *  caso o usuário clique no botão voltar do aparelho
	 */

	public void mensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Sair?");
		alertaBuilder.setMessage("O aplicativo será fechado.");
		alertaBuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				finish();
			}
		});
		alertaBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog alerta = alertaBuilder.create();
		alerta.show();		
	}
}