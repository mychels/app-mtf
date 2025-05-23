package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.TreinoExercicio;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciciosAdicionadosAdapter extends BaseAdapter{

	private Context context;
	private List<TreinoExercicio> listaExercicios;
	
	
	public ExerciciosAdicionadosAdapter(Context context,List<TreinoExercicio> listaExercicios) {

		this.context = context;
		this.listaExercicios = listaExercicios;
	}

	@Override
	public int getCount() {
		return listaExercicios.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaExercicios.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TreinoExercicio treinoexercicio = listaExercicios.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.exerciciosadicionados_itemlista, null);
		
		TextView nome = (TextView) view.findViewById(R.id.tvNomeexercicioCT);
		nome.setText(treinoexercicio.getExercicio().getNomeExercicio());
				
		return view;
	}	
}