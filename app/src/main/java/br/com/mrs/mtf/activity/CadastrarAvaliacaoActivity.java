package br.com.mrs.mtf.activity;


import java.text.SimpleDateFormat;
import java.util.Date;

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

public class CadastrarAvaliacaoActivity extends Activity{

	private AvaliacaoDAO avaliacaoDAO;
	private Avaliacao avaliacao;
	private EditText altura, peso, peito, ombro, bracorelaxado, bracocontraido, 
	antebraco, abdomen, cintura, coxasuperior, perna, coxamedia, coxainferior, quadril, 
	dcsubescapular, dctricipital, dcbicipital, dcpeitoral, dcaxilarmedia, dcsuprailiaca, 
	dcabdominal, dccoxasuperior, dccoxamedia, dccoxainferior, dcpanturrilhamedial; 
	private String dataatual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_avaliacao_activity);

		recuperarDataAtual();

		avaliacaoDAO = new AvaliacaoDAO(this);

		altura = (EditText) findViewById(R.id.edtAlturaCA);
		peso = (EditText) findViewById(R.id.edtPesoCA);
		peito = (EditText) findViewById(R.id.edtPeitoCA);
		ombro = (EditText) findViewById(R.id.edtOmbroCA);
		bracorelaxado = (EditText) findViewById(R.id.edtBracorelaxadoCA);
		bracocontraido = (EditText) findViewById(R.id.edtBracocontraidoCA);
		antebraco = (EditText) findViewById(R.id.edtAntebracoCA);
		cintura = (EditText) findViewById(R.id.edtCinturaCA);
		abdomen = (EditText) findViewById(R.id.edtAbdomenCA);
		quadril = (EditText) findViewById(R.id.edtQuadrilCA);
		coxasuperior = (EditText) findViewById(R.id.edtCoxasuperiorCA);
		coxamedia = (EditText) findViewById(R.id.edtCoxamediaCA);
		coxainferior = (EditText) findViewById(R.id.edtCoxainferiorCA);
		perna = (EditText) findViewById(R.id.edtPernaCA);
		dcbicipital = (EditText) findViewById(R.id.edtBicipitalCA);
		dctricipital = (EditText) findViewById(R.id.edtTricipitalCA);
		dcsubescapular = (EditText) findViewById(R.id.edtSubescapularCA);
		dcaxilarmedia = (EditText) findViewById(R.id.edtAxilarmediaCA);
		dcabdominal = (EditText) findViewById(R.id.edtAbdominalCA);
		dcsuprailiaca = (EditText) findViewById(R.id.edtSuprailiacaCA);
		dcpeitoral = (EditText) findViewById(R.id.edtPeitoralCA);
		dccoxasuperior = (EditText) findViewById(R.id.edtDcCoxasuperiorCA);
		dccoxamedia = (EditText) findViewById(R.id.edtDcCoxamediaCA);
		dccoxainferior = (EditText) findViewById(R.id.edtDcCoxainferiorCA);
		dcpanturrilhamedial = (EditText) findViewById(R.id.edtPanturrilhamedialCA);
	}


	// recupera a data atual do aparelho
	private String recuperarDataAtual() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		dataatual = dateFormat.format(date);

		return dataatual;
	}


	// cadastra a avaliação
	public void salvarAvaliacao(){
		try {
			avaliacao = new Avaliacao();

			avaliacao.setDataAvaliacao(dataatual);
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
			avaliacaoDAO.cadastrarAvaliacao(avaliacao);
			avaliacaoDAO.close();

			Toast.makeText(CadastrarAvaliacaoActivity.this,
					"Avaliação cadastrada!", Toast.LENGTH_SHORT).show();

			fecharTela();

		} catch (Exception e) {
			Toast.makeText(CadastrarAvaliacaoActivity.this,
					"Erro ao cadastrar a avaliação!", Toast.LENGTH_SHORT).show();
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
		alertaBuilder.setMessage("A avaliação não será cadastrada.");
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
		inflater.inflate(R.menu.cadastrar_avaliacao_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarCA:
			salvarAvaliacao();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}