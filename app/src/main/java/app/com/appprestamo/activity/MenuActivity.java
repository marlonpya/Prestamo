package app.com.appprestamo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import app.com.appprestamo.R;
import app.com.appprestamo.fragment.CobradoresFragment;
import app.com.appprestamo.fragment.PerfilFragment;
import app.com.appprestamo.fragment.PrestamosFragment;
import app.com.appprestamo.fragment.SolicitarPrestamoFragment;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_menu,new PrestamosFragment()).commit();
        getSupportActionBar().setTitle(R.string.item_prestamo);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Método para inflar un menú al toobar dentro de esta actividad(lugar del menú, menú de variable)
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Manejador del menú inflado para esta actividad(item del menú)
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método para manejar el NavigatorDrawer o Panel,
     * contiene una baraja de fragmmentos que interactuaran en la actividad
     * MenuActividad que sirve como contenedor
     * contiene el Texto de Título de fragmento para el toolbar y
     * el fragmento para ubicarlo en la actividad
     * @param item
     * @return el fragmento del item(id);
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.i_prestamo) {
            fragment = new PrestamosFragment();
            fragmentTransaction = true;

        } else if (id == R.id.i_perfil_prestamo) {
            fragment = new PerfilFragment();
            fragmentTransaction = true;

        } else if (id == R.id.i_cobro) {
            fragment = new CobradoresFragment();
            fragmentTransaction = true;

        }else if (id == R.id.i_pedido_prestamo) {
            fragment = new SolicitarPrestamoFragment();
            fragmentTransaction = true;

        }else if (id == R.id.i_contacto) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.bbvacontinental.pe/empresas/financiacion-y-cobertura-de-riesgo/financiacion/prestamos-comerciales/"));
            startActivity(intent);

        }else if (id == R.id.i_seguridad) {
            startActivity(new Intent(MenuActivity.this,LoginActivity.class));
            finish();
            SharedPreferences settings = MenuActivity.this.getSharedPreferences(LoginActivity.PreferenciaUsuario, Context.MODE_PRIVATE);
            settings.edit().clear().commit();

        }else{
            fragment = new PerfilFragment();
            fragmentTransaction = true;
        }

        if (fragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_menu,fragment).commit();
            item.isChecked();
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
