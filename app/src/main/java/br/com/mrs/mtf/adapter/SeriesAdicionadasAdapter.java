package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Serie;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SeriesAdicionadasAdapter extends BaseAdapter{

	private Context context;
	private List<Serie> listaSeries;


	public SeriesAdicionadasAdapter(Context context,List<Serie> listaSeries) {

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

		Serie serie = listaSeries.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.seriesadicionadas_itemlista, null);

		TextView repeticao = (TextView) view.findViewById(R.id.tvQtdrepeticaoAE);
		repeticao.setText(serie.getQtdRepeticao());

		TextView carga = (TextView) view.findViewById(R.id.tvQtdcargaAE);
		carga.setText(serie.getQtdCarga());

		TextView descanso = (TextView) view.findViewById(R.id.tvQtddescansoAE);
		descanso.setText(serie.getQtdDescanso());

		return view;
	}	
}