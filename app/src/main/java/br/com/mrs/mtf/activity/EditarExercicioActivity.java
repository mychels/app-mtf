package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.dao.GrupoDAO;
import br.com.mrs.mtf.dao.TipoExercicioDAO;
import br.com.mrs.mtf.model.Exercicio;
import br.com.mrs.mtf.model.Grupo;
import br.com.mrs.mtf.model.TipoExercicio;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarExercicioActivity extends Activity{

	private Exercicio exercicio;
	private ExercicioDAO exercicioDAO;
	private TipoExercicio tipoexercicio;
	private TipoExercicioDAO tipoexercicioDAO;
	private Spinner spntipoexercicio;
	private List<TipoExercicio> listatiposexercicio;
	private ArrayAdapter<TipoExercicio> arraytipoexercicio;
	private long idtipoexercicio = 0;
	private GrupoDAO grupoDAO;
	private Spinner spnGrupo;
	private Grupo grupo;
	private List<Grupo> listagrupos;
	private ArrayAdapter<Grupo> arrayGrupo;
	private long idgrupo = 0;
	private EditText nomeExercicio, execucaoExercicio;
	private long idexercicio = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_exercicio_activity);

		idexercicio = getIntent().getLongExtra("idexercicio", 0);

		exercicioDAO = new ExercicioDAO(this);
		tipoexercicioDAO = new TipoExercicioDAO(this);
		grupoDAO = new GrupoDAO(this);

		nomeExercicio = (EditText) findViewById(R.id.edtNomeexercicioEE);
		execucaoExercicio = (EditText) findViewById(R.id.edtExecucaoexercicioEE);
		exibeExercicio();

		spntipoexercicio = (Spinner) findViewById(R.id.spnTipoexercicioEE);
		carregarSpinnerTipoexercicio();

		spntipoexercicio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				tipoexercicio = (TipoExercicio) arraytipoexercicio.getItem(position);
				idtipoexercicio = tipoexercicio.getIdTipoExercicio();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub	
			}
		});


		spnGrupo = (Spinner) findViewById(R.id.spnGrupoMuscularEE);	
		carregarSpinnerGrupo();

		spnGrupo.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				grupo = (Grupo) arrayGrupo.getItem(position);
				idgrupo = grupo.getIdGrupo();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}


	// recupera os tipos de exercicio
	public void recuperarTiposExercicio(){
		tipoexercicioDAO.open();
		listatiposexercicio = tipoexercicioDAO.listarTiposExercicio();
		tipoexercicioDAO.close();
	}

	// carrega o spinner com os tipos de exercicio
	public void carregarSpinnerTipoexercicio(){

		recuperarTiposExercicio();
		try {

			arraytipoexercicio = new ArrayAdapter<TipoExercicio>(this, android.R.layout.simple_spinner_item, listatiposexercicio);
			arraytipoexercicio.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spntipoexercicio.setAdapter(arraytipoexercicio);

		} catch (Exception e) {
			Toast.makeText(EditarExercicioActivity.this,
					"Erro ao listar os tipos de exercício!", Toast.LENGTH_SHORT).show();
		}
	}

	// recupera os grupos musculares
	public void recuperarGruposMusculares(){
		grupoDAO.open();
		listagrupos = grupoDAO.listarGrupos();
		grupoDAO.close();
	}


	// carrega o spinner com todos os grupos musculares
	public void carregarSpinnerGrupo(){

		recuperarGruposMusculares();
		try {

			arrayGrupo = new ArrayAdapter<Grupo>(this, android.R.layout.simple_spinner_item, listagrupos);
			arrayGrupo.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spnGrupo.setAdapter(arrayGrupo);

		} catch (Exception e) {
			Toast.makeText(EditarExercicioActivity.this,
					"Erro ao listar os grupos musculares!", Toast.LENGTH_SHORT).show();
		}
	}


	// recupera os exercicio
	public void recuperarExercicio(){
		exercicioDAO.open();
		exercicio = exercicioDAO.buscarExercicio(idexercicio);
		exercicioDAO.close();
	}


	// exibe o exercicio
	public void exibeExercicio(){

		recuperarExercicio();
		try {

			nomeExercicio.setText(exercicio.getNomeExercicio().toString());
			execucaoExercicio.setText(exercicio.getExecucaoExercicio().toString());

		} catch (Exception e) {
			Toast.makeText(EditarExercicioActivity.this,
					"Erro ao exibir o exercício!", Toast.LENGTH_SHORT).show();
		}
	}


	// atualiza o exercicio
	public void salvarExercicio(){
		if(nomeExercicio.getText().toString().equals("")){
			Toast.makeText(EditarExercicioActivity.this,
					"Insira um nome para o exercício!", Toast.LENGTH_SHORT).show();
		}
		else{

			try {
				grupo = new Grupo();
				exercicio = new Exercicio();
				tipoexercicio = new TipoExercicio();

				exercicio.setIdExercicio(idexercicio);
				tipoexercicio.setIdTipoExercicio(idtipoexercicio);
				grupo.setIdGrupo(idgrupo);

				exercicio.setNomeExercicio(nomeExercicio.getText().toString());
				exercicio.setExecucaoExercicio(execucaoExercicio.getText().toString());

				exercicioDAO.open();
				exercicioDAO.atualizarExercicio(tipoexercicio, grupo, exercicio);
				exercicioDAO.close();

				Toast.makeText(EditarExercicioActivity.this, "Exercício alterado!", Toast.LENGTH_SHORT).show();

				fecharTela();

			} catch (Exception e) {
				Toast.makeText(EditarExercicioActivity.this, "Erro ao alterar o exercício!", Toast.LENGTH_SHORT).show();
			}
		}
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
		alertaBuilder.setMessage("O exercício não será alterado.");
		alertaBuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				fecharTela();
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


	public void fecharTela(){
		Intent i = new Intent(this, ExerciciosCadastradosActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.editar_exercicio_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menusalvarEE:
			salvarExercicio();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}