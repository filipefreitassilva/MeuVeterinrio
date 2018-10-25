package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import meuveterinario.filipe.com.br.meuveterinrio.Clinicas.Fragment_clinicas;
import meuveterinario.filipe.com.br.meuveterinrio.Consultas.Fragment_consultas;
import meuveterinario.filipe.com.br.meuveterinrio.Forum.Fragment_forum;
import meuveterinario.filipe.com.br.meuveterinrio.MeusAnimais.Fragment_meusanimais;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Fragment_minhaconta;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

        private Button button_login, button_cadastrar;
        private FirebaseAuth auth;
        private FirebaseUser user;

        private FirebaseAuth.AuthStateListener authStateListener;


    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        button_login = (Button) findViewById(R.id.button_login);
        button_cadastrar = (Button) findViewById(R.id.button_cadastrar);

        button_login.setOnClickListener(this);
        button_cadastrar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        estadoAutenticacao();
    }

    private void estadoAutenticacao(){
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               FirebaseUser user = firebaseAuth.getCurrentUser();

               if(user != null){

                   Toast.makeText(getBaseContext(), "Usuário" + user.getEmail() + "está logado", Toast.LENGTH_LONG).show();
               }else{


               }
            }
        };
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_minhaconta) {

             Fragment_minhaconta fragment_minhaconta = new Fragment_minhaconta();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_minhaconta, "F1");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Minha Conta");
             fragmentTransaction.commit();

        } else if (id == R.id.nav_meusanimais) {

             Fragment_meusanimais fragment_meusanimais = new Fragment_meusanimais();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_meusanimais, "F2");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Meus Animais");
             fragmentTransaction.commit();

        } else if (id == R.id.nav_consultas) {

             Fragment_consultas fragment_consultas = new Fragment_consultas();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_consultas, "F3");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Consultas");
             fragmentTransaction.commit();

        } else if (id == R.id.nav_clinicas) {

             Fragment_clinicas fragment_clinicas = new Fragment_clinicas();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_clinicas, "F4");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Clínicas");
             fragmentTransaction.commit();

        } else if (id == R.id.nav_forum) {

             Fragment_forum fragment_forum = new Fragment_forum();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_forum, "F5");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Fórum");
             fragmentTransaction.commit();



        } else if (id == R.id.nav_sair) {

             finishAffinity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                //execcutar um comando
                user = auth.getCurrentUser();
                if (user == null) {

                    startActivity(new Intent(this, LoginEmailActivity.class));
                }else {
                    startActivity(new Intent(this, PrincipalActivity.class));
                }

                break;

            case R.id.button_cadastrar:
                //executar comando

            startActivity(new Intent(this, CadastrarActivity.class));

                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            auth.removeAuthStateListener(authStateListener);
        }
    }
}
