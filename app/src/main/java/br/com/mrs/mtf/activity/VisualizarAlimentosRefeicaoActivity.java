package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.AlimentosAdicionadosAdapter;
import br.com.mrs.mtf.dao.DietaRefeicaoDAO;
import br.com.mrs.mtf.model.DietaRefeicao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class VisualizarAlimentosRefeicaoActivity extends Activity{

	private long iddieta = 0;
	private long idrefeicao = 0;
	private ListView listview;
	private DietaRefeicaoDAO dietarefeicaoDAO;
	private AlimentosAdicionadosAdapter adapter;
	private List<DietaRefeicao> listaalimentos; 
	private TextView nomeRefeicao;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_alimentos_refeicao_activity);

		// recuperando os valores da tela visualizar dieta
		iddieta = getIntent().getLongExtra("iddieta", 0);
		idrefeicao = getIntent().getLongExtra("idrefeicao", 0);

		dietarefeicaoDAO = new DietaRefeicaoDAO(this);

		listview = (ListView) findViewById(R.id.lvAlimentosVA);
		listarAlimentos();

		nomeRefeicao = (TextView) findViewById(R.id.tvNomerefeicaoVA);
		exibeNomerefeicao();
	}

	// recupera os alimentos da refeicao
	public void recuperarAlimentosRefeicao(){
		dietarefeicaoDAO.open();
		listaalimentos = dietarefeicaoDAO.listarAlimentosRefeicao(iddieta, idrefeicao);
		dietarefeicaoDAO.close();
	}

	// lista os alimentos da refeicao
	public void listarAlimentos(){
		recuperarAlimentosRefeicao();

		adapter = new AlimentosAdicionadosAdapter(this, listaalimentos);
		listview.setAdapter(adapter);
	}

	// exibe o nome da refeicao
	public void exibeNomerefeicao(){

		if(idrefeicao == 1){
			nomeRefeicao.setText("Café da manhã");
		}
		else
			if(idrefeicao == 2){
				nomeRefeicao.setText("Lanche da manhã");
			}
			else
				if(idrefeicao == 3){
					nomeRefeicao.setText("Almoço");
				}
				else
					if(idrefeicao == 4){
						nomeRefeicao.setText("Lanche da tarde");
					}
					else
						if(idrefeicao == 5){
							nomeRefeicao.setText("Jantar");
						}
						else
							if(idrefeicao == 6){
								nomeRefeicao.setText("Ceia");
							}
	}

	@Override
	public void onBackPressed() {

		Intent  i = new Intent(this, VisualizarDietaActivity.class);
		i.putExtra("iddieta", iddieta);
		startActivity(i);
		finish();
	}
}