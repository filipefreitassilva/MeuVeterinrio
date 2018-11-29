package meuveterinario.filipe.com.br.meuveterinrio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsultaCep {

    @SerializedName("cep")
    @Expose
    private String cep;

    @SerializedName("localidade")
    @Expose
    private String localidade;

    @SerializedName("uf")
    @Expose
    private String uf;


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }



    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }



}

