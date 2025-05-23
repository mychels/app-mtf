package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.SeriesRegistradasAdapter;
import br.com.mrs.mtf.dao.LogSerieDAO;
import br.com.mrs.mtf.model.LogSerie;
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

public class FragmentSeriesRegistradas extends Fragment{
	
	private LogSerieDAO logserieDAO;
	private LogSerie logserie;
	private SeriesRegistradasAdapter adapter;
	private List<LogSerie> listaseries;
	private ListView listview;
	private long idlogserie = 0;
	private int qtdlogseries = 0;
	private long idexercicio = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_series_registradas, container, false);
		listview = (ListView) view.findViewById(R.id.lvSeriesFSR);
		
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		logserieDAO = new LogSerieDAO(getActivity());

		listarLogsserie();
	}
	
	
	// recupera o id do exercicio
	public void recuperarParametro(long idexercicio){
		this.idexercicio = idexercicio;
	}

	// recupera as series registradas do exercicio
	public void recuperarLogsserie(){
		logserieDAO.open();
		listaseries = logserieDAO.listarLogseries(idexercicio);
		logserieDAO.close();
	}
	
	// lista as series
	public void listarLogsserie(){
		recuperarLogsserie();

		adapter = new SeriesRegistradasAdapter(getActivity(), listaseries);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				logserie = (LogSerie) adapter.getItem(position);
				idlogserie = logserie.getIdLogSerie();
				
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
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	
				
			}
		});
			}
	
	// remove uma serie
	public void removerSerie(){
		try {
			
			logserie = new LogSerie();
			logserie.setIdLogSerie(idlogserie);
			
			logserieDAO.open();
			logserieDAO.removerLogserie(logserie);
			logserieDAO.close();
			
			listarLogsserie();
			
			Toast.makeText(getActivity(), "Série excluída!", Toast.LENGTH_SHORT).show();
			
			
		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir a série!", Toast.LENGTH_SHORT).show();
		}
	}
	
	// exclui todas as series do exercicio
	public void excluirLogseries(){
		try {
		
			logserieDAO.open();
			logserieDAO.excluirLogseries(idexercicio);
			logserieDAO.close();
			
			listarLogsserie();
			
			Toast.makeText(getActivity(), "Séries excluídas!", Toast.LENGTH_SHORT).show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// recupera a quantidade de log series registradas
	public int recuperarQtdseries(){
		qtdlogseries = listview.getCount();
		return qtdlogseries;
	}
}