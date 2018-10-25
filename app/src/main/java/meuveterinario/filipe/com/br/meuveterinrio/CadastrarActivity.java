package meuveterinario.filipe.com.br.meuveterinrio;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastrarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText text_emailcadastro, text_senhacadastro, text_repetirsenhacadastro;
    private Button button_cadastrarusuario, button_cancelar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        getSupportActionBar().setTitle("Cadastrar");

        text_emailcadastro = (EditText) findViewById(R.id.text_emailcadastro);
        text_senhacadastro = (EditText) findViewById(R.id.text_senhacadastro);
        text_repetirsenhacadastro = (EditText) findViewById(R.id.text_repetirsenhacadastro);

        button_cadastrarusuario = (Button) findViewById(R.id.button_cadastrarusuario);
        button_cancelar = (Button) findViewById(R.id.button_cancelar);

        button_cadastrarusuario.setOnClickListener(this);
        button_cancelar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cadastrarusuario:
                //executar comando
                cadastrar();
                break;

            case R.id.button_cancelar:
                //executar coomando

                break;
        }
    }

    private void cadastrar() {

        String email = text_emailcadastro.getText().toString().trim();
        String senha = text_senhacadastro.getText().toString().trim();
        String confirmasenha = text_repetirsenhacadastro.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty() || confirmasenha.isEmpty()) {
            Toast.makeText(getBaseContext(), "Preencha os campos necessários!", Toast.LENGTH_LONG).show();
        } else {


            if (senha.contentEquals(confirmasenha)) {

                if(Util.verificarInternet(this)) {
                    criarUsuario(email, senha);
                }
                else {
                    Toast.makeText(getBaseContext(), "Verifique se sua conexão com a internet está funcionando!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getBaseContext(), "Senhas diferentes!", Toast.LENGTH_LONG).show();
            }

        }
    }

    private void criarUsuario(String email, String senha) {

        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    String resposta = task.getException().toString();
                    Util.opcoesErro(getBaseContext(), resposta);

                }
            }
        });

    }

    private void opcoesErro(String resposta) {
        if (resposta.contains("Password should be at least 6 characters")) {

            Toast.makeText(getBaseContext(), "Digite uma senha maior que 5 caracteres!", Toast.LENGTH_LONG).show();

        } else if (resposta.contains("The email address is badly formatted")) {

            Toast.makeText(getBaseContext(), "Formato de e-mail inválido!", Toast.LENGTH_LONG).show();

        } else if (resposta.contains("address is already")) {

            Toast.makeText(getBaseContext(), "E-mail já existe cadastrado!", Toast.LENGTH_LONG).show();

        } else if (resposta.contains("interrupted connection")) {

            Toast.makeText(getBaseContext(), "Sem conexão com o Firebase!", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getBaseContext(), resposta, Toast.LENGTH_LONG).show();
        }

    }


}
