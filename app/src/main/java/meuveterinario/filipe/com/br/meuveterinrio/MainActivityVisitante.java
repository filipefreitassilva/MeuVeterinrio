package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import meuveterinario.filipe.com.br.meuveterinrio.Clinicas.Fragment_clinicas;
import meuveterinario.filipe.com.br.meuveterinrio.Consultas.Fragment_consultas;
import meuveterinario.filipe.com.br.meuveterinrio.Forum.Fragment_forum;
import meuveterinario.filipe.com.br.meuveterinrio.MeusAnimais.Fragment_meusanimais;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Fragment_minhaconta;

public class MainActivityVisitante extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
          {



              private GoogleSignInClient googleSignInClient;

    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_visitante);
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

          if (id == R.id.nav_clinicas) {

             Fragment_clinicas fragment_clinicas = new Fragment_clinicas();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_clinicas, "F4");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Clínicas");
             fragmentTransaction.commit();

        } else if (id == R.id.nav_promocoes) {

             Fragment_promocoes fragment_promocoes = new Fragment_promocoes();
             fragmentTransaction = getSupportFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.frameLayout, fragment_promocoes, "F5");
             fragmentTransaction.addToBackStack(null);
             getSupportActionBar().setTitle("Promoções");
             fragmentTransaction.commit();



        }else if (id == R.id.nav_login) {

              FirebaseAuth.getInstance().signOut();

              GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                      .requestIdToken(getString(R.string.default_web_client_id))
                      .requestEmail()
                      .build();

              googleSignInClient = GoogleSignIn.getClient(this, gso);
              googleSignInClient.signOut();

              LoginManager.getInstance().logOut();



              finish();
              startActivity(new Intent(this, Activity_login.class));

         }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
