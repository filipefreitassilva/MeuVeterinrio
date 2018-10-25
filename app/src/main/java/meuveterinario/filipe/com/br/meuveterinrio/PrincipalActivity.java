package meuveterinario.filipe.com.br.meuveterinrio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_deslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        getSupportActionBar().setTitle("Principal");

        button_deslogar = (Button)findViewById(R.id.button_deslogar);
        button_deslogar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_deslogar:
            FirebaseAuth.getInstance().signOut();
            finish();
                break;
        }
    }
}
