package app.com.appprestamo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.appprestamo.R;
import app.com.appprestamo.aplication.Configuracion;

/**
 * ACTIVIDA REGISTRA en esta actividad se registran los usuarios validando que el correo
 * no se repita ya que es único (unique)
 */
public class RegistrarActivity extends AppCompatActivity {
    private EditText txtUserName, txtCorreo, txtPassword, txtApePat, txtApeMat, txtTel;
    private AppCompatButton btnRegistrarUsuario;
    private TextInputLayout tilCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtApePat = (EditText) findViewById(R.id.txtApePat);
        txtApeMat = (EditText) findViewById(R.id.txtApeMat);
        txtTel = (EditText) findViewById(R.id.txtTel);
        btnRegistrarUsuario = (AppCompatButton) findViewById(R.id.btnRegistrarUsuario);
        tilCorreo = (TextInputLayout) findViewById(R.id.tilCorreo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user = txtUserName.getText().toString().trim();
                final String apePat = txtApePat.getText().toString().trim();
                final String apeMat = txtApeMat.getText().toString().trim();
                final String password = txtPassword.getText().toString().trim();
                final String telefono = txtTel.getText().toString().trim();
                final String correo = txtCorreo.getText().toString().trim();

                if (user.isEmpty() || apePat.isEmpty() || apeMat.isEmpty() || password.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();

                } else if (Configuracion.querys.registrarUsuario(user, apePat, apeMat, password, telefono, correo)) {

                    //DIÁLOGO si es satisfactorio el registro de usuario
                    new AlertDialog.Builder(RegistrarActivity.this)
                            .setTitle("Mensaje")
                            .setMessage("Registro exitoso \n\n" + user)
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(RegistrarActivity.this, LoginActivity.class));
                                    finish();
                                }
                            })
                            .create()
                            .show();

                } else {

                    //DIÁLOGO si el correo ya está en uso
                    new AlertDialog.Builder(RegistrarActivity.this)
                            .setTitle("Error")
                            .setCancelable(false)
                            .setMessage("Está usando un correo existente, por favor cambie de correo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    tilCorreo.setError("Correo Existente!");
                                }
                            })
                            .create()
                            .show();
                }
            }
        });
    }
}
