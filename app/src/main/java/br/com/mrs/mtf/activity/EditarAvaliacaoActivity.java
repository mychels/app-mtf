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
import android.widget.EditText;
import android.widget.Toast;

public class EditarAvaliacaoActivity extends Activity{

	private AvaliacaoDAO avaliacaoDAO;
	private Avaliacao avaliacao;
	private EditText altura, peso, peito, ombro, bracorelaxado, bracocontraido, 
	antebraco, abdomen, cintura, coxasuperior, perna, coxamedia, coxainferior, quadril, 
	dcsubescapular, dctricipital, dcbicipital, dcpeitoral, dcaxilarmedia, dcsuprailiaca, 
	dcabdominal, dccoxasuperior, dccoxamedia, dccoxainferior, dcpanturrilhamedial; 
	private long idavaliacao = 0;
	private String dataavaliacao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_avaliacao_activity);

		// recuperando o valor da tela visualizar avaliacao
		idavaliacao = getIntent().getLongExtra("idavaliacao", 0);

		avaliacaoDAO = new AvaliacaoDAO(this);

		altura = (EditText) findViewById(R.id.edtAlturaEA);
		peso = (EditText) findViewById(R.id.edtPesoEA);
		peito = (EditText) findViewById(R.id.edtPeitoEA);
		ombro = (EditText) findViewById(R.id.edtOmbroEA);
		bracorelaxado = (EditText) findViewById(R.id.edtBracorelaxadoEA);
		bracocontraido = (EditText) findViewById(R.id.edtBracocontraidoEA);
		antebraco = (EditText) findViewById(R.id.edtAntebracoEA);
		cintura = (EditText) findViewById(R.id.edtCinturaEA);
		abdomen = (EditText) findViewById(R.id.edtAbdomenEA);
		quadril = (EditText) findViewById(R.id.edtQuadrilEA);
		coxasuperior = (EditText) findViewById(R.id.edtCoxasuperiorEA);
		coxamedia = (EditText) findViewById(R.id.edtCoxamediaEA);
		coxainferior = (EditText) findViewById(R.id.edtCoxainferiorEA);
		perna = (EditText) findViewById(R.id.edtPernaEA);	
		dcbicipital = (EditText) findViewById(R.id.edtBicipitalEA);
		dctricipital = (EditText) findViewById(R.id.edtTricipitalEA);
		dcsubescapular = (EditText) findViewById(R.id.edtSubescapularEA);
		dcaxilarmedia = (EditText) findViewById(R.id.edtAxilarmediaEA);
		dcabdominal = (EditText) findViewById(R.id.edtAbdominalEA);
		dcsuprailiaca = (EditText) findViewById(R.id.edtSuprailiacaEA);
		dcpeitoral = (EditText) findViewById(R.id.edtPeitoralEA);
		dccoxasuperior = (EditText) findViewById(R.id.edtDcCoxasuperiorEA);
		dccoxamedia = (EditText) findViewById(R.id.edtDcCoxamediaEA);
		dccoxainferior = (EditText) findViewById(R.id.edtDcCoxainferiorEA);
		dcpanturrilhamedial = (EditText) findViewById(R.id.edtPanturrilhamedialEA);

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

			// inserindo a data da avaliação em uma variavel
			dataavaliacao = avaliacao.getDataAvaliacao().toString();

			// inserindo os valores nos edittext
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
			Toast.makeText(EditarAvaliacaoActivity.this,
					"Erro ao exibir a avaliação!", Toast.LENGTH_SHORT).show();
		}
	}


	// atualiza a avaliacao
	public void salvarAvaliacao(){	
		try {

			avaliacao = new Avaliacao();

			avaliacao.setIdAvaliacao(idavaliacao);
			avaliacao.setDataAvaliacao(dataavaliacao); // passando o valor da variavel
			avaliacao.setAltura(altura.getText().toString());
			avaliacao.setPeso(peso.getText().toString());
			avaliacao.setPeito(peito.getText().toString());
			avaliacao.setOmbro(ombro.getText().toString());
			avaliacao.setBracoRelaxado(bracorelaxado.getText().toString());
			avaliacao.setBracoContraido(bracocontraido.getText().toString());
			avaliacao.setAntebraco(antebraco.getText().toString());
			avaliacao.setCintura(cintura.getText().toString());
			avaliacao.setAbdomen(abdomen.getText().toString());
			avaliacao.setQuadril(quadril.getText().toString());
			avaliacao.setCoxaSuperior(coxasuperior.getText().toString());
			avaliacao.setCoxaMedia(coxamedia.getText().toString());
			avaliacao.setCoxaInferior(coxainferior.getText().toString());
			avaliacao.setPerna(perna.getText().toString());
			avaliacao.setDcBicipital(dcbicipital.getText().toString());
			avaliacao.setDcTricipital(dctricipital.getText().toString());
			avaliacao.setDcSubescapular(dcsubescapular.getText().toString());
			avaliacao.setDcAxilarmedia(dcaxilarmedia.getText().toString());
			avaliacao.setDcAbdominal(dcabdominal.getText().toString());
			avaliacao.setDcSuprailiaca(dcsuprailiaca.getText().toString());
			avaliacao.setDcPeitoral(dcpeitoral.getText().toString());
			avaliacao.setDcCoxasuperior(dccoxasuperior.getText().toString());
			avaliacao.setDcCoxamedia(dccoxamedia.getText().toString());
			avaliacao.setDcCoxainferior(dccoxainferior.getText().toString());
			avaliacao.setDcPanturrilhamedial(dcpanturrilhamedial.getText().toString());

			avaliacaoDAO.open();
			avaliacaoDAO.atualizarAvaliacao(avaliacao);
			avaliacaoDAO.close();

			Toast.makeText(EditarAvaliacaoActivity.this,
					"Avaliação alterada!", Toast.LENGTH_SHORT).show();

			fecharTela();

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(EditarAvaliacaoActivity.this,
					"Erro ao alterar a avaliação!", Toast.LENGTH_SHORT).show();
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
		alertaBuilder.setMessage("A avaliação não será alterada.");
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
		Intent i = new Intent(this, AvaliacoesCadastradasActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.editar_avaliacao_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarEA:
			salvarAvaliacao();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}