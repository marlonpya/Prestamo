package app.com.appprestamo.aplication;

import app.com.appprestamo.sqlite.QuerysSQL;

/**
 * Clase que se inicializa antes de cualquier actividad
 */
public class Configuracion extends android.app.Application {
    public static QuerysSQL querys;

    @Override
    public void onCreate() {
        super.onCreate();
        querys = new QuerysSQL(getApplicationContext());
    }
}
