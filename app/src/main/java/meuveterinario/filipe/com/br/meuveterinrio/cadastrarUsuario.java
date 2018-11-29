package meuveterinario.filipe.com.br.meuveterinrio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import meuveterinario.filipe.com.br.meuveterinrio.DAO.ConfiguracaoFirebase;
import meuveterinario.filipe.com.br.meuveterinrio.MinhaConta.Usuario;

public class cadastrarUsuario extends AppCompatActivity implements View.OnClickListener {

    private EditText text_nomeUsuario, text_cpfUsuario, text_dataNascUsuario, text_telefoneUsuario, text_enderecoUsuario, text_cepUsuario,
            text_estadoUsuario, text_cidadeUsuario, text_emailcadastro, text_senhacadastro, text_repetirsenhacadastro;
    private Button button_cadastrarusuario, button_cancelar;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private DatabaseReference referenciaDatabase;
    private StorageReference storageReference;
    private Usuario usuario;
    private ImageView imagem_perfilUpload;
    private static final int SELECAO_GALERIA = 200;

    TextView localidade;
    TextView uf;
    TextView cep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Cadastro de Usuário");
        setContentView(R.layout.activity_cadastrar_usuario);

        text_nomeUsuario = (EditText) findViewById(R.id.text_nomeUsuario);

        text_cpfUsuario = (EditText) findViewById(R.id.text_cpfUsuario);
        text_dataNascUsuario = (EditText) findViewById(R.id.text_dataNascUsuario);
        text_telefoneUsuario = (EditText) findViewById(R.id.text_telefoneUsuario);
        text_enderecoUsuario = (EditText) findViewById(R.id.text_enderecoUsuario);
        text_cepUsuario = (EditText) findViewById(R.id.text_cepUsuario);

        text_cepUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && text_cepUsuario.getText().length()==8) {
                    new BuscaDadosServidor(cadastrarUsuario.this).execute(text_cepUsuario.getText().toString());
                }else if (text_cepUsuario.getText().length() > 8) {
                    Toast.makeText(getBaseContext(), "CEP incorreto!", Toast.LENGTH_LONG).show();
                } else if (text_cepUsuario.getText().length() > 1 && text_cepUsuario.getText().length() < 8 ){
                    Toast.makeText(getBaseContext(), "CEP incorreto!", Toast.LENGTH_LONG).show();
                }
            }
        });

        text_estadoUsuario = (EditText) findViewById(R.id.text_estadoUsuario);
        text_cidadeUsuario = (EditText) findViewById(R.id.text_cidadeUsuario);
        text_emailcadastro = (EditText) findViewById(R.id.text_emailcadastro);
        text_senhacadastro = (EditText) findViewById(R.id.text_senhacadastro);
        text_repetirsenhacadastro = (EditText) findViewById(R.id.text_repetirsenhacadastro);

        button_cadastrarusuario = (Button) findViewById(R.id.button_cadastrarusuario);
        button_cancelar = (Button) findViewById(R.id.button_cancelar);

        button_cadastrarusuario.setOnClickListener(this);
        button_cancelar.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        storageReference = ConfiguracaoFirebase.getFirebaseStorageReference();

        imagem_perfilUpload = (ImageView) findViewById(R.id.imagem_perfilUpload);

        carregaImagemPadrao();

    imagem_perfilUpload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, SELECAO_GALERIA);
            }
        }
    });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        final int heigth = (250);
        final int width = (250);

        if (resultCode == RESULT_OK) {

            Bitmap imagem = null;

            try{

                switch (requestCode){
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        break;

                }

                if(imagem !=null){
                    imagem_perfilUpload.setImageBitmap(imagem);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] dadosImagem = baos.toByteArray();
                    StorageReference imageRef = storageReference.child("imagens").child("perfil").child(text_emailcadastro.getText().toString()+".jpeg");

                    UploadTask uploadTask = imageRef.putBytes(dadosImagem);

                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getBaseContext(), "Erro ao fazer o upload da imagem!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getBaseContext(), "Sucesso ao fazer o Upload da imagem!", Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
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

                finish();
                startActivity(new Intent(getBaseContext(), Activity_login.class));

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
                    usuario = new Usuario();

                    usuario.setNome(text_nomeUsuario.getText().toString());
                    usuario.setCpf(text_cpfUsuario.getText().toString());
                    usuario.setData_nasc(text_dataNascUsuario.getText().toString());
                    usuario.setTelefone(text_telefoneUsuario.getText().toString());
                    usuario.setEndereco(text_enderecoUsuario.getText().toString());
                    usuario.setCep(text_cepUsuario.getText().toString());
                    usuario.setEstado(text_estadoUsuario.getText().toString());
                    usuario.setCidade(text_cidadeUsuario.getText().toString());
                    usuario.setEmail(text_emailcadastro.getText().toString());



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
                    insereUsuarioDatabase(usuario);
                   // Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso!", Toast.LENGTH_LONG).show();

                    finish();
                    startActivity(new Intent(getBaseContext(), MainActivityLogado.class));
                } else {
                    String resposta = task.getException().toString();
                    Util.opcoesErro(getBaseContext(), resposta);

                }
            }
        });

    }

    private boolean insereUsuarioDatabase(Usuario usuario){
         try {

             referenciaDatabase = ConfiguracaoFirebase.getFirebase().child("usuarios");
             referenciaDatabase.push().setValue(usuario);
             


             Toast.makeText(getBaseContext(), "Cadastro efetuado com sucesso no database!", Toast.LENGTH_LONG).show();
             return true;


         }catch (Exception e){
             Toast.makeText(getBaseContext(), "Erro ao inserir usuario ao database!", Toast.LENGTH_LONG).show();
             e.printStackTrace();
             return false;
         }
    }

    public void updateCep(ConsultaCep cep) {

        this.text_cidadeUsuario.setText(cep.getLocalidade());
        this.text_estadoUsuario.setText(cep.getUf());

    }

    private void uploadFotoPerfil(){


    }

    private void carregaImagemPadrao(){
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageReference = storage.getReference("perfil.png");

        final int heigth = (250);
        final int width = (250);

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(cadastrarUsuario.this).load(uri.toString()).resize(width, heigth).centerCrop().into(imagem_perfilUpload);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
