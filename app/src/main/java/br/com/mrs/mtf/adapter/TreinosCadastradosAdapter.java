package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Treino;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TreinosCadastradosAdapter extends BaseAdapter {

	private Context context;
	private List<Treino> listatreinos;
	private Treino treino;


	public TreinosCadastradosAdapter(Context context, List<Treino> listatreinos ){

		this.context = context;
		this.listatreinos = listatreinos;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listatreinos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listatreinos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		treino = listatreinos.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.treinoscadastrados_itemlista, null);

		TextView nomeTreino = (TextView) view.findViewById(R.id.tvNometreinoTC);
		nomeTreino.setText(treino.getNomeTreino());

		return view;
	}
}
