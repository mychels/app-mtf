package br.com.mrs.mtf.activity;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.dao.DietaDAO;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import br.com.mrs.mtf.model.Dieta;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class VisualizarDietaActivity extends Activity{

	private DietaDAO dietaDAO;
	private Dieta dieta;
	private TextView nomeDieta;
	private String nomedieta;
	private long iddieta = 0;
	private RelativeLayout rlcafemanha, rllanchemanha, rlalmoco, rllanchetarde, rljantar, rlceia;
	private long cafemanha = 1, lanchemanha = 2, almoco = 3, lanchetarde = 4, jantar = 5, ceia = 6;
	private AlimentoDAO alimentoDAO;
	private UnidadeMedidaDAO unidadeDAO;
	private int qtdalimentoscadastrados = 0;
	private int qtdunidadescadastradas = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_dieta_activity);

		// recuperando o valor das telas dietas cadastradas e visualizar alimentos
		iddieta = getIntent().getLongExtra("iddieta", 0);

		dietaDAO = new DietaDAO(this);
		alimentoDAO = new AlimentoDAO(this);
		unidadeDAO = new UnidadeMedidaDAO(this);

		nomeDieta = (TextView) findViewById(R.id.tvNomedietaVD);
		rlcafemanha = (RelativeLayout) findViewById(R.id.rlCafemanhaVD);
		rllanchemanha = (RelativeLayout) findViewById(R.id.rlLanchemanhaVD);
		rlalmoco = (RelativeLayout) findViewById(R.id.rlAlmocoVD);
		rllanchetarde = (RelativeLayout) findViewById(R.id.rlLanchetardeVD);
		rljantar = (RelativeLayout) findViewById(R.id.rlJantarVD);
		rlceia = (RelativeLayout) findViewById(R.id.rlCeiaVD);

		exibeDieta();
		recuperarQtdAlimentos();
		recuperarQtdUnidades();

		rlcafemanha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", cafemanha);
				startActivity(i);	
				finish();
			}

		});

		rllanchemanha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", lanchemanha);
				startActivity(i);	
				finish();


			}

		});


		rlalmoco.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", almoco);
				startActivity(i);	
				finish();

			}

		});

		rllanchetarde.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", lanchetarde);
				startActivity(i);	
				finish();

			}

		});

		rljantar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", jantar);
				startActivity(i);	
				finish();

			}

		});

		rlceia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				Intent  i = new Intent(VisualizarDietaActivity.this, VisualizarAlimentosRefeicaoActivity.class);
				i.putExtra("iddieta", iddieta);
				i.putExtra("idrefeicao", ceia);
				startActivity(i);	
				finish();
			}
		});
	}

	// exibe a dieta
	public void exibeDieta(){
		dietaDAO.open();			
		dieta = dietaDAO.buscarDieta(iddieta);
		dietaDAO.close();

		nomeDieta.setText(dieta.getNomeDieta().toString());
	}

	// recupera a quantidade de alimentos cadastrados
	public void recuperarQtdAlimentos(){
		alimentoDAO.open();
		qtdalimentoscadastrados = alimentoDAO.recuperarQtdAlimentos();
		alimentoDAO.close();
	}


	// recupera a quantidade de unidades cadastradas
	public void recuperarQtdUnidades(){
		unidadeDAO.open();
		qtdunidadescadastradas = unidadeDAO.recuperarQtdUnidades();
		unidadeDAO.close();
	}


	// exibe uma mensagem de confirmação para exclusao da dieta
	public void exibeMensagem(){

		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Excluir?");
		alerta.setMessage("A dieta será excluida.");
		alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				excluirDieta();
			}
		});
		alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog exibeAlerta = alerta.create();
		exibeAlerta.show();		
	}


	// exibe a tela editar dieta
	public void editarDieta(){
		Intent i;
		nomedieta = nomeDieta.getText().toString();
		if(qtdalimentoscadastrados == 0 ){
			Toast.makeText(VisualizarDietaActivity.this,
					"Nenhum alimento cadastrado!", Toast.LENGTH_SHORT).show();
		}
		else	
			if(qtdunidadescadastradas == 0 ){
				Toast.makeText(VisualizarDietaActivity.this,
						"Nenhuma unidade cadastrada!", Toast.LENGTH_SHORT).show();
			}
			else
				try {

					i = new Intent(this, EditarDietaActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("nomedieta", nomedieta);
					finish();
					startActivity(i);
				} catch (Exception e) {
				}
	}



	// exclui a dieta
	public void excluirDieta(){

		dieta = new Dieta();
		dieta.setIdDieta(iddieta);

		dietaDAO.open();			
		dietaDAO.excluirDieta(dieta);
		dietaDAO.close();

		Toast.makeText(this, "Dieta excluída!", Toast.LENGTH_SHORT).show();
		finish();
		Intent  i = new Intent(this, DietasCadastradasActivity.class);
		startActivity(i);	
	}


	@Override
	public void onBackPressed() {
		Intent  i = new Intent(this, DietasCadastradasActivity.class);
		startActivity(i);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.visualizar_dieta_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menueditarVD:
			editarDieta();
			break;

		case R.id.menuexcluirVD:
			exibeMensagem();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}