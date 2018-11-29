package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = auth.getCurrentUser();
                if(user == null){
                    finish();
                    startActivity(new Intent(getBaseContext(), Activity_login.class));

                } else {
                    finish();
                    startActivity(new Intent(getBaseContext(), MainActivityLogado.class));
                }

            }
        }, 3000);
    }
}
