package meuveterinario.filipe.com.br.meuveterinrio.MinhaConta;

import android.widget.ImageView;

import com.google.firebase.database.Exclude;

public class Usuario {


    private String senha;
    private String nome;
    private String cpf;
    private String data_nasc;
    private String telefone;
    private String cep;
    private String endereco;
    private String cidade;
    private String estado;

   // private ImageView imagemPerfil;

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    @Exclude
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public ImageView getImagemPerfil() {
//        return imagemPerfil;
//    }
//
//    public void setImagemPerfil(ImageView imagemPerfil) {
//        this.imagemPerfil = imagemPerfil;
//    }

}
