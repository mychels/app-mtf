package br.com.mrs.mtf.activity;

import java.util.List;



import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.dao.SerieDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.dao.TreinoExercicioDAO;
import br.com.mrs.mtf.fragment.FragmentSeriesAdicionadas;
import br.com.mrs.mtf.model.Exercicio;
import br.com.mrs.mtf.model.Serie;
import br.com.mrs.mtf.model.Treino;
import br.com.mrs.mtf.model.TreinoExercicio;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarExercicioTreinoActivity extends FragmentActivity{

	private SerieDAO serieDAO;
	private Serie serie;
	private TreinoDAO treinoDAO;
	private AlertDialog.Builder alertabuilder;
	private AlertDialog alerta;
	private ExercicioDAO exercicioDAO;
	private Exercicio exercicio;
	private Spinner spnExercicio, spnDescanso;
	private ArrayAdapter<Exercicio> arrayExercicios;
	private List<Exercicio> listaexercicios;
	private ArrayAdapter<String> arrayDescanso;
	private String nometreino;
	private boolean editandotreino = true;
	private long idtreinoexercicio = 0;
	private long idtreino = 0;
	private long idexercicio = 0;
	private Treino treino;
	private TreinoExercicioDAO treinoexercicioDAO;
	private TreinoExercicio treinoexercicio;
	private int qtdseries = 0;
	private boolean treinocadastrado = true;
	private EditText qtdRepeticao, qtdCarga;
	private String qtdDescanso;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adicionar_exercicio_treino_activity);

		idtreino = getIntent().getLongExtra("idtreino", 0); // recuperando da tela editar treino
		nometreino = getIntent().getStringExtra("nometreino"); // recuperando da tela cadastrar treino e editar treino 

		treinoDAO = new TreinoDAO(this);
		recuperarIdtreino();

		treinoexercicioDAO = new TreinoExercicioDAO(this);
		serieDAO = new SerieDAO(this);

		exercicioDAO = new ExercicioDAO(this);
		spnExercicio = (Spinner) findViewById(R.id.spnExercicioAE);
		carregarSpinnerExercicio();
		spnExercicio.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				exercicio = (Exercicio) arrayExercicios.getItem(position);
				idexercicio = exercicio.getIdExercicio();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spnDescanso = (Spinner) findViewById(R.id.spnDescansoAE);
		carregarSpinnerDescanso();
		spnDescanso.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				qtdDescanso = (String) arrayDescanso.getItem(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		qtdRepeticao = (EditText) findViewById(R.id.edtQtdrepeticaoAE);
		qtdCarga = (EditText) findViewById(R.id.edtQtdcargaAE);

		adicionarExercicioTreino();
		passarParametro();
	}


	// recupera o idtreino
	public void recuperarIdtreino(){
		if(idtreino == 0){

			editandotreino = false;

			treinoDAO.open();
			idtreino = treinoDAO.recuperarUltimoIdTreino();
			treinoDAO.close();
		}
	}


	// passa o idtreinoexercicio para o fragment
	public void passarParametro(){
		FragmentSeriesAdicionadas frag = (FragmentSeriesAdicionadas) fm.findFragmentById(R.id.fragSeriesadicionadas);
		frag.recuperarParametro(idtreinoexercicio);
	}


	// recupera os exercicios cadastrados
	public void recuperarExercicios(){
		exercicioDAO.open();
		listaexercicios = exercicioDAO.listarTodosExercicios();
		exercicioDAO.close();
	}

	// carrega o spinner com os exercicios
	public void carregarSpinnerExercicio(){

		recuperarExercicios();
		try {
			arrayExercicios = new ArrayAdapter<Exercicio>(this, android.R.layout.simple_spinner_item, listaexercicios);
			arrayExercicios.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spnExercicio.setAdapter(arrayExercicios);
		} catch (Exception e) {
			Toast.makeText(this, "Erro ao listar os exercícios!", Toast.LENGTH_SHORT).show();
		}
	}

	// carrega o spinner com as quantidades de descanso
	public void carregarSpinnerDescanso(){

		String segundos[] = {"15 segundos","30 segundos","45 segundos","60 segundos"};

		arrayDescanso = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, segundos);
		arrayDescanso.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnDescanso.setAdapter(arrayDescanso);
	}


	// adiciona o exercicio no treino
	public void adicionarExercicioTreino(){

		try {

			treino = new Treino();
			exercicio = new Exercicio();

			treino.setIdTreino(idtreino);
			exercicio.setIdExercicio(idexercicio);

			treinoexercicioDAO.open();
			treinoexercicioDAO.adicionarExercicioTreino(treino, exercicio);

			recuperarUltimoidtreinoexercicio();
			treinoexercicioDAO.close();

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao adicionar o exercício!", Toast.LENGTH_SHORT).show();
		}
	}

	// recupera o ultimo idtreinoexercicio
	public void recuperarUltimoidtreinoexercicio(){
		idtreinoexercicio = treinoexercicioDAO.recuperarUltimoIdTreinoExercicio();
	}


	//atualiza a tabela treinoexercicio
	public void atualizarTreinoexercicio(){

		FragmentSeriesAdicionadas frag = (FragmentSeriesAdicionadas) fm.findFragmentById(R.id.fragSeriesadicionadas);
		qtdseries = frag.recuperarQtdseries();

		if(qtdseries == 0){
			Toast.makeText(AdicionarExercicioTreinoActivity.this,
					"Nenhuma série foi adicionada!", Toast.LENGTH_SHORT).show();
		}
		else
			try {

				treino = new Treino();
				exercicio = new Exercicio();
				treinoexercicio = new TreinoExercicio();

				treinoexercicio.setIdTreinoExercicio(idtreinoexercicio);
				treino.setIdTreino(idtreino);
				exercicio.setIdExercicio(idexercicio);

				treinoexercicioDAO.open();
				treinoexercicioDAO.atualizarExercicioTreino(treinoexercicio, treino, exercicio);
				treinoexercicioDAO.close();

				Toast.makeText(AdicionarExercicioTreinoActivity.this,
						"Exercício adicionado!", Toast.LENGTH_SHORT).show();

				fecharTela();
			} catch (Exception e) {
				Toast.makeText(AdicionarExercicioTreinoActivity.this,
						"Erro ao adicionar o exercício!", Toast.LENGTH_SHORT).show();
			}
	}


	/** exibe uma mensagem de confirmação de saida, 
	 *  caso o usuário clique no botão voltar do aparelho
	 */

	public void mensagem(){

		alertabuilder = new AlertDialog.Builder(this);
		alertabuilder.setTitle("Sair?");
		alertabuilder.setMessage("O exercício não será adicionado.");
		alertabuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				removerTreinoexercicio();
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


	// remove o registro da tabela treinoexercicio
	public void removerTreinoexercicio(){
		try {

			treinoexercicio = new TreinoExercicio();
			treinoexercicio.setIdTreinoExercicio(idtreinoexercicio);

			treinoexercicioDAO.open();
			treinoexercicioDAO.excluirExercicioTreino(treinoexercicio);
			treinoexercicioDAO.close();

			fecharTela();
		} catch (Exception e) {
			Toast.makeText(this, "Erro ao remover o exercício!", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onBackPressed(){
		mensagem();
	}

	// fecha a tela
	public void fecharTela(){
		Intent i;
		if(editandotreino == false){
			finish();
			i = new Intent(this, CadastrarTreinoActivity.class);
			i.putExtra("nometreino", nometreino);
			i.putExtra("treinocadastrado", treinocadastrado);
			startActivity(i);
		}
		else
			if(editandotreino == true){
				finish();
				i = new Intent(this, EditarTreinoActivity.class);
				i.putExtra("idtreino", idtreino);
				i.putExtra("nometreino", nometreino);
				startActivity(i);
			}
	}


	// adiciona uma serie para o exercicio
	public void adicionarSerie(View view){

		if(qtdRepeticao.getText().toString().equals("") || qtdCarga.getText().toString().equals("")){
			Toast.makeText(AdicionarExercicioTreinoActivity.this,
					"Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
		}
		else {

			try {
				serie = new Serie();
				treinoexercicio = new TreinoExercicio();

				treinoexercicio.setIdTreinoExercicio(idtreinoexercicio);

				serie.setQtdRepeticao(qtdRepeticao.getText().toString());
				serie.setQtdCarga(qtdCarga.getText().toString());
				serie.setQtdDescanso(qtdDescanso);

				serieDAO.open();
				serieDAO.adicionarSerie(treinoexercicio, serie);
				serieDAO.close();

				Toast.makeText(AdicionarExercicioTreinoActivity.this,
						"Série adicionada!", Toast.LENGTH_SHORT).show();

				FragmentSeriesAdicionadas frag = (FragmentSeriesAdicionadas) fm.findFragmentById(R.id.fragSeriesadicionadas);
				frag.listarSeriesExercicio();

			} catch (Exception e) {
				Toast.makeText(AdicionarExercicioTreinoActivity.this,
						"Erro ao adicionar a série!", Toast.LENGTH_SHORT).show();
			}
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.adicionar_exercicio_treino_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarAE:
			atualizarTreinoexercicio();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}