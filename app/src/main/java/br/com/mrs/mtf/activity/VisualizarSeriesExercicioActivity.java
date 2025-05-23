package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.fragment.FragmentVisualizarSeries;
import br.com.mrs.mtf.model.Exercicio;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.widget.TextView;

public class VisualizarSeriesExercicioActivity extends FragmentActivity{


	private ExercicioDAO exercicioDAO;
	private Exercicio exercicio;
	private long idtreino = 0;
	private long idtreinoexercicio = 0;
	private long idexercicio = 0;
	private TextView nomeExercicio;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_series_exercicio_activity);

		idtreino = getIntent().getLongExtra("idtreino", 0);
		idexercicio = getIntent().getLongExtra("idexercicio", 0);
		idtreinoexercicio = getIntent().getLongExtra("idtreinoexercicio", 0);

		nomeExercicio = (TextView) findViewById(R.id.tvNomeexercicioVS);
		exercicioDAO = new ExercicioDAO(this);

		FragmentVisualizarSeries frag = (FragmentVisualizarSeries) fm.findFragmentById(R.id.fragSeriesVS);
		frag.recuperarIdtreinoexercicio(idtreinoexercicio);
		exibeExercicio();
	}


	// exibe o exercicio
	public void exibeExercicio(){
		exercicioDAO.open();
		exercicio = exercicioDAO.buscarExercicio(idexercicio);
		exercicioDAO.close();

		nomeExercicio.setText(exercicio.getNomeExercicio().toString());
	}


	@Override
	public void onBackPressed() {
		finish();
		Intent i = new Intent(this, VisualizarTreinoActivity.class);
		i.putExtra("idtreino", idtreino);
		startActivity(i);
	}
}