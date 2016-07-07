package app.com.appprestamo.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.com.appprestamo.R;
import app.com.appprestamo.activity.LoginActivity;
import app.com.appprestamo.adapter.AdapterCobradores;
import app.com.appprestamo.aplication.Configuracion;
import app.com.appprestamo.model.Usuario;

public class CobradoresFragment extends Fragment {
    private RecyclerView rvListaCobradores;
    private AdapterCobradores adapter;
    private ArrayList<Usuario> lista;

    public CobradoresFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cobradores, container, false);
        rvListaCobradores = (RecyclerView)view.findViewById(R.id.rvListaCobradores);

        SharedPreferences preferences = getContext().getSharedPreferences(LoginActivity.keyPreferenciaPassword, Context.MODE_PRIVATE);
        String correo = preferences.getString(LoginActivity.keyPreferenciaCorreo,"");
        int id = Configuracion.querys.traerTipoUsuario(correo);

        lista = Configuracion.querys.listarCobradoresOUsuarios();

        rvListaCobradores.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvListaCobradores.setLayoutManager(manager);

        rvListaCobradores.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterCobradores(getContext(),lista);
        rvListaCobradores.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
