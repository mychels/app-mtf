package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.AvaliacoesCadastradasAdapter;
import br.com.mrs.mtf.dao.AvaliacaoDAO;
import br.com.mrs.mtf.model.Avaliacao;
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

public class AvaliacoesCadastradasActivity extends Activity implements OnItemClickListener{

	private Avaliacao avaliacao;
	private AvaliacaoDAO avaliacaoDAO;
	private List<Avaliacao> listaavaliacoes;
	private ListView listview;
	private AvaliacoesCadastradasAdapter adapter;
	private long idavaliacao = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avaliacoes_cadastradas_activity);

		avaliacaoDAO = new AvaliacaoDAO(this);

		listview = (ListView) findViewById(R.id.lvAvaliacoesAC);
		listarAvaliacoes();
		listview.setOnItemClickListener(this);
	}

	// recupera todas as avaliações cadastradas
	public void recuperarAvaliacoes(){
		avaliacaoDAO.open();
		listaavaliacoes = avaliacaoDAO.listarAvaliacoes();
		avaliacaoDAO.close();	
	}

	// lista todas as avaliações
	public void listarAvaliacoes(){
		recuperarAvaliacoes();

		try {
			adapter = new AvaliacoesCadastradasAdapter(this, listaavaliacoes);
			listview.setAdapter(adapter);
		} catch (Exception e) {
			Toast.makeText(AvaliacoesCadastradasActivity.this,
					"Erro ao listar todas as avaliações!", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

		avaliacao = (Avaliacao) adapter.getItem(position);
		idavaliacao = avaliacao.getIdAvaliacao();

		Intent i = new Intent(this, VisualizarAvaliacaoActivity.class);
		i.putExtra("idavaliacao", idavaliacao);
		startActivity(i);
		finish();
	}

	// abre a tela nova avaliacao
	public void novaAvaliacao(){
		Intent i = new Intent(this, CadastrarAvaliacaoActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public void onBackPressed(){
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.avaliacoes_cadastradas_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menunovaAC:
			novaAvaliacao();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}