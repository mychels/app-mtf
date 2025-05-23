package br.com.mrs.mtf.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.mrs.mtf.R;
import br.com.mrs.mtf.dao.LogSerieDAO;
import br.com.mrs.mtf.model.LogSerie;
import br.com.mrs.mtf.model.Serie;
import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarLogserieActivity extends FragmentActivity{

	private long idserie = 0;
	private EditText qtdrepeticao, qtdcarga;
	private String dataatual;
	private LogSerieDAO logserieDAO;
	private LogSerie logserie;
	private Serie serie;

	FragmentManager fm = getSupportFragmentManager();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrar_logserie_activity);

		idserie = getIntent().getLongExtra("idserie", 0);

		qtdrepeticao = (EditText) findViewById(R.id.edtQtdrepeticaoRS);
		qtdcarga = (EditText) findViewById(R.id.edtQtdcargaRS);

		recuperarDataAtual();

		logserieDAO = new LogSerieDAO(this);
	}

	// recupera a data atual do aparelho
	private void recuperarDataAtual() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		dataatual = dateFormat.format(date);
	}


	// adiciona uma logserie para o exercicio
	public void adicionarLogSerie(View view){

		if(qtdrepeticao.getText().toString().equals("") || qtdcarga.getText().toString().equals("")){
			Toast.makeText(RegistrarLogserieActivity.this,
					"Todos os campos são obrigatórios!", Toast.LENGTH_SHORT).show();
		}
		else {

			try {
				logserie = new LogSerie();
				serie = new Serie();

				serie.setIdSerie(idserie);
				logserie.setDataLog(dataatual);
				logserie.setQtdRepeticao(qtdrepeticao.getText().toString());
				logserie.setQtdCarga(qtdcarga.getText().toString());

				logserieDAO.open();
				logserieDAO.adicionarLogserie(serie, logserie);
				logserieDAO.close();

				Toast.makeText(RegistrarLogserieActivity.this,
						"Série registrada!", Toast.LENGTH_SHORT).show();

				finish();

			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(RegistrarLogserieActivity.this,
						"Erro ao registrar a série!", Toast.LENGTH_SHORT).show();
			}
		}
	}


	@Override
	public void onBackPressed() {
		finish();
	}
}