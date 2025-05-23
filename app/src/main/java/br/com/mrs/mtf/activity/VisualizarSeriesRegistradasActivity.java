package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.fragment.FragmentSeriesRegistradas;
import br.com.mrs.mtf.model.Exercicio;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class VisualizarSeriesRegistradasActivity extends FragmentActivity{


	private Exercicio exercicio;
	private ExercicioDAO exercicioDAO;
	private TextView nomeExercicio;
	private long idexercicio = 0;
	private int qtdlogseries = 0;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_series_registradas_activity);

		// recuperando o valor da tela visualizar series
		idexercicio = getIntent().getLongExtra("idexercicio", 0);

		nomeExercicio = (TextView) findViewById(R.id.tvNomeexercicioSR);
		exercicioDAO = new ExercicioDAO(this);
		exibeExercicio();
		passarParametro();
	}


	// exibe o exercicio
	public void exibeExercicio(){
		exercicioDAO.open();
		exercicio = exercicioDAO.buscarExercicio(idexercicio);
		exercicioDAO.close();

		nomeExercicio.setText(exercicio.getNomeExercicio().toString());
	}

	// passa o id do exercicio para o fragment
	public void passarParametro(){
		FragmentSeriesRegistradas frag = (FragmentSeriesRegistradas) fm.findFragmentById(R.id.fragSeriesSR);
		frag.recuperarParametro(idexercicio);
	}


	public void exibeMensagem(){

		recuperarQtdLogseries();
		if(qtdlogseries == 0){
			Toast.makeText(this, "Nenhuma série registrada!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
			alertaBuilder.setTitle("Excluir?");
			alertaBuilder.setMessage("Todas as séries serão excluídas.");
			alertaBuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {					
					dialog.dismiss();
					excluirLogseries();
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

	// exclui todas as series
	public void excluirLogseries(){
		FragmentSeriesRegistradas frag = (FragmentSeriesRegistradas) fm.findFragmentById(R.id.fragSeriesSR);
		frag.excluirLogseries();
	}

	// recupera a quantidade de log series registradas
	public void recuperarQtdLogseries(){
		FragmentSeriesRegistradas frag = (FragmentSeriesRegistradas) fm.findFragmentById(R.id.fragSeriesSR);
		qtdlogseries = frag.recuperarQtdseries();
	}


	@Override
	public void onBackPressed() {
		Intent i = new Intent(this, VisualizarLogTreinoActivity.class);
		finish();
		startActivity(i);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.series_registradas_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menuexcluirSR:
			exibeMensagem();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}