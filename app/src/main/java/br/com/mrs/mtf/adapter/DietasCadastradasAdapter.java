package br.com.mrs.mtf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Dieta;


public class DietasCadastradasAdapter extends BaseAdapter{

	private Context context;
	private List<Dieta> listaDietas;


	public DietasCadastradasAdapter(Context context, List<Dieta> listaDietas ){

		this.context = context;
		this.listaDietas = listaDietas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaDietas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaDietas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Dieta dieta = listaDietas.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dietascadastradas_itemlista, null);

		TextView nome = (TextView) view.findViewById(R.id.tvNomedietaDC);
		nome.setText(dieta.getNomeDieta());

		return view;
	}
}