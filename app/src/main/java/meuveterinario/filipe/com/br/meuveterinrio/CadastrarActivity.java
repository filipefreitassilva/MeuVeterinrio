package meuveterinario.filipe.com.br.meuveterinrio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText text_emailcadastro, text_senhacadastro, text_repetirsenhacadastro;
    private Button button_cadastrarusuario, button_cancelar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        text_emailcadastro = (EditText)findViewById(R.id.text_emailcadastro);
        text_senhacadastro = (EditText)findViewById(R.id.text_senhacadastro);
        text_repetirsenhacadastro = (EditText)findViewById(R.id.text_repetirsenhacadastro);

        button_cadastrarusuario = (Button)findViewById(R.id.button_cadastrarusuario);
        button_cancelar = (Button)findViewById(R.id.button_cancelar);

        button_cadastrarusuario.setOnClickListener(this);
        button_cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_cadastrarusuario:
                //executar comando
                cadastrar();
                break;

            case R.id.button_cancelar:
                //executar coomando

                break;
        }
    }

    private void cadastrar(){
        Toast.makeText(this, "Cadastrar Clicado",Toast.LENGTH_LONG).show();
    }
}
