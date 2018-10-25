package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText text_emaillogin, text_senhalogin;
    private Button button_oklogin, button_recuperarsenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginemail);
        getSupportActionBar().setTitle("Login - Email");

        text_emaillogin = (EditText)findViewById(R.id.text_emaillogin);
        text_senhalogin = (EditText)findViewById(R.id.text_senhalogin);

        button_oklogin = (Button)findViewById(R.id.button_oklogin);
        button_recuperarsenha = (Button)findViewById(R.id.button_recuperarlogin);

        button_oklogin.setOnClickListener(this);
        button_recuperarsenha.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_oklogin:

                loginEmail();
                break;

            case R.id.button_recuperarlogin:

                recuperarSenha();
                break;
        }
    }

    private void recuperarSenha(){

        String email = text_emaillogin.getText().toString().trim();
        if(email.isEmpty()){
            Toast.makeText(getBaseContext(), "Insira pelo menos o seu e-mail para poder recuperar a senha!", Toast.LENGTH_LONG).show();
        }
        else {
            enviarEmail(email);
        }

    }

    private void enviarEmail(String email){

        auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getBaseContext(), "Enviamos um link de redefinição de senha para o seu e-mail: "+ text_emaillogin.getText().toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String erro = e.toString();

                Util.opcoesErro(getBaseContext(), erro);
            }
        });
    }

    private void loginEmail(){

        String email = text_emaillogin.getText().toString().trim();
        String senha = text_senhalogin.getText().toString().trim();

        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(getBaseContext(), "Insira os campos obrigatórios!", Toast.LENGTH_LONG).show();
        }
        else {
            if(Util.verificarInternet(this)) {

                ConnectivityManager conexao = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

                confirmarLogin(email, senha);
            }
            else {
                Toast.makeText(getBaseContext(), "Verifique se sua conexão com a internet está funcionando!", Toast.LENGTH_LONG).show();
            }

        }


    }

    private void confirmarLogin(String email, String senha){

        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(getBaseContext(), PrincipalActivity.class));
                    Toast.makeText(getBaseContext(), "Usuário logado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    String resposta = task.getException().toString();
                    Util.opcoesErro(getBaseContext(),resposta);
                }
            }
        });
    }

}
