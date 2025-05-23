package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.LogTreinoAdapter;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.model.Exercicio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class VisualizarLogTreinoActivity extends Activity implements OnItemClickListener{

	private Exercicio exercicio;
	private ExercicioDAO exercicioDAO;
	private List<Exercicio> listaexercicios;
	private ListView listview;
	private LogTreinoAdapter adapter;
	private long idexercicio = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_log_treino);

		exercicioDAO = new ExercicioDAO(this);

		listview = (ListView) findViewById(R.id.lvExerciciosLG);
		listarExercicios();
		listview.setOnItemClickListener(this);
	}

	// recupera todos os exercicios cadastrados
	public void recuperarExercicios(){
		exercicioDAO.open();
		listaexercicios = exercicioDAO.listarTodosExercicios();
		exercicioDAO.close();
	}

	// lista os exercicios
	public void listarExercicios(){
		recuperarExercicios();

		adapter = new LogTreinoAdapter(this, listaexercicios);
		listview.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		exercicio = (Exercicio) adapter.getItem(position);
		idexercicio = exercicio.getIdExercicio();

		Intent i = new Intent(this, VisualizarSeriesRegistradasActivity.class);
		i.putExtra("idexercicio", idexercicio);
		finish();
		startActivity(i);
	}

	@Override
	public void onBackPressed(){
		finish();
	}	
}