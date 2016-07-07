package app.com.appprestamo.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import app.com.appprestamo.R;
import app.com.appprestamo.activity.LoginActivity;
import app.com.appprestamo.aplication.Configuracion;

/**
 * FRAGMENTO PARA SOLICITAR UN PRÉSTAMO
 * el usuario puede registrar un prestamo y es guardado en el sqlite
 */
public class SolicitarPrestamoFragment extends Fragment {
    private EditText txtMontoSolicitado, txtDescripcionSolicitud;
    private AppCompatButton btnSolicitarPrestamo;

    public SolicitarPrestamoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_solicitar_prestamo, container, false);
        txtMontoSolicitado = (EditText) view.findViewById(R.id.txtMontoSolicitado);
        txtDescripcionSolicitud = (EditText) view.findViewById(R.id.txtDescripcionSolicitud);
        btnSolicitarPrestamo = (AppCompatButton) view.findViewById(R.id.btnSolicitarPrestamo);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnSolicitarPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String descripcion = txtDescripcionSolicitud.getText().toString().trim();
                final double monto = Double.parseDouble(txtMontoSolicitado.getText().toString());
                if (!descripcion.isEmpty() && monto > 0) {

                    //DIÁLOGO para aceptar los términos y condiciones que implica el préstamo
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Términos y Condiciones");
                    builder.setMessage(getString(R.string.mensaje_aceptar_terminos_y_condiciones));
                    builder.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            builder1.setTitle("Mensaje Enviado");
                            builder1.setMessage("Su solicitud fue enviada con éxito");
                            builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //Llamo a mi Shared Preferences para traer el id del usuario en sesión
                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences(LoginActivity.PreferenciaUsuario, Context.MODE_PRIVATE);
                                    int idUsuario = sharedPreferences.getInt(LoginActivity.keyIdUsuario, 0);

                                    //Registra el préstamo como último paso
                                    Configuracion.querys.registrarPrestamo(idUsuario, monto, descripcion);
                                    txtDescripcionSolicitud.setText("");
                                    txtMontoSolicitado.setText("");
                                }
                            });
                            //no sirve crear el diálogo si no es mostrado, está obligado a mostrarlo con show();
                            builder1.create().show();
                        }
                    });
                    //seteando un botón negativo en el díalogo builder
                    builder.setNegativeButton("CANCELAR", null);

                    //no sirve crear el diálogo si no es mostrado, está obligado a mostrarlo con show();
                    builder.create().show();


                } else {
                    Toast.makeText(getContext(), "Debe Ingresar todos los valores", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
