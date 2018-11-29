package meuveterinario.filipe.com.br.meuveterinrio.MinhaConta;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import meuveterinario.filipe.com.br.meuveterinrio.MainActivityLogado;
import meuveterinario.filipe.com.br.meuveterinrio.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_minhaconta extends Fragment {


    private FirebaseAuth autenticacao;
    private DatabaseReference referenciaFirebase;
    private TextView mostra_nome, mostra_email;
    private String nomeLogado, emailLogado, cepLogado, telefoneLogado, dataNascLogado, cpfLogado, cidadeLogado, estadoLogado, enderecoLogado;
    private Usuario usuario;
    private ImageView imagem_usuarioLogado, imagem_minhaconta;
    private TextView nome_minhaconta, cpf_minhaconta, telefone_minhaconta, dataNasc_minhaconta, cep_minhaconta, endereco_minhaconta, cidade_minhaconta, estado_minhaconta, email_minhaconta;

    public Fragment_minhaconta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_fragment_minhaconta, container, false);
        //tentando popular a minha conta
        nome_minhaconta = (TextView)  view.findViewById(R.id.nome_minhaconta);
        cpf_minhaconta = (TextView) view.findViewById(R.id.cpf_minhaconta);
        dataNasc_minhaconta = (TextView) view.findViewById(R.id.dataNasc_minhaconta);
        telefone_minhaconta = (TextView) view.findViewById(R.id.telefone_minhaconta);
        endereco_minhaconta = (TextView) view.findViewById(R.id.endereco_minhaconta);
        cidade_minhaconta = (TextView) view.findViewById(R.id.cidade_minhaconta);
        estado_minhaconta = (TextView) view.findViewById(R.id.estado_minhaconta);
        cep_minhaconta = (TextView) view.findViewById(R.id.cep_minhaconta);
        email_minhaconta = (TextView) view.findViewById(R.id.email_minhaconta);
        imagem_minhaconta = (ImageView) view.findViewById(R.id.imagem_minhaconta);


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
                for (DataSnapshot postDataSnapshot : dataSnapshot.getChildren()) {
                    emailLogado = postDataSnapshot.child("email").getValue().toString();
                    nomeLogado = postDataSnapshot.child("nome").getValue().toString();





                    //minha conta

                    cepLogado = postDataSnapshot.child("cep").getValue().toString();
                    telefoneLogado = postDataSnapshot.child("telefone").getValue().toString();
                    enderecoLogado = postDataSnapshot.child("endereco").getValue().toString();
                    cidadeLogado = postDataSnapshot.child("cidade").getValue().toString();
                    estadoLogado = postDataSnapshot.child("estado").getValue().toString();
                    cpfLogado = postDataSnapshot.child("cpf").getValue().toString();
                    dataNascLogado = postDataSnapshot.child("data_nasc").getValue().toString();


                    nome_minhaconta.setText("Nome: " + nomeLogado);
                    email_minhaconta.setText("E-mail: " + emailLogado);
                    cep_minhaconta.setText("CEP: " + cepLogado);
                    telefone_minhaconta.setText("Telefone: " + telefoneLogado);
                    cpf_minhaconta.setText("CPF: " + cpfLogado);
                    endereco_minhaconta.setText("Endereço: " + enderecoLogado);
                    cidade_minhaconta.setText("Cidade: " + cidadeLogado);
                    estado_minhaconta.setText("Estado: " + estadoLogado);
                    dataNasc_minhaconta.setText("Data de Nascimento: " + dataNascLogado);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageReference = storage.getReference("imagens/perfil/" + autenticacao.getCurrentUser().getEmail().toString().trim() + ".jpeg");

        final int heigth = (100);
        final int width = (100);

        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.with(getContext()).load(uri.toString()).resize(width, heigth).centerCrop().into(imagem_minhaconta);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        return view;
    }

}
