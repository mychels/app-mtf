package br.com.mrs.mtf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.DietaRefeicao;

public class AlimentosAdicionadosAdapter extends BaseAdapter{

	private Context context;
	private List<DietaRefeicao> listaAlimentos;


	public AlimentosAdicionadosAdapter(Context context,List<DietaRefeicao> listaAlimentos) {

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

		DietaRefeicao dietaalimentos = listaAlimentos.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.alimentosadicionados_itemlista, null);

		TextView nomeAlimento = (TextView) view.findViewById(R.id.tvNomealimentoAA);
		nomeAlimento.setText(dietaalimentos.getAlimento().getNomeAlimento());

		TextView qtdAlimento = (TextView) view.findViewById(R.id.tvQtdalimentoAA);
		qtdAlimento.setText(dietaalimentos.getQtdAlimento());

		TextView nomeUnidade = (TextView) view.findViewById(R.id.tvNomeunidadeAA);
		nomeUnidade.setText(dietaalimentos.getUnidade().getNomeUnidadeMedida());

		return view;
	}
}