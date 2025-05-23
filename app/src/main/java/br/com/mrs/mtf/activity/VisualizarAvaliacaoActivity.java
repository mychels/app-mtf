package br.com.mrs.mtf.activity;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.AvaliacaoDAO;
import br.com.mrs.mtf.model.Avaliacao;
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

public class VisualizarAvaliacaoActivity extends Activity{

	private AvaliacaoDAO avaliacaoDAO;
	private Avaliacao avaliacao;
	private TextView altura, peso, peito, ombro, bracorelaxado, bracocontraido, 
	antebraco, abdomen, cintura, coxasuperior, perna, coxamedia, coxainferior, quadril, 
	dcsubescapular, dctricipital, dcbicipital, dcpeitoral, dcaxilarmedia, dcsuprailiaca, 
	dcabdominal, dccoxasuperior, dccoxamedia, dccoxainferior, dcpanturrilhamedial; 
	private long idavaliacao = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_avaliacao_activity);

		// recuperando o valor da tela avaliacoes cadastradas
		idavaliacao = getIntent().getLongExtra("idavaliacao", 0);

		altura = (TextView) findViewById(R.id.tvAlturaVA); 
		peso = (TextView) findViewById(R.id.tvPesoVA);
		peito = (TextView) findViewById(R.id.tvPeitoVA);
		ombro = (TextView) findViewById(R.id.tvOmbroVA); 
		antebraco = (TextView) findViewById(R.id.tvAntebracoVA);
		bracorelaxado = (TextView) findViewById(R.id.tvBracorelVA); 
		bracocontraido = (TextView) findViewById(R.id.tvBracoconVA);
		cintura = (TextView) findViewById(R.id.tvCinturaVA);
		abdomen = (TextView) findViewById(R.id.tvAbdomenVA); 
		quadril = (TextView) findViewById(R.id.tvQuadrilVA); 
		coxasuperior = (TextView) findViewById(R.id.tvCoxasupVA); 
		coxamedia = (TextView) findViewById(R.id.tvCoxamedVA); 
		coxainferior = (TextView) findViewById(R.id.tvCoxainfVA); 
		perna = (TextView) findViewById(R.id.tvPernaVA); 
		dcbicipital = (TextView) findViewById(R.id.tvBicipitalVA);
		dctricipital = (TextView) findViewById(R.id.tvTricipitalVA);
		dcsubescapular = (TextView) findViewById(R.id.tvSubescapularVA); 
		dcaxilarmedia = (TextView) findViewById(R.id.tvAxilarmedVA); 
		dcabdominal = (TextView) findViewById(R.id.tvAbdominalVA); 
		dcsuprailiaca = (TextView) findViewById(R.id.tvSuprailiacaVA); 
		dcpeitoral = (TextView) findViewById(R.id.tvPeitoralVA); 
		dccoxasuperior = (TextView) findViewById(R.id.tvDccoxasupVA); 
		dccoxamedia = (TextView) findViewById(R.id.tvDccoxamedVA);
		dccoxainferior = (TextView) findViewById(R.id.tvDccoxainfVA);
		dcpanturrilhamedial = (TextView) findViewById(R.id.tvPanturrilhamedVA);

		avaliacaoDAO = new AvaliacaoDAO(this);


		exibeAvaliacao();
	}

	// recupera a avaliacao pelo id
	public void recuperarAvaliacao(){
		avaliacaoDAO.open();
		avaliacao = avaliacaoDAO.buscarAvaliacao(idavaliacao);
		avaliacaoDAO.close();
	}


	// exibe a avaliacao
	public void exibeAvaliacao(){

		recuperarAvaliacao();

		try {

			altura.setText(avaliacao.getAltura().toString());
			peso.setText(avaliacao.getPeso().toString());
			peito.setText(avaliacao.getPeito().toString());
			ombro.setText(avaliacao.getOmbro().toString());
			antebraco.setText(avaliacao.getAntebraco().toString());
			bracorelaxado.setText(avaliacao.getBracoRelaxado().toString());
			bracocontraido.setText(avaliacao.getBracoContraido().toString());
			cintura.setText(avaliacao.getCintura().toString());
			abdomen.setText(avaliacao.getAbdomen().toString()); 
			quadril.setText(avaliacao.getQuadril().toString());
			coxasuperior.setText(avaliacao.getCoxaSuperior().toString());
			coxamedia.setText(avaliacao.getCoxaMedia().toString()); 
			coxainferior.setText(avaliacao.getCoxaInferior().toString());
			perna.setText(avaliacao.getPerna().toString());
			dcbicipital.setText(avaliacao.getDcBicipital().toString());
			dctricipital.setText(avaliacao.getDcTricipital().toString());
			dcsubescapular.setText(avaliacao.getDcSubescapular().toString());
			dcaxilarmedia.setText(avaliacao.getDcAxilarmedia().toString());
			dcabdominal.setText(avaliacao.getDcAbdominal().toString());
			dcsuprailiaca.setText(avaliacao.getDcSuprailiaca().toString()); 
			dcpeitoral.setText(avaliacao.getDcPeitoral().toString()); 
			dccoxasuperior.setText(avaliacao.getDcCoxasuperior().toString());
			dccoxamedia.setText(avaliacao.getDcCoxamedia().toString());
			dccoxainferior.setText(avaliacao.getDcCoxainferior().toString());
			dcpanturrilhamedial.setText(avaliacao.getDcPanturrilhamedial().toString());

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao exibir a avaliação!", Toast.LENGTH_SHORT).show();
		}
	}


	// abre a tela editar avaliacao
	public void editarAvaliacao(){	
		Intent i = new Intent(this, EditarAvaliacaoActivity.class);
		i.putExtra("idavaliacao", idavaliacao);
		startActivity(i);
		finish();
	}


	/** exibe uma mensagem de confirmação para exclusão
	 */

	public void exibeMensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Excluir?");
		alertaBuilder.setMessage("A avaliação será excluída.");
		alertaBuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				excluiAvaliacao();
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


	// exclui a avaliacao
	public void excluiAvaliacao(){

		try {
			avaliacao = new Avaliacao();
			avaliacao.setIdAvaliacao(idavaliacao);

			avaliacaoDAO.open();
			avaliacaoDAO.excluirAvaliacao(avaliacao);
			avaliacaoDAO.close();

			Toast.makeText(this, "Avaliação excluída!", Toast.LENGTH_SHORT).show();

			fecharTela();

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao excluir a avaliação!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed(){
		fecharTela();
	}

	public void fecharTela(){
		Intent i = new Intent(this, AvaliacoesCadastradasActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.visualizar_avaliacao_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menueditarVA:
			editarAvaliacao();
			break;

		case R.id.menuexcluirVA:
			exibeMensagem();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}