package meuveterinario.filipe.com.br.meuveterinrio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

        private Button button_login, button_cadastrar;
        private FirebaseAuth auth;
        private FirebaseUser user;

        private GoogleSignInClient googleSignInClient;
        private CardView cardView_loginGoogle, cardView_loginFacebook;

        private TextView mostra_email, mostra_nome;

        private CallbackManager callbackManager;

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

        mostra_email = (TextView) findViewById(R.id.mostra_email);
        mostra_nome = (TextView) findViewById(R.id.mostra_nome);

        auth = FirebaseAuth.getInstance();

        estadoAutenticacao();

        servicosGoogle();

        servicosFacebook();
        cardView_loginGoogle = (CardView)findViewById(R.id.cardView_loginGoogle);

        cardView_loginGoogle.setOnClickListener(this);

        cardView_loginFacebook = (CardView)findViewById(R.id.cardView_loginFacebook);

        cardView_loginFacebook.setOnClickListener(this);

    }
//-----------------------------serviços login -------------------------------
    private void servicosFacebook(){

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                adicionarContaFacebookFirebase(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

                Toast.makeText(getBaseContext(), "Cancelado!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(getBaseContext(), "Erro ao fazer o login com o Facebook!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void servicosGoogle(){

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);


    }
//----------------------servicos autenticacao-----------------------------------
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



        }else if (id == R.id.nav_login) {



         }


        else if (id == R.id.nav_sair) {

             finishAffinity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//-----------------------tratamento de clicks----------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login:
                //execcutar um comando

            singInEmail();
                break;

            case R.id.button_cadastrar:
                //executar comando

            startActivity(new Intent(this, CadastrarActivity.class));

                break;

            case R.id.cardView_loginGoogle:

                singInGoogle();

                break;

            case R.id.cardView_loginFacebook:

                singInFacebook();

                break;
        }
    }
//------------------------metodos de login----------------------------------

    private void singInFacebook(){

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
    }


    private void singInGoogle(){

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account == null){
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 555);

        }else {
            ///já tem algum usuario conectado
            Toast.makeText(getBaseContext(), "Já existe alguem logado!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getBaseContext(), PrincipalActivity.class));


        }

    }

    private void singInEmail(){
        user = auth.getCurrentUser();
        if (user == null) {

            startActivity(new Intent(this, LoginEmailActivity.class));
        }else {
            startActivity(new Intent(this, PrincipalActivity.class));

        }
    }

    //-------------------autenticacao firebase------------------------------

    private void adicionarContaFacebookFirebase(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getBaseContext(), PrincipalActivity.class));
                        } else {
                            Toast.makeText(getBaseContext(), "Erro ao logar com a conta do Facebook", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }

    private void addContaGoogleFirebase(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent(getBaseContext(), PrincipalActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getBaseContext(), "Erro ao logar com a conta do Google", Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 555){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);

                addContaGoogleFirebase(account);

            }
            catch (ApiException e){

                Toast.makeText(getBaseContext(), "Erro ao logar com a conta do Google", Toast.LENGTH_LONG).show();

            }
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
