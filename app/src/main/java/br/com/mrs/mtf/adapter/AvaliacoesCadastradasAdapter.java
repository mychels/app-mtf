package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Avaliacao;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AvaliacoesCadastradasAdapter extends BaseAdapter{

	private Context context;
	private List<Avaliacao> listaAvaliacoes;


	public AvaliacoesCadastradasAdapter(Context context, List<Avaliacao> listaAvaliacoes ){
		this.context = context;
		this.listaAvaliacoes = listaAvaliacoes;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaAvaliacoes.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaAvaliacoes.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Avaliacao avaliacao = listaAvaliacoes.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.avaliacoescadastradas_itemlista, null);

		TextView dataAvaliacao = (TextView) view.findViewById(R.id.tvDataavaliacaoAC);
		dataAvaliacao.setText(avaliacao.getDataAvaliacao());

		return view;
	}


}
