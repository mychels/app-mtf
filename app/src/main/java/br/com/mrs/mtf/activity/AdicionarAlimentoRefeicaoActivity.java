package br.com.mrs.mtf.activity;


import java.util.List;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.dao.DietaDAO;
import br.com.mrs.mtf.dao.DietaRefeicaoDAO;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import br.com.mrs.mtf.fragment.FragmentAlimentosAdicionados;
import br.com.mrs.mtf.model.Alimento;
import br.com.mrs.mtf.model.Dieta;
import br.com.mrs.mtf.model.DietaRefeicao;
import br.com.mrs.mtf.model.Refeicao;
import br.com.mrs.mtf.model.UnidadeMedida;
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

public class AdicionarAlimentoRefeicaoActivity extends FragmentActivity{


	private UnidadeMedida unidade;
	private UnidadeMedidaDAO unidademedidaDAO;
	private List<UnidadeMedida> listaunidades;
	private ArrayAdapter<UnidadeMedida> arrayUnidade;
	private long idunidade = 0;
	private Dieta dieta;
	private DietaDAO dietaDAO;
	private Refeicao refeicao;
	private Alimento alimento;
	private AlimentoDAO alimentoDAO;
	private DietaRefeicao dietarefeicao;
	private DietaRefeicaoDAO dietarefeicaoDAO;
	private Spinner spinnerAlimento, spinnerUnidade;
	private EditText qtdAlimento;
	private List<Alimento> listaalimentos;
	private ArrayAdapter<Alimento> arrayAlimento;
	private long iddieta = 0;
	private long idrefeicao = 0;
	private long idalimento = 0;
	private String nomedieta;
	private boolean editando = false;
	private boolean cadastrando = false;


	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adicionar_alimento_refeicao_activity);

		// recuperando da tela editar dieta
		iddieta = getIntent().getLongExtra("iddieta", 0);

		// recuperando das telas cadastrar dieta e editar dieta
		nomedieta = getIntent().getStringExtra("nomedieta");
		idrefeicao = getIntent().getLongExtra("idrefeicao", 0);

		dietaDAO = new DietaDAO(this);
		recuperarIddieta();
		passarParametros();

		alimentoDAO = new AlimentoDAO(this);
		unidademedidaDAO = new UnidadeMedidaDAO(this);
		dietarefeicaoDAO = new DietaRefeicaoDAO(this);


		spinnerAlimento = (Spinner) findViewById(R.id.spnAlimentoAA);
		qtdAlimento = (EditText) findViewById(R.id.edtQtdalimentoAA);
		spinnerUnidade = (Spinner) findViewById(R.id.spnUnidademedidaAA);

		carregarSpinnerAlimento();
		spinnerAlimento.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				alimento = (Alimento) arrayAlimento.getItem(position);
				idalimento = alimento.getIdAlimento();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub	
			}
		});

		carregarSpinnerUnidade();
		spinnerUnidade.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				unidade = (UnidadeMedida) arrayUnidade.getItem(position);
				idunidade = unidade.getIdUnidadeMedida();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	// recupera o id da dieta
	public void recuperarIddieta(){
		try {
			if(iddieta != 0){
				editando = true;
			}
			else
				if(iddieta == 0){
					cadastrando = true;

					dietaDAO.open();
					iddieta = dietaDAO.recuperarUltimoIdDieta();
					dietaDAO.close();
				}
		} catch (Exception e) {
		}
	}


	// passa os ids para o fragment
	public void passarParametros(){
		FragmentAlimentosAdicionados frag = (FragmentAlimentosAdicionados) fm.findFragmentById(R.id.fragAlimentosAA);
		frag.recuperarParametros(iddieta, idrefeicao);
	}


	// recupera os alimentos cadastrados
	public void recuperarAlimentos(){
		alimentoDAO.open();
		listaalimentos = alimentoDAO.listarAlimentos();
		alimentoDAO.close();
	}


	// carrega o spinner alimento
	public void carregarSpinnerAlimento(){

		recuperarAlimentos();
		try {
			arrayAlimento = new ArrayAdapter<Alimento>(this, android.R.layout.simple_spinner_item, listaalimentos);
			arrayAlimento.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spinnerAlimento.setAdapter(arrayAlimento);

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao listar os alimentos!", Toast.LENGTH_SHORT).show();
		}
	}


	// recupera as unidades de medidas cadastradas
	public void recuperarUnidades(){
		unidademedidaDAO.open();
		listaunidades = unidademedidaDAO.listarUnidades();
		unidademedidaDAO.close();
	}


	// carrega o spinner unidade
	public void carregarSpinnerUnidade(){

		recuperarUnidades();
		try {
			arrayUnidade = new ArrayAdapter<UnidadeMedida>(this, android.R.layout.simple_spinner_item, listaunidades);
			arrayUnidade.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spinnerUnidade.setAdapter(arrayUnidade);

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao listar as unidades de medidas!", Toast.LENGTH_SHORT).show();
		}
	}


	// adiciona o alimento na refeicao
	public void adicionarAlimento(View view){

		if(qtdAlimento.getText().toString().equals("")){
			Toast.makeText(AdicionarAlimentoRefeicaoActivity.this,
					"Insira uma quantidade!", Toast.LENGTH_SHORT).show();
		}
		else
			try {

				dieta = new Dieta();
				refeicao = new Refeicao();
				alimento = new Alimento();
				unidade = new UnidadeMedida();
				dietarefeicao = new DietaRefeicao();

				dieta.setIdDieta(iddieta);
				refeicao.setIdRefeicao(idrefeicao);
				alimento.setIdAlimento(idalimento);
				dietarefeicao.setQtdAlimento(qtdAlimento.getText().toString());
				unidade.setIdUnidadeMedida(idunidade);

				dietarefeicaoDAO.open();
				dietarefeicaoDAO.adicionarAlimentoRefeicao(dieta, refeicao, alimento, dietarefeicao, unidade);
				dietarefeicaoDAO.close();

				Toast.makeText(AdicionarAlimentoRefeicaoActivity.this,
						"Alimento adicionado!", Toast.LENGTH_SHORT).show();


				FragmentAlimentosAdicionados frag = (FragmentAlimentosAdicionados) fm.findFragmentById(R.id.fragAlimentosAA);
				frag.listarAlimentos();

			} catch (Exception e) {
				Toast.makeText(AdicionarAlimentoRefeicaoActivity.this,
						"Erro ao adicionar o alimento!", Toast.LENGTH_SHORT).show();
			}
	}


	public void salvar(){
		fecharTela();	
	}


	public void fecharTela(){
		Intent i;

		if(editando == true){
			i = new Intent(this, EditarDietaActivity.class);
			i.putExtra("iddieta", iddieta);
			i.putExtra("nomedieta", nomedieta);
			startActivity(i);
			finish();
		}
		else
			if(cadastrando == true){
				i = new Intent(this, CadastrarDietaActivity.class);
				i.putExtra("cadastrada", cadastrando);
				i.putExtra("nomedieta", nomedieta);
				startActivity(i);
				finish();
			}
	}


	@Override
	public void onBackPressed(){
		fecharTela();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.adicionar_alimento_refeicao_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarAA:
			salvar();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}