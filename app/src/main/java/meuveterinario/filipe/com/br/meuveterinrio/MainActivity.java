package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;

import meuveterinario.filipe.com.br.meuveterinrio.Clinicas.Fragment_clinicas;
import meuveterinario.filipe.com.br.meuveterinrio.Consultas.Fragment_consultas;
import meuveterinario.filipe.com.br.meuveterinrio.Forum.Fragment_forum;
import meuveterinario.filipe.com.br.meuveterinrio.MeusAnimais.Fragment_meusanimais;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Fragment_minhaconta;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
          {



              private GoogleSignInClient googleSignInClient;

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



//        viewPager = findViewById(R.id.viewPager);
//        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager());
//
//        adapterViewPager.addFragmento(new Fragment_clinicas());
//        adapterViewPager.addFragmento(new Fragment_consultas());
//
//        viewPager.setAdapter(adapterViewPager);


//        FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
//        fragmentManager.add(new Fragment_consultas(),"consultas");
//        fragmentManager.commit();

        //viewPager.setCurrentItem(0);






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
             //viewPager.setCurrentItem(1, false);

        } else if (id == R.id.nav_meusanimais) {

             Fragment_meusanimais fragment_meusanimais = new Fragment_meusanimais();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_meusanimais, "F2");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Meus Animais");
             fragmentTransaction.commit();

            // viewPager.setCurrentItem(1, false);
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



        }else if (id == R.id.nav_login) {



         }


        else if (id == R.id.nav_sair) {

             FirebaseAuth.getInstance().signOut();

             GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                     .requestIdToken(getString(R.string.default_web_client_id))
                     .requestEmail()
                     .build();

             googleSignInClient = GoogleSignIn.getClient(this, gso);
             googleSignInClient.signOut();

             LoginManager.getInstance().logOut();
             finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
