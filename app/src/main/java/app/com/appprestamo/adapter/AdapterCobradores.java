package app.com.appprestamo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.com.appprestamo.R;
import app.com.appprestamo.model.Usuario;

public class AdapterCobradores extends RecyclerView.Adapter<AdapterCobradores.MyViewHolder> {
    private Context context;
    private ArrayList<Usuario> lista;

    public AdapterCobradores(Context context, ArrayList<Usuario> lista) {
        this.context = context;
        this.lista = lista;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView rowNombreC,rowTelC,rowCorreoP;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowNombreC = (TextView)itemView.findViewById(R.id.rowNombreC);
            rowTelC = (TextView)itemView.findViewById(R.id.rowTelC);
            rowCorreoP = ( TextView)itemView.findViewById(R.id.rowCorreoP);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cobradores,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Usuario item = lista.get(position);
        holder.rowNombreC.setText(item.getUsu_nom());
        holder.rowTelC.setText(item.getUsu_tel());
        holder.rowTelC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+item.getUsu_tel()));
                try{
                   context.startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(context,"Número Des-habilitado",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.rowCorreoP.setText(item.getUsu_cor());
        holder.rowCorreoP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{item.getUsu_cor()});
                email.putExtra(Intent.EXTRA_SUBJECT, "Mensaje de Prestamos App");
                email.setType("message/rfc822");
                context.startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
