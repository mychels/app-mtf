package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.TreinosCadastradosAdapter;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.model.Treino;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class TreinosCadastradosActivity extends Activity implements OnItemClickListener{

	private ExercicioDAO exercicioDAO;
	private int qtdexercicioscadastrados = 0;
	private Treino treino;
	private TreinoDAO treinoDAO;
	private List<Treino> listatreinos;
	private ListView listview;
	private TreinosCadastradosAdapter adapter;
	private long idtreino = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treinos_cadastrados_activity);

		exercicioDAO = new ExercicioDAO(this);
		treinoDAO = new TreinoDAO(this);
		recuperarTreinos();

		listview = (ListView) findViewById(R.id.lvTreinosTC);

		adapter = new TreinosCadastradosAdapter(this, listatreinos);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);

		recuperarQtdExerciciosCadastrados();
	}


	// recupera a quantidade de exercicios cadastrados
	public void recuperarQtdExerciciosCadastrados(){
		exercicioDAO.open();
		qtdexercicioscadastrados = exercicioDAO.recuperarQtdExercicios();
		exercicioDAO.close();
	}


	// recupera todos os treinos cadastrados
	public void recuperarTreinos(){
		treinoDAO.open();
		listatreinos = treinoDAO.listarTreinos();
		treinoDAO.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		treino = (Treino) adapter.getItem(position);
		finish();
		idtreino = treino.getIdTreino();
		Intent i = new Intent(this, VisualizarTreinoActivity.class);
		i.putExtra("idtreino", idtreino);
		startActivity(i);
	}

	// exibe a tela novo treino
	public void novoTreino(){
		Intent i;
		if(qtdexercicioscadastrados == 0){
			Toast.makeText(this, "Nenhum exercício cadastrado!", Toast.LENGTH_SHORT).show();
		}
		else{
			finish();
			i = new Intent(this, CadastrarTreinoActivity.class);
			startActivity(i);
		}
	}


	@Override
	public void onBackPressed(){
		finish();
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.treinos_cadastrados_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menunovoTC:
			novoTreino();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}