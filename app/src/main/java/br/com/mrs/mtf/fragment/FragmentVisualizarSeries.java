package br.com.mrs.mtf.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.activity.RegistrarLogserieActivity;
import br.com.mrs.mtf.adapter.SeriesAdicionadasAdapter;
import br.com.mrs.mtf.dao.LogSerieDAO;
import br.com.mrs.mtf.dao.SerieDAO;
import br.com.mrs.mtf.model.Serie;
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
import android.widget.Toast;

public class FragmentVisualizarSeries extends Fragment{

	private Serie serie;
	private SerieDAO serieDAO;
	private LogSerieDAO logserieDAO;
	private SeriesAdicionadasAdapter adapter;
	private List<Serie> listaseries;
	private ListView listview;
	private long idtreinoexercicio = 0;
	private long idserie = 0;
	private String dataatual;
	private boolean existelog = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_visualizar_series, container, false);
		listview = (ListView) view.findViewById(R.id.lvSeriesFVS);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		serieDAO = new SerieDAO(getActivity());
		logserieDAO = new LogSerieDAO(getActivity());

		recuperarIdtreinoexercicio(idtreinoexercicio);
		listarSeriesExercicio();
	}

	// recupera o idtreinoexercicio
	public void recuperarIdtreinoexercicio(long idtreinoexercicio){
		this.idtreinoexercicio = idtreinoexercicio;
	}


	public void recuperarSeriesExercicio(){
		serieDAO.open();
		listaseries = serieDAO.listarSeries(idtreinoexercicio);
		serieDAO.close();
	}

	public void listarSeriesExercicio(){

		recuperarSeriesExercicio();

		adapter = new SeriesAdicionadasAdapter(getActivity(), listaseries);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				serie = (Serie) adapter.getItem(position);
				idserie = serie.getIdSerie();

				verificarExisteLogserie();

				if(existelog == true){
					Toast.makeText(getActivity(),"A série já foi registrada!", Toast.LENGTH_SHORT).show();	
				}
				else
					if(existelog == false){

						Intent i = new Intent(getActivity(), RegistrarLogserieActivity.class);
						i.putExtra("idserie", idserie);
						startActivity(i);
					}
			}
		});
	}

	// recupera a data atual do aparelho
	private void recuperarDataAtual() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		dataatual = dateFormat.format(date);
	}

	public void verificarExisteLogserie(){

		recuperarDataAtual();

		logserieDAO.open();
		existelog = logserieDAO.verificarLogserieExiste(idserie, dataatual);
		logserieDAO.close();
	}
}