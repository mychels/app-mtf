package br.com.mrs.mtf.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.RotinaTreino;

public class TreinosAdicionadosAdapter extends BaseAdapter{

	private Context context;
	private List<RotinaTreino> listaTreinos;


	public TreinosAdicionadosAdapter(Context context,List<RotinaTreino> listaTreinos) {

		this.context = context;
		this.listaTreinos = listaTreinos;
	}

	@Override
	public int getCount() {
		return listaTreinos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaTreinos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		RotinaTreino rotinatreinos = listaTreinos.get(position);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.treinosadicionados_itemlista, null);

		TextView nome = (TextView) view.findViewById(R.id.tvNometreinoTA);
		nome.setText(rotinatreinos.getTreino().getNomeTreino());

		return view;
	}
}