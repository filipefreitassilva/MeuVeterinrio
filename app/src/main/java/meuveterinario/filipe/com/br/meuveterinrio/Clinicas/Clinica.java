package meuveterinario.filipe.com.br.meuveterinrio.Clinicas;

import android.graphics.Bitmap;

public class Clinica {

    private int clinicaId;
    private Bitmap imagemClinica;
    private String nomeClinica;
    private String telefoneClinica;
    private String enderecoClinica;
    private String medicoClinica;

    public Clinica(){}
    public Clinica(int clinicaId, Bitmap imagemClinica, String nomeClinica, String telefoneClinica, String enderecoClinica, String medicoClinica){
        this.clinicaId = clinicaId;
        this.imagemClinica = imagemClinica;
        this.nomeClinica = nomeClinica;
        this.telefoneClinica = telefoneClinica;
        this.enderecoClinica = enderecoClinica;
        this.medicoClinica = medicoClinica;
    }


    public int getClinicaId() {
        return clinicaId;
    }

    public void setClinicaId(int clinicaId) {
        this.clinicaId = clinicaId;
    }


    public String getNomeClinica() {
        return nomeClinica;
    }

    public void setNomeClinica(String nomeClinica) {
        this.nomeClinica = nomeClinica;
    }

    public String getTelefoneClinica() {
        return telefoneClinica;
    }

    public void setTelefoneClinica(String telefoneClinica) {
        this.telefoneClinica = telefoneClinica;
    }

    public String getEnderecoClinica() {
        return enderecoClinica;
    }

    public void setEnderecoClinica(String enderecoClinica) {
        this.enderecoClinica = enderecoClinica;
    }

    public String getMedicoClinica() {
        return medicoClinica;
    }

    public void setMedicoClinica(String medicoClinica) {
        this.medicoClinica = medicoClinica;
    }

    public Bitmap getImagemClinica() {
        return imagemClinica;
    }

    public void setImagemClinica(Bitmap imagemClinica) {
        this.imagemClinica = imagemClinica;
    }

}
