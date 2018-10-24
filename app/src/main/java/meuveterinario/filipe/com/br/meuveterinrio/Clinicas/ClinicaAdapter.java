package meuveterinario.filipe.com.br.meuveterinrio.Clinicas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

import meuveterinario.filipe.com.br.meuveterinrio.R;

public class ClinicaAdapter extends RecyclerView.Adapter<ClinicaHolder>{

    private List<Clinica> clinicaList;



    public ClinicaAdapter(List<Clinica> clinicaList){
        this.clinicaList = clinicaList;
    }

    @NonNull
    @Override
    public ClinicaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_clinica,parent,false);

        return new ClinicaHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ClinicaHolder holder, int position) {
        final Clinica clinica = clinicaList.get(position);

        holder.getNomeClinica().setText(clinica.getNomeClinica());
        final int id = clinica.getClinicaId();
        clinica.getImagemClinica();



        holder.getImagemClinica().setImageBitmap(clinica.getImagemClinica());


        //.setImageResource(R.drawable.my_image);
        //Picasso.get().load(serie.getSerieImage()).into(holder.getSerieBackdrop());

        holder.getImagemClinica().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(view.getContext(),DetalhesClinicas.class);
                Bundle bundle = new Bundle();

                it.putExtra("id", id);

                view.getContext().startActivity(it);

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.clinicaList.size();
    }
}
