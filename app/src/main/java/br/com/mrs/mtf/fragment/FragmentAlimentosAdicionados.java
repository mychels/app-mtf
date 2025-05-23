package br.com.mrs.mtf.fragment;

import java.util.List;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.adapter.AlimentosAdicionadosAdapter;
import br.com.mrs.mtf.dao.DietaRefeicaoDAO;
import br.com.mrs.mtf.model.DietaRefeicao;
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


public class FragmentAlimentosAdicionados extends Fragment{


	private DietaRefeicaoDAO dietarefeicaoDAO;
	private DietaRefeicao dietarefeicao;
	private AlimentosAdicionadosAdapter adapter;
	private List<DietaRefeicao> listaalimentos;
	private ListView listview;
	private long iddieta = 0;
	private long idrefeicao = 0;
	private long iddietarefeicao = 0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_alimentos_adicionados, container, false);
		listview = (ListView) view.findViewById(R.id.lvAlimentosFAA);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		dietarefeicaoDAO = new DietaRefeicaoDAO(getActivity());

		listarAlimentos();
	}

	// recupera os ids
	public void recuperarParametros(long iddieta, long idrefeicao){

		this.iddieta = iddieta;
		this.idrefeicao = idrefeicao;
	}


	// recupera os alimentos da refeicao
	public void recuperarAlimentosRefeicao(){
		dietarefeicaoDAO.open();
		listaalimentos = dietarefeicaoDAO.listarAlimentosRefeicao(iddieta, idrefeicao);
		dietarefeicaoDAO.close();
	}

	// lista os alimentos da refeicao
	public void listarAlimentos(){

		recuperarAlimentosRefeicao();

		adapter = new AlimentosAdicionadosAdapter(getActivity(), listaalimentos);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				dietarefeicao = (DietaRefeicao) adapter.getItem(position);
				iddietarefeicao = dietarefeicao.getIdDietaRefeicao();

				AlertDialog.Builder alertabuilder = new AlertDialog.Builder(getActivity());
				alertabuilder.setTitle("Excluir?");
				alertabuilder.setMessage("O alimento será excluído da refeição.");
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
						removerAlimentoRefeicao();
					}
				});

				AlertDialog alerta = alertabuilder.create();
				alerta.show();	

			}
		});
	}

	// remove o alimento da refeicao
	public void removerAlimentoRefeicao(){
		try {

			dietarefeicao = new DietaRefeicao();
			dietarefeicao.setIdDietaRefeicao(iddietarefeicao);

			dietarefeicaoDAO.open();
			dietarefeicaoDAO.removerAlimentoRefeicao(dietarefeicao);
			dietarefeicaoDAO.close();

			listarAlimentos();

			Toast.makeText(getActivity(), "Alimento excluído!", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			Toast.makeText(getActivity(), "Erro ao excluir o alimento!", Toast.LENGTH_LONG).show();
		}
	}
}