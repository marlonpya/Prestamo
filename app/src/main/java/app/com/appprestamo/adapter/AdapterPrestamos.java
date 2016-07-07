package app.com.appprestamo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.appprestamo.R;
import app.com.appprestamo.model.Prestamo;

/**
 * Adapter mas way Material Design
 * cambio el ListView por RecyclerView
 * cambio el BaseAdapter por RecyclerView.Adapter<AdapterPrestamos.MyViewHolder>
 *     <AdapterPrestamos.MyViewHolder> AdapterPrestamos es el nombre de mi clase Adapter
 *     MyViewHolder la clase estática para definir el layout.xml para inflar
 *     layout.xml son los items que se va a múltiplicar en en RecyclerView
 */
public class AdapterPrestamos extends RecyclerView.Adapter<AdapterPrestamos.MyViewHolder> {
    //Lista con su Clase Prestamo
    private ArrayList<Prestamo> lista;

    //Constructor para la lista
    public AdapterPrestamos(ArrayList<Prestamo> lista) {
        this.lista = lista;
    }

    //Método para definir e inflar el layout.xml
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_prestamos,parent,false);
        return new MyViewHolder(view);
    }

    //Método para definir los view del layout.xml con la clase
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Prestamo item = lista.get(position);
        holder.rowNombreP.setText(item.getPre_nom());
        holder.rowPlataP.setText(String.valueOf(item.getPre_pla()));
        holder.rowFechaP.setText(item.getPre_fec());
    }

    //Método para traer el tamaño de la lista
    @Override
    public int getItemCount() {
        return lista.size();
    }

    //Clase que define para crear
    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowNombreP,rowPlataP,rowFechaP;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowNombreP=(TextView)itemView.findViewById(R.id.rowNombreP);
            rowPlataP=(TextView)itemView.findViewById(R.id.rowPlataP);
            rowFechaP=(TextView)itemView.findViewById(R.id.rowFechaP);
        }
    }
}
