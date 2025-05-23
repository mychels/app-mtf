package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.LogSerie;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeriesRegistradasAdapter extends BaseAdapter{

	private Context context;
	private List<LogSerie> listaSeries;


	public SeriesRegistradasAdapter(Context context,List<LogSerie> listaSeries) {

		this.context = context;
		this.listaSeries = listaSeries;
	}

	@Override
	public int getCount() {
		return listaSeries.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaSeries.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LogSerie logserie = listaSeries.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.seriesregistradas_itemlista, null);

		TextView datalog = (TextView) view.findViewById(R.id.tvDatalogSR);
		datalog.setText(logserie.getDataLog());
		
		TextView repeticao = (TextView) view.findViewById(R.id.tvQtdrepeticoesSR);
		repeticao.setText(logserie.getQtdRepeticao());

		TextView carga = (TextView) view.findViewById(R.id.tvQtdcargaSR);
		carga.setText(logserie.getQtdCarga());

		return view;
	}	
}