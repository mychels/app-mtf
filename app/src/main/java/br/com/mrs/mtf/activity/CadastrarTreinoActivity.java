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


public class CadastrarTreinoActivity extends FragmentActivity{


	private EditText nomeTreino;
	private TreinoDAO treinoDAO;
	private Treino treino;
	private boolean treinocadastrado = false;
	private long idtreino = 0;
	private String nometreino;
	private AlertDialog.Builder alertabuilder;
	private AlertDialog alerta;
	private int qtdexerciciosadicionados = 0;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_treino_activity);

		// recuperando valores da tela adicionar exercicio
		treinocadastrado = getIntent().getBooleanExtra("treinocadastrado", false);
		nometreino = getIntent().getStringExtra("nometreino");

		treinoDAO = new TreinoDAO(this);

		nomeTreino = (EditText) findViewById(R.id.edtNometreinoCT);
		nomeTreino.setText(nometreino);

		recuperarIdtreino();
		passarParametro();
	}

	// passa o idtreino para o fragment
	public void passarParametro(){
		FragmentExerciciosAdicionados frag = (FragmentExerciciosAdicionados) fm.findFragmentById(R.id.fragExerciciosCT);
		frag.recuperarParametro(idtreino);
	}


	// abre a tela adicionar exercicio 
	public void novoExercicio(){
		Intent i;
		if(nomeTreino.getText().toString().equals("")){
			Toast.makeText(CadastrarTreinoActivity.this,
					"Insira um nome para o treino!", Toast.LENGTH_SHORT).show();
		}
		else
			if(treinocadastrado == false){

				cadastrarTreino();

				nometreino = nomeTreino.getText().toString();

				i = new Intent(this, AdicionarExercicioTreinoActivity.class);
				i.putExtra("nometreino", nometreino);
				startActivity(i);
				finish();
			}
			else
				if(treinocadastrado == true){

					nometreino = nomeTreino.getText().toString();

					i = new Intent(this, AdicionarExercicioTreinoActivity.class);
					i.putExtra("nometreino", nometreino);
					startActivity(i);
					finish();
				}
	}


	// cadastra o treino
	public void cadastrarTreino(){

		if(treinocadastrado == false){

			try {
				treino = new Treino();

				treino.setNomeTreino(nomeTreino.getText().toString());

				treinoDAO.open();
				treinoDAO.cadastrarTreino(treino);
				treinoDAO.close();

				treinocadastrado = true;

			} catch (Exception e) {
				Toast.makeText(CadastrarTreinoActivity.this,
						"Erro ao cadastrar o treino!", Toast.LENGTH_SHORT).show();
			}
		}
	}


	// recupera o id do ultimo treino cadastrado
	public void recuperarIdtreino(){

		if(treinocadastrado == true){
			treinoDAO.open();
			idtreino = treinoDAO.recuperarUltimoIdTreino();
			treinoDAO.close();
		}
	}


	// recupera a quantidade de exercicios adicionados no treino
	public void recuperarQtdExerciciosAdicionados(){
		FragmentExerciciosAdicionados frag = (FragmentExerciciosAdicionados) fm.findFragmentById(R.id.fragExerciciosCT);
		qtdexerciciosadicionados = frag.recuperarQtdexercicios();
	}


	// atualiza o treino
	public void atualizarTreino(){

		recuperarQtdExerciciosAdicionados();
		recuperarIdtreino();

		if(nomeTreino.getText().toString().equals("")){
			Toast.makeText(CadastrarTreinoActivity.this,
					"Insira um nome para o treino!", Toast.LENGTH_SHORT).show();
		}
		else
			if(qtdexerciciosadicionados == 0){
				Toast.makeText(CadastrarTreinoActivity.this,
						"Nenhum exercício foi adicionado!", Toast.LENGTH_SHORT).show();
			}
			else
				try {

					if(treinocadastrado == true && qtdexerciciosadicionados != 0){

						treino = new Treino();

						treino.setIdTreino(idtreino);
						treino.setNomeTreino(nomeTreino.getText().toString());

						treinoDAO.open();
						treinoDAO.atualizarTreino(treino);
						treinoDAO.close();

						Toast.makeText(CadastrarTreinoActivity.this,
								"Treino cadastrado!", Toast.LENGTH_SHORT).show();

						fecharTela();
					}
				} catch (Exception e) {
					Toast.makeText(CadastrarTreinoActivity.this,
							"Erro ao cadastrar o treino!", Toast.LENGTH_SHORT).show();
				}
	}


	/** exibe uma mensagem de confirmação de saida, 
	 *  caso o usuário clique no botão voltar do aparelho
	 */
	public void mensagem(){

		alertabuilder = new AlertDialog.Builder(this);
		alertabuilder.setTitle("Sair?");
		alertabuilder.setMessage("O treino não será cadastrado.");
		alertabuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				removerTreino();
			}

		});
		alertabuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		alerta = alertabuilder.create();
		alerta.show();		
	}


	@Override
	public void onBackPressed(){
		mensagem();
	}


	// exclui o treino
	public void removerTreino(){
		recuperarIdtreino();

		try {
			treino = new Treino();

			treino.setIdTreino(idtreino);

			treinoDAO.open();
			treinoDAO.excluirTreino(treino);
			treinoDAO.close();

			fecharTela();
		} catch (Exception e) {
			Toast.makeText(this, "Erro ao cancelar o cadastro do treino!", Toast.LENGTH_SHORT).show();
		}
	}


	public void fecharTela(){
		Intent i = new Intent(this, TreinosCadastradosActivity.class);
		startActivity(i);
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cadastrar_treino_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menuadicionarCT:
			novoExercicio();
			break;

		case R.id.menusalvarCT:
			atualizarTreino();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}