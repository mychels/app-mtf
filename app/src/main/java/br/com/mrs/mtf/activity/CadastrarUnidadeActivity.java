package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.UnidadeMedidaDAO;
import br.com.mrs.mtf.fragment.FragmentUnidadesCadastradas;
import br.com.mrs.mtf.model.UnidadeMedida;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarUnidadeActivity extends FragmentActivity{

	private UnidadeMedida unidade;
	private UnidadeMedidaDAO unidadeDAO;
	private EditText nomeUnidade;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_unidade_activity);

		unidadeDAO = new UnidadeMedidaDAO(this);

		nomeUnidade = (EditText) findViewById(R.id.edtNomeunidade);
	}


	// cadastra uma nova unidade de medida
	public void salvarUnidade(View view){

		if(nomeUnidade.getText().toString().equals("")){
			Toast.makeText(this, "Insira um nome para a unidade!", Toast.LENGTH_SHORT).show();
		}
		else{

			unidade = new UnidadeMedida();

			unidade.setNomeUnidadeMedida(nomeUnidade.getText().toString());

			unidadeDAO.open();
			unidadeDAO.cadastrarUnidade(unidade);
			unidadeDAO.close();

			Toast.makeText(this, "Unidade cadastrada!", Toast.LENGTH_SHORT).show();

			FragmentUnidadesCadastradas frag = (FragmentUnidadesCadastradas) fm.findFragmentById(R.id.fragUnidadescadastradas);
			frag.listarUnidades();
		}
	}


	@Override
	public void onBackPressed() {
		finish();
	}
}