package br.com.mrs.mtf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.UnidadeMedida;

public class UnidadesCadastradasAdapter extends BaseAdapter{

	private Context context;
	private List<UnidadeMedida> listaUnidades;



	public UnidadesCadastradasAdapter(Context context,List<UnidadeMedida> listaUnidades) {

		this.context = context;
		this.listaUnidades = listaUnidades;
	}

	@Override
	public int getCount() {
		return listaUnidades.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaUnidades.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		UnidadeMedida unidade = listaUnidades.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.unidadescadastradas_itemlista, null);

		TextView nome = (TextView) view.findViewById(R.id.tvNomeunidadeUC);
		nome.setText(unidade.getNomeUnidadeMedida());

		return view;
	}
}