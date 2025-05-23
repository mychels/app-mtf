package br.com.mrs.mtf.activity;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.model.Exercicio;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class VisualizarExercicioActivity extends Activity{

	private ExercicioDAO exercicioDAO;
	private Exercicio exercicio;
	private TextView nomeexercicio, tipoexercicio, grupomuscular, execucaoexercicio;
	private long idexercicio = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_exercicio_activity);

		idexercicio = getIntent().getLongExtra("idexercicio", 0);

		exercicioDAO = new ExercicioDAO(this);

		nomeexercicio = (TextView) findViewById(R.id.tvNomeexercicioVE);
		tipoexercicio = (TextView) findViewById(R.id.tvTipoexercicioVE);
		grupomuscular = (TextView) findViewById(R.id.tvGrupomuscularVE);
		execucaoexercicio = (TextView) findViewById(R.id.tvExecucaoexercicioVE);

		exibeExercicio();
	}


	// recupera o exercicio
	public void recuperarExercicio(){
		exercicioDAO.open();
		exercicio = exercicioDAO.buscarExercicio(idexercicio);
		exercicioDAO.close();
	}

	// exibe o exercicio
	public void exibeExercicio(){

		recuperarExercicio();
		try {

			nomeexercicio.setText(exercicio.getNomeExercicio().toString());
			tipoexercicio.setText(exercicio.getTipoexercicio().getNomeTipoExercicio().toString());
			grupomuscular.setText(exercicio.getGrupo().getNomeGrupo().toString());
			execucaoexercicio.setText(exercicio.getExecucaoExercicio().toString());

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao exibir o exercício!", Toast.LENGTH_SHORT).show();
		}
	}


	// abre a tela editar exercicio
	public void editarExercicio(){
		Intent i = new Intent(this, EditarExercicioActivity.class);
		i.putExtra("idexercicio", idexercicio);
		startActivity(i);
		finish();
	}


	/** exibe uma mensagem de confirmação para exclusão
	 */

	public void exibeMensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Excluir?");
		alertaBuilder.setMessage("O exercício será excluído.");
		alertaBuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				excluiExercicio();
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


	// exclui o exercicio
	public void excluiExercicio(){

		try {

			exercicio = new Exercicio();
			exercicio.setIdExercicio(idexercicio);

			exercicioDAO.open();
			exercicioDAO.excluirExercicio(exercicio);
			exercicioDAO.close();

			Toast.makeText(this, "Exercício excluído!", Toast.LENGTH_SHORT).show();

			fecharTela();

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao excluir o exercício!", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onBackPressed(){
		fecharTela();
	}


	public void fecharTela(){
		Intent i = new Intent(this, ExerciciosCadastradosActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.visualizar_exercicio_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menueditarVE:
			editarExercicio();
			break;

		case R.id.menuexcluirVE:
			exibeMensagem();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}