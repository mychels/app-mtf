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

public class CadastrarDietaActivity extends Activity{


	private Dieta dieta;
	private DietaDAO dietaDAO;
	private EditText nomeDieta;
	private RelativeLayout rlcafemanha, rllanchemanha, rlalmoco, rllanchetarde, rljantar, rlceia;
	private long iddieta = 0;
	private String nomedieta;
	private boolean cadastrada = false;
	private long cafemanha = 1, lanchemanha = 2, almoco = 3, lanchetarde = 4, jantar = 5, ceia = 6;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_dieta_activity);

		// recuperando valores da tela adicionar alimento
		nomedieta = getIntent().getStringExtra("nomedieta");
		cadastrada = getIntent().getBooleanExtra("cadastrada", false);

		dietaDAO = new DietaDAO(this);

		nomeDieta = (EditText) findViewById(R.id.edtNomedietaCD);
		rlcafemanha = (RelativeLayout) findViewById(R.id.rlCafemanha);
		rllanchemanha = (RelativeLayout) findViewById(R.id.rlLanchemanha);
		rlalmoco = (RelativeLayout) findViewById(R.id.rlAlmoco);
		rllanchetarde = (RelativeLayout) findViewById(R.id.rlLanchetarde);
		rljantar = (RelativeLayout) findViewById(R.id.rlJantar);
		rlceia = (RelativeLayout) findViewById(R.id.rlCeia);

		nomeDieta.setText(nomedieta);

		rlcafemanha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if(nomeDieta.getText().toString().equals("")){

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", cafemanha);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
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

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", lanchemanha);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
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

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", almoco);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
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

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", lanchetarde);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
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

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", jantar);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
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

					Toast.makeText(CadastrarDietaActivity.this,
							"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
				}
				else
					if(cadastrada == false){

						cadastrarDieta();

						nomedieta = nomeDieta.getText().toString();
						Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
						i.putExtra("idrefeicao", ceia);
						i.putExtra("nomedieta", nomedieta);
						startActivity(i);	
						finish();
					}
					else
						if(cadastrada == true){

							nomedieta = nomeDieta.getText().toString();
							Intent  i = new Intent(CadastrarDietaActivity.this, AdicionarAlimentoRefeicaoActivity.class);
							i.putExtra("idrefeicao", ceia);
							i.putExtra("nomedieta", nomedieta);
							startActivity(i);	
							finish();
						}
			}
		});
	}

	// cadastra a dieta
	public void cadastrarDieta(){

		if(cadastrada == false){
			try {
				dieta = new Dieta();

				dieta.setNomeDieta("nome dieta");

				dietaDAO.open();
				dietaDAO.cadastrarDieta(dieta);
				dietaDAO.close();

				cadastrada = true;
			} catch (Exception e) {
				Toast.makeText(CadastrarDietaActivity.this,
						"Erro ao cadastrar a dieta!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	// recupera o id da ultima dieta cadastrada
	public void recuperarIddieta(){
		if(cadastrada == true){
			dietaDAO.open();
			iddieta = dietaDAO.recuperarUltimoIdDieta();
			dietaDAO.close();
		}
	}


	// salva a dieta
	public void salvarDieta(){
		Intent i;
		recuperarIddieta();

		if(nomeDieta.getText().toString().equals("")){
			Toast.makeText(CadastrarDietaActivity.this,
					"Insira um nome para a dieta!", Toast.LENGTH_SHORT).show();
		}
		else
			if(cadastrada == false){

				dieta = new Dieta();

				dieta.setNomeDieta(nomeDieta.getText().toString());

				dietaDAO.open();
				dietaDAO.cadastrarDieta(dieta);
				dietaDAO.close();

				Toast.makeText(CadastrarDietaActivity.this,
						"Dieta cadastrada!", Toast.LENGTH_SHORT).show();

				finish();
				i = new Intent(this, DietasCadastradasActivity.class);
				startActivity(i);
			}
			else
				if(cadastrada == true){

					dieta = new Dieta();

					dieta.setIdDieta(iddieta);
					dieta.setNomeDieta(nomeDieta.getText().toString());

					dietaDAO.open();
					dietaDAO.atualizarDieta(dieta);
					dietaDAO.close();

					Toast.makeText(CadastrarDietaActivity.this,
							"Dieta cadastrada!", Toast.LENGTH_SHORT).show();

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

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Sair?");
		alertaBuilder.setMessage("A dieta não será cadastrada.");
		alertaBuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				cancelarCadastroDieta();
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


	public void cancelarCadastroDieta(){
		Intent i;
		recuperarIddieta();

		try {
			if(cadastrada == true){

				dieta = new Dieta();

				dieta.setIdDieta(iddieta);

				dietaDAO.open();
				dietaDAO.excluirDieta(dieta);
				dietaDAO.close();

				finish();
				i = new Intent(this, DietasCadastradasActivity.class);
				startActivity(i);
			}
			else
				if(cadastrada == false){
					finish();
					i = new Intent(this, DietasCadastradasActivity.class);
					startActivity(i);
				}
		} catch (Exception e) {
			Toast.makeText(this, "Erro ao cancelar o cadastro da dieta!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cadastrar_dieta_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarCD:
			salvarDieta();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}