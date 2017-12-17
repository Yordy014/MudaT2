package text.android.mudat2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import text.android.mudat2.Dao.AnuncioDbo;
import text.android.mudat2.Entity.clsAnuncio;
import text.android.mudat2.Registro.RegistroAnuncio;
import text.android.mudat2.view.Adapters.ListAdapter.AnuncioListAdapter;

public class VisualizarAnunciosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_anuncios);
        buscarDatos();
    }

    private void buscarDatos() {
        AnuncioDbo db = new AnuncioDbo(this);
        ListView list = findViewById(R.id.listviewDatos);

        AnuncioListAdapter adaptador = new AnuncioListAdapter(this, db.listar(clsAnuncio.IDUSUARIO + "=" + MainActivity.clsUsuarioActual.getId()));
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                clsAnuncio clsAnuncio = ((clsAnuncio) arg0.getItemAtPosition(position));
                Intent i = new Intent(VisualizarAnunciosActivity.this, RegistroAnuncio.class);

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

    public void crearAnuncioClick(View view) {
        startActivity(new Intent(this, RegistroAnuncio.class));
    }
}
