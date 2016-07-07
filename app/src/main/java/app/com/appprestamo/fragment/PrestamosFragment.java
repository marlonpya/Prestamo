package app.com.appprestamo.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.com.appprestamo.R;
import app.com.appprestamo.activity.LoginActivity;
import app.com.appprestamo.adapter.AdapterPrestamos;
import app.com.appprestamo.aplication.Configuracion;
import app.com.appprestamo.model.Prestamo;

public class PrestamosFragment extends Fragment {
    private static final String TAG = PrestamosFragment.class.getSimpleName();
    private RecyclerView rvListaPrestamos;
    private AdapterPrestamos adapter;
    private ArrayList<Prestamo> lista;
    private int idUsuario;

    public PrestamosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prestamos, container, false);
        rvListaPrestamos=(RecyclerView)view.findViewById(R.id.rvListaPrestamos);

        SharedPreferences sharedPreferences = PrestamosFragment.this.getContext().getSharedPreferences(LoginActivity.PreferenciaUsuario, Context.MODE_PRIVATE);
        String thiscorreo = sharedPreferences.getString(LoginActivity.keyPreferenciaCorreo, "");

        Log.d(TAG,"este es el id : "+String.valueOf(thiscorreo));

        idUsuario = Configuracion.querys.traerIdUsuario(thiscorreo);

        lista = Configuracion.querys.listarPrestamos(idUsuario);
        rvListaPrestamos.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        rvListaPrestamos.setLayoutManager(manager);

        rvListaPrestamos.setItemAnimator(new DefaultItemAnimator());
        adapter = new AdapterPrestamos(lista);
        rvListaPrestamos.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Prestamo> lista = Configuracion.querys.listarPrestamos(idUsuario);
        adapter = new AdapterPrestamos(lista);
        rvListaPrestamos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
