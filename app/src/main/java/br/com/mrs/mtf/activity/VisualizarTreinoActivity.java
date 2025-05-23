package br.com.mrs.mtf.activity;


import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.ExerciciosAdicionadosAdapter;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.dao.TreinoExercicioDAO;
import br.com.mrs.mtf.model.Treino;
import br.com.mrs.mtf.model.TreinoExercicio;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VisualizarTreinoActivity extends FragmentActivity implements OnItemClickListener{

	private ExercicioDAO exercicioDAO;
	private TreinoDAO treinoDAO;
	private Treino treino;
	private TreinoExercicioDAO treinoexercicioDAO;
	private TreinoExercicio treinoexercicio;
	private List<TreinoExercicio> listaexercicios;
	private ListView listview;
	private ExerciciosAdicionadosAdapter adapterExercicios;
	private TextView nomeTreino;
	private long idtreino = 0;
	private long idtreinoexercicio = 0;
	private long idexercicio = 0;
	private String nometreino;
	private int qtdexercicios = 0;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_treino_activity);

		idtreino = getIntent().getLongExtra("idtreino", 0);

		nomeTreino = (TextView) findViewById(R.id.tvNometreinoVT);
		listview = (ListView) findViewById(R.id.lvExerciciosVT);

		treinoDAO = new TreinoDAO(this);

		treinoexercicioDAO = new TreinoExercicioDAO(this);

		exibeTreino();
		exibeExercicios();

		exercicioDAO = new ExercicioDAO(this);
		verificarQtdexercicios();

		listview.setOnItemClickListener(this);
	}

	// verifica a quantidade de exercicios cadastrados
	public void verificarQtdexercicios(){
		exercicioDAO.open();
		qtdexercicios = exercicioDAO.recuperarQtdExercicios();
		exercicioDAO.close();	
	}

	// exibe o treino
	public void exibeTreino(){
		treinoDAO.open();			
		treino = treinoDAO.buscarTreino(idtreino);
		treinoDAO.close();

		nomeTreino.setText(treino.getNomeTreino().toString());	
	}

	// recupera os exercicios que estao no treino
	public void recuperarExerciciosTreino(){
		treinoexercicioDAO.open();
		listaexercicios = treinoexercicioDAO.listarExerciciosTreino(idtreino);
		treinoexercicioDAO.close();
	}


	// exibe os exercicios do treino
	public void exibeExercicios(){

		recuperarExerciciosTreino();
		try {
			adapterExercicios = new ExerciciosAdicionadosAdapter(this, listaexercicios);
			listview.setAdapter(adapterExercicios);
		} catch (Exception e) {
			Toast.makeText(VisualizarTreinoActivity.this,
					"Erro ao exibir os exercícios!", Toast.LENGTH_SHORT).show();
		}
	}

	// abre a tela editar treino
	public void editarTreino(){
		Intent i;
		if(qtdexercicios == 0 ){
			Toast.makeText(VisualizarTreinoActivity.this,
					"Nenhum exercício cadastrado!", Toast.LENGTH_SHORT).show();
		}
		else{

			nometreino = nomeTreino.getText().toString();


			i = new Intent(this, EditarTreinoActivity.class);
			i.putExtra("idtreino", idtreino);
			i.putExtra("nometreino",nometreino);
			startActivity(i);
			finish();
		}
	}

	/** exibe uma mensagem de confirmação 
	 *  para exclusao do treino
	 */

	public void exibeMensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Excluir?");
		alertaBuilder.setMessage("O treino será excluido.");
		alertaBuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				excluirTreino();
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

	// exclui o treino
	public void excluirTreino(){
		Intent i;
		try {

			treino = new Treino();
			treino.setIdTreino(idtreino);

			treinoDAO.open();
			treinoDAO.excluirTreino(treino);			
			treinoDAO.close();

			Toast.makeText(this, "Treino excluído!", Toast.LENGTH_SHORT).show();

			i = new Intent(this, TreinosCadastradosActivity.class);
			startActivity(i);
			finish();

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao excluir o treino!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed(){
		finish();
		Intent i;
		i = new Intent(this, TreinosCadastradosActivity.class);
		startActivity(i);
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		treinoexercicio = (TreinoExercicio) adapterExercicios.getItem(position);

		idtreinoexercicio = treinoexercicio.getIdTreinoExercicio();
		idexercicio = treinoexercicio.getExercicio().getIdExercicio();

		Intent i = new Intent(this, VisualizarSeriesExercicioActivity.class);
		i.putExtra("idtreino", idtreino);
		i.putExtra("idexercicio", idexercicio);
		i.putExtra("idtreinoexercicio", idtreinoexercicio);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.visualizar_treino_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menueditarVT:
			editarTreino();
			break;

		case R.id.menuexcluirVT:
			exibeMensagem();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}