package br.com.mrs.mtf.activity;


import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.AlimentoDAO;
import br.com.mrs.mtf.fragment.FragmentAlimentosCadastrados;
import br.com.mrs.mtf.model.Alimento;
import br.com.mrs.mtf.service.AlimentoService;

import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarAlimentoActivity extends FragmentActivity{

	private Alimento alimento;
	private AlimentoDAO alimentoDAO;
	private EditText nomeAlimento;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastrar_alimento_activity);

		alimentoDAO = new AlimentoDAO(this);

		nomeAlimento = (EditText) findViewById(R.id.edtNomealimentoCA);
	}


	// cadastra um novo alimento
	public void salvarAlimento(View view){

		if(nomeAlimento.getText().toString().equals("")){
			Toast.makeText(this, "Insira um nome para o alimento!", Toast.LENGTH_SHORT).show();
		}
		else{

			alimento = new Alimento();
			alimento.setNomeAlimento(nomeAlimento.getText().toString().trim());
			alimentoDAO.open();
			AlimentoService service = new AlimentoService(alimentoDAO);
			boolean sucesso = service.cadastrarAlimento(alimento);
			alimentoDAO.close();

			if (sucesso) {
				Toast.makeText(this, "Alimento cadastrado!", Toast.LENGTH_SHORT).show();
				FragmentAlimentosCadastrados frag = (FragmentAlimentosCadastrados) fm.findFragmentById(R.id.fragAlimentoscadastradosCA);
				frag.listarAlimentos();
			} else {
				Toast.makeText(this, "Já existe um alimento com esse nome!", Toast.LENGTH_SHORT).show();
			}
		}
	}


	@Override
	public void onBackPressed() {
		finish();
	}
}