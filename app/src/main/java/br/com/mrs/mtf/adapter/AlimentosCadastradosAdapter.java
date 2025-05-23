package br.com.mrs.mtf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Alimento;

public class AlimentosCadastradosAdapter extends BaseAdapter{

	private Context context;
	private List<Alimento> listaAlimentos;



	public AlimentosCadastradosAdapter(Context context,List<Alimento> listaAlimentos) {

		this.context = context;
		this.listaAlimentos = listaAlimentos;
	}

	@Override
	public int getCount() {
		return listaAlimentos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaAlimentos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Alimento alimento = listaAlimentos.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.alimentoscadastrados_itemlista, null);

		TextView nome = (TextView) view.findViewById(R.id.tvNomealimentoCA);
		nome.setText(alimento.getNomeAlimento());

		return view;
	}
}