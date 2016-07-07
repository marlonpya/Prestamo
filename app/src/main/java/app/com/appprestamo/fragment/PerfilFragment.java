package app.com.appprestamo.fragment;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import app.com.appprestamo.R;
import app.com.appprestamo.activity.LoginActivity;
import app.com.appprestamo.activity.MenuActivity;
import app.com.appprestamo.aplication.Configuracion;
import app.com.appprestamo.model.Usuario;
import app.com.appprestamo.sqlite.ManageOpenHelper;
import app.com.appprestamo.util.Querys;

public class PerfilFragment extends Fragment {
    private static final String TAG = PerfilFragment.class.getSimpleName();
    private EditText txtNombreUsuario,txtApellidoPaternoUsuario,txtApellidoMaternoUsuario,txtPasswordUsuario,txtTelefonoUsuario,txtCorreoUsuario;
    private TextView lblIndiceNombre;
    private AppCompatButton btnActualizarPerfil;
    private String nombre,apellido_paterno,apellido_materno,password,telefono,correo;
    private Usuario usuario;
    private ManageOpenHelper conexion;
    private int idUsuarioGlobal;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        lblIndiceNombre = (TextView)view.findViewById(R.id.lblLetra);
        txtNombreUsuario = (EditText)view.findViewById(R.id.txtNombreUsuario);
        txtApellidoPaternoUsuario = (EditText)view.findViewById(R.id.txtApellidoPaternoUsuario);
        txtApellidoMaternoUsuario = (EditText)view.findViewById(R.id.txtApellidoMaternoUsuario);
        txtPasswordUsuario = (EditText)view.findViewById(R.id.txtPasswordUsuario);
        txtTelefonoUsuario = (EditText)view.findViewById(R.id.txtTelefonoUsuario);
        txtCorreoUsuario = (EditText)view.findViewById(R.id.txtCorreoUsuario);
        btnActualizarPerfil = (AppCompatButton)view.findViewById(R.id.btnActualizarPerfil);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PerfilFragment.this.getContext().getSharedPreferences(LoginActivity.PreferenciaUsuario, Context.MODE_PRIVATE);
        String thiscorreo = sharedPreferences.getString(LoginActivity.keyPreferenciaCorreo, "");

        usuario = Configuracion.querys.traerCuenta(thiscorreo);
        idUsuarioGlobal = usuario.getUsu_id();

        nombre = usuario.getUsu_nom().length()               > 0 ? usuario.getUsu_nom() : (String) getText(R.string.no_existen_datos);
        apellido_paterno = usuario.getUsu_ape_pat().length() > 0 ? usuario.getUsu_ape_pat() : (String) getText(R.string.no_existen_datos);
        apellido_materno = usuario.getUsu_ape_mat().length() > 0 ? usuario.getUsu_ape_mat() : (String) getText(R.string.no_existen_datos);
        password = usuario.getUsu_pas().length()             > 0 ? usuario.getUsu_pas() : (String) getText(R.string.no_existen_datos);
        telefono = usuario.getUsu_tel().length()             > 0 ? usuario.getUsu_tel() : (String) getText(R.string.no_existen_datos);
        correo = usuario.getUsu_cor().length()               > 0 ? usuario.getUsu_cor() : (String) getText(R.string.no_existen_datos);

        lblIndiceNombre.setText(nombre.substring(0,1).toUpperCase());
        txtNombreUsuario.setText(nombre);
        txtApellidoPaternoUsuario.setText(apellido_paterno);
        txtApellidoMaternoUsuario.setText(apellido_materno);
        txtPasswordUsuario.setText(password);
        txtTelefonoUsuario.setText(telefono);
        txtCorreoUsuario.setText(correo);

        btnActualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Título")
                        .setMessage("Se guardarán todos sus datos")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ContentValues values = new ContentValues();

                                values.put(Querys.USU_NOM,utilString(txtNombreUsuario));
                                values.put(Querys.USU_APE_PAT,utilString(txtApellidoPaternoUsuario));
                                values.put(Querys.USU_APE_MAT,utilString(txtApellidoMaternoUsuario));
                                values.put(Querys.USU_PAS,utilString(txtPasswordUsuario));
                                values.put(Querys.USU_TEL,utilString(txtTelefonoUsuario));
                                values.put(Querys.USU_COR,utilString(txtCorreoUsuario));

                                conexion = new ManageOpenHelper(getContext());

                                SQLiteDatabase db =  conexion.getWritableDatabase();

                                db.update(Querys.TB_USUARIO,values,Querys.USU_ID +"="+idUsuarioGlobal,null);
                                startActivity(new Intent(getActivity(), MenuActivity.class));
                            }
                        })
                        .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private String utilString(EditText texto){
        return  texto.getText().toString().trim();
    }

}
