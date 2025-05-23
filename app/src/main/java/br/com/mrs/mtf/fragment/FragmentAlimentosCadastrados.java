package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.AlimentosCadastradosAdapter;
import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.model.Alimento;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class FragmentAlimentosCadastrados extends Fragment{

	private AlimentoDAO alimentoDAO;
	private Alimento alimento;
	private AlimentosCadastradosAdapter adapter;
	private List<Alimento> listaalimentos;
	private ListView listview;
	private long idalimento = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_alimentos_cadastrados, container, false);
		listview = (ListView) view.findViewById(R.id.lvAlimentosFAC);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		alimentoDAO = new AlimentoDAO(getActivity());

		listarAlimentos();
	}


	public void recuperarAlimentos(){
		alimentoDAO.open();
		listaalimentos = alimentoDAO.listarAlimentos();
		alimentoDAO.close();
	}

	public void listarAlimentos(){

		recuperarAlimentos();

		adapter = new AlimentosCadastradosAdapter(getActivity(), listaalimentos);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				alimento = (Alimento) adapter.getItem(position);
				idalimento = alimento.getIdAlimento();

				AlertDialog.Builder alertabuilder = new AlertDialog.Builder(getActivity());
				alertabuilder.setTitle("Excluir?");
				alertabuilder.setMessage("O alimento será excluído.");
				alertabuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {					
						dialog.dismiss();
					}

				});
				alertabuilder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						removerAlimento();
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	

			}
		});
	}

	public void removerAlimento(){
		try {
			alimento = new Alimento();
			alimento.setIdAlimento(idalimento);

			alimentoDAO.open();
			alimentoDAO.excluirAlimento(alimento);
			alimentoDAO.close();

			listarAlimentos();

			Toast.makeText(getActivity(), "Alimento excluído!", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir o alimento!", Toast.LENGTH_LONG).show();
		}
	}
}