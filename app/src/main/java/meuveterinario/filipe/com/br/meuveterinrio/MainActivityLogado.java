package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import meuveterinario.filipe.com.br.meuveterinrio.Clinicas.Fragment_clinicas;
import meuveterinario.filipe.com.br.meuveterinrio.Consultas.Fragment_consultas;
import meuveterinario.filipe.com.br.meuveterinrio.Forum.Fragment_forum;
import meuveterinario.filipe.com.br.meuveterinrio.MeusAnimais.Fragment_meusanimais;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Fragment_minhaconta;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Usuario;

public class MainActivityLogado extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
          {



              private GoogleSignInClient googleSignInClient;

    FragmentTransaction fragmentTransaction;
    private FirebaseAuth autenticacao;
    private DatabaseReference referenciaFirebase;
    private TextView mostra_nome, mostra_email;
    private String nomeLogado, emailLogado, cepLogado, telefoneLogado, dataNascLogado, cpfLogado, cidadeLogado, estadoLogado, enderecoLogado;
    private Usuario usuario;
    private ImageView imagem_usuarioLogado, imagem_minhaconta;
    private TextView nome_minhaconta, cpf_minhaconta, telefone_minhaconta, dataNasc_minhaconta, cep_minhaconta, endereco_minhaconta, cidade_minhaconta, estado_minhaconta, email_minhaconta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_logado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View hView = navigationView.getHeaderView(0);
        mostra_email = (TextView) hView.findViewById(R.id.text_mostra_email);
        mostra_nome = (TextView) hView.findViewById(R.id.text_mostra_nome);
        imagem_usuarioLogado = (ImageView) hView.findViewById(R.id.imagem_usuarioLogado);
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


        autenticacao = FirebaseAuth.getInstance();

        //recebendo o email do user logado
        String email = autenticacao.getCurrentUser().getEmail().toString();

        referenciaFirebase = FirebaseDatabase.getInstance().getReference();

        referenciaFirebase.child("usuarios").orderByChild("email").equalTo(email.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postDataSnapshot : dataSnapshot.getChildren()){
                    emailLogado = postDataSnapshot.child("email").getValue().toString();
                    nomeLogado = postDataSnapshot.child("nome").getValue().toString();



                    mostra_nome.setText("Nome: " + nomeLogado);
                    mostra_email.setText("E-mail: " + emailLogado);


                    //minha conta





                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageReference = storage.getReference("imagens/perfil/"+autenticacao.getCurrentUser().getEmail().toString().trim()+".jpeg");

        final int heigth = (100);
        final int width = (100);

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(MainActivityLogado.this).load(uri.toString()).resize(width, heigth).centerCrop().into(imagem_usuarioLogado);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }






    public void insereValoresLogin(Usuario usuario){



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



        } else if (id == R.id.nav_sair) {

             FirebaseAuth.getInstance().signOut();

             GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                     .requestIdToken(getString(R.string.default_web_client_id))
                     .requestEmail()
                     .build();

             googleSignInClient = GoogleSignIn.getClient(this, gso);
             googleSignInClient.signOut();

             LoginManager.getInstance().logOut();

             finish();
             startActivity(new Intent(getBaseContext(), Activity_login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
