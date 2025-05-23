package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.ExerciciosAdicionadosAdapter;
import br.com.mrs.mtf.dao.TreinoExercicioDAO;
import br.com.mrs.mtf.model.TreinoExercicio;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentExerciciosAdicionados extends Fragment{


	private TreinoExercicio treinoexercicio;
	private TreinoExercicioDAO treinoexercicioDAO;
	private ExerciciosAdicionadosAdapter adapter;
	private List<TreinoExercicio> listaexercicios;
	private ListView listview;
	private long idtreino = 0;
	private long idtreinoexercicio = 0;
	private int qtdexercicios = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_exercicios_adicionados, container, false);
		listview = (ListView) view.findViewById(R.id.lvExerciciosFCT);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		treinoexercicioDAO = new TreinoExercicioDAO(getActivity());
		listarExerciciosTreino();
	}


	// recupera o idtreino
	public void recuperarParametro(long idtreino){
		this.idtreino = idtreino;
	}


	// recupera todos os exercicios adicionados no treino
	public void recuperarExerciciosTreino(){
		treinoexercicioDAO.open();
		listaexercicios = treinoexercicioDAO.listarExerciciosTreino(idtreino);
		treinoexercicioDAO.close();
	}

	// lista todos os exercicios adicionados no treino
	public void listarExerciciosTreino(){

		recuperarExerciciosTreino();

		adapter = new ExerciciosAdicionadosAdapter(getActivity(), listaexercicios);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				treinoexercicio = (TreinoExercicio) adapter.getItem(position);
				idtreinoexercicio = treinoexercicio.getIdTreinoExercicio();

				AlertDialog.Builder alertabuilder = new AlertDialog.Builder(getActivity());
				alertabuilder.setTitle("Excluir?");
				alertabuilder.setMessage("O exercício será excluído do treino.");
				alertabuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {					
						dialog.dismiss();
					}

				});
				alertabuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						removerExercicio();
						listarExerciciosTreino();
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	
			}
		});
	}


	// recupera a quantidade de exercicios adicionados no treino
	public int recuperarQtdexercicios(){
		qtdexercicios = listview.getCount();
		return qtdexercicios;
	}


	// remove o registro da tabela treinoexercicio
	public void removerExercicio(){
		try {

			treinoexercicio = new TreinoExercicio();
			treinoexercicio.setIdTreinoExercicio(idtreinoexercicio);

			treinoexercicioDAO.open();
			treinoexercicioDAO.excluirExercicioTreino(treinoexercicio);
			treinoexercicioDAO.close();

			Toast.makeText(getActivity(), "Exercício excluído!", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir o exercício do treino!", Toast.LENGTH_SHORT).show();
		}
	}
}