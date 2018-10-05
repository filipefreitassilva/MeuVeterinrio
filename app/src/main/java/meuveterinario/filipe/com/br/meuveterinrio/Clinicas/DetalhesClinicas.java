package meuveterinario.filipe.com.br.meuveterinrio.Clinicas;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import meuveterinario.filipe.com.br.meuveterinrio.R;

public class DetalhesClinicas extends AppCompatActivity{
    //Atributes

    private ImageView imagemClinica;
    private TextView nomeClinica;
    private TextView telefoneClinica;
    private TextView enderecoClinica;
    private TextView medicoClinica;

    private String urlPoster = null;
    private String overview = null;
    private String name = null;
    private String date = null;
    private String homepage = null;
    private String retorno = null;

    private String urlDetails = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_clinicas);
        getSupportActionBar().hide();

        imagemClinica = findViewById(R.id.imagemClinica);
        nomeClinica = findViewById(R.id.nomeClinica);
        telefoneClinica = findViewById(R.id.telefoneClinica);
        enderecoClinica = findViewById(R.id.enderecoClinica);
        medicoClinica = findViewById(R.id.medicoClinica);

        int clinicaId = this.getIntent().getIntExtra("id",0);


        MyTask myTask = new MyTask();
        myTask.execute(urlDetails);



    }
    class MyTask extends AsyncTask<String,Void,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];

            StringBuffer buffer = new StringBuffer();

            if(stringUrl.contains(urlDetails)){

                InputStream inputStream = null;
                InputStreamReader inputStreamReader = null;


                try {
                    URL url = new URL(stringUrl);
                    //Faz a requisição, abre a conexão
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                    //Recupera os dados em Bytes
                    inputStream = conexao.getInputStream();

                    //inputStreamReader lê os dados em Bytes e decodifica para caracteres
                    inputStreamReader = new InputStreamReader(inputStream);

                    //Objeto utilizado para leitura dos caracteres do InputStreamReader
                    BufferedReader reader = new BufferedReader(inputStreamReader);


                    String linha = "";

                    while ((linha = reader.readLine()) != null) {

                        buffer.append(linha);
                    }

                    try {
                        JSONObject jsonObject = new JSONObject(buffer.toString());

                        urlPoster = jsonObject.getString("poster_path");
                        overview = jsonObject.getString("overview");
                        name = jsonObject.getString("name");
                        date = jsonObject.getString("first_air_date");


                        retorno = "1";

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //return buffer.toString();
            return retorno;

        }

        @Override
        protected void onPostExecute(String resultado) {

            super.onPostExecute(resultado);

            try{

                if (resultado.contains("1")) {
                    nomeClinica.setText((CharSequence) nomeClinica);
                    telefoneClinica.setText((CharSequence) telefoneClinica);
                    enderecoClinica.setText((CharSequence) enderecoClinica);


                }
            }catch (Exception e)
            {
                Log.i("INFO", "Erro no processamento de componentes" + e);
            }

        }
    }
}
