package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.fragment.FragmentExerciciosAdicionados;
import br.com.mrs.mtf.model.Treino;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


public class EditarTreinoActivity extends FragmentActivity{

	private TreinoDAO treinoDAO;
	private EditText nomeTreino;
	private Treino treino;
	private long idtreino;
	private String nometreino;
	private int qtdexerciciosadicionados = 0;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editar_treino_activity);

		idtreino = getIntent().getLongExtra("idtreino", 0); // recuperando da tela visualizar treino e adicionar exercicio
		nometreino = getIntent().getStringExtra("nometreino"); // recuperando da tela visualizar treino e adicionar exercicio

		treinoDAO = new TreinoDAO(this);

		nomeTreino = (EditText) findViewById(R.id.edtNometreinoET);
		nomeTreino.setText(nometreino);

		passarParametro();
	}


	// passando o id do treino que esta sendo editado para o fragment
	public void passarParametro(){
		FragmentExerciciosAdicionados frag = (FragmentExerciciosAdicionados) fm.findFragmentById(R.id.fragExerciciosET);
		frag.recuperarParametro(idtreino);
	}


	// exibe a tela adicionar exercicio
	public void novoExercicio(){
		Intent i;
		if(nomeTreino.getText().toString().equals("")){
			Toast.makeText(EditarTreinoActivity.this,
					"Insira um nome para o treino!", Toast.LENGTH_SHORT).show();
		}
		else{
			nometreino = nomeTreino.getText().toString();

			i = new Intent(this, AdicionarExercicioTreinoActivity.class);
			i.putExtra("idtreino", idtreino);
			i.putExtra("nometreino", nometreino);
			startActivity(i);
			finish();
		}
	}


	/** exibe uma mensagem de confirmação de saida, 
	 *  caso o usuário clique no botão voltar do aparelho
	 */

	public void mensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Salvar?");
		alertaBuilder.setMessage("O treino será salvo.");
		alertaBuilder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				atualizarTreino();
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


	// recupera a quantidade de exercicios adicionados no treino
	public void recuperarQtdExerciciosAdicionados(){
		FragmentExerciciosAdicionados frag = (FragmentExerciciosAdicionados) fm.findFragmentById(R.id.fragExerciciosET);
		qtdexerciciosadicionados = frag.recuperarQtdexercicios();
	}

	// atualiza o treino
	public void atualizarTreino(){
		Intent i;

		recuperarQtdExerciciosAdicionados();

		if(nomeTreino.getText().toString().equals("")){
			Toast.makeText(EditarTreinoActivity.this,
					"Insira um nome para o treino!", Toast.LENGTH_SHORT).show();
		}
		else
			if(qtdexerciciosadicionados == 0){
				Toast.makeText(EditarTreinoActivity.this,
						"Nenhum exercício foi adicionado!", Toast.LENGTH_SHORT).show();
			}
			else
				try {

					if(qtdexerciciosadicionados != 0){

						treino = new Treino();

						treino.setIdTreino(idtreino);
						treino.setNomeTreino(nomeTreino.getText().toString());

						treinoDAO.open();
						treinoDAO.atualizarTreino(treino);
						treinoDAO.close();

						Toast.makeText(EditarTreinoActivity.this,
								"Treino alterado!", Toast.LENGTH_SHORT).show();

						i = new Intent(this, TreinosCadastradosActivity.class);
						startActivity(i);
						finish();
					}
				} catch (Exception e) {
					Toast.makeText(EditarTreinoActivity.this,
							"Erro ao alterar o treino!", Toast.LENGTH_SHORT).show();
				}
	}


	@Override
	public void onBackPressed(){
		mensagem();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.editar_treino_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menuadicionarET:
			novoExercicio();
			break;

		case R.id.menusalvarET:
			atualizarTreino();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}