package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.SeriesAdicionadasAdapter;
import br.com.mrs.mtf.dao.SerieDAO;
import br.com.mrs.mtf.model.Serie;
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

public class FragmentSeriesAdicionadas extends Fragment{

	
	private SerieDAO serieDAO;
	private Serie serie;
	private SeriesAdicionadasAdapter adapter;
	private List<Serie> listaseries;
	private ListView listview;
	private long idserie = 0;
	private int qtdseries = 0;
	private long idtreinoexercicio = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_series_adicionadas, container, false);
		listview = (ListView) view.findViewById(R.id.lvSeriesFSA);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		serieDAO = new SerieDAO(getActivity());
		listarSeriesExercicio();
	}


	// recupera o idtreinoexercicio
	public void recuperarParametro(long idtreinoexercicio){
		this.idtreinoexercicio = idtreinoexercicio;
	}


	// recupera as series adicionadas
	public void recuperarSeriesExercicio(){
		serieDAO.open();
		listaseries = serieDAO.listarSeries(idtreinoexercicio);
		serieDAO.close();
	}

	// lista as series adicionadas
	public void listarSeriesExercicio(){

		recuperarSeriesExercicio();

		adapter = new SeriesAdicionadasAdapter(getActivity(), listaseries);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				serie = (Serie) adapter.getItem(position);
				idserie = serie.getIdSerie();

				AlertDialog.Builder alertabuilder = new AlertDialog.Builder(getActivity());
				alertabuilder.setTitle("Excluir?");
				alertabuilder.setMessage("A série será excluída.");
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
						removerSerie();
						listarSeriesExercicio();
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	
			}
		});
	}


	// recupera a quantidade de series que foi adicionada para o exercicio
	public int recuperarQtdseries(){
		qtdseries = listview.getCount();
		return qtdseries;
	}


	// remove a serie
	public void removerSerie(){

		try {

			serie = new Serie();
			serie.setIdSerie(idserie);

			serieDAO.open();
			serieDAO.removerSerie(serie);
			serieDAO.close();

			Toast.makeText(getActivity(), "Série excluída!", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir a série!", Toast.LENGTH_SHORT).show();
		}
	}
}