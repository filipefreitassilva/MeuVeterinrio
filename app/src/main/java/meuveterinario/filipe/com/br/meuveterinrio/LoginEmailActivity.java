package meuveterinario.filipe.com.br.meuveterinrio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginEmailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText text_emaillogin, text_senhalogin;
    private Button button_oklogin, button_recuperarsenha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginemail);

        text_emaillogin = (EditText)findViewById(R.id.text_emaillogin);
        text_senhalogin = (EditText)findViewById(R.id.text_senhalogin);

        button_oklogin = (Button)findViewById(R.id.button_oklogin);
        button_recuperarsenha = (Button)findViewById(R.id.button_recuperarlogin);

        button_oklogin.setOnClickListener(this);
        button_recuperarsenha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_oklogin:

                break;

            case R.id.button_recuperarlogin:

                break;
        }
    }
}
