package meuveterinario.filipe.com.br.meuveterinrio.Clinicas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import meuveterinario.filipe.com.br.meuveterinrio.R;


public class ClinicaHolder extends RecyclerView.ViewHolder {

        private TextView nomeClinica;
        private ImageView imagemClinica;

        public ClinicaHolder(View itemView) {
            super(itemView);
            nomeClinica = itemView.findViewById(R.id.nomeClinica);
            imagemClinica = itemView.findViewById(R.id.imagemClinica);
        }

        public TextView getNomeClinica() {
            return nomeClinica;
        }

        public void setNomeClinica(TextView nomeClinica) {
            this.nomeClinica = nomeClinica;
        }

        public ImageView getImagemClinica() {
            return imagemClinica;
        }

        public void setImagemClinica(ImageView imagemClinica) {
            this.imagemClinica = imagemClinica;
        }
    }


