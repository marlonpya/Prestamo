package app.com.appprestamo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.com.appprestamo.R;
import app.com.appprestamo.aplication.Configuracion;

/**
 * ACTIVIDAD DONDE EL USUARIO PUEDE INGRESAR CON UN CUENTA O SI NO,
 * DIRIGIRSE A REGISTRASE Y TAMBIÉN CAMBIAR SU PASSWORD CON EL BOTÓN QUE DIRECCIONA
 * LA SESIÓN ESTÁ HECHO CON SHARED PREFERENCES Y CUANDO PRESIONA SALIR,
 * LIMPIÓ EL SHARED PREFERENCES
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextInputLayout tilCorreo, tilPassword;
    private EditText txtCorreo, txtPassword;
    private AppCompatButton btnRegistrar, btnIngresar;
    public static final String PreferenciaUsuario = "PreferenciaUsuario";
    public static final String keyPreferenciaCorreo = "keyPreferenciaCorreo";
    public static final String keyPreferenciaPassword = "keyPreferenciaPassword";
    public static final String keyIdUsuario = "keyIdUsuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences(PreferenciaUsuario,Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString(keyPreferenciaCorreo,"");
        String password = sharedPreferences.getString(keyPreferenciaPassword,"");

        tilCorreo = (TextInputLayout) findViewById(R.id.tilCorreo);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegistrar = (AppCompatButton) findViewById(R.id.btnRegistrar);
        btnIngresar = (AppCompatButton) findViewById(R.id.btnIngresar);

        if (!correo.equals("")&&!password.equals("")){
            /*txtCorreo.setText(correo);
            txtPassword.setText(password);*/
            startActivity(new Intent(LoginActivity.this,MenuActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = txtCorreo.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (Configuracion.querys.validarUsuario(correo,password)) {

                    SharedPreferences sharedPreferences = getSharedPreferences(PreferenciaUsuario, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(keyPreferenciaCorreo,correo);
                    editor.putString(keyPreferenciaPassword,password);
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                    finish();

                } else {

                    tilCorreo.setError("Usuario Incorrecto");
                    tilPassword.setError("Password Incorrecto");
                    Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrarActivity.class));
            }
        });

    };

}
