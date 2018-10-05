package meuveterinario.filipe.com.br.meuveterinrio.Clinicas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import meuveterinario.filipe.com.br.meuveterinrio.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_clinicas extends Fragment {


    public Fragment_clinicas() {
        // Required empty public constructor
    }

    private ArrayList<Clinica> listaClinicas = new ArrayList();
    private RecyclerView recyclerView;
    private ClinicaAdapter clinicaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_clinicas, container, false);

        recyclerView = view.findViewById(R.id.recycleClinica);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        this.listaClinicas.add(new Clinica(1, "Clinica Palmas", "Clinica Palmas", "9879879879", "Rua Barão", "José Pereira"));
        this.listaClinicas.add(new Clinica(1, "Clinica Palmas", "Clinica Palmas", "9879879879", "Rua Barão", "José Pereira"));
        this.listaClinicas.add(new Clinica(1, "Clinica Palmas", "Clinica Palmas", "9879879879", "Rua Barão", "José Pereira"));
        this.listaClinicas.add(new Clinica(1, "Clinica Palmas", "Clinica Palmas", "9879879879", "Rua Barão", "José Pereira"));
        this.listaClinicas.add(new Clinica(1, "Clinica Palmas", "Clinica Palmas", "9879879879", "Rua Barão", "José Pereira"));

        clinicaAdapter = new ClinicaAdapter(this.listaClinicas);

        recyclerView.setAdapter(clinicaAdapter);
        return view;
    }

}
