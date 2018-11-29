package meuveterinario.filipe.com.br.meuveterinrio;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BuscaDadosServidor extends AsyncTask <String, Void, ConsultaCep> {

    cadastrarUsuario activity = null;
    ProgressDialog progress = null;
    ConsultaCep cep = null;
    String cepTemp;

    BuscaDadosServidor(cadastrarUsuario activity)
    {
        this.activity = activity;
    }

    //Metodo chamado antes da Thread comecar a Executar
    public void onPreExecute()
    {
        progress = new ProgressDialog(activity);
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);
        progress.setMessage("Baixando dados...");
        progress.setTitle("Aguarde!");
        progress.show();
    }

    //Metodo que executa em uma Thread diferente da principal

    protected ConsultaCep doInBackground(String... string)
    {
        //Abrir conexao
        try
        {
            String cepConsulta = string[0]; //Recebe CEP informado pelo usuario
            OkHttpClient cliente = new OkHttpClient();
            Request request = new Request.Builder().url("https://viacep.com.br/ws/"+cepConsulta+"/json/").build();
            Response response = cliente.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            cep = gson.fromJson(json, ConsultaCep.class);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cep;
    }

    //Metodo chamado apos o termino da execucao da Thread
    public void onPostExecute(ConsultaCep cep)
    {
        progress.dismiss();
        activity.updateCep(cep);
    }
}
