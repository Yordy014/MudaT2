package text.android.mudat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import text.android.mudat2.Dao.AnuncioDbo;
import text.android.mudat2.Dao.UsuarioDbo;
import text.android.mudat2.Entity.clsAnuncio;
import text.android.mudat2.Entity.clsUsuario;
import text.android.mudat2.Entity.Utils;
import text.android.mudat2.view.Adapters.ListAdapter.AnuncioListAdapter;

public class MainActivity extends AppCompatActivity {

    public static clsUsuario clsUsuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0);
        buscarDatos();

        UsuarioDbo dbo = new UsuarioDbo(this);

        for (clsUsuario x : dbo.listar()) {
            Log.i("Usuarios Mudat", x.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(MainActivity.clsUsuarioActual != null){
            getMenuInflater().inflate(R.menu.menu_login, menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_logout, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cerrarSesion:

                Utils.guardarPreferencia(this,Utils.LOGIN_ID,null);
                Utils.guardarPreferencia(this,Utils.LOGIN_NAME,null);
                MainActivity.clsUsuarioActual = null;
                return true;
            case R.id.menu_iniciarSesion:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void buscarDatos() {
        AnuncioDbo db = new AnuncioDbo(this);
        ListView list = findViewById(R.id.listviewDatos);
        AnuncioListAdapter adaptador = new AnuncioListAdapter(this, db.listar(null));
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clsAnuncio clsAnuncio = ((clsAnuncio) arg0.getItemAtPosition(position));
                Intent i = new Intent(MainActivity.this, DetalleAnuncioActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable(clsAnuncio.class.getSimpleName(), clsAnuncio);

                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarDatos();
    }

    public static boolean validarEntry(EditText entry) {
        if (entry.getText().length() <= 0) {
            entry.setError("Valor obligatorio");
            return false;
        }

        return true;
    }

    public void crearAnuncioClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
