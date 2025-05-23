package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.UnidadesCadastradasAdapter;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import br.com.mrs.mtf.model.UnidadeMedida;
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


public class FragmentUnidadesCadastradas extends Fragment{

	private UnidadeMedidaDAO unidadeDAO;
	private UnidadeMedida unidade;
	private UnidadesCadastradasAdapter adapter;
	private List<UnidadeMedida> listaunidades;
	private ListView listview;
	private long idunidade = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_unidades_cadastradas, container, false);
		listview = (ListView) view.findViewById(R.id.lvUnidades);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		unidadeDAO = new UnidadeMedidaDAO(getActivity());

		listarUnidades();
	}


	public void recuperarUnidades(){
		unidadeDAO.open();
		listaunidades = unidadeDAO.listarUnidades();
		unidadeDAO.close();
	}

	public void listarUnidades(){

		recuperarUnidades();

		adapter = new UnidadesCadastradasAdapter(getActivity(), listaunidades);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				unidade = (UnidadeMedida) adapter.getItem(position);
				idunidade = unidade.getIdUnidadeMedida();

				AlertDialog.Builder alertabuilder = new AlertDialog.Builder(getActivity());
				alertabuilder.setTitle("Excluir?");
				alertabuilder.setMessage("A unidade será excluída.");
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
						removerUnidade();
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	

			}
		});
	}

	public void removerUnidade(){
		try {
			unidade = new UnidadeMedida();
			unidade.setIdUnidadeMedida(idunidade);

			unidadeDAO.open();
			unidadeDAO.excluirUnidade(unidade);
			unidadeDAO.close();

			listarUnidades();

			Toast.makeText(getActivity(), "Unidade excluída!", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir a unidade!", Toast.LENGTH_SHORT).show();
		}
	}
}