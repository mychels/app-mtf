package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.activity.VisualizarExercicioActivity;
import br.com.mrs.mtf.adapter.ExerciciosCadastradosAdapter;
import br.com.mrs.mtf.dao.ExercicioDAO;
import br.com.mrs.mtf.model.Exercicio;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentExerciciosCadastrados extends Fragment implements OnItemClickListener{

	private ExercicioDAO exercicioDAO;
	private Exercicio exercicio;
	private ExerciciosCadastradosAdapter adapter;
	private List<Exercicio> listaexercicios;
	private ListView listview;
	private long idexercicio = 0;
	private long idgrupo = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_exercicios_cadastrados, container, false);
		listview = (ListView) view.findViewById(R.id.lvExerciciosFEC);
		
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		exercicioDAO = new ExercicioDAO(getActivity());

		listarTodosExercicios();
		listview.setOnItemClickListener(this);
	}


	// recupera todos os exercicios
	public void recuperarTodosExercicios(){
		exercicioDAO.open();
		listaexercicios = exercicioDAO.listarTodosExercicios();
		exercicioDAO.close();
	}

	// recupera os exercicios pelo idgrupo
	public void recuperarExerciciosGrupo(){
		exercicioDAO.open();
		listaexercicios = exercicioDAO.listarExerciciosGrupo(idgrupo);
		exercicioDAO.close();
	}

	// lista todos os exercicios
	public void listarTodosExercicios(){

		recuperarTodosExercicios();

		adapter = new ExerciciosCadastradosAdapter(getActivity(), listaexercicios);
		listview.setAdapter(adapter);
	}

	// lista os exercicios pelo idgrupo
	public void listarExerciciosGrupo(long idgrupo){

		this.idgrupo = idgrupo;

		recuperarExerciciosGrupo();

		adapter = new ExerciciosCadastradosAdapter(getActivity(), listaexercicios);
		listview.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		exercicio = (Exercicio) adapter.getItem(position);

		getActivity().finish();
		idexercicio = exercicio.getIdExercicio();
		Intent i = new Intent(getActivity(), VisualizarExercicioActivity.class);
		i.putExtra("idexercicio", idexercicio);
		startActivity(i);
	}
}