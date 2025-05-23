package br.com.mrs.mtf.activity;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.TreinosAdicionadosAdapter;
import br.com.mrs.mtf.dao.RotinaDAO;
import br.com.mrs.mtf.dao.RotinaTreinoDAO;
import br.com.mrs.mtf.model.Rotina;
import br.com.mrs.mtf.model.RotinaTreino;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VisualizarRotinaActivity extends Activity{


	private RotinaTreino rotinatreino;
	private Rotina rotina;
	private RotinaDAO rotinaDAO;
	private RotinaTreinoDAO rotinatreinoDAO;
	private List<RotinaTreino> listatreinos;
	private ListView lvtreinosegunda, lvtreinoterca, lvtreinoquarta, lvtreinoquinta, 
	lvtreinosexta, lvtreinosabado;
	private long idrotina = 0;
	private long idtreinosegunda = 0, idtreinoterca = 0, idtreinoquarta = 0, idtreinoquinta = 0, 
			idtreinosexta = 0, idtreinosabado = 0;
	private long segunda = 1, terca = 2, quarta = 3, quinta = 4, sexta = 5, sabado = 6;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visualizar_rotina_activity);

		rotinaDAO = new RotinaDAO(this);
		rotinatreinoDAO = new RotinaTreinoDAO(this);

		lvtreinosegunda = (ListView) findViewById(R.id.lvTreinosegundaVR);
		lvtreinoterca = (ListView) findViewById(R.id.lvTreinotercaVR);
		lvtreinoquarta = (ListView) findViewById(R.id.lvTreinoquartaVR);
		lvtreinoquinta = (ListView) findViewById(R.id.lvTreinoquintaVR);
		lvtreinosexta = (ListView) findViewById(R.id.lvTreinosextaVR);
		lvtreinosabado = (ListView) findViewById(R.id.lvTreinosabadoVR);

		buscarIdrotina();

		listarTreinosegunda();
		listarTreinoterca();
		listarTreinoquarta();
		listarTreinoquinta();
		listarTreinosexta();
		listarTreinosabado();		
	}

	// busca o id da rotina
	public void buscarIdrotina(){
		rotinaDAO.open();
		idrotina = rotinaDAO.recuperarIdRotina();
		rotinaDAO.close();
	}

	// lista o treino
	public void listarTreinosegunda(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, segunda);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinosegunda.setAdapter(adapter);

		lvtreinosegunda.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				RotinaTreino rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinosegunda = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinosegunda);
				startActivity(i);
			}
		});
	}

	// lista o treino de segunda
	public void listarTreinoterca(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, terca);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinoterca.setAdapter(adapter);

		lvtreinoterca.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinoterca = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinoterca);
				startActivity(i);
			}
		});
	}


	// lista o treino de segunda
	public void listarTreinoquarta(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, quarta);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinoquarta.setAdapter(adapter);
		lvtreinoquarta.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinoquarta = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinoquarta);
				startActivity(i);
			}
		});
	}

	// lista o treino de segunda
	public void listarTreinoquinta(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, quinta);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinoquinta.setAdapter(adapter);
		lvtreinoquinta.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinoquinta = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinoquinta);
				startActivity(i);
			}
		});
	}

	// lista o treino de segunda
	public void listarTreinosexta(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, sexta);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinosexta.setAdapter(adapter);
		lvtreinosexta.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinosexta = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinosexta);
				startActivity(i);
			}
		});
	}

	// lista o treino de segunda
	public void listarTreinosabado(){

		rotinatreinoDAO.open();
		listatreinos = rotinatreinoDAO.listarTreinoDia(idrotina, sabado);
		rotinatreinoDAO.close();

		final TreinosAdicionadosAdapter adapter = new TreinosAdicionadosAdapter(this, listatreinos);
		lvtreinosabado.setAdapter(adapter);
		lvtreinosabado.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				rotinatreino = (RotinaTreino) adapter.getItem(position);
				finish();
				idtreinosabado = rotinatreino.getTreino().getIdTreino();

				Intent i = new Intent(VisualizarRotinaActivity.this, VisualizarTreinoActivity.class);
				i.putExtra("idtreino", idtreinosabado);
				startActivity(i);
			}
		});
	}


	// abre a tela editar rotina
	public void editarRotina(){
		Intent i = new Intent(this, EditarRotinaActivity.class);
		startActivity(i);
		finish();
	}


	/** exibe uma mensagem de confirmação 
	 *  para exclusao da rotina
	 */

	public void exibeMensagem(){

		AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(this);
		alertaBuilder.setTitle("Excluir?");
		alertaBuilder.setMessage("A rotina será excluida.");
		alertaBuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {					
				dialog.dismiss();
				excluirRotina();
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


	// exclui a rotina
	public void excluirRotina(){
		try {

			rotina = new Rotina();
			rotina.setIdRotina(idrotina);

			rotinaDAO.open();
			rotinaDAO.excluirRotina(rotina);			
			rotinaDAO.close();

			Toast.makeText(this, "Rotina excluída!", Toast.LENGTH_SHORT).show();
			finish();

		} catch (Exception e) {
			Toast.makeText(this, "Erro ao excluir a rotina!", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed(){
		finish();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.visualizar_rotina_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {		
		case R.id.menueditarVR:
			editarRotina();
			break;

		case R.id.menuexcluirVR:
			exibeMensagem();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}