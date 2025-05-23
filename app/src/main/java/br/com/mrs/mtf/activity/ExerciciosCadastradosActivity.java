package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.GrupoDAO;
import br.com.mrs.mtf.fragment.FragmentExerciciosCadastrados;
import br.com.mrs.mtf.model.Grupo;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ExerciciosCadastradosActivity extends FragmentActivity implements OnItemSelectedListener{

	private Grupo grupo;
	private Spinner spnGrupo;
	private GrupoDAO grupoDAO;
	private List<Grupo> listagrupos;
	private ArrayAdapter<Grupo> arrayGrupo;
	private long idgrupo = 0;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicios_cadastrados_activity);

		spnGrupo = (Spinner) findViewById(R.id.spnGrupomuscularEC);	
		grupoDAO = new GrupoDAO(this);
		carregarSpinnerGrupo();
		spnGrupo.setOnItemSelectedListener(this);
	}

	// abre a tela novo exercicio
	public void novoExercicio(){
		Intent i = new Intent(this, CadastrarExercicioActivity.class);
		startActivity(i);
		finish();
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
			Toast.makeText(ExerciciosCadastradosActivity.this,
					"Erro ao listar os grupos musculares!", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		grupo = (Grupo) arrayGrupo.getItem(position);
		idgrupo = grupo.getIdGrupo();
	}


	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}


	/** filtra os exercicios
	 *  conforme o grupo muscular selecionado no spinner 
	 */

	public void filtrarExercicios(View view){
		try {

			FragmentExerciciosCadastrados frag = (FragmentExerciciosCadastrados) fm.findFragmentById(R.id.fragExercicioscadastrados);
			frag.listarExerciciosGrupo(idgrupo);

		} catch (Exception e) {
			Toast.makeText(ExerciciosCadastradosActivity.this,
					"Erro ao filtrar os exercícios!", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onBackPressed(){
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.exercicios_cadastrados_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menunovoEC:
			novoExercicio();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}