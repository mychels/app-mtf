package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.RotinaDAO;
import br.com.mrs.mtf.dao.RotinaTreinoDAO;
import br.com.mrs.mtf.dao.TreinoDAO;
import br.com.mrs.mtf.model.Dia;
import br.com.mrs.mtf.model.Rotina;
import br.com.mrs.mtf.model.Treino;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastrarRotinaActivity extends Activity{


	private Rotina rotina;
	private RotinaDAO rotinaDAO;
	private Dia dia;
	private Treino treino;
	private TreinoDAO treinoDAO;
	private RotinaTreinoDAO rotinatreinoDAO;
	private List<Treino> listatreinos;
	private ArrayAdapter<Treino> array;
	private Spinner spntreinosegunda, spntreinoterca, spntreinoquarta, spntreinoquinta, 
	spntreinosexta, spntreinosabado;
	private long idtreinosegunda, idtreinoterca, idtreinoquarta, idtreinoquinta,
	idtreinosexta, idtreinosabado;
	private long idrotina = 0;
	private AlertDialog.Builder alertabuilder;
	private AlertDialog alerta;
	private long segunda = 1, terca = 2, quarta = 3, quinta = 4, sexta = 5, sabado = 6;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_rotina_activity);

		rotinaDAO = new RotinaDAO(this);
		treinoDAO = new TreinoDAO(this);
		rotinatreinoDAO = new RotinaTreinoDAO(this);

		spntreinosegunda = (Spinner) findViewById(R.id.spnTreinosegundaCR);
		spntreinoterca = (Spinner) findViewById(R.id.spnTreinotercaCR);
		spntreinoquarta = (Spinner) findViewById(R.id.spnTreinoquartaCR);
		spntreinoquinta = (Spinner) findViewById(R.id.spnTreinoquintaCR);
		spntreinosexta = (Spinner) findViewById(R.id.spnTreinosextaCR);
		spntreinosabado = (Spinner) findViewById(R.id.spnTreinosabadoCR);

		carregarSpinners();

		spntreinosegunda.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinosegunda = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spntreinoterca.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinoterca = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spntreinoquarta.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinoquarta = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spntreinoquinta.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinoquinta = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spntreinosexta.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinosexta = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		spntreinosabado.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				treino = (Treino) array.getItem(position);
				idtreinosabado = treino.getIdTreino();	
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});
	}

	// recupera todos os treinos cadastrados
	public void recuperarTreinos(){
		treinoDAO.open();
		listatreinos = treinoDAO.listarTreinos();
		treinoDAO.close();
	}

	// carrega os spinners com os treinos
	public void carregarSpinners(){

		recuperarTreinos();
		try {

			array = new ArrayAdapter<Treino>(this, android.R.layout.simple_spinner_item, listatreinos);
			array.setDropDownViewResource(android.R.layout.simple_spinner_item);
			spntreinosegunda.setAdapter(array);
			spntreinoterca.setAdapter(array);
			spntreinoquarta.setAdapter(array);
			spntreinoquinta.setAdapter(array);
			spntreinosexta.setAdapter(array);
			spntreinosabado.setAdapter(array);
		} catch (Exception e) {
			Toast.makeText(CadastrarRotinaActivity.this,
					"Erro ao listar treinos!", Toast.LENGTH_SHORT).show();
		}
	}


	// salva a rotina
	public void salvarRotina(){

		cadastrarRotina();
		recuperarIdrotina();
		adicionarTreinosegunda();
		adicionarTreinoterca();
		adicionarTreinoquarta();
		adicionarTreinoquinta();
		adicionarTreinosexta();
		adicionarTreinosabado();

		Toast.makeText(CadastrarRotinaActivity.this,
				"Rotina cadastrada!", Toast.LENGTH_SHORT).show();

		finish();
	}

	// cadastra a rotina
	public void cadastrarRotina(){

		rotina = new Rotina();

		rotina.setNomeRotina("Minha rotina");

		rotinaDAO.open();
		rotinaDAO.cadastrarRotina(rotina);
		rotinaDAO.close();
	}


	// recupera o id da rotina
	public void recuperarIdrotina(){
		rotinaDAO.open();
		idrotina = rotinaDAO.recuperarIdRotina();
		rotinaDAO.close();
	}


	// adiciona um treino
	public void adicionarTreinosegunda(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(segunda);
		treino.setIdTreino(idtreinosegunda);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}

	// adiciona um treino
	public void adicionarTreinoterca(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(terca);
		treino.setIdTreino(idtreinoterca);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}

	// adiciona um treino
	public void adicionarTreinoquarta(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(quarta);
		treino.setIdTreino(idtreinoquarta);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}

	// adiciona um treino
	public void adicionarTreinoquinta(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(quinta);
		treino.setIdTreino(idtreinoquinta);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}

	// adiciona um treino
	public void adicionarTreinosexta(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(sexta);
		treino.setIdTreino(idtreinosexta);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}

	// adiciona um treino
	public void adicionarTreinosabado(){

		rotina = new Rotina();
		dia = new Dia();
		treino = new Treino();

		rotina.setIdRotina(idrotina);
		dia.setIdDia(sabado);
		treino.setIdTreino(idtreinosabado);

		rotinatreinoDAO.open();
		rotinatreinoDAO.adicionarTreinoDia(rotina, dia, treino);
		rotinatreinoDAO.close();
	}



	@Override
	public void onBackPressed(){
		mensagem();
	}


	/** exibe uma mensagem de confirmação de saida, 
	 *  caso o usuário clique no botão voltar do aparelho
	 */

	public void mensagem(){

		alertabuilder = new AlertDialog.Builder(this);
		alertabuilder.setTitle("Sair?");
		alertabuilder.setMessage("A rotina não será cadastrada.");
		alertabuilder.setPositiveButton("Sair", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				finish();
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
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.cadastrar_rotina_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menusalvarCR:
			salvarRotina();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}