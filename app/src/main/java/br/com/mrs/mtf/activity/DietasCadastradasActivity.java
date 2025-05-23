package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.DietasCadastradasAdapter;
import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.dao.DietaDAO;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import br.com.mrs.mtf.model.Dieta;
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

public class DietasCadastradasActivity extends Activity implements OnItemClickListener{

	private AlimentoDAO alimentoDAO;
	private UnidadeMedidaDAO unidadeDAO;
	private Dieta dieta;
	private DietaDAO dietaDAO;
	private List<Dieta> listadietas;
	ListView listview;
	DietasCadastradasAdapter adapterDietas;
	private int qtdalimentoscadastrados = 0;
	private int qtdunidadescadastradas = 0;
	private long iddieta = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dietas_cadastradas_activity);

		alimentoDAO = new AlimentoDAO(this);
		unidadeDAO = new UnidadeMedidaDAO(this);
		dietaDAO = new DietaDAO(this);
		recuperarDietas();

		listview = (ListView) findViewById(R.id.lvDietasDC);

		adapterDietas = new DietasCadastradasAdapter(this, listadietas);
		listview.setAdapter(adapterDietas);
		listview.setOnItemClickListener(this);

		recuperarQtdAlimentos();
		recuperarQtdUnidades();
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

	// recupera todas as dietas cadastradas
	public void recuperarDietas(){
		dietaDAO.open();
		listadietas = dietaDAO.listarDietas();
		dietaDAO.close();
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		dieta = (Dieta) adapterDietas.getItem(position);
		iddieta = dieta.getIdDieta();

		Intent i = new Intent(this, VisualizarDietaActivity.class);
		i.putExtra("iddieta", iddieta);
		finish();
		startActivity(i);
	}


	// exibe a tela nova dieta
	public void novaDieta(){
		Intent i;
		if(qtdalimentoscadastrados == 0){
			Toast.makeText(this, "Nenhum alimento cadastrado!", Toast.LENGTH_SHORT).show();
		}
		else
			if(qtdunidadescadastradas == 0){
				Toast.makeText(this, "Nenhuma unidade cadastrada!", Toast.LENGTH_SHORT).show();
			}
			else{
				i = new Intent(this, CadastrarDietaActivity.class);
				startActivity(i);
				finish();
			}	
	}



	@Override
	public void onBackPressed(){
		finish();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dietas_cadastradas_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menunovaDC:
			novaDieta();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}