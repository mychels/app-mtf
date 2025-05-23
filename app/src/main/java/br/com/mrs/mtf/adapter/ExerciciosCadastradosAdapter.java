package br.com.mrs.mtf.adapter;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.model.Exercicio;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ExerciciosCadastradosAdapter extends BaseAdapter {

	private Context context;
	private List<Exercicio> listaexercicios;
	private Exercicio exercicio;


	public ExerciciosCadastradosAdapter(Context context, List<Exercicio> listaexercicios ){

		this.context = context;
		this.listaexercicios = listaexercicios;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaexercicios.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaexercicios.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		exercicio = listaexercicios.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.exercicioscadastrados_itemlista, null);

		TextView nomeExercicio = (TextView) view.findViewById(R.id.tvNomeexercicioEC);
		nomeExercicio.setText(exercicio.getNomeExercicio());

		TextView grupoMuscular = (TextView) view.findViewById(R.id.tvGrupomuscularEC);
		grupoMuscular.setText(exercicio.getGrupo().getNomeGrupo());

		return view;
	}
}