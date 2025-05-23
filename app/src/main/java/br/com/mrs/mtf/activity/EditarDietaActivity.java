package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.DietaDAO;
import br.com.mrs.mtf.model.Dieta;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class EditarDietaActivity extends Activity{


	private Dieta dieta;
	private DietaDAO dietaDAO;
	private EditText nomeDieta;
	private RelativeLayout rlcafemanha, rllanchemanha, rlalmoco, rllanchetarde, rljantar, rlceia;
	private long iddieta = 0;
	private String nomedieta;
	private long cafemanha = 1, lanchemanha = 2, almoco = 3, lanchetarde = 4, jantar = 5, ceia = 6;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_dieta_activity);

		// recuperando os valores das telas visualizar dieta e adicionar alimento
		iddieta = getIntent().getLongExtra("iddieta", 0);
		nomedieta = getIntent().getStringExtra("nomedieta");

		dietaDAO = new DietaDAO(this);

		nomeDieta = (EditText) findViewById(R.id.edtNomedietaED);
		rlcafemanha = (RelativeLayout) findViewById(R.id.rlCafemanhaED);
		rllanchemanha = (RelativeLayout) findViewById(R.id.rlLanchemanhaED);
		rlalmoco = (RelativeLayout) findViewById(R.id.rlAlmocoED);
		rllanchetarde = (RelativeLayout) findViewById(R.id.rlLanchetardeED);
		rljantar = (RelativeLayout) findViewById(R.id.rlJantarED);
		rlceia = (RelativeLayout) findViewById(R.id.rlCeiaED);

		nomeDieta.setText(nomedieta);

		rlcafemanha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);

					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", cafemanha);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});

		rllanchemanha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", lanchemanha);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});


		rlalmoco.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", almoco);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});

		rllanchetarde.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", lanchetarde);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});

		rljantar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", jantar);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});

		rlceia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(EditarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else{
					nomedieta = nomeDieta.getText().toString();
					Intent  i = new Intent(EditarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
					i.putExtra("iddieta", iddieta);
					i.putExtra("idrefeicao", ceia);
					i.putExtra("nomedieta", nomedieta);
					startActivity(i);	
					finish();
				}
			}
		});
	}

	// atualiza a dieta
	public void atualizarDieta(){
		Intent i;

		if(nomeDieta.getText().toString().equals("")){
			Toast.makeText(this,"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
		}
		else{
			dieta = new Dieta();

			dieta.setIdDieta(iddieta);
			dieta.setNomeDieta(nomeDieta.getText().toString());

			dietaDAO.open();
			dietaDAO.atualizarDieta(dieta);
			dietaDAO.close();

			Toast.makeText(EditarDietaActivity.this,
					"Dieta alterada!", Toast.LENGTH_SHORT).show();

			finish();
			i = new Intent(this, DietasCadastradasActivity.class);
			startActivity(i);
		}
	}



	@Override
	public void onBackPressed(){
		mensagem();
	}


	public void mensagem(){

		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Salvar?");
		alerta.setMessage("A dieta será salva.");
		alerta.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				atualizarDieta();
			}

		});
		alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog exibeAlerta = alerta.create();
		exibeAlerta.show();		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.editar_dieta_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarED:
			atualizarDieta();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}